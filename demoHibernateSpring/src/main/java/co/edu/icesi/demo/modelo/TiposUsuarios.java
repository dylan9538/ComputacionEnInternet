package co.edu.icesi.demo.modelo;
// Generated 29-ene-2016 16:33:55 by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

/**
 * TiposUsuarios generated by hbm2java
 */
public class TiposUsuarios implements java.io.Serializable {

	private long tusuCodigo;
	private String tusuNombre;
	private Set usuarioses = new HashSet(0);

	public TiposUsuarios() {
	}

	public TiposUsuarios(long tusuCodigo, String tusuNombre) {
		this.tusuCodigo = tusuCodigo;
		this.tusuNombre = tusuNombre;
	}

	public TiposUsuarios(long tusuCodigo, String tusuNombre, Set usuarioses) {
		this.tusuCodigo = tusuCodigo;
		this.tusuNombre = tusuNombre;
		this.usuarioses = usuarioses;
	}

	public long getTusuCodigo() {
		return this.tusuCodigo;
	}

	public void setTusuCodigo(long tusuCodigo) {
		this.tusuCodigo = tusuCodigo;
	}

	public String getTusuNombre() {
		return this.tusuNombre;
	}

	public void setTusuNombre(String tusuNombre) {
		this.tusuNombre = tusuNombre;
	}

	public Set getUsuarioses() {
		return this.usuarioses;
	}

	public void setUsuarioses(Set usuarioses) {
		this.usuarioses = usuarioses;
	}

}
