package br.com.digix.pokemart.controllers;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import br.com.digix.pokemart.builders.TipoBuilder;
import br.com.digix.pokemart.models.Tipo;
import br.com.digix.pokemart.repository.TipoRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class TipoControllerTest {
    @Autowired
	private MockMvc mockMvc;

	@Autowired
	private TipoRepository tipoRepository;

	@BeforeEach
	public void deletaDados() {
		tipoRepository.deleteAll();
	}

    @Test
	public void deve_buscar_os_tipos_cadastrados() throws Exception {
		Tipo water = new TipoBuilder().construir();
		Tipo fire = new TipoBuilder().comNome("Fire").construir();
		tipoRepository.saveAll(Arrays.asList(fire, water));

		this.mockMvc
			.perform(get("/tipos"))
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("Fire")));
	}

	@Test
	public void deve_remover_um_tipo_pelo_id() throws Exception {
		Tipo water = new TipoBuilder().construir();
		Tipo fire = new TipoBuilder().comNome("Fire").construir();
		tipoRepository.saveAll(Arrays.asList(fire, water));

		this.mockMvc
			.perform(delete("/tipos/" + water.getId()))
			.andExpect(status().isOk());

		List<Tipo> tiposRestornados = tipoRepository.findByNomeContainingIgnoreCase(water.getNome());

		Assertions.assertThat(tiposRestornados).isEmpty();
	}

	@Test
	public void deve_incluir_um_tipo() throws Exception {
		Tipo tipo = new TipoBuilder().construir(); 
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(tipo);
		this.mockMvc
			.perform(post("/tipos").content(json))
			.andExpect(status().isCreated());

		List<Tipo> tiposRetornados = tipoRepository.findByNomeContainingIgnoreCase(tipo.getNome());

		Assertions.assertThat(tiposRetornados.size()).isEqualTo(1);
		Assertions.assertThat(
			tipo.getNome()).isIn(tiposRetornados.stream().map(Tipo::getNome)
		);
	}
}
