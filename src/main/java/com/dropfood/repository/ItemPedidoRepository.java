package com.dropfood.repository;

import com.dropfood.model.ItemPedidoModel;
import com.dropfood.model.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemPedidoRepository extends JpaRepository<ItemPedidoModel, Integer> {
    List<ItemPedidoModel> findByPedido(PedidoModel pedido);
}