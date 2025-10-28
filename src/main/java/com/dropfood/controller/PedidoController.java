package com.dropfood.controller;

// CORREÇÃO: Referências de DTO atualizadas
import com.dropfood.dto.PedidoDto;
import com.dropfood.model.PedidoModel;
import com.dropfood.service.PedidoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/pedido")
@CrossOrigin("*")
public class    PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<PedidoModel> criar(@Valid @RequestBody PedidoDto dto) {
        try {
            PedidoModel novoPedido = pedidoService.salvar(dto);
            return ResponseEntity.ok(novoPedido);
        } catch (EntityNotFoundException e) {
            // Retorna 404 se o Usuário ou Empresa não forem encontrados
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            // Retorna 400 se a validação manual no service falhar
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<PedidoModel>> listarTodos() {
        return ResponseEntity.ok(pedidoService.listarTodos());
    }

    @GetMapping("/empresa/{idEmpresa}")
    public ResponseEntity<List<PedidoModel>> listarPorEmpresa(@PathVariable Integer idEmpresa) {
        try {
            return ResponseEntity.ok(pedidoService.listarPorEmpresa(idEmpresa));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoModel> listarPorId(@PathVariable Integer id) {
        return pedidoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<PedidoModel> atualizaStatus(@PathVariable Integer id,
                                                      @Valid @RequestBody PedidoDto dto) {
        try {
            return pedidoService.atualizaStatus(id, dto)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            // Retorna 400 se a validação manual no service falhar
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        boolean deletado = pedidoService.deletar(id);
        if (deletado) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

}