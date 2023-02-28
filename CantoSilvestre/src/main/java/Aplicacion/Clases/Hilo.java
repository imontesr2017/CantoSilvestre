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
public class Hilo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String titulo;
	
	private String descripcion;
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Mensaje> listaMensajes = new LinkedList<Mensaje>();
	

	protected Hilo() {}
	
	public Hilo(String titulo, String descripcion) {
		this.titulo = titulo;
		this.descripcion = descripcion;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<Mensaje> getListaMensajes() {
		return listaMensajes;
	}

	public void setListaMensajes(List<Mensaje> listaMensajes) {
		this.listaMensajes = listaMensajes;
	}

	@Override
	public String toString() {
		return "Hilo [titulo=" + titulo + ", listaMensajes=" + listaMensajes + "]";
	}
	
	

}
