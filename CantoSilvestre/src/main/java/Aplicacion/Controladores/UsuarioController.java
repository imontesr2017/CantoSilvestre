package Aplicacion.Controladores;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

import javax.annotation.PostConstruct;

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
import Aplicacion.Clases.Jaula;
import Aplicacion.Clases.Mensaje;
import Aplicacion.Clases.Pajaro;
import Aplicacion.Clases.Usuario;
import Aplicacion.Repositorios.UsuarioRepository;

@RestController("/usuario")
public class UsuarioController {
	@Autowired
	private UsuarioRepository usuarios;
	
	@PostConstruct
	public void init() {
		Usuario pepe = new Usuario(1, "Pepe", "1234");
		Usuario marta = new Usuario(2, "Marta", "1111");
		Usuario sara = new Usuario(3, "Sara", "pajaro");
		
		Jaula jaulaCrianza = new Jaula("Crianza");
		Jaula jaulaAdultos = new Jaula("Competicion");
		Jaula jaulaMama = new Jaula("Para mama");
		
		Pajaro pajaro1 = new Pajaro(1, "Jilguero", jaulaCrianza);
		Pajaro pajaro2 = new Pajaro(2, "Canario", null);
		Pajaro pajaro3 = new Pajaro(3, "Jilguero", jaulaAdultos, "Preparado para competición");
		Pajaro pajaro4 = new Pajaro(4, "Jilguero", jaulaCrianza, "Para cria");
		
		Hilo hilo1 = new Hilo("Cambio jilguero por canario");
		Hilo hilo2 = new Hilo("Como alimento a mis pajaros?");
		Hilo hilo3 = new Hilo("Vendo jaulas");
		
		Mensaje mensaje1 = new Mensaje("Hola tengo 3 jilgueros para cria y me gustaria 3 canarios para cria");
		Mensaje mensaje2 = new Mensaje("Hola estoy interesada en el cambio");
		
		hilo1.getListaMensajes().add(mensaje1);
		hilo1.getListaMensajes().add(mensaje2);
		
		pepe.getMensajes().add(mensaje1);
		pepe.getMensajes().add(mensaje2);
		
		jaulaCrianza.getPajaros().add(pajaro4);
		jaulaCrianza.getPajaros().add(pajaro1);
		jaulaAdultos.getPajaros().add(pajaro3);
		
		pepe.getPajaros().add(pajaro4);
		pepe.getPajaros().add(pajaro1);
		marta.getPajaros().add(pajaro3);
		marta.getPajaros().add(pajaro2);
		
		pepe.getHilos().add(hilo1);
		pepe.getHilos().add(hilo2);
		marta.getHilos().add(hilo3);
		
		pepe.getJaulas().add(jaulaCrianza);
		marta.getJaulas().add(jaulaAdultos);
		marta.getJaulas().add(jaulaMama);
		
		usuarios.save(pepe);
		usuarios.save(marta);
		usuarios.save(sara);
		
	}
	
	@GetMapping("/")
	public Collection<Usuario> getUsuarios() {
	return usuarios.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getUsuario(@PathVariable long id) {
		Optional<Usuario> usuario = usuarios.findById(id);
		return ResponseEntity.of(usuario);
	}
	
	@PostMapping("/")
	public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
		usuarios.save(usuario);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.created(location).body(usuario);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> replaceUsuario(@PathVariable long id, @RequestBody Usuario newUsuario) {
		Optional<Usuario> usuario = usuarios.findById(id);
		return ResponseEntity.of(usuario.map(p -> {
			newUsuario.setId(id);
			usuarios.save(newUsuario);
			return newUsuario;
		}));	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Usuario> deleteUsuario(@PathVariable long id) {
		Optional<Usuario> usuario = usuarios.findById(id);
		usuario.ifPresent(p -> usuarios.deleteById(id));
		return ResponseEntity.of(usuario);
	}
}
