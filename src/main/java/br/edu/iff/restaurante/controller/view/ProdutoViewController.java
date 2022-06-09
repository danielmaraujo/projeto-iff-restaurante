package br.edu.iff.restaurante.controller.view;

import br.edu.iff.restaurante.model.Produto;
import br.edu.iff.restaurante.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/produtos")
public class ProdutoViewController {
    @Autowired
    private ProdutoService service;

    @GetMapping
    public String getAll(Model model){
        model.addAttribute("produtos", service.findAll());
        return "produtos";
    }

    @GetMapping(path = "/produto")
    public String save(Model model){
        model.addAttribute("produto", new Produto());
        return "formProduto";
    }

    @PostMapping(path="/produto")
    public String save(@Valid @ModelAttribute Produto produto, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("msgErros", result.getAllErrors());
            return "formProduto";
        }
        try {
            service.save(produto);
            model.addAttribute("msgSucesso", "Produto cadastrado com sucesso.");
            model.addAttribute("produto", new Produto());
            return "formProduto";
        } catch (Exception e) {
            model.addAttribute("msgErros", new ObjectError("Produto", e.getMessage()));
            return "formProduto";
        }
    }

    @GetMapping(path="/produto/{id}")
    public String alterar(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("produto", service.findById(id));
        return "formProduto";
    }

    @PostMapping(path = "/produto/{id}")
    public String update(@Valid @ModelAttribute Produto produto, BindingResult result, @PathVariable("id") Integer id, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("msgErros", result.getAllErrors());
            return "formProduto";
        }
        produto.setId(id);
        try {
            service.update(produto);
            model.addAttribute("msgSucesso", "Produto atualizado com sucesso.");
            model.addAttribute("produto", produto);
            return "formProduto";
        } catch (Exception e) {
            model.addAttribute("msgErros", new ObjectError("Produto", e.getMessage()));
            return "formProduto";
        }
    }

    @GetMapping(path = "/{id}/deletar")
    public String deletar(@PathVariable("id") Integer id) {
        service.delete(id);
        return "redirect:/produtos";
    }
}
