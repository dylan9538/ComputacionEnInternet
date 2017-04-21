package co.edu.icesi.demo.modelo;
// Generated 29-ene-2016 16:33:55 by Hibernate Tools 4.0.0

import java.math.BigDecimal;
import java.util.Date;

/**
 * Consignaciones generated by hbm2java
 */
public class Consignaciones implements java.io.Serializable {

	private ConsignacionesId id;
	private Usuarios usuarios;
	private Cuentas cuentas;
	private BigDecimal conValor;
	private Date conFecha;
	private String conDescripcion;

	public Consignaciones() {
	}

	public Consignaciones(ConsignacionesId id, Cuentas cuentas, BigDecimal conValor, Date conFecha,
			String conDescripcion) {
		this.id = id;
		this.cuentas = cuentas;
		this.conValor = conValor;
		this.conFecha = conFecha;
		this.conDescripcion = conDescripcion;
	}

	public Consignaciones(ConsignacionesId id, Usuarios usuarios, Cuentas cuentas, BigDecimal conValor, Date conFecha,
			String conDescripcion) {
		this.id = id;
		this.usuarios = usuarios;
		this.cuentas = cuentas;
		this.conValor = conValor;
		this.conFecha = conFecha;
		this.conDescripcion = conDescripcion;
	}

	public ConsignacionesId getId() {
		return this.id;
	}

	public void setId(ConsignacionesId id) {
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

	public BigDecimal getConValor() {
		return this.conValor;
	}

	public void setConValor(BigDecimal conValor) {
		this.conValor = conValor;
	}

	public Date getConFecha() {
		return this.conFecha;
	}

	public void setConFecha(Date conFecha) {
		this.conFecha = conFecha;
	}

	public String getConDescripcion() {
		return this.conDescripcion;
	}

	public void setConDescripcion(String conDescripcion) {
		this.conDescripcion = conDescripcion;
	}

}
