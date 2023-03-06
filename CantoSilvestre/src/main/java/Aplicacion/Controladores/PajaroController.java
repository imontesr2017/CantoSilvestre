package Aplicacion.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import Aplicacion.Service.PajaroService;

@Controller
public class PajaroController {

	@Autowired
	private PajaroService pajaroService;
	
	@RequestMapping("/borrarPajaro/{idPajaro}")
	public String borrarPajaro(Model model, @PathVariable long idPajaro) {
		pajaroService.delete(idPajaro);
		return "/";
	}
}
