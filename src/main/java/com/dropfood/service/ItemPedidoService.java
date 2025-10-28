package com.dropfood.service;

import com.dropfood.dto.ItemPedidoDto;
import com.dropfood.model.ItemPedidoModel;
import com.dropfood.model.PedidoModel;
import com.dropfood.model.ProdutoModel;
import com.dropfood.repository.ItemPedidoRepository;
import com.dropfood.repository.PedidoRepository;
import com.dropfood.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ItemPedidoService {

    private final ItemPedidoRepository itemPedidoRepository;
    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final PedidoService pedidoService;

    public ItemPedidoService(ItemPedidoRepository itemPedidoRepository, PedidoRepository pedidoRepository, ProdutoRepository produtoRepository, PedidoService pedidoService) {
        this.itemPedidoRepository = itemPedidoRepository;
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
        this.pedidoService = pedidoService;
    }

    @Transactional
    public ItemPedidoModel salvar(Integer idPedido, ItemPedidoDto dto) {
        PedidoModel pedido = pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new EntityNotFoundException("Pedido com ID " + idPedido + " não encontrado."));

        ProdutoModel produto = produtoRepository.findById(dto.idProduto())
                .orElseThrow(() -> new EntityNotFoundException("Produto com ID " + dto.idProduto() + " não encontrado."));

        ItemPedidoModel item = new ItemPedidoModel();
        item.setPedido(pedido);
        item.setProduto(produto);
        item.setQuantidade(dto.quantidade());
        item.setValorUnitario(produto.getPreco());
        item.setValorTotal(produto.getPreco() * dto.quantidade());
        ItemPedidoModel itemSalvo = itemPedidoRepository.save(item);
        pedidoService.recalcularTotalPedido(idPedido);
        return itemSalvo;
    }

    public List<ItemPedidoModel> listarItensPorPedido(Integer idPedido) {
        PedidoModel pedido = pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new EntityNotFoundException("Pedido com ID " + idPedido + " não encontrado."));
        return itemPedidoRepository.findByPedido(pedido);
    }

    public Optional<ItemPedidoModel> findById(Integer idItem) {
        return itemPedidoRepository.findById(idItem);
    }

    @Transactional
    public void deletar(Integer idItem) {
        ItemPedidoModel item = itemPedidoRepository.findById(idItem)
                .orElseThrow(() -> new EntityNotFoundException("Item com ID " + idItem + " não encontrado."));
        Integer idPedido = item.getPedido().getIdPedido();
        itemPedidoRepository.delete(item);
        pedidoService.recalcularTotalPedido(idPedido);
    }
}