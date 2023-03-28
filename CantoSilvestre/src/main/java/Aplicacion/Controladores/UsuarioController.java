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
import org.springframework.security.crypto.password.PasswordEncoder;

import Aplicacion.Clases.Hilo;
import Aplicacion.Clases.Jaula;
import Aplicacion.Clases.Mensaje;
import Aplicacion.Clases.Pajaro;
import Aplicacion.Clases.Usuario;
import Aplicacion.Service.HiloService;
import Aplicacion.Service.JaulaService;
import Aplicacion.Service.MensajeService;
import Aplicacion.Service.PajaroService;
import Aplicacion.Service.UsuarioService;

@Controller
public class UsuarioController {

	

	//Servicios de acceso a la base de datos
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private HiloService hiloService;
	@Autowired
	private MensajeService mensajeService;
	@Autowired
	private JaulaService jaulaService;
	@Autowired
	private PajaroService pajaroService;
	
	//Seguridad
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@ModelAttribute
	public void addAttributes(Model model, HttpServletRequest request) {

		Principal principal = request.getUserPrincipal();

		if (principal != null) {

			model.addAttribute("logged", true);
			model.addAttribute("userName", principal.getName());
			model.addAttribute("admin", request.isUserInRole("ADMIN"));
			Usuario usuario = usuarioService.findByName(principal.getName()).get();
			model.addAttribute("userId", usuario.getId());

		} else {
			model.addAttribute("logged", false);
		}
	}
	
	@PostConstruct
	public void init() {
		Usuario pepe = new Usuario(1, "Pepe", passwordEncoder.encode("1234"), "USER");
		Usuario marta = new Usuario(2, "Marta", passwordEncoder.encode("1111"), "USER");
		Usuario sara = new Usuario(3, "Sara", passwordEncoder.encode("pajaro"), "USER");
		Usuario admin = new Usuario(0, "admin", passwordEncoder.encode("admin"), "USER", "ADMIN");
		
		Jaula jaulaCrianza = new Jaula(pepe.getId(),"Crianza");
		Jaula jaulaAdultos = new Jaula(marta.getId(),"Competicion");
		Jaula jaulaMama = new Jaula(marta.getId(), "Para mama");
		
		Pajaro pajaro1 = new Pajaro(1, "Jilguero", jaulaCrianza);
		Pajaro pajaro2 = new Pajaro(2, "Canario", null);
		Pajaro pajaro3 = new Pajaro(3, "Jilguero", jaulaAdultos, "Preparado para competición");
		Pajaro pajaro4 = new Pajaro(4, "Jilguero", jaulaCrianza, "Para cria");
		
		Hilo hilo1 = new Hilo("Cambio jilguero por canario", "Hola tengo 3 jilgueros para cria y me gustaria 3 canarios para cria");
		Hilo hilo2 = new Hilo("Como alimento a mis pajaros?", "¿no sé que pienso deberia dar a mis pajaros?");
		Hilo hilo3 = new Hilo("Vendo jaulas", "");
		
		Mensaje mensaje1 = new Mensaje("Pepe: solo me quedan dos");
		Mensaje mensaje2 = new Mensaje("Marta: Hola estoy interesada en el cambio");
		
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
	
	@GetMapping("/usuarios")
	public String mostrarUsuarios(Model model) {

		model.addAttribute("usuarios", usuarioService.findAll());

		return "usuarios";
	}
	
	@GetMapping("/usuarios/{id}")
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
		return "/usuarios/"+usuario.getId();
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
		usuario.setName(nombre);
		usuario.setPassword(pass);
		usuarioService.save(usuario);
		return "redirect:/usuarios/"+usuario.getId();
	}
	
	
}
