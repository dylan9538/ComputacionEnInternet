package co.edu.icesi.demo.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipos_usuarios database table.
 * 
 */
@Entity
@Table(name="tipos_usuarios")
@NamedQuery(name="TiposUsuario.findAll", query="SELECT t FROM TiposUsuario t")
public class TiposUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="tusu_codigo")
	private long tusuCodigo;

	@Column(name="tusu_nombre")
	private String tusuNombre;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="tiposUsuario")
	private List<Usuario> usuarios;

	public TiposUsuario() {
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

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setTiposUsuario(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setTiposUsuario(null);

		return usuario;
	}

}