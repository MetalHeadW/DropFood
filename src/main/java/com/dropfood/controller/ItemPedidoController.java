package com.dropfood.controller;

import com.dropfood.dto.ItemPedidoDto;
import com.dropfood.model.ItemPedidoModel;
import com.dropfood.service.ItemPedidoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/pedido/{idPedido}/itens")
@CrossOrigin("*")
public class ItemPedidoController {

    private final ItemPedidoService itemPedidoService;

    public ItemPedidoController(ItemPedidoService itemPedidoService) {
        this.itemPedidoService = itemPedidoService;
    }

    @PostMapping
    public ResponseEntity<ItemPedidoModel> criar(@PathVariable Integer idPedido,
                                                 @Valid @RequestBody ItemPedidoDto dto) {
        try {
            ItemPedidoModel novoItem = itemPedidoService.salvar(idPedido, dto);
            return ResponseEntity.ok(novoItem);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build(); // Pedido ou Produto não encontrado
        }
    }

    @GetMapping
    public ResponseEntity<List<ItemPedidoModel>> listarItensDoPedido(@PathVariable Integer idPedido) {
        try {
            return ResponseEntity.ok(itemPedidoService.listarItensPorPedido(idPedido));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build(); // Pedido não encontrado
        }
    }

    @GetMapping("/{idItem}")
    public ResponseEntity<ItemPedidoModel> buscarItemPorId(@PathVariable Integer idPedido,
                                                           @PathVariable Integer idItem) {
        return itemPedidoService.findById(idItem)
                // Validação extra (opcional) para garantir que o item pertence ao pedido
                .filter(item -> item.getPedido().getIdPedido().equals(idPedido))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{idItem}")
    public ResponseEntity<Void> deletar(@PathVariable Integer idPedido,
                                        @PathVariable Integer idItem) {
        try {itemPedidoService.deletar(idItem);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build(); // Item não encontrado
        }
    }
}
