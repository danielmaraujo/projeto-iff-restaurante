package br.edu.iff.restaurante.controller;

import br.edu.iff.restaurante.model.Funcionario;
import br.edu.iff.restaurante.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping
    public ResponseEntity getAll(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                 @RequestParam(name = "size", defaultValue = "10", required = false) int size){
        return ResponseEntity.status(HttpStatus.OK).body(funcionarioService.findAll(page, size));
    }

    @GetMapping(path = "/{cpf}")
    public ResponseEntity getOne(@PathVariable("cpf") String cpf){
        return ResponseEntity.ok(funcionarioService.findById(cpf));
    }

    @PostMapping
    public ResponseEntity save(@Valid @RequestBody Funcionario funcionario){
        funcionarioService.save(funcionario);
        return ResponseEntity.status(HttpStatus.CREATED).body(funcionario);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody Funcionario funcionario){
        funcionarioService.update(funcionario, "", "", "");
        return ResponseEntity.status(HttpStatus.OK).body(funcionario);
    }

    @DeleteMapping(path = "/{cpf}")
    public ResponseEntity delete(@PathVariable("cpf") String cpf){
        funcionarioService.delete(cpf);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/{id}/senha")
    public ResponseEntity alterarSenha(@PathVariable("id") String cpf,
                                       @RequestParam(name = "senhaAtual", defaultValue = "", required = true) String senhaAtual,
                                       @RequestParam(name = "novaSenha", defaultValue = "", required = true) String novaSenha,
                                       @RequestParam(name = "confirmarNovaSenha", defaultValue = "", required = true) String confirmarNovaSenha){

        Funcionario f = funcionarioService.findById(cpf);
        funcionarioService.update(f, senhaAtual, novaSenha, confirmarNovaSenha);
        return ResponseEntity.ok().build();
    }
}
