package co.edu.icesi.demo.modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the retiros database table.
 * 
 */
@Embeddable
public class RetiroPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ret_codigo")
	private long retCodigo;

	@Column(name="cue_numero", insertable=false, updatable=false)
	private String cueNumero;

	public RetiroPK() {
	}
	public long getRetCodigo() {
		return this.retCodigo;
	}
	public void setRetCodigo(long retCodigo) {
		this.retCodigo = retCodigo;
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
		if (!(other instanceof RetiroPK)) {
			return false;
		}
		RetiroPK castOther = (RetiroPK)other;
		return 
			(this.retCodigo == castOther.retCodigo)
			&& this.cueNumero.equals(castOther.cueNumero);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.retCodigo ^ (this.retCodigo >>> 32)));
		hash = hash * prime + this.cueNumero.hashCode();
		
		return hash;
	}
}