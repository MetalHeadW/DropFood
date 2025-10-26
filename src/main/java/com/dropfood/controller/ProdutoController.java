package com.dropfood.controller;

import com.dropfood.dto.ProdutoDto;
import com.dropfood.model.ProdutoModel;
import com.dropfood.service.ProdutoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/produto")
@CrossOrigin("*")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<ProdutoModel> criar(@Valid @RequestBody ProdutoDto dto) {
        try {
            ProdutoModel novoProduto = produtoService.salvar(dto);
            return ResponseEntity.ok(novoProduto);
        } catch (EntityNotFoundException e) {
            // Retorna 404 se a Empresa associada não for encontrada
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ProdutoModel>> listarTodos() {
        return ResponseEntity.ok(produtoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoModel> listarPorId(@PathVariable Integer id) {
        return produtoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/empresa/{idEmpresa}/ativos")
    public ResponseEntity<List<ProdutoModel>> listarAtivosPorEmpresa(@PathVariable Integer idEmpresa) {
        return ResponseEntity.ok(produtoService.listarAtivosPorEmpresa(idEmpresa));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoModel> atualizaDados(@PathVariable Integer id,
                                                      @Valid @RequestBody ProdutoDto dto) {
        try {
            return produtoService.atualizaDados(id, dto)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (EntityNotFoundException e) {
            // Retorna 404 se a *nova* Empresa associada não for encontrada
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        try {
            boolean deletado = produtoService.deletar(id);
            if (deletado) {
                return ResponseEntity.noContent().build(); // 204 No Content
            } else {
                return ResponseEntity.notFound().build(); // 404 Not Found
            }
        } catch (RuntimeException e) {
            // Retorna 409 Conflict se o produto não puder ser deletado (ex: em uso)
            return ResponseEntity.status(409).build();
        }
    }
}