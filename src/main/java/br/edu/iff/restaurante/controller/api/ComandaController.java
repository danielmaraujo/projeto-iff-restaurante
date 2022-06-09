package br.edu.iff.restaurante.controller.api;

import br.edu.iff.restaurante.model.Comanda;
import br.edu.iff.restaurante.service.ComandaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/comandas")
public class ComandaController {
    @Autowired
    private ComandaService comandaService;

    @GetMapping
    public ResponseEntity getAll(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
                  @RequestParam(name = "size", defaultValue = "10", required = false) int size){
        return ResponseEntity.ok(comandaService.findAll(page, size));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getOne(@PathVariable(name = "id") Integer id){
        return ResponseEntity.ok(comandaService.findById(id));
    }

    @PostMapping
    public ResponseEntity save(@Valid @RequestBody Comanda comanda){
        comandaService.save(comanda);
        return ResponseEntity.status(HttpStatus.CREATED).body(comanda);
    }

    @PutMapping
    public ResponseEntity update(@Valid @RequestBody Comanda comanda){
        comandaService.update(comanda);
        return ResponseEntity.status(HttpStatus.OK).body(comanda);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id){
        comandaService.delete(id);
        return ResponseEntity.ok().build();
    }
}
