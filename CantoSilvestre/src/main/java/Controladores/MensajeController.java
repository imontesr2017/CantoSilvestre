package Controladores;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.annotation.PostConstruct;

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
import org.springframework.web.bind.annotation.RestController;


@Controller
public class MensajeController {

	@Autowired
	private MensajeRepository mensajes;
	
	@PostConstruct
	public void init() {
		mensajes.save(new Mensaje("Pepe Vendo moto Barata, barata"));
		mensajes.save(new Mensaje("Juan Compro coche Pago bien"));
		mensajes.save(new Mensaje("Por favor funciona"));
	}
	
	/*@GetMapping("/mensaje")
	public String iteration(Model model) {
		model.addAttribute("name", "mundo");
		return "greeting_template";
	}
	
	@GetMapping("/")
	public Collection<Mensaje> getMensajes() {
		return mensajes.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Mensaje> getMensaje(@PathVariable long id) {
		Optional<Mensaje> mensaje = mensajes.findById(id);
		if (mensaje.isPresent()) {
			return ResponseEntity.ok(mensaje.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/")
	public ResponseEntity<Mensaje> createMensaje(@RequestBody Mensaje mensaje) {
		mensajes.save(mensaje);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(mensaje.getId()).toUri();
		return ResponseEntity.created(location).body(mensaje);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Mensaje> replaceMensaje(@PathVariable long id, @RequestBody Mensaje newMensaje) {
		Optional<Mensaje> mensaje = mensajes.findById(id);
		if (mensaje.isPresent()) {
			newMensaje.setId(id);
			mensajes.save(newMensaje);
			return ResponseEntity.ok(newMensaje);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Mensaje> deleteMensaje(@PathVariable long id) {
		Optional<Mensaje> mensaje = mensajes.findById(id);
		if (mensaje.isPresent()) {
			mensajes.deleteById(id);
			return ResponseEntity.ok(mensaje.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}*/
	
}
