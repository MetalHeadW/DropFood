package com.dropfood.dto;


import com.dropfood.model.EmpresaModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

import java.util.List;

public record UsuarioDto(

        @NotBlank(message= "Campo 'Nome_Usuário' não pode ser em Branco")
        @NotNull(message= "Campo 'Nome_Usuário' não pode ser Nulo")
        String nm_Usuario,

        @NotBlank(message= "Campo 'Email' não pode ser em Branco")
        @NotNull(message= "Campo 'Email' não pode ser Nulo")
        String email,

        @NotBlank(message= "Campo 'Senha_Usuario' não pode ser em Branco")
        @NotNull(message= "Campo 'Senha_Usuario' não pode ser Nulo")
        String senha_Usuario,

        @NotBlank(message= "Campo 'Telefone' não pode ser em Branco")
        @NotNull(message= "Campo 'Telefone' não pode ser Nulo")
        String telefone,

        @NotBlank(message= "Campo 'Tipo_Usuário' não pode ser em Branco")
        @NotNull(message= "Campo 'Tipo_Usuário' não pode ser Nulo")
        @Pattern(regexp = "^1|2|3|4$", message = "Só é possível salvar o Tipo de Usuário com um dos números 1-Administrador. 2-Funcionario 3-Entregador. 4-Cliente.")
        Integer tip_Usuario, // 1-Administrador. 2-Funcionario 3-Entregador. 4-Cliente.

        @NotBlank(message= "Campo 'CPF' não pode ser em Branco")
        @NotNull(message= "Campo 'CPF' não pode ser Nulo")
        String cpf,

        @NotBlank(message= "Campo 'Endereço' não pode ser em Branco")
        @NotNull(message= "Campo 'Endereço' não pode ser Nulo")
        String endereco,

        @NotBlank(message= "Campo 'Endereço' não pode ser em Branco")
        @NotNull(message= "Campo 'Endereço' não pode ser Nulo")
        @Pattern(regexp = "^A|I$", message = "Só é possível salvar o flg_ativo com um dos caracteres A-Ativo. I-Inativo.")
        String flg_Ativo,

        String peferencia1,
        String peferencia2,
        Integer id_Empresa
) {
}