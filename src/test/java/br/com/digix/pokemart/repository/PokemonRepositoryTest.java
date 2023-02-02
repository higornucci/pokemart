package br.com.digix.pokemart.repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.digix.pokemart.builders.PokemonBuilder;
import br.com.digix.pokemart.builders.TipoBuilder;
import br.com.digix.pokemart.models.Pokemon;
import br.com.digix.pokemart.models.Tipo;

@DataJpaTest
public class PokemonRepositoryTest {

    @Autowired
    private PokemonRepository pokemonRepository;

    @Test
    public void deve_salvar_um_pokemon() throws Exception {
        Pokemon pokemon = new PokemonBuilder().construir();

        pokemonRepository.save(pokemon);

        Assertions.assertNotNull(pokemon.getId());
    }

    @Test
    public void deve_poder_remover() throws Exception {
        Pokemon pokemon = new PokemonBuilder().construir();
        pokemonRepository.save(pokemon);

        pokemonRepository.delete(pokemon);

        Optional<Pokemon> pokemonRetornado = pokemonRepository.findById(pokemon.getId());
        Assertions.assertFalse(pokemonRetornado.isPresent());       
    }

    @Test
    public void deve_buscar_pelo_nome() throws Exception {
        String nome = "Charmander";
        Pokemon charmander = new PokemonBuilder().comNome(nome).construir();
        pokemonRepository.save(charmander);

        List<Pokemon> pokemons = pokemonRepository.findByNomeContainingIgnoreCase(nome);

        Assertions.assertTrue(pokemons.contains(charmander));
    }

    @Test
    public void deve_buscar_pelo_tipo() throws Exception {
        List<Tipo> tipoGrass = Arrays.asList(new TipoBuilder().comNome("Grass").construir());
        Pokemon bulbassauro = new PokemonBuilder().comNome("Bulbassauro").comTipos(tipoGrass).construir();
        
        Pokemon pikachu = new PokemonBuilder().construir();

        pokemonRepository.saveAll(Arrays.asList(bulbassauro, pikachu));

        List<Pokemon> pokemons = pokemonRepository.findByTiposIn(tipoGrass);

        Assertions.assertTrue(pokemons.contains(bulbassauro));
        Assertions.assertFalse(pokemons.contains(pikachu));
        Assertions.assertTrue(pokemons.stream().allMatch(pokemon -> pokemon.getTipos().containsAll(tipoGrass)));
    }
    
}
