package com.camilo.treinos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.camilo.treinos.model.Material;
import com.camilo.treinos.repository.Materiais;
import com.camilo.treinos.repository.filter.MaterialFilter;

@Service
public class CadastroMaterialService {

	@Autowired
	private Materiais materiais;
	
	public void salvar(Material material) {
		try {
			materiais.save(material);
		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Formato de data inválido");
		}
	}
	
	public void excluir(Long id) {
		materiais.delete(id);
	}
	
	public List<Material> filtrar(MaterialFilter filtro) {
		String nome = filtro.getNome() == null ? "%" : filtro.getNome();
		return  materiais.findByNomeContaining(nome);
	}
}
