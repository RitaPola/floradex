package com.floradex.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "piante")
public class Pianta implements Serializable {
	
	private static final long serialVersionUID = 1086299809753596578L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer idPianta;
	
	@Column(name = "nome", length = 256, nullable = false)
	private String nome;
	
	@Column(name = "descrizione", length = 500, nullable = false)
	private String descrizione;
	
	@Column(name = "fotopath", length = 256, nullable = false)
	private String fotopath;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn (name = "id_tipo", referencedColumnName = "id") 
	@JsonIgnoreProperties("piante")
	private Tipo tipo;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinTable(name = "piante_utenti",
		joinColumns = { 
			@JoinColumn(name = "id_pianta", 	
					referencedColumnName = "id")
		}, 
		inverseJoinColumns = { 
			@JoinColumn(name = "id_utente",
					referencedColumnName = "id")
		})
	@JsonIgnoreProperties("piante")
	private List<Utente> utenti = new ArrayList<>();
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinTable(name = "piante_zone",
		joinColumns = { 
			@JoinColumn(name = "id_pianta", 	
					referencedColumnName = "id")
		}, 
		inverseJoinColumns = { 
			@JoinColumn(name = "id_zona",
					referencedColumnName = "id")
		})
	@JsonIgnoreProperties("piante")
	private Set<Zona> zone = new HashSet<>();
	
	public Pianta() {
	}

	public Integer getIdPianta() {
		return idPianta;
	}

	public void setIdPianta(Integer idPianta) {
		this.idPianta = idPianta;
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

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public List<Utente> getUtenti() {
		return utenti;
	}

	public void setUtenti(List<Utente> utenti) {
		this.utenti = utenti;
	}

	public Set<Zona> getZone() {
		return zone;
	}

	public void setZone(Set<Zona> zone) {
		this.zone = zone;
	}

	public boolean addUtente(Utente utente) {
		boolean added = true;
		try {
			utenti.add(utente);
		} catch (Exception e) {
			added = false;
		}
		return added;
	}
	
	public boolean addZona(Zona zona) {
		boolean added = true;
		try {
			zone.add(zona);
		} catch (Exception e) {
			added = false;
		}
		return added;
	}	
}