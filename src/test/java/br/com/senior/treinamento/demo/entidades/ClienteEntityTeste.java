package br.com.senior.treinamento.demo.entidades;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ClienteEntityTeste {
	
	private ClienteEntity cliente;
	
	@Before
	public void setUp() {
		cliente = new ClienteEntity();		
	}
	
	@Test
	public void getId() {
		Long id = 2L;
		cliente.setId(2L);		
		assertEquals(id, cliente.getId());
	}
	
	@Test
	public void getNome() {
		String nome = "Joao";
		cliente.setNome("pedro");
		assertEquals(nome,cliente.getNome());
	}
	
}
