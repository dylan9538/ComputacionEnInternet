package co.edu.icesi.demo.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the cuentas database table.
 * 
 */
@Entity
@Table(name="cuentas")
@NamedQuery(name="Cuenta.findAll", query="SELECT c FROM Cuenta c")
public class Cuenta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cue_numero")
	private String cueNumero;

	@Column(name="cue_activa")
	private String cueActiva;

	@Column(name="cue_clave")
	private String cueClave;

	@Column(name="cue_saldo")
	private BigDecimal cueSaldo;

	//bi-directional many-to-one association to Consignacione
	@OneToMany(mappedBy="cuenta")
	private List<Consignacione> consignaciones;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="cli_id")
	private Cliente cliente;

	//bi-directional many-to-one association to Retiro
	@OneToMany(mappedBy="cuenta")
	private List<Retiro> retiros;

	public Cuenta() {
	}

	public String getCueNumero() {
		return this.cueNumero;
	}

	public void setCueNumero(String cueNumero) {
		this.cueNumero = cueNumero;
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

	public BigDecimal getCueSaldo() {
		return this.cueSaldo;
	}

	public void setCueSaldo(BigDecimal cueSaldo) {
		this.cueSaldo = cueSaldo;
	}

	public List<Consignacione> getConsignaciones() {
		return this.consignaciones;
	}

	public void setConsignaciones(List<Consignacione> consignaciones) {
		this.consignaciones = consignaciones;
	}

	public Consignacione addConsignacione(Consignacione consignacione) {
		getConsignaciones().add(consignacione);
		consignacione.setCuenta(this);

		return consignacione;
	}

	public Consignacione removeConsignacione(Consignacione consignacione) {
		getConsignaciones().remove(consignacione);
		consignacione.setCuenta(null);

		return consignacione;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Retiro> getRetiros() {
		return this.retiros;
	}

	public void setRetiros(List<Retiro> retiros) {
		this.retiros = retiros;
	}

	public Retiro addRetiro(Retiro retiro) {
		getRetiros().add(retiro);
		retiro.setCuenta(this);

		return retiro;
	}

	public Retiro removeRetiro(Retiro retiro) {
		getRetiros().remove(retiro);
		retiro.setCuenta(null);

		return retiro;
	}

}