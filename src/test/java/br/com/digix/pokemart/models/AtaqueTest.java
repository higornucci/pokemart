package br.com.digix.pokemart.models;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.com.digix.pokemart.builders.AtaqueBuilder;
import br.com.digix.pokemart.builders.TipoBuilder;
import br.com.digix.pokemart.models.enums.Categoria;
import br.com.digix.pokemart.models.exceptions.AcuraciaInvalidaException;
import br.com.digix.pokemart.models.exceptions.ForcaInvalidaException;

public class AtaqueTest {
    @Test
    public void deve_criar_um_ataque() throws Exception {
        // Arrange
        int forca = 40;
        Categoria categoria = Categoria.FISICO;
        int acuracia = 100;
        String nome = "Chamas da paixao";
        String descricao = "Deixa o inimigo apaixonado";
        int pontosDePoder = 15;

        // Action
        Ataque ataque = new AtaqueBuilder().construir();

        // Assertion
        Assertions.assertEquals(forca, ataque.getForca());
        Assertions.assertEquals(categoria, ataque.getCategoria());
        Assertions.assertEquals(acuracia, ataque.getAcuracia());
        Assertions.assertEquals(nome, ataque.getNome());
        Assertions.assertEquals(descricao, ataque.getDescricao());
        Assertions.assertEquals(pontosDePoder, ataque.getPontosDePoder());
    }

    @Test
    public void nao_deve_ter_acuracia_menor_que_zero() {
        Assertions.assertThrows(AcuraciaInvalidaException.class,
        ()-> {
            new AtaqueBuilder().comAcuracia(-1).construir();
        });
    }

    @Test
    public void nao_deve_ter_acuracia_maior_que_cem() {
        Assertions.assertThrows(AcuraciaInvalidaException.class,
        ()-> {
            new AtaqueBuilder().comAcuracia(101).construir();
        });
    }

    @Test
    public void nao_deve_ter_forca_menor_que_zero() {
        Assertions.assertThrows(ForcaInvalidaException.class,
        ()-> {
            new AtaqueBuilder().comForca(-1).construir();
        });
    }

    @Test
    public void deve_ter_um_tipo() throws Exception {
        // Arrange
        Tipo tipo = new TipoBuilder().construir();

        // Action
        Ataque ataque = new AtaqueBuilder().comTipo(tipo).construir();

        // Assert
        Assertions.assertEquals(tipo, ataque.getTipo());
    }
}
