package com.dropfood.repository;

import com.dropfood.model.EmpresaModel;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EmpresaRepository extends JpaRepository<EmpresaModel, Integer> {
    Optional<EmpresaModel> findByCnpjEmpresa(String cnpj_Empresa);
}
