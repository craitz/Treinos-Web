package com.camilo.treinos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.camilo.treinos.model.Material;

public interface Materiais extends JpaRepository<Material, Long>{

	public List<Material> findByNomeContaining(String nome);
}
