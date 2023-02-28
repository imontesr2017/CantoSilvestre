package Aplicacion.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Aplicacion.Clases.Hilo;
import Aplicacion.Repositorios.HiloRepository;

@Service
public class HiloService {
	@Autowired
	private HiloRepository hilos;
	
	public Optional<Hilo> findById(long id) {
		return hilos.findById(id);
	}
	
	public boolean exist(long id) {
		return hilos.existsById(id);
	}

	public List<Hilo> findAll() {
		return hilos.findAll();
	}

	public void save(Hilo hilo) {
		hilos.save(hilo);
	}

	public void delete(long id) {
		hilos.deleteById(id);
	}
}
