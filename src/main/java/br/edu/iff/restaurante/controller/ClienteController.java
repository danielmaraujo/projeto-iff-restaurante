package br.edu.iff.restaurante.controller;

import br.edu.iff.restaurante.model.Cliente;
import br.edu.iff.restaurante.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity getAll(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                 @RequestParam(name = "size", defaultValue = "10", required = false) int size){
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.findAll(page, size));
    }

    @GetMapping(path = "/{cpf}")
    public ResponseEntity getOne(@PathVariable("cpf") String cpf){
        return ResponseEntity.ok(clienteService.findById(cpf));
    }

    @PostMapping
    public ResponseEntity save(@Valid @RequestBody Cliente cliente){
        clienteService.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }

    @PutMapping
    public ResponseEntity update(@Valid @RequestBody Cliente cliente){
        clienteService.update(cliente);
        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }

    @DeleteMapping(path = "/{cpf}")
    public ResponseEntity delete(@PathVariable("cpf") String cpf){
        clienteService.delete(cpf);
        return ResponseEntity.ok().build();
    }
}
