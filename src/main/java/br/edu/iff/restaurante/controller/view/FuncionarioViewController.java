package br.edu.iff.restaurante.controller.view;


import br.edu.iff.restaurante.model.Funcionario;
import br.edu.iff.restaurante.repository.PermissaoRepository;
import br.edu.iff.restaurante.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/funcionarios")
public class FuncionarioViewController {
    @Autowired
    private FuncionarioService service;
    @Autowired
    private PermissaoRepository permissaoRepository;

    @GetMapping
    public String getAll(Model model){
        model.addAttribute("funcionarios", service.findAll());
        return "funcionarios";
    }

    @GetMapping(path = "/funcionario")
    public String save(Model model){
        model.addAttribute("funcionario", new Funcionario());
        model.addAttribute("permissoes", permissaoRepository.findAll());
        return "formFuncionario";
    }

    @PostMapping(path = "/funcionario")
    public String save(@Valid @ModelAttribute Funcionario funcionario, BindingResult result, @RequestParam("confirmarSenha") String confirmarSenha, Model model) {
        model.addAttribute("permissoes", permissaoRepository.findAll());

        if (result.hasErrors()) {
            model.addAttribute("msgErros", result.getAllErrors());
            return "formFuncionario";
        }
        if (!funcionario.getSenha().equals(confirmarSenha)){
            model.addAttribute("msgErros", new ObjectError("funcionario", "Campos Senha e Confirmar Senha são diferentes"));
            return "formFuncionario";
        }

        try {
            service.save(funcionario);
            model.addAttribute("msgSucesso", "Funcionário cadastrado com sucesso.");
            model.addAttribute("funcionario", new Funcionario());
            return "formFuncionario";
        } catch (Exception e) {
            model.addAttribute("msgErros", new ObjectError("Funcionario", e.getMessage()));
            return "formFuncionario";
        }
    }

    @GetMapping(path = "/funcionario/{cpf}")
    public String alterar(@PathVariable("cpf") String cpf, Model model) {
        model.addAttribute("funcionario", service.findById(cpf));
        model.addAttribute("permissoes", permissaoRepository.findAll());
        return "formFuncionario";
    }

    @PostMapping(path = "/funcionario/{cpf}")
    public String update(@Valid @ModelAttribute Funcionario funcionario, BindingResult result, @PathVariable("cpf") String cpf, Model model) {
        model.addAttribute("permissoes", permissaoRepository.findAll());

        List<FieldError> list = new ArrayList<>();
        for(FieldError fe : result.getFieldErrors()){
            if(!fe.getField().equals("senha")){
                list.add(fe);
            }
        }
        if (!list.isEmpty()) {
            model.addAttribute("msgErros", list);
            return "formFuncionario";
        }

        funcionario.setCpf(cpf);
        funcionario.setSenha(service.findById(cpf).getSenha());
        try {
            service.update(funcionario, "", "", "");
            model.addAttribute("msgSucesso", "Funcionário atualizado com sucesso.");
            model.addAttribute("funcionario", funcionario);
            return "formFuncionario";
        } catch (Exception e) {
            model.addAttribute("msgErros", new ObjectError("Funcionario", e.getMessage()));
            return "formFuncionario";
        }
    }

    @GetMapping(path = "/{cpf}/deletar")
    public String deletar(@PathVariable("cpf") String cpf) {
        service.delete(cpf);
        return "redirect:/funcionarios";
    }

    @GetMapping(path = "/meusdados")
    public String getMeusDados(@AuthenticationPrincipal User user, Model model){
        Funcionario funcionario = service.findById(user.getUsername());
        model.addAttribute("funcionario", funcionario);
        return "formMeusDados";
    }

    @PostMapping(path = "/meusdados")
    public String updateMeusDados(
            @Valid @ModelAttribute Funcionario funcionario,
            BindingResult result,
            @AuthenticationPrincipal User user,
            @RequestParam("senhaAtual") String senhaAtual,
            @RequestParam("novaSenha") String novaSenha,
            @RequestParam("confirmarNovaSenha") String confirmarNovaSenha,
            Model model) {

        List<FieldError> list = new ArrayList<>();
        for(FieldError fe : result.getFieldErrors()){
            if(!fe.getField().equals("senha") && !fe.getField().equals("permissoes") ){
                list.add(fe);
            }
        }
        if (!list.isEmpty()) {
            model.addAttribute("msgErros", list);
            return "formMeusDados";
        }

        Funcionario funcionarioBD = service.findById(user.getUsername());
        if(!funcionarioBD.getCpf().equals(funcionario.getCpf())){
            throw new RuntimeException("Acesso negado.");
        }
        try {
            funcionario.setPermissoes(funcionarioBD.getPermissoes());
            service.update(funcionario, senhaAtual, novaSenha, confirmarNovaSenha);
            model.addAttribute("msgSucesso", "Funcionário atualizado com sucesso.");
            model.addAttribute("funcionario", funcionario);
            return "formMeusDados";
        } catch (Exception e) {
            model.addAttribute("msgErros", new ObjectError("funcionario", e.getMessage()));
            return "formMeusDados";
        }
    }
}
