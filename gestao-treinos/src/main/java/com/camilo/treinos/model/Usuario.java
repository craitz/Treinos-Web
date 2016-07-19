package com.camilo.treinos.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;

@Entity
public class Usuario {

	@Id
	@NotNull 
	@NotEmpty(message = "O campo <e-mail> é obrigatório!")
	@Size(max = 80, message = "O nome deve ter no máximo 80 caracteres!")
	@Email(message = "E-mail inválido!")
	private String email;

	@NotNull 
	@NotEmpty(message = "O campo <senha> é obrigatório!")
	@Size(min = 8, max = 8, message = "A senha deve ter exatamente 8 caracteres!")
	private String pwd;
	
	@NotNull 
	@NotEmpty(message = "O campo <nome completo> é obrigatório!")
	@Size(max = 40, message = "O nome deve ter no máximo 40 caracteres!")
	private String nomeCompleto;

	@NotNull 
	@NotEmpty(message = "O campo <endereço> é obrigatório!")
	@Size(max = 80, message = "O nome deve ter no máximo 80 caracteres!")
	private String endereco;

	@NotNull(message = "O campo <data de nascimento> é obrigatório!")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataNascimento;
	
//	private List<Marca> marcas;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPwd() {
		return pwd;
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

//	@OneToMany(mappedBy = "usuario")
//	public List<Marca> getMarcas() {
//		return marcas;
//	}
//
//	public void setMarcas(List<Marca> marcas) {
//		this.marcas = marcas;
//	}
}
