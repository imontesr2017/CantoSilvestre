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

import Aplicacion.Clases.Pajaro;
import Aplicacion.Repositorios.PajaroRepository;

@RestController
public class PajaroController {

	@Autowired
	private PajaroRepository pajaros;
	
	@GetMapping("/pajaro")
	public Collection<Pajaro> getPajaros() {
	return pajaros.findAll();
	}
	
	@GetMapping("/pajaro/{id}")
	public ResponseEntity<Pajaro> getPajaro(@PathVariable long id) {
		Optional<Pajaro> pajaro = pajaros.findById(id);
		return ResponseEntity.of(pajaro);
	}
	
	@PostMapping("/pajaro/")
	public ResponseEntity<Pajaro> createPajaro(@RequestBody Pajaro pajaro) {
		pajaros.save(pajaro);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pajaro.getId()).toUri();
		return ResponseEntity.created(location).body(pajaro);
	}
	
	@PutMapping("/pajaro/{id}")
	public ResponseEntity<Pajaro> replacePajaro(@PathVariable long id, @RequestBody Pajaro newPajaro) {
		Optional<Pajaro> pajaro = pajaros.findById(id);
		return ResponseEntity.of(pajaro.map(p -> {
			newPajaro.setId(id);
			pajaros.save(newPajaro);
			return newPajaro;
		}));	
	}
	
	@DeleteMapping("/pajaro/{id}")
	public ResponseEntity<Pajaro> deletePajaro(@PathVariable long id) {
		Optional<Pajaro> pajaro = pajaros.findById(id);
		pajaro.ifPresent(p -> pajaros.deleteById(id));
		return ResponseEntity.of(pajaro);
	}
}
