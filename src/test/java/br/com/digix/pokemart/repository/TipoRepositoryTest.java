package br.com.digix.pokemart.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.digix.pokemart.builders.TipoBuilder;
import br.com.digix.pokemart.models.Tipo;

@DataJpaTest
public class TipoRepositoryTest {

    @Autowired
    private TipoRepository tipoRepository;

    @Test
    public void deve_salvar_um_tipo() throws Exception {
        // Arrange
        Tipo tipo = new TipoBuilder().construir();

        // Action
        tipoRepository.save(tipo);

        // Assert
        Assertions.assertNotNull(tipo.getId());
        System.out.println(tipo.getId());
    }
}
