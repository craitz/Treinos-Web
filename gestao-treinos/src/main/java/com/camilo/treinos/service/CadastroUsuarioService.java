package com.camilo.treinos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.camilo.treinos.model.Usuario;
import com.camilo.treinos.repository.Usuarios;
import com.camilo.treinos.repository.filter.UsuarioFilter;

@Service
public class CadastroUsuarioService {

	@Autowired
	private Usuarios usuarios;		
	
	public void salvar(Usuario usuario) {
		try {
			
			//procura pelo usuário
			Usuario found = usuarios.findOne(usuario.getEmail());
			
			//se o usuário já existe, lança uma exceção
			if (found != null){
				throw new UnsupportedOperationException("O e-mail <" + found.getEmail() + "> já está cadastrado!");
			}
			
			//senão, salva no BD
			usuarios.save(usuario);
			
		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Formato de data inválido");
	    }
	}
	
	public void excluir(String email) {
		usuarios.delete(email);
	}
	
	public Usuario verificaLogin(UsuarioFilter filtro) {
		
		String email = filtro.getEmail() == null ? "" : filtro.getEmail();
		String pwd = filtro.getPwd() == null ? "" : filtro.getPwd();
		
		return  usuarios.findByEmailAndPwd(email, pwd);
	}
}
