package Controladores;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/pajaros")
public class PajaroController {

	@Autowired
	private PajaroRepository pajaros;
	
	@PostConstruct
	public void init() {
		pajaros.save(new Pajaro());
		pajaros.save(new Pajaro());
		pajaros.save(new Pajaro());
	}
	
	@GetMapping("/p")
	public String iteration(Model model) {
		model.addAttribute("name", "mundo");
		return "greeting_template";
	}
	
	@GetMapping("/")
	public Collection<Pajaro> getPajaros() {
		return pajaros.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pajaro> getPajaro(@PathVariable long id) {
		Optional<Pajaro> pajaro = pajaros.findById(id);
		if (pajaro.isPresent()) {
			return ResponseEntity.ok(pajaro.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/")
	public ResponseEntity<Pajaro> createPajaro(@RequestBody Pajaro pajaro) {
		pajaros.save(pajaro);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pajaro.getId()).toUri();
		return ResponseEntity.created(location).body(pajaro);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Pajaro> replacePajaro(@PathVariable long id, @RequestBody Pajaro newPajaro) {
		Optional<Pajaro> post = pajaros.findById(id);
		if (post.isPresent()) {
			newPajaro.setId(id);
			pajaros.save(newPajaro);
			return ResponseEntity.ok(newPajaro);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Pajaro> deletePajaro(@PathVariable long id) {
		Optional<Pajaro> post = pajaros.findById(id);
		if (post.isPresent()) {
			pajaros.deleteById(id);
			return ResponseEntity.ok(post.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
}
