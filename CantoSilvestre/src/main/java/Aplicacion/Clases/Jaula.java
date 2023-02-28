package Aplicacion.Clases;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Jaula {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String nombre;
	
	@OneToMany(mappedBy="jaula", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Pajaro> pajaros = new LinkedList<Pajaro>();

	public Jaula() {
		
	}
	
	public Jaula(String nombre) {
		super();
		this.nombre = nombre;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Pajaro> getPajaros() {
		return pajaros;
	}

	public void setPajaros(List<Pajaro> pajaros) {
		this.pajaros = pajaros;
	}

	@Override
	public String toString() {
		return "Jaula [id=" + id + ", nombre=" + nombre + ", pajaros=" + pajaros + "]";
	}
	
	
	
}
