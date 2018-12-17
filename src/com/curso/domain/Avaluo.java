package com.curso.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="Avaluo")
public class Avaluo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAval;
	@Column(name="lugarAval")
	private String lugarAval;
	
	@OneToOne
	@JoinColumn(name = "Tramite_idTram")
	private Tramite tramite;

	public Avaluo() {

	}
	
	public Avaluo(String lugarAval) {
		this.lugarAval =  lugarAval;
	}


	public int getIdVal() {
		return idAval;
	}

	public void setIdVal(int idVal) {
		this.idAval = idVal;
	}

	public String getLugarAval() {
		return lugarAval;
	}

	public void setLugarAval(String lugarAval) {
		this.lugarAval = lugarAval;
	}

	public Tramite getTramite() {
		return tramite;
	}

	public void setTramite(Tramite tramite) {
		this.tramite = tramite;
	}

	@Override
	public String toString() {
		return "Avaluo [idVal=" + idAval + ", lugarAval=" + lugarAval + ", tramite=" + tramite + "]";
	}
	

}
