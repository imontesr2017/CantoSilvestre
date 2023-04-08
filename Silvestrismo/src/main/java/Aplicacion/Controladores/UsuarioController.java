package Aplicacion.Controladores;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Aplicacion.Clases.Pajaro;
import Aplicacion.Clases.Usuario;
import Aplicacion.Service.UsuarioService;

@RestController
public class UsuarioController {

	private final int PAJAROS_MAX=10;

	//Servicios de acceso a la base de datos
	@Autowired
	private UsuarioService usuarioService;
	
	
	@PostConstruct
	public void init() {
		List<Pajaro> user0, user1, user2, user3, user4, user5, user6;
		user0 = new LinkedList();
		user1 = new LinkedList();
		user2 = new LinkedList();
		user3 = new LinkedList();
		user4 = new LinkedList();
		user5 = new LinkedList();
		user6 = new LinkedList();
		
		
		for(int i = 0; i<PAJAROS_MAX; i++) {
			user0.add(new Pajaro(0*PAJAROS_MAX+i));
			user1.add(new Pajaro(1*PAJAROS_MAX+i));
			user2.add(new Pajaro(2*PAJAROS_MAX+i));
			user3.add(new Pajaro(3*PAJAROS_MAX+i));
			user4.add(new Pajaro(4*PAJAROS_MAX+i));
			user5.add(new Pajaro(5*PAJAROS_MAX+i));
			user6.add(new Pajaro(6*PAJAROS_MAX+i));
		}
		
		Usuario pepe = new Usuario(1, user1);
		Usuario marta = new Usuario(2, user2);
		Usuario sara = new Usuario(3, user3);
		Usuario admin = new Usuario(0, user0);
		Usuario usuario4 = new Usuario(4, user4);
		Usuario usuario5 = new Usuario(5, user5);
		Usuario usuario6 = new Usuario(6, user6);
		
		usuarioService.save(pepe);
		usuarioService.save(marta);
		usuarioService.save(sara);
		usuarioService.save(admin);
		usuarioService.save(usuario4);
		usuarioService.save(usuario5);
		usuarioService.save(usuario6);
		
	}
	
	@GetMapping("/usuarios/{id}/pajaro/{etiqueta}")
	public Boolean PajaroCorrecto(Model model, @PathVariable String id, @PathVariable String etiqueta) {

		Usuario u = usuarioService.findById(Long.parseLong(id)).get();
		for (Pajaro p : u.getPajaros()) {
			if(p.getId()==Long.parseLong(etiqueta)) {
				return true;
			}
		}
		return false;
	}
	
	@GetMapping("/usuarios/{id}")
	public Boolean UsuarioCorrecto(Model model, @PathVariable String id) {

		return !usuarioService.findById(Long.parseLong(id)).isEmpty();
		
	}
}
