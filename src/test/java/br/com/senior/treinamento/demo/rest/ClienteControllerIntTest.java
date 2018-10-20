package br.com.senior.treinamento.demo.rest;

import org.hibernate.engine.spi.Mapping;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.io.IOException;

import br.com.senior.treinamento.demo.DemoApplication;
import br.com.senior.treinamento.demo.entidades.ClienteEntity;
import br.com.senior.treinamento.demo.service.ClienteService;
import br.com.senior.treinamento.demo.util.TestUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class})
public class ClienteControllerIntTest {
	
	@Autowired
	private ClienteService clienteService;
	private MockMvc restClienteMockMvc;
	
	@Autowired
	private MappingJackson2HttpMessageConverter jacksonMessageConverter;
	
	private ClienteEntity cliente;
	
	@Before
	public void setup() {
		final ClienteController clienteController = new ClienteController(clienteService);
		this.restClienteMockMvc = MockMvcBuilders.standaloneSetup(clienteController)
				.setMessageConverters(jacksonMessageConverter).build();
	}
	
	@Before
	public void initTest() {
		cliente = criarEntidade();
	}
	
	private static ClienteEntity criarEntidade() {
		ClienteEntity cliente = new ClienteEntity();
		cliente.setNome("Maria");
		return cliente;
	}
	
	@Test
	public void criarCliente() throws IOException, Exception {
		restClienteMockMvc.perform(post("/api/clientes")
				.contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(cliente)))
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}
	
}
