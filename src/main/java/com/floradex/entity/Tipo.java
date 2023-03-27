package com.floradex.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table (name = "tipi")
public class Tipo implements Serializable{
	
	private static final long serialVersionUID = -1294601171158375326L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "id") 
	private Integer idTipo;

	@Column (name = "nome" , length = 256 , nullable = false)
	private String nome;

	@Column (name = "descrizione" , length = 500 , nullable = false)
	private String descrizione;
	
	@Column (name = "fotopath" , length = 256 , nullable = false)
	private String fotopath;

	@OneToMany(mappedBy = "tipo", 
		       cascade = CascadeType.ALL, 
		       fetch = FetchType.LAZY)
	@JsonBackReference
	private Set<Pianta> piante = new HashSet<>();

	public Tipo() {
	}

	public Integer getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(Integer idTipo) {
		this.idTipo = idTipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public String getFotopath() {
		return fotopath;
	}

	public void setFotopath(String fotopath) {
		this.fotopath = fotopath;
	}

	public Set<Pianta> getPiante() {
		return piante;
	}

	public void setPiante(Set<Pianta> piante) {
		this.piante = piante;
	}

	public boolean addPianta(Pianta pianta) {
		boolean added = true;
		try {
			piante.add(pianta);
		} catch (Exception e) {
			added = false;
		}
		return added;
	}
	

}
