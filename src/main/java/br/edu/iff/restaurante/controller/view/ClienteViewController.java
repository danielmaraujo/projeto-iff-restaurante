package br.edu.iff.restaurante.controller.view;

import br.edu.iff.restaurante.model.Cliente;
import br.edu.iff.restaurante.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/clientes")
public class ClienteViewController {
    @Autowired
    private ClienteService service;

    @GetMapping
    public String getAll(Model model){
        model.addAttribute("clientes", service.findAll());
        return "clientes";
    }

    @GetMapping(path = "/cliente")
    public String save(Model model){
        model.addAttribute("cliente", new Cliente());
        return "formCliente";
    }

    @PostMapping(path = "/cliente")
    public String save(@Valid @ModelAttribute Cliente cliente, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("msgErros", result.getAllErrors());
            return "formCliente";
        }
        try {
            service.save(cliente);
            model.addAttribute("msgSucesso", "Cliente cadastrado com sucesso.");
            model.addAttribute("cliente", new Cliente());
            return "formCliente";
        } catch (Exception e) {
            model.addAttribute("msgErros", new ObjectError("Cliente", e.getMessage()));
            return "formCliente";
        }
    }

    @GetMapping(path = "/cliente/{cpf}")
    public String alterar(@PathVariable("cpf") String cpf, Model model) {
        model.addAttribute("cliente", service.findById(cpf));
        return "formCliente";
    }

    @PostMapping(path = "/cliente/{cpf}")
    public String update(@Valid @ModelAttribute Cliente cliente, BindingResult result, @PathVariable("cpf") String cpf, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("msgErros", result.getAllErrors());
            return "formCliente";
        }
        cliente.setCpf(cpf);
        try {
            service.update(cliente);
            model.addAttribute("msgSucesso", "Cliente atualizado com sucesso.");
            model.addAttribute("cliente", cliente);
            return "formCliente";
        } catch (Exception e) {
            model.addAttribute("msgErros", new ObjectError("Cliente", e.getMessage()));
            return "formCliente";
        }
    }

    @GetMapping(path = "/{cpf}/deletar")
    public String deletar(@PathVariable("cpf") String cpf) {
        service.delete(cpf);
        return "redirect:/clientes";
    }
}
