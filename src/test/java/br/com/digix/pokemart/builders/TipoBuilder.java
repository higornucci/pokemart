package br.com.digix.pokemart.builders;

import java.io.IOException;

import br.com.digix.pokemart.models.Tipo;
import br.com.digix.pokemart.utils.ArquivoUtils;

public class TipoBuilder {

    private String nome = "Water";
    private String cor = "#4592c4";
    private byte[] simbolo;

    public TipoBuilder() throws IOException {
        this.simbolo = ArquivoUtils.abrirArquivo("src\\test\\java\\br\\com\\digix\\pokemart\\icons\\Pokemon_Type_Icon_Water.svg");
    }

    public Tipo construir() {
        return new Tipo(nome, cor, simbolo);
    }

    public TipoBuilder comNome(String nome) {
        this.nome = nome;
        return this;
    }

}
