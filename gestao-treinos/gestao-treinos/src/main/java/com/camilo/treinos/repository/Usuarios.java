package com.camilo.treinos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.camilo.treinos.model.Usuario;

public interface Usuarios extends JpaRepository<Usuario, String>{
	
	public Usuario findByEmailAndPwd(String email, String pwd);
}