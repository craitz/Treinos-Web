package com.camilo.treinos.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Material {

	@Id
	@GeneratedValue
	private long id;
	
	@Size(max = 40, message = "O nome não pode conter mais de 40 caracteres!")
	@NotEmpty(message = "O campo <nome> é obrigatório!")
	private String nome;

	@NotNull(message = "O campo <data de vencimento> é obrigatório!")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataAquisicao;

//	private List<Marca> marcas;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataAquisicao() {
		return dataAquisicao;
	}

	public void setDataAquisicao(Date dataAquisicao) {
		this.dataAquisicao = dataAquisicao;
	}

//	@ManyToMany(mappedBy = "materiais")
//	public List<Marca> getMarcas() {
//		return marcas;
//	}
//
//	public void setMarcas(List<Marca> marcas) {
//		this.marcas = marcas;
//	}
}
