package com.dropfood.service;

import com.dropfood.dto.EmpresaDto;
import com.dropfood.model.EmpresaModel;
import com.dropfood.repository.EmpresaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public EmpresaModel salvar(EmpresaDto dto) {
        if (empresaRepository.findByCnpjEmpresa(dto.cnpj_Empresa()).isPresent()) {
            throw new IllegalArgumentException("Uma empresa com este CNPJ já existe.");
        }

        EmpresaModel empresa = new EmpresaModel();
        empresa.setNm_Empresa(dto.nm_Empresa());
        empresa.setEndereco_Empresa(dto.endereco_Empresa());
        empresa.setTelefone_Empresa(dto.telefone_Empresa());
        empresa.setCnpj_Empresa(dto.cnpj_Empresa());
        empresa.setTipo_Empresa(dto.tipo_Empresa());

        return empresaRepository.save(empresa);
    }

    public List<EmpresaModel> listarTodas() {
        return empresaRepository.findAll();
    }

    public Optional<EmpresaModel> findById(Integer id) {
        return empresaRepository.findById(id);
    }

    public Optional<EmpresaModel> atualizaDados(Integer id, EmpresaDto dto) {
        return empresaRepository.findById(id).map(empresa -> {
            empresa.setNm_Empresa(dto.nm_Empresa());
            empresa.setEndereco_Empresa(dto.endereco_Empresa());
            empresa.setTelefone_Empresa(dto.telefone_Empresa());
            empresa.setCnpj_Empresa(dto.cnpj_Empresa());
            empresa.setTipo_Empresa(dto.tipo_Empresa());
            return empresaRepository.save(empresa);
        });
    }

    public boolean deletar(Integer id) {
        if (empresaRepository.existsById(id)) {
            try {
                empresaRepository.deleteById(id);
                return true;
            } catch (Exception e) {
                throw new RuntimeException("Não foi possível deletar a empresa. Verifique se ela possui usuários associados.", e);
            }
        }
        return false;
    }
}