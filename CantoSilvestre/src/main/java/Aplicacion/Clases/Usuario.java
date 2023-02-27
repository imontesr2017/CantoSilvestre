package Aplicacion.Clases;

import java.util.LinkedList;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Usuario {
	
	@Id
	private long id;
	
	private String nombre;
	private String contraseña;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Mensaje> mensajes = new LinkedList<Mensaje>();
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Pajaro> pajaros = new LinkedList<Pajaro>();
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Jaula> jaulas = new LinkedList<Jaula>();
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Hilo> hilos = new LinkedList<Hilo>();
	
	
	
	protected Usuario() {
	}
	
	public Usuario(long id, String nombre, String contraseña) {
		this.id = id;
		this.nombre = nombre;
		this.contraseña = contraseña;
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



	public String getContraseña() {
		return contraseña;
	}



	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}





	public List<Jaula> getJaulas() {
		return jaulas;
	}



	public void setJaulas(List<Jaula> jaulas) {
		this.jaulas = jaulas;
	}



	public List<Hilo> getHilos() {
		return hilos;
	}



	public void setHilos(List<Hilo> hilos) {
		this.hilos = hilos;
	}

	public List<Mensaje> getMensajes() {
		return mensajes;
	}

	public void setMensajes(List<Mensaje> mensajes) {
		this.mensajes = mensajes;
	}

	public List<Pajaro> getPajaros() {
		return pajaros;
	}

	public void setPajaros(List<Pajaro> pajaros) {
		this.pajaros = pajaros;
	}
}
