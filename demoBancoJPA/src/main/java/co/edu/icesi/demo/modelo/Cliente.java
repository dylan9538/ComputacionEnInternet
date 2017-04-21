package co.edu.icesi.demo.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the clientes database table.
 * 
 */
@Entity
@Table(name="clientes")
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cli_id")
	private long cliId;

	@Column(name="cli_direccion")
	private String cliDireccion;

	@Column(name="cli_mail")
	private String cliMail;

	@Column(name="cli_nombre")
	private String cliNombre;

	@Column(name="cli_telefono")
	private String cliTelefono;

	//bi-directional many-to-one association to TiposDocumento
	@ManyToOne
	@JoinColumn(name="tdoc_codigo")
	private TiposDocumento tiposDocumento;

	//bi-directional many-to-one association to Cuenta
	@OneToMany(mappedBy="cliente")
	private List<Cuenta> cuentas;

	public Cliente() {
	}

	public long getCliId() {
		return this.cliId;
	}

	public void setCliId(long cliId) {
		this.cliId = cliId;
	}

	public String getCliDireccion() {
		return this.cliDireccion;
	}

	public void setCliDireccion(String cliDireccion) {
		this.cliDireccion = cliDireccion;
	}

	public String getCliMail() {
		return this.cliMail;
	}

	public void setCliMail(String cliMail) {
		this.cliMail = cliMail;
	}

	public String getCliNombre() {
		return this.cliNombre;
	}

	public void setCliNombre(String cliNombre) {
		this.cliNombre = cliNombre;
	}

	public String getCliTelefono() {
		return this.cliTelefono;
	}

	public void setCliTelefono(String cliTelefono) {
		this.cliTelefono = cliTelefono;
	}

	public TiposDocumento getTiposDocumento() {
		return this.tiposDocumento;
	}

	public void setTiposDocumento(TiposDocumento tiposDocumento) {
		this.tiposDocumento = tiposDocumento;
	}

	public List<Cuenta> getCuentas() {
		return this.cuentas;
	}

	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

	public Cuenta addCuenta(Cuenta cuenta) {
		getCuentas().add(cuenta);
		cuenta.setCliente(this);

		return cuenta;
	}

	public Cuenta removeCuenta(Cuenta cuenta) {
		getCuentas().remove(cuenta);
		cuenta.setCliente(null);

		return cuenta;
	}

}