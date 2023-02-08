package br.com.digix.pokemart.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.digix.pokemart.models.Ataque;
import br.com.digix.pokemart.repository.AtaqueRepository;

@RestController
@RequestMapping(path = "/ataques")
public class AtaqueController {
    @Autowired
    private AtaqueRepository ataqueRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Ataque>> bucarTodos() {
        Iterable<Ataque> iterale = ataqueRepository.findAll();
        List<Ataque> ataques= new ArrayList<>();
        iterale.forEach(ataques::add);
        return ResponseEntity.ok().body(ataques);
    }
    @DeleteMapping(path = "/{id}")
    public void remover(@PathVariable Long id) {
        ataqueRepository.deleteById(id);
    }
}