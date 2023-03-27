package com.floradex.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table (name = "ruoli")
public class Ruolo implements Serializable {
	private static final long serialVersionUID = 252274246683751522L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "id") 
	private Integer idRuolo;

	@Column (name = "nome" , length = 256 ,unique = true, nullable = false)
	private String nome;
	
	@Column (name = "tipo" , length = 256 , nullable = false)
	private String tipo;

	@ManyToMany(mappedBy ="ruoli")
	@JsonIgnoreProperties("ruoli")
	private Set<Utente> utentir = new HashSet<Utente>();


	public Ruolo() {

	}


	public Integer getIdRuolo() {
		return idRuolo;
	}


	public void setIdRuolo(Integer idRuolo) {
		this.idRuolo = idRuolo;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public Set<Utente> getUtenti() {
		return utentir;
	}


	public void setUtenti(Set<Utente> utenti) {
		this.utentir = utenti;
	}


	public boolean addUtente(Utente utente) {
		boolean added = true;
		try {
			utentir.add(utente);
		} catch (Exception e) {
			added = false;
		}
		return added;
	}
	
	public boolean isRuolo() {
		return tipo.equals("ruolo");
		}
	
}




