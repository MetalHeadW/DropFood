package com.dropfood.service;

import com.dropfood.dto.ProdutoDto;
import com.dropfood.model.EmpresaModel;
import com.dropfood.model.ProdutoModel;
import com.dropfood.repository.EmpresaRepository;
import com.dropfood.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final EmpresaRepository empresaRepository;

    public ProdutoService(ProdutoRepository produtoRepository, EmpresaRepository empresaRepository) {
        this.produtoRepository = produtoRepository;
        this.empresaRepository = empresaRepository;
    }

    public ProdutoModel salvar(ProdutoDto dto) {
        EmpresaModel empresa = empresaRepository.findById(dto.idEmpresa())
                .orElseThrow(() -> new EntityNotFoundException("Empresa com ID " + dto.idEmpresa() + " não encontrada."));

        ProdutoModel produto = new ProdutoModel();
        produto.setNmProduto(dto.nmProduto());
        produto.setDsProduto(dto.dsProduto());
        produto.setPreco(dto.preco());
        produto.setCategoria(dto.categoria());
        produto.setFlgAtivo(dto.flgAtivo());
        produto.setEmpresa(empresa);
        return produtoRepository.save(produto);
    }

    public List<ProdutoModel> listarTodos() {
        return produtoRepository.findAll();
    }

    public Optional<ProdutoModel> findById(Integer id) {
        return produtoRepository.findById(id);
    }

    public List<ProdutoModel> listarAtivosPorEmpresa(Integer idEmpresa) {
        return produtoRepository.findAtivosByIdEmpresa(idEmpresa);
    }

    public Optional<ProdutoModel> atualizaDados(Integer id, ProdutoDto dto) {
        return produtoRepository.findById(id).map(produto -> {
            produto.setNmProduto(dto.nmProduto());
            produto.setDsProduto(dto.dsProduto());
            produto.setPreco(dto.preco());
            produto.setCategoria(dto.categoria());
            produto.setFlgAtivo(dto.flgAtivo());
            if (!produto.getEmpresa().getIdEmpresa().equals(dto.idEmpresa())) {
                EmpresaModel novaEmpresa = empresaRepository.findById(dto.idEmpresa())
                        .orElseThrow(() -> new EntityNotFoundException("Empresa com ID " + dto.idEmpresa() + " não encontrada."));
                produto.setEmpresa(novaEmpresa);
            }
            return produtoRepository.save(produto);
        });
    }

    public boolean deletar(Integer id) {
        if (produtoRepository.existsById(id)) {
            try {
                produtoRepository.deleteById(id);
                return true;
            } catch (Exception e) {
                // Captura exceções (ex: ConstraintViolation)
                throw new RuntimeException("Não foi possível deletar o produto.", e);
            }
        }
        return false;
    }
}