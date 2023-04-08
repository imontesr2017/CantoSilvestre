package Aplicacion.Clases;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Pajaro {
	
	@Id
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Pajaro [id=" + id + "]";
	}

	public Pajaro(long id) {
		super();
		this.id = id;
	}

	public Pajaro() {
		super();
	}
	
	

}
