package br.com.digix.pokemart.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.digix.pokemart.models.Ataque;
import br.com.digix.pokemart.models.enums.Categoria;

@Repository
public interface AtaqueRepository extends CrudRepository<Ataque, Long> {
    List<Ataque> findByNomeContainingIgnoreCase(String nome);

    List<Ataque> findByCategoria(Categoria categoria);
}
