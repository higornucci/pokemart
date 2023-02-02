package br.com.digix.pokemart.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    
}
