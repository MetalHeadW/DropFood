package com.dropfood.controller;
import com.dropfood.dto.UsuarioDto;
import com.dropfood.model.UsuarioModel;
import com.dropfood.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/usuario")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioModel> criar (@Valid @RequestBody UsuarioDto dto) {
        UsuarioModel usuarioModel = usuarioService.salvar(dto);
        return ResponseEntity.ok(usuarioModel);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioModel>> listarTodos(){
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<UsuarioModel>> listarAtivas(){
        return ResponseEntity.ok(usuarioService.listarUsuariosAtivos());
    }

    @GetMapping("/{id_Usuario}")
    public ResponseEntity<UsuarioModel> listarPorIdUsuario (@PathVariable Integer id_Usuario) {
        return usuarioService.findByIdUsuario(id_Usuario).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id_Usuario}")
    public ResponseEntity<UsuarioModel> atualizaDados(@PathVariable Integer id_Usuario,
                                                      @Valid @RequestBody UsuarioDto dto) {
        return usuarioService.atualizaDados(id_Usuario, dto).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


}