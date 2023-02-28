package Aplicacion.Clases;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Pajaro {
	@Id
	private long id;
	
	private String especie;
	private String apuntes;
	
	@ManyToOne
	@JsonIgnore
	private Jaula jaula;

	protected Pajaro() {
		
	}

	public Pajaro(long id, String especie, Jaula jaula) {
		this.jaula = jaula;
		this.especie = especie;
		this.id = id;
	}

	public Pajaro(long id, String especie, Jaula jaula, String apuntes) {
		this.jaula = jaula;
		this.especie = especie;
		this.apuntes = apuntes;
		this.id = id;
	}

	
	
	public String getApuntes() {
		return apuntes;
	}

	public void setApuntes(String apuntes) {
		this.apuntes = apuntes;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public Jaula getJaula() {
		return jaula;
	}

	public void setJaula(Jaula jaula) {
		this.jaula = jaula;
	}

	@Override
	public String toString() {
		return "Pajaro [id=" + id + ", especie=" + especie + ", apuntes=" + apuntes + "]";
	}
	
	

}
