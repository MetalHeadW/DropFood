package com.dropfood.service;

import com.dropfood.dto.PedidoDto;
import com.dropfood.model.EmpresaModel;
import com.dropfood.model.ItemPedidoModel;
import com.dropfood.model.PedidoModel;
import com.dropfood.model.UsuarioModel;
import com.dropfood.repository.EmpresaRepository;
import com.dropfood.repository.ItemPedidoRepository;
import com.dropfood.repository.PedidoRepository;
import com.dropfood.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;
    private final EmpresaRepository empresaRepository;
    private final ItemPedidoRepository itemPedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository, UsuarioRepository usuarioRepository, EmpresaRepository empresaRepository, ItemPedidoRepository itemPedidoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.usuarioRepository = usuarioRepository;
        this.empresaRepository = empresaRepository;
        this.itemPedidoRepository = itemPedidoRepository;
    }

    @Transactional
    public PedidoModel salvar(PedidoDto dto) {
        if (dto.idUsuario() == null) throw new IllegalArgumentException("ID do Usuário não pode ser nulo.");
        if (dto.idEmpresa() == null) throw new IllegalArgumentException("ID da Empresa não pode ser nulo.");
        if (dto.tpPagamento() == null || dto.tpPagamento().isBlank()) {
            throw new IllegalArgumentException("Tipo de Pagamento não pode ser nulo ou em branco.");
        }
        UsuarioModel usuario = usuarioRepository.findById(dto.idUsuario())
                .orElseThrow(() -> new EntityNotFoundException("Usuário com ID " + dto.idUsuario() + " não encontrado."));

        EmpresaModel empresa = empresaRepository.findById(dto.idEmpresa())
                .orElseThrow(() -> new EntityNotFoundException("Empresa com ID " + dto.idEmpresa() + " não encontrada."));
        PedidoModel pedido = new PedidoModel();
        pedido.setUsuario(usuario);
        pedido.setEmpresa(empresa);
        pedido.setTpPagamento(dto.tpPagamento());
        pedido.setVlrTotal(0.0);
        pedido.setData(LocalDate.now());
        pedido.setHora(LocalTime.now());
        pedido.setStsPedido("PENDENTE");
        pedido.setStsPagamento("A RECEBER");
        pedido.setDtPagamento(null);
        return pedidoRepository.save(pedido);
    }

    @Transactional
    public void recalcularTotalPedido(Integer idPedido) {
        PedidoModel pedido = pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new EntityNotFoundException("Pedido com ID " + idPedido + " não encontrado."));

        List<ItemPedidoModel> itens = itemPedidoRepository.findByPedido(pedido);
        Double novoTotal = itens.stream()
                .mapToDouble(ItemPedidoModel::getValorTotal)
                .sum();
        pedido.setVlrTotal(novoTotal);
        pedidoRepository.save(pedido);
    }

    public List<PedidoModel> listarTodos() {
        return pedidoRepository.findAll();
    }

    public Optional<PedidoModel> findById(Integer id) {
        return pedidoRepository.findById(id);
    }

    public List<PedidoModel> listarPorEmpresa(Integer idEmpresa) {
        EmpresaModel empresa = empresaRepository.findById(idEmpresa)
                .orElseThrow(() -> new EntityNotFoundException("Empresa com ID " + idEmpresa + " não encontrada."));
        return pedidoRepository.findByEmpresa(empresa);
    }

    @Transactional
    public Optional<PedidoModel> atualizaStatus(Integer id, PedidoDto dto) {
        if (dto.stsPedido() == null || dto.stsPedido().isBlank()) {
            throw new IllegalArgumentException("Status do Pedido não pode ser nulo ou em branco.");
        }
        if (dto.stsPagamento() == null || dto.stsPagamento().isBlank()) {
            throw new IllegalArgumentException("Status do Pagamento não pode ser nulo ou em branco.");
        }
        return pedidoRepository.findById(id).map(pedido -> {
            pedido.setStsPedido(dto.stsPedido());
            if (dto.stsPagamento().equals("CONCLUÍDO") && pedido.getStsPagamento().equals("A RECEBER")) {
                pedido.setDtPagamento(LocalDate.now());
            }
            pedido.setStsPagamento(dto.stsPagamento());
            return pedidoRepository.save(pedido);
        });
    }

    @Transactional
    public boolean deletar(Integer id) {
        if (pedidoRepository.existsById(id)) {
            PedidoModel pedido = pedidoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));
            List<ItemPedidoModel> itens = itemPedidoRepository.findByPedido(pedido);
            itemPedidoRepository.deleteAll(itens);
            pedidoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}