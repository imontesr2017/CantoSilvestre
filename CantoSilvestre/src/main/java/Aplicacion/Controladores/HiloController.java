package Aplicacion.Controladores;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Aplicacion.Clases.Hilo;
import Aplicacion.Clases.Usuario;
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
	
	@GetMapping("usuario/{user}/hilos/{id}")
	public String mostrarLibro(Model model, @PathVariable long user, @PathVariable long id) {
		Optional<Hilo> hilo = hiloService.findById(id);
		if (hilo.isPresent()) {
			model.addAttribute("hilo", hilo.get());
			return "hilo";
		} else {
			return "hilos";
		}

	}
	
	@RequestMapping("/borrarHilo/{idHilo}")
	public String borrarHilo(Model model, @PathVariable long idHilo) {
		hiloService.delete(idHilo);
		return "/";
	}
	
	/*@GetMapping("/editarHilo/{id}")
	public String editarHilo(Model model, @PathVariable long id) {

		Optional<Hilo> hilo = hiloService.findById(id);
		if (hilo.isPresent()) {
			hiloService.delete(id);
			model.addAttribute("hilo", hilo.get());
		}
		return "editarhilo";
	}
	@RequestMapping("/actualizarHilo/{id}")
	public String actualizarHilo(Model model, @PathVariable long id, @RequestParam String titulo, @RequestParam String descripcion) {
		Hilo hilo = hiloService.findById(id).get();
		hilo.setTitulo(titulo);
		hilo.setDescripcion(descripcion);
		hiloService.save(hilo);
		return "redirect:/";
	}*/
	
}
