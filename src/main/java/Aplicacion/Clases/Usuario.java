package Aplicacion.Clases;

import java.util.LinkedList;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Entity(name = "UserTable")
public class Usuario {
	
	@Id
	private long id;
	
	private String name;
	
	private String password;
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Mensaje> mensajes = new LinkedList<Mensaje>();
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Pajaro> pajaros = new LinkedList<Pajaro>();
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Jaula> jaulas = new LinkedList<Jaula>();
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Hilo> hilos = new LinkedList<Hilo>();
	
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;
	
	protected Usuario() {
	}
	
	public Usuario(long id, String nombre, String contraseña, String... roles) {
		this.id = id;
		this.name = nombre;
		this.password = contraseña;
		this.roles = List.of(roles);
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String nombre) {
		this.name = nombre;
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

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + name + ", mensajes=" + mensajes + ", pajaros=" + pajaros
				+ ", jaulas=" + jaulas + ", hilos=" + hilos + "]";
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
	
}
