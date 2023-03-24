package Aplicacion.Controladores;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Aplicacion.Clases.Hilo;
import Aplicacion.Clases.Jaula;
import Aplicacion.Clases.Mensaje;
import Aplicacion.Clases.Pajaro;
import Aplicacion.Clases.Usuario;
import Aplicacion.Service.UsuarioService;

@Controller
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;
	
	@ModelAttribute
	public void addAttributes(Model model, HttpServletRequest request) {

		Principal principal = request.getUserPrincipal();

		if (principal != null) {

			model.addAttribute("logged", true);
			model.addAttribute("userName", principal.getName());
			model.addAttribute("admin", request.isUserInRole("ADMIN"));

		} else {
			model.addAttribute("logged", false);
		}
	}
	
	@PostConstruct
	public void init() {
		Usuario pepe = new Usuario(1, "Pepe", "1234", "USER");
		Usuario marta = new Usuario(2, "Marta", "1111", "USER");
		Usuario sara = new Usuario(3, "Sara", "pajaro", "USER");
		Usuario admin = new Usuario(0, "admin", "admin", "ADMIN");
		
		Jaula jaulaCrianza = new Jaula(pepe.getId(),"Crianza");
		Jaula jaulaAdultos = new Jaula(marta.getId(),"Competicion");
		Jaula jaulaMama = new Jaula(marta.getId(), "Para mama");
		
		Pajaro pajaro1 = new Pajaro(1, "Jilguero", jaulaCrianza);
		Pajaro pajaro2 = new Pajaro(2, "Canario", null);
		Pajaro pajaro3 = new Pajaro(3, "Jilguero", jaulaAdultos, "Preparado para competición");
		Pajaro pajaro4 = new Pajaro(4, "Jilguero", jaulaCrianza, "Para cria");
		
		Hilo hilo1 = new Hilo(pepe.getId(), "Cambio jilguero por canario", "Hola tengo 3 jilgueros para cria y me gustaria 3 canarios para cria");
		Hilo hilo2 = new Hilo(pepe.getId(), "Como alimento a mis pajaros?", "¿no sé que pienso deberia dar a mis pajaros?");
		Hilo hilo3 = new Hilo(marta.getId(), "Vendo jaulas", "");
		
		Mensaje mensaje1 = new Mensaje("solo me quedan dos");
		Mensaje mensaje2 = new Mensaje("Hola estoy interesada en el cambio");
		
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
		usuarioService.save(admin);
		
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
	
	@GetMapping("/borrarUsuario/{id}")
	public String borrarUsuario(Model model, @PathVariable long id) {

		Optional<Usuario> usuario = usuarioService.findById(id);
		if (usuario.isPresent()) {
			usuarioService.delete(id);
			model.addAttribute("usuario", usuario.get());
		}
		return "usuarioBorrado";
	}
	
	@GetMapping("/editarUsuario/{id}")
	public String editarUsuario(Model model, @PathVariable long id) {

		Optional<Usuario> usuario = usuarioService.findById(id);
		if (usuario.isPresent()) {
			usuarioService.delete(id);
			model.addAttribute("usuario", usuario.get());
		}
		return "editarUsuario";
	}
	
	@RequestMapping("/actualizarUsuario/{id}")
	public String actualizarUsuario(Model model, @PathVariable long id, @RequestParam String nombre, @RequestParam String pass) {
		Usuario usuario = usuarioService.findById(id).get();
		usuario.setNombre(nombre);
		usuario.setContraseña(pass);
		usuarioService.save(usuario);
		return "redirect:/usuario/"+usuario.getId();
	}
	
	
	
	@GetMapping("/usuario/{id}/nuevoHilo")
	public String nuevoHilo(Model model, @PathVariable long id) {
		model.addAttribute("id", id);
		return "nuevoHilo";
	}
	
	@RequestMapping("/usuario/{id}/guardarHilo")
	public String guardarHilo(Model model, @PathVariable long id, @RequestParam String titulo, @RequestParam String descripcion) {
		Usuario usuario = usuarioService.findById(id).get();
		Hilo hilo = new Hilo(id, titulo, descripcion);
		usuario.getHilos().add(hilo);
		usuarioService.save(usuario);
		
		return "redirect:/usuario/"+usuario.getId();
	}
	
	@GetMapping("/usuario/{idUsuario}/hilo/{idHilo}/nuevoMensaje")
	public String nuevoMensaje(Model model, @PathVariable long idUsuario, @PathVariable long idHilo) {
		model.addAttribute("idUsuario", idUsuario);
		model.addAttribute("idHilo", idHilo);
		return "nuevoMensaje";
	}
	
	@RequestMapping("/usuario/{idUsuario}/hilo/{idHilo}/guardarMensaje")
	public String guardarMensaje(Model model, @PathVariable long idUsuario, @PathVariable int idHilo, @RequestParam String texto) {
		Usuario usuario = usuarioService.findById(idUsuario).get();
		Hilo hilo = null;
		for(Hilo h : usuario.getHilos()) {
			if (h.getId()==idHilo) {
				hilo = h;
				break;
			}
			
		}
		
		Mensaje mensaje = new Mensaje(texto);
		hilo.getListaMensajes().add(mensaje);
		usuario.getMensajes().add(mensaje);
		usuarioService.save(usuario);
		
		return "redirect:/usuario/"+usuario.getId();
	}
	
	@GetMapping("/usuario/{id}/nuevaJaula")
	public String nuevaJaula(Model model, @PathVariable long id) {
		model.addAttribute("id", id);
		return "nuevaJaula";
	}
	
	@RequestMapping("/usuario/{id}/guardarJaula")
	public String guardarJaula(Model model, @PathVariable long id, @RequestParam String nombre) {
		Usuario usuario = usuarioService.findById(id).get();
		Jaula jaula = new Jaula(id, nombre);
		usuario.getJaulas().add(jaula);
		usuarioService.save(usuario);
		
		return "redirect:/usuario/"+usuario.getId();
	}
	
	@GetMapping("/usuario/{idUsuario}/jaula/{idJaula}/nuevoPajaro")
	public String nuevoPajaro(Model model, @PathVariable long idUsuario, @PathVariable long idJaula) {
		model.addAttribute("idUsuario", idUsuario);
		model.addAttribute("idJaula", idJaula);
		return "nuevoPajaro";
	}
	
	@RequestMapping("/usuario/{idUsuario}/jaula/{idJaula}/guardarPajaro")
	public String guardarPajaro(Model model, @PathVariable long idUsuario, @PathVariable int idJaula, @RequestParam String idPajaro, @RequestParam String especie, @RequestParam String apuntes) {
		Usuario usuario = usuarioService.findById(idUsuario).get();
		Jaula jaula = null;
		
		if(idJaula!=-1) {
			for(Jaula j : usuario.getJaulas()) {
				if (j.getId()==idJaula) {
					jaula = j;
					break;
				}	
			}	
		}
		Pajaro pajaro = new Pajaro(Long.parseLong(idPajaro), especie, jaula, apuntes);
		if(idJaula!=-1) {
			jaula.getPajaros().add(pajaro);
		}
		
		usuario.getPajaros().add(pajaro);
		usuarioService.save(usuario);
		
		return "redirect:/usuario/"+usuario.getId();
	}
}
