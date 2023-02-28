package Aplicacion.Controladores;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import Aplicacion.Clases.Hilo;
import Aplicacion.Clases.Jaula;
import Aplicacion.Clases.Mensaje;
import Aplicacion.Clases.Pajaro;
import Aplicacion.Clases.Usuario;
import Aplicacion.Repositorios.UsuarioRepository;
import Aplicacion.Service.UsuarioService;

@Controller
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;
	
	
	@PostConstruct
	public void init() {
		Usuario pepe = new Usuario(1, "Pepe", "1234");
		Usuario marta = new Usuario(2, "Marta", "1111");
		Usuario sara = new Usuario(3, "Sara", "pajaro");
		
		Jaula jaulaCrianza = new Jaula("Crianza");
		Jaula jaulaAdultos = new Jaula("Competicion");
		Jaula jaulaMama = new Jaula("Para mama");
		
		Pajaro pajaro1 = new Pajaro(1, "Jilguero", jaulaCrianza);
		Pajaro pajaro2 = new Pajaro(2, "Canario", null);
		Pajaro pajaro3 = new Pajaro(3, "Jilguero", jaulaAdultos, "Preparado para competición");
		Pajaro pajaro4 = new Pajaro(4, "Jilguero", jaulaCrianza, "Para cria");
		
		Hilo hilo1 = new Hilo("Cambio jilguero por canario", "Hola tengo 3 jilgueros para cria y me gustaria 3 canarios para cria");
		Hilo hilo2 = new Hilo("Como alimento a mis pajaros?", "¿no sé que pienso deberia dar a mis pajaros?");
		Hilo hilo3 = new Hilo("Vendo jaulas", "");
		
		Mensaje mensaje1 = new Mensaje("pepe","solo me quedan dos");
		Mensaje mensaje2 = new Mensaje("marta","Hola estoy interesada en el cambio");
		
		hilo1.getListaMensajes().add(mensaje1);
		hilo1.getListaMensajes().add(mensaje2);
		
		pepe.getMensajes().add(mensaje1);
		marta.getMensajes().add(mensaje2);
		
		jaulaCrianza.getPajaros().add(pajaro4);
		jaulaCrianza.getPajaros().add(pajaro1);
		jaulaAdultos.getPajaros().add(pajaro3);
		
		pepe.getPajaros().add(pajaro4);
		pepe.getPajaros().add(pajaro1);
		marta.getPajaros().add(pajaro3);
		marta.getPajaros().add(pajaro2);
		
		pepe.getHilos().add(hilo1);
		pepe.getHilos().add(hilo2);
		marta.getHilos().add(hilo3);
		
		pepe.getJaulas().add(jaulaCrianza);
		marta.getJaulas().add(jaulaAdultos);
		marta.getJaulas().add(jaulaMama);
		
		usuarioService.save(pepe);
		usuarioService.save(marta);
		usuarioService.save(sara);
		
	}
	
	@GetMapping("/mostrarUsuarios")
	public String mostrarUsuarios(Model model) {

		model.addAttribute("usuarios", usuarioService.findAll());

		return "usuarios";
	}
	
	@GetMapping("/usuario/{id}")
	public String mostrarUsuario(Model model, @PathVariable long id) {

		Optional<Usuario> usuario = usuarioService.findById(id);
		if (usuario.isPresent()) {
			model.addAttribute("usuario", usuario.get());
			return "usuario";
		} else {
			return "usuarios";
		}

	}
	
	@GetMapping("/nuevoUsuario")
	public String nuevoUsuario(Model model) {
		return "nuevoUsuario";
	}
	
	@RequestMapping("/guardarUsuario")
	public String guardarUsuario(Model model, @RequestParam String id, @RequestParam String nombre, @RequestParam String pass) {
		Usuario usuario = new Usuario(Long.parseLong(id), nombre, pass);
		usuarioService.save(usuario);
		return "/mostrarUsuarios";
	}
	
	@GetMapping("/usuario/nuevoHilo")
	public String nuevoHilo(Model model) {
		
		return "nuevoHilo";
	}
	
	@RequestMapping("/usuario/guardarHilo")
	public String guardarHilo(Model model, @RequestParam String id, @RequestParam String titulo, @RequestParam String descripcion) {
		Hilo hilo = new Hilo(titulo, descripcion);
		usuarioService.findById(Long.parseLong(id)).get().getHilos().add(hilo);
		
		return "redirect:/hilos/"+hilo.getId();
	}
	
}
