package com.dropfood.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dropfood.PedidoModel;
@Repository

public interface PedidoRepository extends Jparepository<PedidoModel, Integer>{

}
