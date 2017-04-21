package co.edu.icesi.demo.modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the consignaciones database table.
 * 
 */
@Embeddable
public class ConsignacionePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="con_codigo")
	private long conCodigo;

	@Column(name="cue_numero", insertable=false, updatable=false)
	private String cueNumero;

	public ConsignacionePK() {
	}
	public long getConCodigo() {
		return this.conCodigo;
	}
	public void setConCodigo(long conCodigo) {
		this.conCodigo = conCodigo;
	}
	public String getCueNumero() {
		return this.cueNumero;
	}
	public void setCueNumero(String cueNumero) {
		this.cueNumero = cueNumero;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ConsignacionePK)) {
			return false;
		}
		ConsignacionePK castOther = (ConsignacionePK)other;
		return 
			(this.conCodigo == castOther.conCodigo)
			&& this.cueNumero.equals(castOther.cueNumero);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.conCodigo ^ (this.conCodigo >>> 32)));
		hash = hash * prime + this.cueNumero.hashCode();
		
		return hash;
	}
}