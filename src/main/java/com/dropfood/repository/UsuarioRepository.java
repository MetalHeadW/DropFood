package com.dropfood.repository;

import com.dropfood.model.UsuarioModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Integer> {

    Optional<UsuarioModel> findByIdUsuario(Integer id_Usuario);

    @Query("SELECT usuar FROM UsuarioModel usuar WHERE usuar.flg_Ativo = 'A'")
    List<UsuarioModel> findByFlgAtivo();

    @Transactional
    long deleteByIdUsuario(Integer id_Usuario);

}