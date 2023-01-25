package br.com.digix.pokemart.models;

import br.com.digix.pokemart.models.enums.Categoria;
import br.com.digix.pokemart.models.exceptions.AcuraciaInvalidaException;
import br.com.digix.pokemart.models.exceptions.ForcaInvalidaException;
import lombok.Getter;

@Getter
public class Ataque {

    private int forca;
    private Categoria categoria;
    private int acuracia;
    private String nome;
    private String descricao;
    private int pontosDePoder;
    private Tipo tipo;

    public Ataque(int forca, Categoria categoria, int acuracia, String nome, String descricao, int pontosDePoder, Tipo tipo) throws AcuraciaInvalidaException, ForcaInvalidaException {
        verificarAcuraciaEntreZeroeCem(acuracia);
        verificarForcaMenorQueZero(forca);
        this.forca = forca;
        this.categoria = categoria;
        this.acuracia = acuracia;
        this.nome = nome;
        this.descricao = descricao;
        this.pontosDePoder = pontosDePoder;
        this.tipo = tipo;
    }

    private void verificarForcaMenorQueZero(int forca) throws ForcaInvalidaException {
        if(forca < 0) {
            throw new ForcaInvalidaException();
        }
    }

    private void verificarAcuraciaEntreZeroeCem(int acuracia) throws AcuraciaInvalidaException {
        if(acuracia < 0 || acuracia > 100) {
            throw new AcuraciaInvalidaException();
        }
    }
    
}
