package br.com.digix.pokemart.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.digix.pokemart.models.Tipo;

@Repository
public interface TipoRepository extends CrudRepository<Tipo, Long> {
}
