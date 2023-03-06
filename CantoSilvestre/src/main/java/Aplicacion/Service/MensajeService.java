package Aplicacion.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Aplicacion.Clases.Mensaje;
import Aplicacion.Repositorios.MensajeRepository;

@Service
public class MensajeService {
	@Autowired
	private MensajeRepository mensajes;
	
	public Optional<Mensaje> findById(long id) {
		return mensajes.findById(id);
	}
	
	public boolean exist(long id) {
		return mensajes.existsById(id);
	}

	public List<Mensaje> findAll() {
		return mensajes.findAll();
	}

	public void save(Mensaje mensaje) {
		mensajes.save(mensaje);
	}

	public void delete(long id) {
		mensajes.deleteById(id);
	}
}
