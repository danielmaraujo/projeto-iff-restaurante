package br.edu.iff.restaurante.controller.view;

import br.edu.iff.restaurante.model.Comanda;
import br.edu.iff.restaurante.model.FormaPagamentoEnum;
import br.edu.iff.restaurante.model.StatusComandaEnum;
import br.edu.iff.restaurante.service.ClienteService;
import br.edu.iff.restaurante.service.ComandaService;
import br.edu.iff.restaurante.service.FuncionarioService;
import br.edu.iff.restaurante.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/comandas")
public class ComandaViewController {
    @Autowired
    private ComandaService service;
    @Autowired
    private FuncionarioService funcionarioService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public String getAll(Model model){
        model.addAttribute("comandas", service.findAll());
        return "comandas";
    }

    @GetMapping(path = "/comanda")
    public String save(Model model){
        model.addAttribute("comanda", new Comanda());
        model.addAttribute("funcionarios", funcionarioService.findAll());
        model.addAttribute("clientes", clienteService.findAll());
        model.addAttribute("listaProdutos", produtoService.findAll());
        model.addAttribute("formasPagamento", FormaPagamentoEnum.values());
        model.addAttribute("tiposStatus", StatusComandaEnum.values());

        return "formComanda";
    }

    @PostMapping(path="/comanda")
    public String save(@Valid @ModelAttribute Comanda comanda, BindingResult result, Model model){
        model.addAttribute("funcionarios", funcionarioService.findAll());
        model.addAttribute("clientes", clienteService.findAll());
        model.addAttribute("listaProdutos", produtoService.findAll());
        model.addAttribute("formasPagamento", FormaPagamentoEnum.values());
        model.addAttribute("tiposStatus", StatusComandaEnum.values());

        if(result.hasErrors()){
            model.addAttribute("msgErros", result.getAllErrors());
            return "formComanda";
        }
        try {
            service.save(comanda);
            model.addAttribute("msgSucesso", "Comanda cadastrada com sucesso.");
            model.addAttribute("comanda", new Comanda());
            return "formComanda";
        } catch (Exception e) {
            model.addAttribute("msgErros", new ObjectError("Comanda", e.getMessage()));
            return "formComanda";
        }
    }

    @GetMapping(path="/comanda/{id}")
    public String alterar(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("comanda", service.findById(id));
        model.addAttribute("funcionarios", funcionarioService.findAll());
        model.addAttribute("clientes", clienteService.findAll());
        model.addAttribute("listaProdutos", produtoService.findAll());
        model.addAttribute("formasPagamento", FormaPagamentoEnum.values());
        model.addAttribute("tiposStatus", StatusComandaEnum.values());

        return "formComanda";
    }

    @PostMapping(path = "/comanda/{id}")
    public String update(@Valid @ModelAttribute Comanda comanda, BindingResult result, @PathVariable("id") Integer id, Model model) {
        model.addAttribute("funcionarios", funcionarioService.findAll());
        model.addAttribute("clientes", clienteService.findAll());
        model.addAttribute("listaProdutos", produtoService.findAll());
        model.addAttribute("formasPagamento", FormaPagamentoEnum.values());
        model.addAttribute("tiposStatus", StatusComandaEnum.values());

        if (result.hasErrors()) {
            model.addAttribute("msgErros", result.getAllErrors());
            return "formComanda";
        }
        comanda.setId(id);
        try {
            service.update(comanda);
            model.addAttribute("msgSucesso", "Comanda atualizada com sucesso.");
            model.addAttribute("comanda", comanda);
            return "formComanda";
        } catch (Exception e) {
            model.addAttribute("msgErros", new ObjectError("Comanda", e.getMessage()));
            return "formComanda";
        }
    }

    @GetMapping(path = "/{id}/deletar")
    public String deletar(@PathVariable("id") Integer id) {
        service.delete(id);
        return "redirect:/comandas";
    }
}
