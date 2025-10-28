package com.dropfood.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UsuarioDto(

        @NotBlank(message= "Campo 'Nome_Usuário' não pode ser em Branco")
        @NotNull(message= "Campo 'Nome_Usuário' não pode ser Nulo")
        String nmUsuario,

        @NotBlank(message= "Campo 'Email' não pode ser em Branco")
        @NotNull(message= "Campo 'Email' não pode ser Nulo")
        String email,

        @NotBlank(message= "Campo 'Senha_Usuario' não pode ser em Branco")
        @NotNull(message= "Campo 'Senha_Usuario' não pode ser Nulo")
        String senhaUsuario,

        @NotBlank(message= "Campo 'Telefone' não pode ser em Branco")
        @NotNull(message= "Campo 'Telefone' não pode ser Nulo")
        String telefone,

        @NotNull(message= "Campo 'Tipo_Usuário' não pode ser Nulo")
        @Min(value = 1, message = "Tipo de Usuário deve ser no mínimo 1")
        @Max(value = 4, message = "Tipo de Usuário deve ser no máximo 4")
        Integer tipUsuario, // 1-Administrador. 2-Funcionario 3-Entregador. 4-Cliente.

        @NotBlank(message= "Campo 'CPF' não pode ser em Branco")
        @NotNull(message= "Campo 'CPF' não pode ser Nulo")
        String cpf,

        @NotBlank(message= "Campo 'Endereço' não pode ser em Branco")
        @NotNull(message= "Campo 'Endereço' não pode ser Nulo")
        String endereco,

        @NotBlank(message= "Campo 'flg_Ativo' não pode ser em Branco")
        @NotNull(message= "Campo 'flg_Ativo' não pode ser Nulo")
        @Pattern(regexp = "^A|I$", message = "Só é possível salvar o flg_ativo com A-Ativo ou I-Inativo.")
        String flgAtivo,

        String preferencia1,
        String preferencia2,

        @NotNull(message = "ID da Empresa não pode ser nulo")
        Integer empresa // Recebe o ID da Empresa
) {
}