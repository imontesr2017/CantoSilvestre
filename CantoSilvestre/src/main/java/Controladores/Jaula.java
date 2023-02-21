package Controladores;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Jaula {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
}
