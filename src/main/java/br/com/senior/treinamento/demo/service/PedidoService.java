package br.com.senior.treinamento.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.senior.treinamento.demo.entidades.PedidoEntity;
import br.com.senior.treinamento.demo.repository.PedidoRepository;

@Service
public class PedidoService {
	
	private PedidoRepository pedidoRepository;
	
	public PedidoService(PedidoRepository pedidoRepository) {
		this.pedidoRepository = pedidoRepository;
	}

	public PedidoEntity salvar(PedidoEntity pedido) {
		return pedidoRepository.save(pedido);
	}

	public Optional<PedidoEntity> buscarPorId(Long id) {
		return pedidoRepository.findById(id);		
	}

	public void deletar(Long id) {
		pedidoRepository.deleteById(id);		
	}

	public List<PedidoEntity> buscarTodos() {		
		return pedidoRepository.findAll();			
	}

}
