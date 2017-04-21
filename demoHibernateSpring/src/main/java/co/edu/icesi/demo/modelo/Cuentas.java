package co.edu.icesi.demo.modelo;
// Generated 29-ene-2016 16:33:55 by Hibernate Tools 4.0.0

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Cuentas generated by hbm2java
 */
public class Cuentas implements java.io.Serializable {

	private String cueNumero;
	private Clientes clientes;
	private BigDecimal cueSaldo;
	private String cueActiva;
	private String cueClave;
	private Set consignacioneses = new HashSet(0);
	private Set retiroses = new HashSet(0);

	public Cuentas() {
	}

	public Cuentas(String cueNumero, Clientes clientes, BigDecimal cueSaldo, String cueActiva, String cueClave) {
		this.cueNumero = cueNumero;
		this.clientes = clientes;
		this.cueSaldo = cueSaldo;
		this.cueActiva = cueActiva;
		this.cueClave = cueClave;
	}

	public Cuentas(String cueNumero, Clientes clientes, BigDecimal cueSaldo, String cueActiva, String cueClave,
			Set consignacioneses, Set retiroses) {
		this.cueNumero = cueNumero;
		this.clientes = clientes;
		this.cueSaldo = cueSaldo;
		this.cueActiva = cueActiva;
		this.cueClave = cueClave;
		this.consignacioneses = consignacioneses;
		this.retiroses = retiroses;
	}

	public String getCueNumero() {
		return this.cueNumero;
	}

	public void setCueNumero(String cueNumero) {
		this.cueNumero = cueNumero;
	}

	public Clientes getClientes() {
		return this.clientes;
	}

	public void setClientes(Clientes clientes) {
		this.clientes = clientes;
	}

	public BigDecimal getCueSaldo() {
		return this.cueSaldo;
	}

	public void setCueSaldo(BigDecimal cueSaldo) {
		this.cueSaldo = cueSaldo;
	}

	public String getCueActiva() {
		return this.cueActiva;
	}

	public void setCueActiva(String cueActiva) {
		this.cueActiva = cueActiva;
	}

	public String getCueClave() {
		return this.cueClave;
	}

	public void setCueClave(String cueClave) {
		this.cueClave = cueClave;
	}

	public Set getConsignacioneses() {
		return this.consignacioneses;
	}

	public void setConsignacioneses(Set consignacioneses) {
		this.consignacioneses = consignacioneses;
	}

	public Set getRetiroses() {
		return this.retiroses;
	}

	public void setRetiroses(Set retiroses) {
		this.retiroses = retiroses;
	}

}