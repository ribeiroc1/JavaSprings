package br.com.senior.treinamento.demo.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.senior.treinamento.demo.entidades.PedidoEntity;
import br.com.senior.treinamento.demo.entidades.PedidoItemEntity;

@Repository
public interface PedidoItemRepository extends JpaRepository<PedidoItemEntity,Long>{
	
	public PedidoItemEntity findByPedido(PedidoEntity pedido);
	
}