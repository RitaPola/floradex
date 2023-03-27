package com.floradex.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;


@Entity
@Table (name = "utenti")
public class Utente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "id") 
	private Integer idUtente;

	@Column (name = "nome" , length = 256 , nullable = true)
	private String nome;

	@Column (name = "cognome" , length = 256 , nullable = true)
	private String cognome;

	@Column (name = "email" , length = 256 , unique = true, nullable = false)
	private String email;

	@Column (name = "password" , length = 256 , nullable = false)
	private String password;

	@ManyToMany(mappedBy = "utenti")
	@JsonIgnoreProperties("utenti")
	private List<Pianta> piante = new ArrayList<>();

	
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinTable(name = "utenti_ruoli",
	joinColumns = { 
			@JoinColumn(name = "id_utente", 	
					referencedColumnName = "id")
	}, 
	inverseJoinColumns = { 
			@JoinColumn(name = "id_ruolo",
					referencedColumnName = "id")
	})
	@JsonIgnoreProperties("utenti")
	private Set<Ruolo> ruoli = new HashSet<Ruolo>();

	public Utente() {

	}

	public Integer getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(Integer idUtente) {
		this.idUtente = idUtente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	public Set<Ruolo> getRuoli() {
		return ruoli;
	}

	public void setRuoli(Set<Ruolo> ruoli) {
		this.ruoli = ruoli;
	}

	public List<Pianta> getPiante() {
		return piante;
	}

	public void setPiante(List<Pianta> piante) {
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
	public boolean addRuolo(Ruolo ruolo) {
		boolean added = true;
		try {
			ruoli.add(ruolo);
		} catch (Exception e) {
			added = false;
		}
		return added;
	}

}
