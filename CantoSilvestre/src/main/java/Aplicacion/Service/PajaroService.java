package Aplicacion.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Aplicacion.Clases.Pajaro;
import Aplicacion.Repositorios.PajaroRepository;

@Service
public class PajaroService {

	@Autowired
	private PajaroRepository pajaros;
	
	public Optional<Pajaro> findById(long id) {
		return pajaros.findById(id);
	}
	
	public boolean exist(long id) {
		return pajaros.existsById(id);
	}

	public List<Pajaro> findAll() {
		return pajaros.findAll();
	}

	public void save(Pajaro pajaro) {
		pajaros.save(pajaro);
	}

	public void delete(long id) {
		pajaros.deleteById(id);
	}
	
}
