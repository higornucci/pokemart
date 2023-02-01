package br.com.digix.pokemart.repository;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.digix.pokemart.builders.AtaqueBuilder;
import br.com.digix.pokemart.models.Ataque;

@DataJpaTest
public class AtaqueRepositoryTest {
    
    @Autowired
    private AtaqueRepository ataqueRepository;

    @Test
    public void deve_salvar_um_ataque() throws Exception {
        Ataque ataque = new AtaqueBuilder().construir();
        
        ataqueRepository.save(ataque);

        Assertions.assertNotNull(ataque.getId());
    }

    @Test
    public void deve_remover_um_ataque_cadastrado() throws Exception {
        Ataque ataque = new AtaqueBuilder().construir();
        ataqueRepository.save(ataque);

        ataqueRepository.deleteById(ataque.getId());

        Optional<Ataque> ataqueBuscado = ataqueRepository.findById(ataque.getId());
        Assertions.assertFalse(ataqueBuscado.isPresent());
    }

    @Test
    public void deve_buscar_pelo_nome() throws Exception {
        String nome = "Water";
        Ataque ataque = new AtaqueBuilder().comNome(nome).construir();
        ataqueRepository.save(ataque);

        List<Ataque> ataqueRetornado = ataqueRepository.findByNomeContainingIgnoreCase(nome);

        Assertions.assertTrue(ataqueRetornado.contains(ataque));
    }
}
