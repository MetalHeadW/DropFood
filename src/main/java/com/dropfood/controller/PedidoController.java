package com.dropfood.controller;
import com.dropfood.model.PedidoModel;
import com.dropfood.service.PedidoService;
import com.dropfood.dto.PedidoDto;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;
    public PedidoController(PedidoService pedidoService){
        this.pedidoService = pedidoService;
    }
    @GetMapping
    public List<PedidoModel> listarTodos(){
        return pedidoService.listarTodos();
    }
    @GetMapping("/{id}")
    public Optional <PedidoModel> listarPorId(@PathVariable Integer id){
        return pedidoService.buscarPorId(id);
    }
    @PostMapping
    public PedidoModel criar(@RequestBody PedidoModel pedido){
        return pedidoService.salvar(pedido);
    }
    @PutMapping("/{id}")
    public PedidoModel atualizar(@PathVariable Integer id, @RequestBody PedidoModel pedidoAtualizado){
        pedidoAtualizado.setId_Pedido(id);
    }


}