package com.dropfood.service;

import com.dropfood.dto.UsuarioDto;
import com.dropfood.model.EmpresaModel;
import com.dropfood.model.UsuarioModel;
import com.dropfood.repository.EmpresaRepository; // CORREÇÃO: Importado o repositório da Empresa
import com.dropfood.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException; // CORREÇÃO: Usar uma exceção mais específica
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final EmpresaRepository empresaRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, EmpresaRepository empresaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.empresaRepository = empresaRepository;
    }

    public UsuarioModel salvar(UsuarioDto dto){
        UsuarioModel usuario = new UsuarioModel();

        EmpresaModel empresa = empresaRepository.findById(dto.empresa())
                .orElseThrow(() -> new EntityNotFoundException("Empresa com ID " + dto.empresa() + " não encontrada."));

        usuario.setNmUsuario(dto.nmUsuario());
        usuario.setEmail(dto.email());
        usuario.setSenhaUsuario(dto.senhaUsuario());
        usuario.setTelefone(dto.telefone());
        usuario.setTipUsuario(dto.tipUsuario());
        usuario.setCpf(dto.cpf());
        usuario.setEndereco(dto.endereco());
        usuario.setPreferencia1(dto.preferencia1());
        usuario.setPreferencia2(dto.preferencia2());
        usuario.setFlgAtivo(dto.flgAtivo() != null ? dto.flgAtivo() : "A");
        usuario.setEmpresa(empresa);
        return usuarioRepository.save(usuario);
    }

    public List<UsuarioModel> listarTodos(){
        return usuarioRepository.findAll();
    }

    public List<UsuarioModel> listarUsuariosAtivos(){
        return usuarioRepository.findByFlgAtivo();
    }

    public Optional<UsuarioModel> findByIdUsuario(Integer id_Usuario){
        return usuarioRepository.findByIdUsuario(id_Usuario);
    }

    public Optional<UsuarioModel> atualizaDados(Integer id_Usuario, UsuarioDto usuarioDto){
        return usuarioRepository.findByIdUsuario(id_Usuario).map(usuario -> {
            usuario.setNmUsuario(usuarioDto.nmUsuario());
            usuario.setEmail(usuarioDto.email());
            usuario.setSenhaUsuario(usuarioDto.senhaUsuario());
            usuario.setTelefone(usuarioDto.telefone());
            usuario.setTipUsuario(usuarioDto.tipUsuario());
            usuario.setCpf(usuarioDto.cpf());
            usuario.setEndereco(usuarioDto.endereco());
            usuario.setPreferencia1(usuarioDto.preferencia1());
            usuario.setPreferencia2(usuarioDto.preferencia2());
            usuario.setFlgAtivo(usuarioDto.flgAtivo());

            if (usuarioDto.empresa() != null && !usuarioDto.empresa().equals(usuario.getEmpresa().getIdEmpresa())) {
                EmpresaModel novaEmpresa = empresaRepository.findById(usuarioDto.empresa())
                        .orElseThrow(() -> new EntityNotFoundException("Empresa com ID " + usuarioDto.empresa() + " não encontrada."));
                usuario.setEmpresa(novaEmpresa);
            }

            return usuarioRepository.save(usuario);
        });
    }
}