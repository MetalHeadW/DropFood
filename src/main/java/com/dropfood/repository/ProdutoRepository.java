package com.dropfood.repository;

import com.dropfood.model.EmpresaModel;
import com.dropfood.model.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<ProdutoModel, Integer> {

    // Busca produtos por flag de ativo
    List<ProdutoModel> findByFlgAtivo(String flgAtivo);

    // Busca produtos por empresa
    List<ProdutoModel> findByEmpresa(EmpresaModel empresa);

    // Busca produtos ativos de uma empresa específica
    List<ProdutoModel> findByEmpresaAndFlgAtivo(EmpresaModel empresa, String flgAtivo);

    // Busca produtos por categoria e empresa
    List<ProdutoModel> findByCategoriaAndEmpresa(String categoria, EmpresaModel empresa);

    // Query customizada para buscar produtos ativos pelo ID da empresa
    @Query("SELECT p FROM ProdutoModel p WHERE p.empresa.idEmpresa = :idEmpresa AND p.flgAtivo = 'A'")
    List<ProdutoModel> findAtivosByIdEmpresa(Integer idEmpresa);
}