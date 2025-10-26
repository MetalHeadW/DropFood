package com.dropfood.repository;

import jakarta.validation.constraints.NotNull;

import java.util.Optional;

public interface EmpresaRepository {
    Optional<Object> findById(@NotNull(message = "ID da Empresa não pode ser nulo") Integer empresa);
}
