package Aplicacion.Controladores;

import java.security.Principal;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Aplicacion.Clases.Hilo;
import Aplicacion.Clases.Usuario;
import Aplicacion.Service.HiloService;
import Aplicacion.Service.JaulaService;
import Aplicacion.Service.MensajeService;
import Aplicacion.Service.PajaroService;
import Aplicacion.Service.UsuarioService;

@Controller
public class HiloController {
	
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
	
	@GetMapping("/")
	public String mostrarHilos(Model model) {

		model.addAttribute("hilos", hiloService.findAll());

		return "hilos";
	}
	
	@GetMapping("/hilos/{id}")
	public String mostrarHilo(Model model, @PathVariable long id) {
		Optional<Hilo> hilo = hiloService.findById(id);
		if (hilo.isPresent()) {
			model.addAttribute("hilo", hilo.get());
			String usuario = (String) model.getAttribute("userName");
			return "hilo";
		} else {
			return "hilos";
		}

	}
	
	@RequestMapping("/borrarHilo/{idHilo}")
	public String borrarHilo(Model model, @PathVariable long idHilo) {
		hiloService.delete(idHilo);
		return "/perfil";
	}
	
	@GetMapping("/nuevoHilo")
	public String nuevoHilo(Model model) {
		return "nuevoHilo";
	}
	
	@RequestMapping("/guardarHilo")
	public String guardarHilo(Model model, @RequestParam String titulo, @RequestParam String descripcion) {
		Usuario usuario = usuarioService.findByName((String) model.getAttribute("userName")).get();
		Hilo hilo = new Hilo(titulo, descripcion);
		usuario.getHilos().add(hilo);
		usuarioService.save(usuario);
		
		return "redirect:/perfil";
	}
	
	@GetMapping("/editarHilo/{id}")
	public String editarHilo(Model model, @PathVariable long id) {
		return "editarhilo";
	}
	
	@RequestMapping("/actualizarHilo/{id}")
	public String actualizarHilo(Model model, @PathVariable long id, @RequestParam String titulo, @RequestParam String descripcion) {
		Hilo hilo = hiloService.findById(id).get();
		hilo.setTitulo(titulo);
		hilo.setDescripcion(descripcion);
		Usuario usuario = usuarioService.findByName((String) model.getAttribute("userName")).get();
		usuario.getHilos().add(hilo);
		usuarioService.save(usuario);
		return "redirect:/hilos/"+hilo.getId();
	}
	
}
