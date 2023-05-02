package Aplicacion.Controladores;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import Aplicacion.Clases.Jaula;
import Aplicacion.Clases.Pajaro;
import Aplicacion.Clases.Usuario;
import Aplicacion.Service.HiloService;
import Aplicacion.Service.JaulaService;
import Aplicacion.Service.MensajeService;
import Aplicacion.Service.PajaroService;
import Aplicacion.Service.UsuarioService;

@Controller
public class PajaroController {

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
	
	@Value("${app.servicio.rest:localhost}")
	private String servicioInternoRest;
	
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
	
	@RequestMapping("/borrarPajaro/{idPajaro}")
	public String borrarPajaro(Model model, @PathVariable long idPajaro) {
		pajaroService.delete(idPajaro);
		return "/perfil";
	}
	
	@GetMapping("/jaula/{idJaula}/nuevoPajaro")
	public String nuevoPajaro(Model model, @PathVariable long idJaula) {
		model.addAttribute("idJaula", idJaula);
		return "nuevoPajaro";
	}
	
	@RequestMapping("/jaula/{idJaula}/guardarPajaro")
	public String guardarPajaro(Model model, @PathVariable int idJaula, @RequestParam String idPajaro, @RequestParam String especie, @RequestParam String apuntes) {
		Usuario usuario = usuarioService.findById((Long) model.getAttribute("userId")).get();
		Jaula jaula = null;
		
		System.setProperty("proxyHost", "yourproxy.server.com");
		System.setProperty("proxyPort", "8080"); 
		
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://"+ servicioInternoRest + ":8080/usuarios" + usuario.getId()+"/pajaro/"+idPajaro;
		Boolean valido = restTemplate.getForObject(url, Boolean.class);
		if (!valido) {
			return "idPajaroInvalido";
		}
		if(!pajaroService.findById(Long.parseLong(idPajaro)).isEmpty()) {
			return "idPajaroExistente";
		}
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
		
		return "redirect:/perfil";
	}
}
