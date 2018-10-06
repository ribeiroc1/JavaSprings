package br.com.senior.treinamento.demo.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import antlr.collections.List;
import br.com.senior.treinamento.demo.entidades.ClienteEntity;
import br.com.senior.treinamento.demo.entidades.PedidoEntity;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Long>{
	
	public PedidoEntity findById(int id);
	
	public PedidoEntity findByData(LocalDateTime data);
	
	public List findByCliente(ClienteEntity cliente);
}
