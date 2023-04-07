package Aplicacion.Controladores;

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

import Aplicacion.Clases.Usuario;
import Aplicacion.Service.UsuarioService;

@RestController
public class UsuarioController {

	private final int PAJAROS_MAX=50;

	//Servicios de acceso a la base de datos
	@Autowired
	private UsuarioService usuarioService;
	
	
	@PostConstruct
	public void init() {
		Usuario pepe = new Usuario(1, PAJAROS_MAX);
		Usuario marta = new Usuario(2, PAJAROS_MAX);
		Usuario sara = new Usuario(3, PAJAROS_MAX);
		Usuario admin = new Usuario(0, PAJAROS_MAX);
		
		
		usuarioService.save(pepe);
		usuarioService.save(marta);
		usuarioService.save(sara);
		usuarioService.save(admin);
		
	}
	
	@GetMapping("/usuarios/{id}")
	public Usuario mostrarUsuario(Model model, @PathVariable String id) {

		Usuario u = usuarioService.findById(Long.parseLong(id)).get();
		return u;

	}
	
}
