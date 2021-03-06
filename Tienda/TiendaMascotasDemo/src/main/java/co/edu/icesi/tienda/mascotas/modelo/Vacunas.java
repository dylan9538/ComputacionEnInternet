package co.edu.icesi.tienda.mascotas.modelo;
// Generated 08-feb-2016 16:38:21 by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

/**
 * Vacunas generated by hbm2java
 */
public class Vacunas implements java.io.Serializable {

	private long vacCodigo;
	private String vacNombre;
	private Set<VacunasMascotas> vacunasMascotases = new HashSet<VacunasMascotas>(0);

	public Vacunas() {
	}

	public Vacunas(long vacCodigo, String vacNombre) {
		this.vacCodigo = vacCodigo;
		this.vacNombre = vacNombre;
	}

	public Vacunas(long vacCodigo, String vacNombre, Set<VacunasMascotas> vacunasMascotases) {
		this.vacCodigo = vacCodigo;
		this.vacNombre = vacNombre;
		this.vacunasMascotases = vacunasMascotases;
	}

	public long getVacCodigo() {
		return this.vacCodigo;
	}

	public void setVacCodigo(long vacCodigo) {
		this.vacCodigo = vacCodigo;
	}

	public String getVacNombre() {
		return this.vacNombre;
	}

	public void setVacNombre(String vacNombre) {
		this.vacNombre = vacNombre;
	}

	public Set<VacunasMascotas> getVacunasMascotases() {
		return this.vacunasMascotases;
	}

	public void setVacunasMascotases(Set<VacunasMascotas> vacunasMascotases) {
		this.vacunasMascotases = vacunasMascotases;
	}

}
