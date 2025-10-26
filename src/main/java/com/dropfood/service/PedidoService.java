package com.dropfood.service;

import com.dropfood.dto.PedidoDto;
import com.dropfood.model.EmpresaModel;
import com.dropfood.model.PedidoModel;
import com.dropfood.model.UsuarioModel;
import com.dropfood.repository.EmpresaRepository;
import com.dropfood.repository.PedidoRepository;
import com.dropfood.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;
    private final EmpresaRepository empresaRepository;

    public PedidoService(PedidoRepository pedidoRepository, UsuarioRepository usuarioRepository, EmpresaRepository empresaRepository) {
        this.pedidoRepository = pedidoRepository;
        this.usuarioRepository = usuarioRepository;
        this.empresaRepository = empresaRepository;
    }

    public PedidoModel salvar(PedidoDto dto) {
        UsuarioModel usuario = usuarioRepository.findById(dto.id_Usuario())
                .orElseThrow(() -> new EntityNotFoundException("Usuário com ID " + dto.id_Usuario() + " não encontrado."));

        EmpresaModel empresa = empresaRepository.findById(dto.id_Empresa())
                .orElseThrow(() -> new EntityNotFoundException("Empresa com ID " + dto.id_Empresa() + " não encontrada."));

        PedidoModel pedido = new PedidoModel();
        pedido.setUsuario(usuario);
        pedido.setEmpresa(empresa);
        pedido.setVlr_Total(dto.vlr_Total());
        pedido.setTp_Pagamento(dto.tp_Pagamento());
        pedido.setData_Hora(LocalDateTime.now());
        pedido.setSts_Pedido("PENDENTE");
        pedido.setSts_Pagamento("A RECEBER");
        pedido.setDt_Pagamento(null);
        return pedidoRepository.save(pedido);
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

    public Optional<PedidoModel> atualizaStatus(Integer id, PedidoDto dto) {
        return pedidoRepository.findById(id).map(pedido -> {
            pedido.setSts_Pedido(dto.sts_Pedido());
            if (dto.sts_Pagamento().equals("CONCLUÍDO") && pedido.getSts_Pagamento().equals("A RECEBER")) {
                pedido.setDt_Pagamento(LocalDate.now());
            }
            pedido.setSts_Pagamento(dto.sts_Pagamento());
            return pedidoRepository.save(pedido);
        });
    }

    public boolean deletar(Integer id) {
        if (pedidoRepository.existsById(id)) {
            pedidoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}