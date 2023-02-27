package Aplicacion.Clases;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.OneToMany;

public class Foro {
	
	
	@OneToMany
	private List<Hilo> hilos = new LinkedList<Hilo>();
}
