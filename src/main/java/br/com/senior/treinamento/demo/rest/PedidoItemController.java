package br.com.senior.treinamento.demo.rest;

import java.util.List;
import java.util.Optional;

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

import br.com.senior.treinamento.demo.entidades.PedidoItemEntity;
import br.com.senior.treinamento.demo.service.PedidoItemService;

@RestController
@RequestMapping("/api")
public class PedidoItemController {
	
	public PedidoItemService pedidoItemService;
	
	public PedidoItemController(PedidoItemService pedidoItemService) {
		this.pedidoItemService = pedidoItemService;
	}
	
	@PostMapping("/pedidosItens")
	public ResponseEntity<PedidoItemEntity> criar(@RequestBody PedidoItemEntity pedidoItem) {
		System.out.println("Criando um item de pedido");
		pedidoItem = pedidoItemService.salvar(pedidoItem);
		return new ResponseEntity<PedidoItemEntity>(pedidoItem, HttpStatus.CREATED);
	}
	
	@PutMapping("/pedidositens")
	public ResponseEntity<Void> alterar(@RequestBody PedidoItemEntity pedidoItem) {
		System.out.println("Alterando item pedido");
		if(!pedidoItemService.buscarPorId(pedidoItem.getId()).isPresent()) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		pedidoItemService.salvar(pedidoItem);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/pedidosItens/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		System.out.println("Deletando item pedido");
		if(!pedidoItemService.buscarPorId(id).isPresent()) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		pedidoItemService.deletar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/pedidosItens/{id}") 
	public ResponseEntity<PedidoItemEntity> buscarPedidoItens(@PathVariable Long id) {
		System.out.println("Buscando item pedido");
		Optional <PedidoItemEntity> pedidoItem = pedidoItemService.buscarPorId(id);
		if(pedidoItem.isPresent()) {
			return new ResponseEntity<PedidoItemEntity>(pedidoItem.get(),HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);		
	}
	
	@GetMapping("/pedidosItens")
	public ResponseEntity<List<PedidoItemEntity>> buscarPedidoItens() {
		System.out.println("Buscando itens pedidos");
		List<PedidoItemEntity> listaItemPedidos = pedidoItemService.buscarTodos();		
		return new ResponseEntity<List<PedidoItemEntity>>(listaItemPedidos,HttpStatus.OK);		
	}
}
