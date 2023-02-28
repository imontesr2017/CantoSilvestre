package Aplicacion.Controladores;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import Aplicacion.Clases.Hilo;
import Aplicacion.Service.HiloService;

@Controller
public class HiloController {
	
	@Autowired
	private HiloService hiloService;
	
	@GetMapping("/")
	public String mostrarHilos(Model model) {

		model.addAttribute("hilos", hiloService.findAll());

		return "hilos";
	}
	
	@GetMapping("/hilos/{id}")
	public String mostrarLibro(Model model, @PathVariable long id) {

		Optional<Hilo> hilo = hiloService.findById(id);
		if (hilo.isPresent()) {
			model.addAttribute("hilo", hilo.get());
			return "hilo";
		} else {
			return "hilos";
		}

	}
	
	
}
