package Aplicacion.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@RequestMapping("/borrarPajaro/{idPajaro}")
	public String borrarPajaro(Model model, @PathVariable long idPajaro) {
		pajaroService.delete(idPajaro);
		return "/";
	}
	
	@GetMapping("/jaula/{idJaula}/nuevoPajaro")
	public String nuevoPajaro(Model model, @PathVariable long idJaula) {
		model.addAttribute("idUsuario", (long) model.getAttribute("userId"));
		model.addAttribute("idJaula", idJaula);
		return "nuevoPajaro";
	}
	
	@RequestMapping("/jaula/{idJaula}/guardarPajaro")
	public String guardarPajaro(Model model, @PathVariable int idJaula, @RequestParam String idPajaro, @RequestParam String especie, @RequestParam String apuntes) {
		Usuario usuario = usuarioService.findById((long) model.getAttribute("userId")).get();
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
		
		return "redirect:/usuarios/"+usuario.getId();
	}
}