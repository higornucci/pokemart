package br.com.digix.pokemart.builders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.digix.pokemart.models.Ataque;
import br.com.digix.pokemart.models.Pokemon;
import br.com.digix.pokemart.models.Tipo;
import br.com.digix.pokemart.models.exceptions.AcuraciaInvalidaException;
import br.com.digix.pokemart.models.exceptions.AlturaInvalidaException;
import br.com.digix.pokemart.models.exceptions.FelicidadeInvalidaException;
import br.com.digix.pokemart.models.exceptions.ForcaInvalidaException;
import br.com.digix.pokemart.models.exceptions.NaoPossuiAtaqueException;
import br.com.digix.pokemart.models.exceptions.NivelInvalidoException;
import br.com.digix.pokemart.models.exceptions.PesoInvalidoException;
import br.com.digix.pokemart.models.exceptions.QuantidadeDeAtaquesInvalidaException;
import br.com.digix.pokemart.models.exceptions.TipoInvalidoException;

public class PokemonBuilder {

    private String nome = "Pikachu";
    private char genero = 'F';
    private float altura = 0.3f;
    private float peso = 2.1f;
    private int felicidade = 100;
    private int nivel = 5;
    private List<Ataque> ataques = new ArrayList<>();
    private List<Tipo> tipos = new ArrayList<>();

    public PokemonBuilder() throws AcuraciaInvalidaException, ForcaInvalidaException, IOException {
        this.ataques.add(new AtaqueBuilder().construir());
        this.tipos.add(new TipoBuilder().construir());
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
        this.ataques = new ArrayList<>();
        return this;
    }

    public Pokemon construir()
            throws FelicidadeInvalidaException, AlturaInvalidaException, PesoInvalidoException, NivelInvalidoException, NaoPossuiAtaqueException, QuantidadeDeAtaquesInvalidaException, TipoInvalidoException {
        return new Pokemon(nome, genero, altura, peso, felicidade, nivel, ataques, tipos);
    }

    public PokemonBuilder comAtaques(List<Ataque> ataques) {
        this.ataques = ataques;
        return this;
    }

    public PokemonBuilder semTipo() {
        this.tipos = new ArrayList<>();
        return this;
    }

    public PokemonBuilder comTipos(List<Tipo> tipos) {
        this.tipos = tipos;
        return this;
    }

    public PokemonBuilder comNome(String nome) {
        this.nome = nome;
        return this;
    }
}
