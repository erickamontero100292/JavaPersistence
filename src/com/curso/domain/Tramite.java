package com.curso.domain;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="Tramite")
public class Tramite {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idTram;
	@Column(name="tipoTram")
	private String tipoTram;
	@Column(name="fhcTram")
	private Timestamp fhcTram;
	@OneToOne(mappedBy = "tramite",cascade =  CascadeType.REMOVE)// Esta relacion hace que sea biderecional
	private Avaluo avaluo;
	
	@OneToMany(mappedBy="tramite")
	private Set<DiarioCliente> diarioClienteSet;
	
	public Tramite() {}
	
	
	public Tramite(String tipoTram, Timestamp fhcTram) {
		this.tipoTram = tipoTram;
		this.fhcTram = fhcTram;
	}


	public int getIdTram() {
		return idTram;
	}
	public void setIdTram(int idTram) {
		this.idTram = idTram;
	}
	public String getTipoTram() {
		return tipoTram;
	}
	public void setTipoTram(String tipoTram) {
		this.tipoTram = tipoTram;
	}
	public Timestamp getFhcTram() {
		return fhcTram;
	}
	public void setFhcTram(Timestamp fhcTram) {
		this.fhcTram = fhcTram;
	}
	
	


	public Avaluo getAvaluo() {
		return avaluo;
	}


	public void setAvaluo(Avaluo avaluo) {
		this.avaluo = avaluo;
	}


	@Override
	public String toString() {
		return "Tramite [idTram=" + idTram + ", tipoTram=" + tipoTram + ", fhcTram=" + fhcTram + "]";
	}
	
	
	

}
