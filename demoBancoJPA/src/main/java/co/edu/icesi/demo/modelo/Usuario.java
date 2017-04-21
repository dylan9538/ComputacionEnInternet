package co.edu.icesi.demo.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the usuarios database table.
 * 
 */
@Entity
@Table(name="usuarios")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="usu_cedula")
	private long usuCedula;

	@Column(name="usu_clave")
	private String usuClave;

	@Column(name="usu_login")
	private String usuLogin;

	@Column(name="usu_nombre")
	private String usuNombre;

	//bi-directional many-to-one association to Consignacione
	@OneToMany(mappedBy="usuario")
	private List<Consignacione> consignaciones;

	//bi-directional many-to-one association to Retiro
	@OneToMany(mappedBy="usuario")
	private List<Retiro> retiros;

	//bi-directional many-to-one association to TiposUsuario
	@ManyToOne
	@JoinColumn(name="tusu_codigo")
	private TiposUsuario tiposUsuario;

	public Usuario() {
	}

	public long getUsuCedula() {
		return this.usuCedula;
	}

	public void setUsuCedula(long usuCedula) {
		this.usuCedula = usuCedula;
	}

	public String getUsuClave() {
		return this.usuClave;
	}

	public void setUsuClave(String usuClave) {
		this.usuClave = usuClave;
	}

	public String getUsuLogin() {
		return this.usuLogin;
	}

	public void setUsuLogin(String usuLogin) {
		this.usuLogin = usuLogin;
	}

	public String getUsuNombre() {
		return this.usuNombre;
	}

	public void setUsuNombre(String usuNombre) {
		this.usuNombre = usuNombre;
	}

	public List<Consignacione> getConsignaciones() {
		return this.consignaciones;
	}

	public void setConsignaciones(List<Consignacione> consignaciones) {
		this.consignaciones = consignaciones;
	}

	public Consignacione addConsignacione(Consignacione consignacione) {
		getConsignaciones().add(consignacione);
		consignacione.setUsuario(this);

		return consignacione;
	}

	public Consignacione removeConsignacione(Consignacione consignacione) {
		getConsignaciones().remove(consignacione);
		consignacione.setUsuario(null);

		return consignacione;
	}

	public List<Retiro> getRetiros() {
		return this.retiros;
	}

	public void setRetiros(List<Retiro> retiros) {
		this.retiros = retiros;
	}

	public Retiro addRetiro(Retiro retiro) {
		getRetiros().add(retiro);
		retiro.setUsuario(this);

		return retiro;
	}

	public Retiro removeRetiro(Retiro retiro) {
		getRetiros().remove(retiro);
		retiro.setUsuario(null);

		return retiro;
	}

	public TiposUsuario getTiposUsuario() {
		return this.tiposUsuario;
	}

	public void setTiposUsuario(TiposUsuario tiposUsuario) {
		this.tiposUsuario = tiposUsuario;
	}

}