package Aplicacion.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Aplicacion.Clases.Mensaje;
import Aplicacion.Clases.Usuario;
import Aplicacion.Clases.Mensaje;
import Aplicacion.Service.MensajeService;

@Controller
public class MensajeController {

	@Autowired
	private MensajeService mensajeService;
	
	@RequestMapping("/borrarMensaje/{idMensaje}")
	public String borrarMensaje(Model model, @PathVariable long idMensaje) {
		mensajeService.delete(idMensaje);
		return "/";
	}
	
	
}
