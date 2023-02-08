package br.com.digix.pokemart.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.digix.pokemart.models.exceptions.AlturaInvalidaException;
import br.com.digix.pokemart.models.exceptions.FelicidadeInvalidaException;
import br.com.digix.pokemart.models.exceptions.NaoPossuiAtaqueException;
import br.com.digix.pokemart.models.exceptions.NivelInvalidoException;
import br.com.digix.pokemart.models.exceptions.PesoInvalidoException;
import br.com.digix.pokemart.models.exceptions.QuantidadeDeAtaquesInvalidaException;
import br.com.digix.pokemart.models.exceptions.TipoInvalidoException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@JsonCreator))
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private char genero;
    private float altura;
    private float peso;
    private int felicidade;
    private int nivel;
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Ataque> ataques;
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Tipo> tipos;

    public Pokemon(String nome, char genero, float altura, float peso, int felicidade, int nivel, List<Ataque> ataques, List<Tipo> tipos) throws FelicidadeInvalidaException, AlturaInvalidaException, PesoInvalidoException, NivelInvalidoException, NaoPossuiAtaqueException, QuantidadeDeAtaquesInvalidaException, TipoInvalidoException {
        verificarFelicidadeEntreZeroECem(felicidade);
        verificarAlturaMenorQueZero(altura);
        verificarPesoMenorQueZero(peso);
        verificarNivelEntreZeroEUm(nivel);
        verificarSeNaoPossuiAtaques(ataques);
        verificarSePossuiMaisQueQuatroAtaques(ataques);
        verificarSePossuiUmOuDoisTipos(tipos);
        this.tipos = tipos;
        this.ataques = ataques;
        this.nome = nome;
        this.genero = genero;
        this.altura = altura;
        this.peso = peso;
        this.felicidade = felicidade;
        this.nivel = nivel;
    }

    private void verificarSePossuiUmOuDoisTipos(List<Tipo> tipos) throws TipoInvalidoException {
        int quantidadeDeTipos = tipos.size();
        if(quantidadeDeTipos < 1 || quantidadeDeTipos > 2) {
            throw new TipoInvalidoException();
        }
    }

    private void verificarSePossuiMaisQueQuatroAtaques(List<Ataque> ataques) throws QuantidadeDeAtaquesInvalidaException {
        if(ataques.size() > 4) {
            throw new QuantidadeDeAtaquesInvalidaException();
        }
    }

    private void verificarSeNaoPossuiAtaques(List<Ataque> ataques) throws NaoPossuiAtaqueException {
        if(ataques.size() == 0) {
            throw new NaoPossuiAtaqueException();
        }
    }

    private void verificarNivelEntreZeroEUm(int nivel) throws NivelInvalidoException {
        if(nivel < 1 || nivel > 100) {
            throw new NivelInvalidoException();
        }
    }

    private void verificarPesoMenorQueZero(float peso) throws PesoInvalidoException {
        if(peso < 0) {
            throw new PesoInvalidoException();
        }
    }

    private void verificarAlturaMenorQueZero(float altura) throws AlturaInvalidaException {
        if(altura < 0) {
            throw new AlturaInvalidaException();
        }
    }

    private void verificarFelicidadeEntreZeroECem(int felicidade) throws FelicidadeInvalidaException {
        if(felicidade < 0 || felicidade > 100) {
            throw new FelicidadeInvalidaException();
        }
    }

    public void alterar(String nome) {
        this.nome = nome;
    }
    
}
