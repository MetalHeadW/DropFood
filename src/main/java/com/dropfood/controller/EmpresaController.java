package com.dropfood.controller;

import com.dropfood.dto.EmpresaDto;
import com.dropfood.model.EmpresaModel;
import com.dropfood.service.EmpresaService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/empresa")
@CrossOrigin("*")
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @PostMapping
    public ResponseEntity<EmpresaModel> criar(@Valid @RequestBody EmpresaDto dto) {
        try {
            EmpresaModel novaEmpresa = empresaService.salvar(dto);
            return ResponseEntity.ok(novaEmpresa);
        } catch (IllegalArgumentException e) {
            // Retorna 400 Bad Request se o CNPJ já existir
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<EmpresaModel>> listarTodas() {
        return ResponseEntity.ok(empresaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaModel> listarPorId(@PathVariable Integer id) {
        return empresaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaModel> atualizaDados(@PathVariable Integer id,
                                                      @Valid @RequestBody EmpresaDto dto) {
        return empresaService.atualizaDados(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        try {
            boolean deletado = empresaService.deletar(id);
            if (deletado) {
                return ResponseEntity.noContent().build(); // 204 No Content
            } else {
                return ResponseEntity.notFound().build(); // 404 Not Found
            }
        } catch (RuntimeException e) {
            // Retorna 409 Conflict se a empresa não puder ser deletada (ex: usuários atrelados)
            return ResponseEntity.status(409).build();
        }
    }
}
