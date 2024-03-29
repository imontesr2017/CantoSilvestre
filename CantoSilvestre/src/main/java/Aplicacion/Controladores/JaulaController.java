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
import org.springframework.web.bind.annotation.RestController;

import Aplicacion.Clases.Jaula;
import Aplicacion.Clases.Usuario;
import Aplicacion.Service.HiloService;
import Aplicacion.Service.JaulaService;
import Aplicacion.Service.MensajeService;
import Aplicacion.Service.PajaroService;
import Aplicacion.Service.UsuarioService;

@Controller
public class JaulaController {

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
	
	@GetMapping("/jaulas")
	public String mostrarJaulas(Model model) {

		model.addAttribute("jaulas", jaulaService.findAll());

		return "jaulas";
	}
	
	@GetMapping("/jaulas/{id}")
	public String mostrarLibro(Model model, @PathVariable long id) {

		Optional<Jaula> jaula = jaulaService.findById(id);
		if (jaula.isPresent()) {
			model.addAttribute("jaula", jaula.get());
			return "jaula";
		} else {
			return "jaulas";
		}

	}
	
	@RequestMapping("/borrarJaula/{idJaula}")
	public String borrarJaula(Model model, @PathVariable long idJaula) {
		jaulaService.delete(idJaula);
		return "redirect:/perfil";
	}
	
	@GetMapping("/nuevaJaula")
	public String nuevaJaula(Model model) {
		return "nuevaJaula";
	}
	
	@RequestMapping("/guardarJaula")
	public String guardarJaula(Model model, @RequestParam String nombre) {
		Usuario usuario = usuarioService.findByName((String) model.getAttribute("userName")).get();
		Jaula jaula = new Jaula(usuario.getId(), nombre);
		usuario.getJaulas().add(jaula);
		usuarioService.save(usuario);
		
		return "redirect:/perfil";
	}
	
}
