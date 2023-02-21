package Controladores;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pajaro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	public Pajaro() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	

}
