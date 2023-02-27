package Aplicacion.Controladores;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import Aplicacion.Clases.Hilo;
import Aplicacion.Repositorios.HiloRepository;

@RestController
public class HiloController {
	
	@Autowired
	private HiloRepository hilos;
	
	@GetMapping("/hilo")
	public Collection<Hilo> getHilos() {
	return hilos.findAll();
	}
	
	@GetMapping("/hilo/{id}")
	public ResponseEntity<Hilo> getHilo(@PathVariable long id) {
		Optional<Hilo> hilo = hilos.findById(id);
		return ResponseEntity.of(hilo);
	}
	
	@PostMapping("/hilo")
	public ResponseEntity<Hilo> createHilo(@RequestBody Hilo hilo) {
		hilos.save(hilo);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(hilo.getId()).toUri();
		return ResponseEntity.created(location).body(hilo);
	}
	
	@PutMapping("/hilo/{id}")
	public ResponseEntity<Hilo> replaceHilo(@PathVariable long id, @RequestBody Hilo newHilo) {
		Optional<Hilo> hilo = hilos.findById(id);
		return ResponseEntity.of(hilo.map(p -> {
			newHilo.setId(id);
			hilos.save(newHilo);
			return newHilo;
		}));	
	}
	
	@DeleteMapping("/hilo/{id}")
	public ResponseEntity<Hilo> deleteHilo(@PathVariable long id) {
		Optional<Hilo> hilo = hilos.findById(id);
		hilo.ifPresent(p -> hilos.deleteById(id));
		return ResponseEntity.of(hilo);
	}
}
