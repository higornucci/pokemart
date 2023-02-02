package br.com.digix.pokemart.controllers;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

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
}
