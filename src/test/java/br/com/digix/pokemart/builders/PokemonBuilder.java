package br.com.digix.pokemart.builders;

import br.com.digix.pokemart.models.Ataque;
import br.com.digix.pokemart.models.Pokemon;
import br.com.digix.pokemart.models.exceptions.AcuraciaInvalidaException;
import br.com.digix.pokemart.models.exceptions.AlturaInvalidaException;
import br.com.digix.pokemart.models.exceptions.FelicidadeInvalidaException;
import br.com.digix.pokemart.models.exceptions.ForcaInvalidaException;
import br.com.digix.pokemart.models.exceptions.NaoPossuiAtaqueException;
import br.com.digix.pokemart.models.exceptions.NivelInvalidoException;
import br.com.digix.pokemart.models.exceptions.PesoInvalidoException;

public class PokemonBuilder {

    private String nome = "Pikachu";
    private char genero = 'F';
    private float altura = 0.3f;
    private float peso = 2.1f;
    private int felicidade = 100;
    private int nivel = 5;
    private Ataque ataque;

    public PokemonBuilder() throws AcuraciaInvalidaException, ForcaInvalidaException {
        this.ataque = new AtaqueBuilder().construir();
    }

    public PokemonBuilder comFelicidade(int felicidade) {
        this.felicidade = felicidade;
        return this;
    }

    public PokemonBuilder comAltura(float altura) {
        this.altura = altura;
        return this;
    }

    public PokemonBuilder comPeso(float peso) {
        this.peso = peso;
        return this;
    }

    public PokemonBuilder comNivel(int nivel) {
        this.nivel = nivel;
        return this;
    }

    public PokemonBuilder semAtaque() {
        this.ataque = null;
        return this;
    }

    public Pokemon construir()
            throws FelicidadeInvalidaException, AlturaInvalidaException, PesoInvalidoException, NivelInvalidoException, NaoPossuiAtaqueException {
        return new Pokemon(nome, genero, altura, peso, felicidade, nivel, ataque);
    }
}
