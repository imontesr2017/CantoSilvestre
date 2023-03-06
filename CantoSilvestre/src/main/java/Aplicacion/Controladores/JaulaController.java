package Aplicacion.Controladores;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Aplicacion.Clases.Jaula;
import Aplicacion.Service.JaulaService;

@Controller
public class JaulaController {

	@Autowired
	private JaulaService jaulaService;
	
	@GetMapping("/mostrarJaulas")
	public String mostrarJaulas(Model model) {

		model.addAttribute("jaulas", jaulaService.findAll());

		return "jaulas";
	}
	
	@GetMapping("usuario/{user}/jaulas/{id}")
	public String mostrarLibro(Model model, @PathVariable long user, @PathVariable long id) {

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
		return "/mostrarJaulas";
	}
	
}
