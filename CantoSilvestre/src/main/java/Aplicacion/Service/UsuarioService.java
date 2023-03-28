package Aplicacion.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Aplicacion.Clases.Hilo;
import Aplicacion.Clases.Usuario;
import Aplicacion.Repositorios.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository usuarios;
	
	public Optional<Usuario> findById(long id) {
		return usuarios.findById(id);
	}
	
	public boolean exist(long id) {
		return usuarios.existsById(id);
	}

	public List<Usuario> findAll() {
		return usuarios.findAll();
	}

	public void save(Usuario usuario) {
		usuarios.save(usuario);
	}

	public void delete(long id) {
		usuarios.deleteById(id);
	}

	public Optional<Usuario> findByName(String name) {
		Optional<Usuario> usuario = usuarios.findByName(name);
		return usuario;
	}
}
