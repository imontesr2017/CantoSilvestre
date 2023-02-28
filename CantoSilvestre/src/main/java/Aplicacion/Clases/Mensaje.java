package Aplicacion.Clases;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Mensaje {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String texto;
	
	private String usuario;

	protected Mensaje() {}
	
	public Mensaje(String usuario, String texto) {
		this.usuario = usuario;
		this.texto = texto;
	}

	public long getId() {
		return id;
	}


	public String getTexto() {
		return texto;
	}

	@Override
	public String toString() {
		return usuario+ ": "+texto;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	
	
	
}
