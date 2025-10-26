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
        if (dto.id_Usuario() == null) throw new IllegalArgumentException("ID do Usuário não pode ser nulo.");
        if (dto.id_Empresa() == null) throw new IllegalArgumentException("ID da Empresa não pode ser nulo.");
        if (dto.tp_Pagamento() == null || dto.tp_Pagamento().isBlank()) {
            throw new IllegalArgumentException("Tipo de Pagamento não pode ser nulo ou em branco.");
        }
        UsuarioModel usuario = usuarioRepository.findById(dto.id_Usuario())
                .orElseThrow(() -> new EntityNotFoundException("Usuário com ID " + dto.id_Usuario() + " não encontrado."));

        EmpresaModel empresa = empresaRepository.findById(dto.id_Empresa())
                .orElseThrow(() -> new EntityNotFoundException("Empresa com ID " + dto.id_Empresa() + " não encontrada."));
        PedidoModel pedido = new PedidoModel();
        pedido.setUsuario(usuario);
        pedido.setEmpresa(empresa);
        pedido.setTp_Pagamento(dto.tp_Pagamento());
        pedido.setVlr_Total(0.0);
        pedido.setData_Hora(LocalDateTime.now());
        pedido.setSts_Pedido("PENDENTE");
        pedido.setSts_Pagamento("A RECEBER");
        pedido.setDt_Pagamento(null);
        return pedidoRepository.save(pedido);
    }

    @Transactional
    public void recalcularTotalPedido(Integer idPedido) {
        PedidoModel pedido = pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new EntityNotFoundException("Pedido com ID " + idPedido + " não encontrado."));

        List<ItemPedidoModel> itens = itemPedidoRepository.findByPedido(pedido);
        Double novoTotal = itens.stream()
                .mapToDouble(ItemPedidoModel::getValor_total)
                .sum();
        pedido.setVlr_Total(novoTotal);
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
        if (dto.sts_Pedido() == null || dto.sts_Pedido().isBlank()) {
            throw new IllegalArgumentException("Status do Pedido não pode ser nulo ou em branco.");
        }
        if (dto.sts_Pagamento() == null || dto.sts_Pagamento().isBlank()) {
            throw new IllegalArgumentException("Status do Pagamento não pode ser nulo ou em branco.");
        }
        return pedidoRepository.findById(id).map(pedido -> {
            pedido.setSts_Pedido(dto.sts_Pedido());
            if (dto.sts_Pagamento().equals("CONCLUÍDO") && pedido.getSts_Pagamento().equals("A RECEBER")) {
                pedido.setDt_Pagamento(LocalDate.now());
            }
            pedido.setSts_Pagamento(dto.sts_Pagamento());
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