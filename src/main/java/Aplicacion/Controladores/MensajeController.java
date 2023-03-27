package Aplicacion.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Aplicacion.Clases.Hilo;
import Aplicacion.Clases.Mensaje;
import Aplicacion.Clases.Usuario;
import Aplicacion.Clases.Mensaje;
import Aplicacion.Service.HiloService;
import Aplicacion.Service.JaulaService;
import Aplicacion.Service.MensajeService;
import Aplicacion.Service.PajaroService;
import Aplicacion.Service.UsuarioService;

@Controller
public class MensajeController {

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
	
	@RequestMapping("/borrarMensaje/{idMensaje}")
	public String borrarMensaje(Model model, @PathVariable long idMensaje) {
		mensajeService.delete(idMensaje);
		return "/";
	}
	
	@GetMapping("/hilo/{idHilo}/nuevoMensaje")
	public String nuevoMensaje(Model model, @PathVariable long idHilo) {
		model.addAttribute("idHilo", idHilo);
		return "nuevoMensaje";
	}
	
	@RequestMapping("/hilo/{idHilo}/guardarMensaje")
	public String guardarMensaje(Model model, @PathVariable int idHilo, @RequestParam String texto) {
		Usuario usuario = usuarioService.findById((long) model.getAttribute("userId")).get();
		Hilo hilo = null;
		for(Hilo h : usuario.getHilos()) {
			if (h.getId()==idHilo) {
				hilo = h;
				break;
			}
			
		}
		
		Mensaje mensaje = new Mensaje(usuario.getName()+": "+texto);
		hilo.getListaMensajes().add(mensaje);
		usuario.getMensajes().add(mensaje);
		usuarioService.save(usuario);
		return "redirect:/usuarios/"+usuario.getId();
	}
}