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
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Pajaro> pajaros = new LinkedList<Pajaro>();
	
	protected Usuario() {
	}
	
	public Usuario(long id, List<Pajaro> pajaros) {
		this.id = id;
		this.pajaros = pajaros;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Pajaro> getPajaros() {
		return pajaros;
	}

	public void setPajaros(List<Pajaro> pajaros) {
		this.pajaros = pajaros;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", pajaros=" + pajaros + "]";
	}

	
	
	
}
