package br.com.digix.pokemart.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.digix.pokemart.models.Pokemon;
import br.com.digix.pokemart.repository.PokemonRepository;

@RestController
@RequestMapping(path = "/pokemons")
public class PokemonController {
    @Autowired
    private PokemonRepository pokemonRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pokemon>> bucarTodos() {
        Iterable<Pokemon> iterable = pokemonRepository.findAll();
        List<Pokemon> pokemons = new ArrayList<>();
        iterable.forEach(pokemons::add);
        return ResponseEntity.ok().body(pokemons);
    }
    @DeleteMapping(path = "/{id}")
    public void remover(@PathVariable Long id) {
        pokemonRepository.deleteById(id);
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces =MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pokemon> cadastrar(@RequestBody Pokemon pokemon) {
      Pokemon  pokemonCadastrado = pokemonRepository.save(pokemon);
        return ResponseEntity.status(HttpStatus.CREATED).body(pokemonCadastrado);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces =MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pokemon> alterar(@RequestBody Pokemon pokemon) {
        Pokemon pokemonAlterado = pokemonRepository.save(pokemon);
        return ResponseEntity.status(HttpStatus.CREATED).body(pokemonAlterado);
}
}