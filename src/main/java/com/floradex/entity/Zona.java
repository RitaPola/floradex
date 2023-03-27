package com.floradex.entity;

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
@Table(name = "zone")
public class Zona {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer idZona;
	
	@Column(name = "nome", length = 256, nullable = false)
	private String nome;
	
	@Column(name = "fotopath", length = 256, nullable = false)
	private String fotopath;
	
	@Column(name = "descrizione", length = 256, nullable = false)
	private String descrizione;
	
	//Attributo aggiunto per l'associazione MOLTI A MOLTI tra Pianta e Zona 
	@ManyToMany(mappedBy = "zone")
	@JsonIgnoreProperties("zone")
	private Set<Pianta> piante = new HashSet<>();
		
	public Zona() {
	}
	
	public Integer getIdZona() {
		return idZona;
	}

	public void setIdZona(Integer idZona) {
		this.idZona = idZona;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getFotopath() {
		return fotopath;
	}

	public void setFotopath(String fotopath) {
		this.fotopath = fotopath;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Set<Pianta> getPiante() {
		return piante;
	}

	public void setPiante(Set<Pianta> piante) {
		this.piante = piante;
	}

	//Metodo aggiunto per l'associazione MOLTI A MOLTI tra Pianta e Zona
	
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
