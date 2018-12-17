package com.curso.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name="DiarioCliente")
public class DiarioCliente {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idDiario;
	
	@Column(name="entradaDiario")
	private String entradaDiario;
	
	@Column(name="fhcDiario")
	private Timestamp fhcDiario;
	
	//Aqui esta la clave foranea
	@ManyToOne
	@JoinColumn (name="Tramite_idTram")
	private Tramite tramite;

	public int getIdDiario() {
		return idDiario;
	}

	public void setIdDiario(int idDiario) {
		this.idDiario = idDiario;
	}

	public String getEntradaDiario() {
		return entradaDiario;
	}

	public void setEntradaDiario(String entradaDiario) {
		this.entradaDiario = entradaDiario;
	}

	public Timestamp getFhcDiario() {
		return fhcDiario;
	}

	public void setFhcDiario(Timestamp fhcDiario) {
		this.fhcDiario = fhcDiario;
	}

	public Tramite getTramite() {
		return tramite;
	}

	public void setTramite(Tramite tramite) {
		this.tramite = tramite;
	}

	@Override
	public String toString() {
		return "DiarioCliente [idDiario=" + idDiario + ", entradaDiario=" + entradaDiario + ", fhcDiario=" + fhcDiario
				+ ", tramite=" + tramite + "]";
	}

	public DiarioCliente(String entradaDiario, Timestamp fhcDiario, Tramite tramite) {
		this.entradaDiario = entradaDiario;
		this.fhcDiario = fhcDiario;
		this.tramite = tramite;
	}

	public DiarioCliente(String entradaDiario, Timestamp fhcDiario) {

		this.entradaDiario = entradaDiario;
		this.fhcDiario = fhcDiario;
	}

	public DiarioCliente() {

	}
	
	
	
	

}
