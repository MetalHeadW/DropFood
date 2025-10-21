package com.dropfood.service;

import com.dropfood.dto.UsuarioDto;
import com.dropfood.model.UsuarioModel;
import com.dropfood.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioModel salvar(UsuarioDto dto){
        UsuarioModel usuario = new UsuarioModel();
        usuario.setNm_Usuario(dto.nm_Usuario());
        usuario.setEmail(dto.email());
        usuario.setSenha_Usuario(dto.senha_Usuario());
        usuario.setTelefone(dto.telefone());
        usuario.setTip_Usuario(dto.tip_Usuario());
        usuario.setCpf(dto.cpf());
        usuario.setEndereco(dto.endereco());
        usuario.setPeferencia1(dto.peferencia1());
        usuario.setPeferencia2(dto.peferencia2());
        usuario.setFlg_Ativo(dto.flg_Ativo());
        usuario.setId_Empresa(dto.id_Empresa());
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
            usuario.setNm_Usuario(usuarioDto.nm_Usuario());
            usuario.setEmail(usuarioDto.email());
            usuario.setSenha_Usuario(usuarioDto.senha_Usuario());
            usuario.setTelefone(usuarioDto.telefone());
            usuario.setTip_Usuario(usuarioDto.tip_Usuario());
            usuario.setCpf(usuarioDto.cpf());
            usuario.setEndereco(usuarioDto.endereco());
            usuario.setPeferencia1(usuarioDto.peferencia1());
            usuario.setPeferencia2(usuarioDto.peferencia2());
            usuario.setFlg_Ativo(usuarioDto.flg_Ativo());
            return usuarioRepository.save(usuario);
        });
    }
}
