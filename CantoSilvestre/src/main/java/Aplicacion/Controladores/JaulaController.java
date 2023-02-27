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

import Aplicacion.Clases.Jaula;
import Aplicacion.Repositorios.JaulaRepository;

@RestController
public class JaulaController {

	@Autowired
	private JaulaRepository jaulas;
	
	@GetMapping("/jaula")
	public Collection<Jaula> getJaulas() {
	return jaulas.findAll();
	}
	
	@GetMapping("/jaula/{id}")
	public ResponseEntity<Jaula> getJaula(@PathVariable long id) {
		Optional<Jaula> jaula = jaulas.findById(id);
		return ResponseEntity.of(jaula);
	}
	
	@PostMapping("/jaula")
	public ResponseEntity<Jaula> createJaula(@RequestBody Jaula jaula) {
		jaulas.save(jaula);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(jaula.getId()).toUri();
		return ResponseEntity.created(location).body(jaula);
	}
	
	@PutMapping("/jaula/{id}")
	public ResponseEntity<Jaula> replaceJaula(@PathVariable long id, @RequestBody Jaula newJaula) {
		Optional<Jaula> jaula = jaulas.findById(id);
		return ResponseEntity.of(jaula.map(p -> {
			newJaula.setId(id);
			jaulas.save(newJaula);
			return newJaula;
		}));	
	}
	
	@DeleteMapping("/jaula/{id}")
	public ResponseEntity<Jaula> deleteJaula(@PathVariable long id) {
		Optional<Jaula> jaula = jaulas.findById(id);
		jaula.ifPresent(p -> jaulas.deleteById(id));
		return ResponseEntity.of(jaula);
	}
	
}
