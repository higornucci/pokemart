package br.com.digix.pokemart.builders;

import java.io.IOException;

import br.com.digix.pokemart.models.Ataque;
import br.com.digix.pokemart.models.Tipo;
import br.com.digix.pokemart.models.enums.Categoria;
import br.com.digix.pokemart.models.exceptions.AcuraciaInvalidaException;
import br.com.digix.pokemart.models.exceptions.ForcaInvalidaException;

public class AtaqueBuilder {
    private int forca = 40;
    private Categoria categoria = Categoria.FISICO;
    private int acuracia = 100;
    private String nome = "Chamas da paixao";
    private String descricao = "Deixa o inimigo apaixonado";
    private int pontosDePoder = 15;
    private Tipo tipo;

    public AtaqueBuilder() throws IOException {
        this.tipo = new TipoBuilder().construir();
    }

    public Ataque construir() throws AcuraciaInvalidaException, ForcaInvalidaException {
        return new Ataque(forca, categoria, acuracia, nome, descricao, pontosDePoder, tipo);
    }

    public AtaqueBuilder comAcuracia(int acuracia) {
        this.acuracia = acuracia;
        return this;
    }

    public AtaqueBuilder comForca(int forca) {
        this.forca = forca;
        return this;
    }

    public AtaqueBuilder comTipo(Tipo tipo) {
        this.tipo = tipo;
        return this;
    }

}
