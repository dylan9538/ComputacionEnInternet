package co.edu.icesi.demo.modelo;
// Generated 29-ene-2016 16:33:55 by Hibernate Tools 4.0.0

import java.math.BigDecimal;
import java.util.Date;

/**
 * Retiros generated by hbm2java
 */
public class Retiros implements java.io.Serializable {

	private RetirosId id;
	private Usuarios usuarios;
	private Cuentas cuentas;
	private BigDecimal retValor;
	private Date retFecha;
	private String retDescripcion;

	public Retiros() {
	}

	public Retiros(RetirosId id, Cuentas cuentas, BigDecimal retValor, Date retFecha, String retDescripcion) {
		this.id = id;
		this.cuentas = cuentas;
		this.retValor = retValor;
		this.retFecha = retFecha;
		this.retDescripcion = retDescripcion;
	}

	public Retiros(RetirosId id, Usuarios usuarios, Cuentas cuentas, BigDecimal retValor, Date retFecha,
			String retDescripcion) {
		this.id = id;
		this.usuarios = usuarios;
		this.cuentas = cuentas;
		this.retValor = retValor;
		this.retFecha = retFecha;
		this.retDescripcion = retDescripcion;
	}

	public RetirosId getId() {
		return this.id;
	}

	public void setId(RetirosId id) {
		this.id = id;
	}

	public Usuarios getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

	public Cuentas getCuentas() {
		return this.cuentas;
	}

	public void setCuentas(Cuentas cuentas) {
		this.cuentas = cuentas;
	}

	public BigDecimal getRetValor() {
		return this.retValor;
	}

	public void setRetValor(BigDecimal retValor) {
		this.retValor = retValor;
	}

	public Date getRetFecha() {
		return this.retFecha;
	}

	public void setRetFecha(Date retFecha) {
		this.retFecha = retFecha;
	}

	public String getRetDescripcion() {
		return this.retDescripcion;
	}

	public void setRetDescripcion(String retDescripcion) {
		this.retDescripcion = retDescripcion;
	}

}
