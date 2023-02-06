package br.com.digix.pokemart.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.digix.pokemart.models.Tipo;
import br.com.digix.pokemart.repository.TipoRepository;

@RestController
@RequestMapping(path = "/tipos")
public class TipoController {

    @Autowired
    private TipoRepository tipoRepository;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Tipo>> bucarTodos() {
        Iterable<Tipo> iterable = tipoRepository.findAll();
        List<Tipo> tipos = new ArrayList<>();
        iterable.forEach(tipos::add);
        return ResponseEntity.ok().body(tipos);
    }

    @DeleteMapping(path = "/{id}")
    public void remover(@PathVariable Long id) {
        tipoRepository.deleteById(id);
    }
    
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Tipo> cadastrar(@RequestBody Tipo tipo) {
        Tipo tipoCadastrado = tipoRepository.save(tipo);
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoCadastrado);
    }
}
