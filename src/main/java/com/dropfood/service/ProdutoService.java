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
        EmpresaModel empresa = empresaRepository.findById(dto.id_empresa())
                .orElseThrow(() -> new EntityNotFoundException("Empresa com ID " + dto.id_empresa() + " não encontrada."));

        ProdutoModel produto = new ProdutoModel();
        produto.setNm_produto(dto.nm_produto());
        produto.setDs_produto(dto.ds_produto());
        produto.setPreco(dto.preco());
        produto.setCategoria(dto.categoria());
        produto.setFlg_ativo(dto.flg_ativo());
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
            produto.setNm_produto(dto.nm_produto());
            produto.setDs_produto(dto.ds_produto());
            produto.setPreco(dto.preco());
            produto.setCategoria(dto.categoria());
            produto.setFlg_ativo(dto.flg_ativo());
            if (!produto.getEmpresa().getId_Empresa().equals(dto.id_empresa())) {
                EmpresaModel novaEmpresa = empresaRepository.findById(dto.id_empresa())
                        .orElseThrow(() -> new EntityNotFoundException("Empresa com ID " + dto.id_empresa() + " não encontrada."));
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