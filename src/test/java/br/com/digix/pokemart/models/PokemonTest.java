package br.com.digix.pokemart.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.com.digix.pokemart.builders.AtaqueBuilder;
import br.com.digix.pokemart.builders.PokemonBuilder;
import br.com.digix.pokemart.builders.TipoBuilder;
import br.com.digix.pokemart.models.exceptions.AlturaInvalidaException;
import br.com.digix.pokemart.models.exceptions.FelicidadeInvalidaException;
import br.com.digix.pokemart.models.exceptions.NaoPossuiAtaqueException;
import br.com.digix.pokemart.models.exceptions.NivelInvalidoException;
import br.com.digix.pokemart.models.exceptions.PesoInvalidoException;
import br.com.digix.pokemart.models.exceptions.QuantidadeDeAtaquesInvalidaException;
import br.com.digix.pokemart.models.exceptions.TipoInvalidoException;

public class PokemonTest {

    @Test
    public void deve_criar_um_pokemon() throws Exception {
        // Arrange
        String nome = "Pikachu";
        char genero = 'F';
        float altura = 0.3f;
        float peso = 2.1f;
        int felicidade = 100;
        int nivel = 5;

        // Action
        Pokemon pokemon = new PokemonBuilder().construir();

        // Assertion
        Assertions.assertEquals(nome, pokemon.getNome());
        Assertions.assertEquals(genero, pokemon.getGenero());
        Assertions.assertEquals(altura, pokemon.getAltura());
        Assertions.assertEquals(peso, pokemon.getPeso());
        Assertions.assertEquals(felicidade, pokemon.getFelicidade());
        Assertions.assertEquals(nivel, pokemon.getNivel());
    } 

    @Test
    public void nao_deve_ter_felicidade_menor_que_zero() {
        // Arrange, Action & Assert
        Assertions.assertThrows(FelicidadeInvalidaException.class, 
        ()->{ 
            new PokemonBuilder().comFelicidade(-1).construir();
        });
    }

    @Test
    public void nao_deve_ter_felicidade_acima_de_cem() {
        // Arrange, Action & Assert
        Assertions.assertThrows(FelicidadeInvalidaException.class, 
        ()->{ 
            new PokemonBuilder().comFelicidade(101).construir();
        });
    }

    @Test
    public void nao_deve_ter_altura_menor_que_zero() {
        Assertions.assertThrows(AlturaInvalidaException.class, 
        ()-> {
            new PokemonBuilder().comAltura(-0.1f).construir();
        });
    }

    @Test
    public void nao_deve_ter_peso_menor_que_zero() {
        Assertions.assertThrows(PesoInvalidoException.class, 
        ()-> {
            new PokemonBuilder().comPeso(-0.1f).construir();
        });
    }

    @Test
    public void nao_deve_ter_nivel_menor_que_um() {
        Assertions.assertThrows(NivelInvalidoException.class, 
        ()-> {
            new PokemonBuilder().comNivel(0).construir();
        });
    }

    @Test
    public void nao_deve_ter_nivel_maior_que_cem() {
        Assertions.assertThrows(NivelInvalidoException.class, 
        ()-> {
            new PokemonBuilder().comNivel(101).construir();
        });
    }

    @Test
    public void deve_ter_pelo_menos_um_ataque() throws Exception {
        // Action
        Assertions.assertThrows(NaoPossuiAtaqueException.class,
        ()-> {
            new PokemonBuilder().semAtaque().construir();
        });
    }

    @Test
    public void deve_poder_ter_quatro_ataques() throws Exception {
        // Arrange
        Ataque ataque1 = new AtaqueBuilder().construir();
        Ataque ataque2 = new AtaqueBuilder().construir();
        Ataque ataque3 = new AtaqueBuilder().construir();
        Ataque ataque4 = new AtaqueBuilder().construir();
        List<Ataque> ataques = new ArrayList<>();
        ataques.add(ataque1);
        ataques.add(ataque2);
        ataques.add(ataque3);
        ataques.add(ataque4);

        // Action
        Pokemon pokemon = new PokemonBuilder().comAtaques(ataques).construir();

        // Assertion
        Assertions.assertTrue(ataques.containsAll(pokemon.getAtaques()));
    }

    @Test
    public void nao_deve_possuir_mais_que_quatro_ataques() throws Exception {
        // Arrange
        List<Ataque> ataques = new ArrayList<>();
        ataques.add(new AtaqueBuilder().construir());
        ataques.add(new AtaqueBuilder().construir());
        ataques.add(new AtaqueBuilder().construir());
        ataques.add(new AtaqueBuilder().construir());
        ataques.add(new AtaqueBuilder().construir());

        // Action & Assert
        Assertions.assertThrows(QuantidadeDeAtaquesInvalidaException.class,
        ()-> {
            new PokemonBuilder().comAtaques(ataques).construir();
        });
    }

    @Test
    public void deve_ter_um_tipo() throws Exception {
        // Arrange
        Tipo tipo = new TipoBuilder().construir();

        // Action
        Pokemon pokemon = new PokemonBuilder().comTipos(Collections.singletonList(tipo)).construir();

        // Assert
        Assertions.assertTrue(pokemon.getTipos().contains(tipo));;
    }

    @Test
    public void deve_ter_pelo_menos_um_tipo() throws Exception {
        Assertions.assertThrows(TipoInvalidoException.class,
            () -> new PokemonBuilder().semTipo().construir()
        );
    }

    @Test
    public void nao_deve_ter_mais_que_dois_tipos() throws Exception {
        List<Tipo> tipos = new ArrayList<>();
        tipos.add(new TipoBuilder().construir());
        tipos.add(new TipoBuilder().construir());
        tipos.add(new TipoBuilder().construir());

        Assertions.assertThrows(TipoInvalidoException.class,
            () -> new PokemonBuilder().comTipos(tipos).construir()
        );
    }
}