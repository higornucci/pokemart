package br.com.digix.pokemart.controllers;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import br.com.digix.pokemart.builders.AtaqueBuilder;
import br.com.digix.pokemart.models.Ataque;
import br.com.digix.pokemart.repository.AtaqueRepository;


@SpringBootTest
@AutoConfigureMockMvc
public class AtaqueControllerTest {
    @Autowired
	private MockMvc mockMvc;

	@Autowired
	private AtaqueRepository  ataqueRepository;


	@BeforeEach
	@AfterEach
	public void deleteDados() {
		ataqueRepository.deleteAll();
		
    }

    
    @Test
	public void deve_buscar_os_ataque_cadastrados() throws Exception {
		Ataque chamasdapaixao = new AtaqueBuilder().construir();
		Ataque boladefogo  = new AtaqueBuilder().comNome("boladefogo").construir();
		ataqueRepository.saveAll(Arrays.asList(chamasdapaixao, boladefogo));

		this.mockMvc
				.perform(get("/ataques"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("boladefogo")));
	}
    @Test
	public void deve_remover_um_ataque_pelo_id() throws Exception {
		Ataque chamasdapaixao = new AtaqueBuilder().construir();
		Ataque boladefogo = new AtaqueBuilder().comNome("boladefogo").construir();
		ataqueRepository.saveAll(Arrays.asList(boladefogo, chamasdapaixao));

		this.mockMvc
				.perform(delete("/ataques/" + chamasdapaixao.getId()))
				.andExpect(status().isOk());

		List<Ataque> ataqueRetornados = ataqueRepository.findByNomeContainingIgnoreCase(chamasdapaixao.getNome());

		Assertions.assertThat(ataqueRetornados).isEmpty();
	}
}
