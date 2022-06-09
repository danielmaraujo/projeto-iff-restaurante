package br.edu.iff.restaurante.controller.api;

import br.edu.iff.restaurante.model.Produto;
import br.edu.iff.restaurante.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity getAll(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                 @RequestParam(name = "size", defaultValue = "10", required = false) int size){
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.findAll(page, size));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getOne(@PathVariable("id") Integer id){
        return ResponseEntity.ok(produtoService.findById(id));
    }

    @PostMapping
    public ResponseEntity save(@Valid @RequestBody Produto produto){
        produtoService.save(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }

    @PutMapping
    public ResponseEntity update(@Valid @RequestBody Produto produto){
        produtoService.update(produto);
        return ResponseEntity.status(HttpStatus.OK).body(produto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id){
        produtoService.delete(id);
        return ResponseEntity.ok().build();
    }
}
