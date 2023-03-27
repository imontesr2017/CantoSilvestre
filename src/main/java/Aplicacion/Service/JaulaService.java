package Aplicacion.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Aplicacion.Clases.Jaula;
import Aplicacion.Repositorios.JaulaRepository;

@Service
public class JaulaService {
	@Autowired
	private JaulaRepository jaulas;
	
	public Optional<Jaula> findById(long id) {
		return jaulas.findById(id);
	}
	
	public boolean exist(long id) {
		return jaulas.existsById(id);
	}

	public List<Jaula> findAll() {
		return jaulas.findAll();
	}

	public void save(Jaula jaula) {
		jaulas.save(jaula);
	}

	public void delete(long id) {
		jaulas.deleteById(id);
	}
}
