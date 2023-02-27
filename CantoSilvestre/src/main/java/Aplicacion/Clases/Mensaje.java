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

	protected Mensaje() {}
	
	public Mensaje(String texto) {
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
		return "Mensaje [texto=" + texto + "]";
	}

	public void setId(long id) {
		this.id = id;
	}

	
	
	
}
