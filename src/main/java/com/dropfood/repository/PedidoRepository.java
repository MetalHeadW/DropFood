package com.dropfood.repository;

import com.dropfood.model.EmpresaModel;
import com.dropfood.model.PedidoModel;
import com.dropfood.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<PedidoModel, Integer> {

    // Busca pedidos de um usuário específico
    List<PedidoModel> findByUsuario(UsuarioModel usuario);

    // Busca pedidos de uma empresa específica
    List<PedidoModel> findByEmpresa(EmpresaModel empresa);

    // Busca pedidos de uma empresa por um status específico
    List<PedidoModel> findByEmpresaAndStsPedido(EmpresaModel empresa, String status);

    // Busca pedidos por status (ex: "PENDENTE")
    List<PedidoModel> findByStsPedido(String status);
}