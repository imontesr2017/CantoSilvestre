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

import Aplicacion.Clases.Mensaje;
import Aplicacion.Repositorios.MensajeRepository;

@RestController
public class MensajeController {

	@Autowired
	private MensajeRepository mensajes;
	
	@GetMapping("/mensajes")
	public Collection<Mensaje> getMensajes() {
	return mensajes.findAll();
	}
	
	@GetMapping("/mensajes/{id}")
	public ResponseEntity<Mensaje> getMensaje(@PathVariable long id) {
		Optional<Mensaje> mensaje = mensajes.findById(id);
		return ResponseEntity.of(mensaje);
	}
	
	@PostMapping("/mensajes")
	public ResponseEntity<Mensaje> createMensaje(@RequestBody Mensaje mensaje) {
		mensajes.save(mensaje);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(mensaje.getId()).toUri();
		return ResponseEntity.created(location).body(mensaje);
	}
	
	@PutMapping("/mensajes/{id}")
	public ResponseEntity<Mensaje> replaceMensaje(@PathVariable long id, @RequestBody Mensaje newMensaje) {
		Optional<Mensaje> mensaje = mensajes.findById(id);
		return ResponseEntity.of(mensaje.map(p -> {
			newMensaje.setId(id);
			mensajes.save(newMensaje);
			return newMensaje;
		}));	
	}
	
	@DeleteMapping("/mensajes/{id}")
	public ResponseEntity<Mensaje> deleteMensaje(@PathVariable long id) {
		Optional<Mensaje> mensaje = mensajes.findById(id);
		mensaje.ifPresent(p -> mensajes.deleteById(id));
		return ResponseEntity.of(mensaje);
	}
	
}
