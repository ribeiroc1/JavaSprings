package br.com.senior.treinamento.demo.rest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.senior.treinamento.demo.entidades.PedidoEntity;
import br.com.senior.treinamento.demo.service.PedidoService;

@RestController
@RequestMapping("/api")
public class PedidoController {
	
	public PedidoService pedidoService;
	
	public PedidoController(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}
	
	@PostMapping("/pedidos")
	public ResponseEntity<PedidoEntity> criar(@RequestBody PedidoEntity pedido) {
		System.out.println("Criando um pedido");
		pedido = pedidoService.salvar(pedido);
		return new ResponseEntity<PedidoEntity>(pedido, HttpStatus.CREATED);
	}
	
	@PutMapping("/pedidos")
	public ResponseEntity<Void> alterar(@RequestBody PedidoEntity pedido) {
		System.out.println("Alterando pedido");
		if(!pedidoService.buscarPorId(pedido.getId()).isPresent()) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		pedidoService.salvar(pedido);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/pedidos/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		System.out.println("Deletando pedido");
		if(!pedidoService.buscarPorId(id).isPresent()) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		pedidoService.deletar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/pedidos/{id}") 
	public ResponseEntity<PedidoEntity> buscarPedido(@PathVariable Long id) {
		System.out.println("Buscando pedido");
		Optional <PedidoEntity> pedido = pedidoService.buscarPorId(id);
		if(pedido.isPresent()) {
			return new ResponseEntity<PedidoEntity>(pedido.get(),HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);		
	}
	
	@GetMapping("/pedidos")
	public ResponseEntity<List<PedidoEntity>> buscarPedido() {
		System.out.println("Buscando pedidos");
		List<PedidoEntity> listaPedidos = pedidoService.buscarTodos();		
		return new ResponseEntity<List<PedidoEntity>>(listaPedidos,HttpStatus.OK);		
	}
	
}
