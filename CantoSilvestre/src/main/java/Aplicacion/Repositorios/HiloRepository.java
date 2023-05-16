package Aplicacion.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import Aplicacion.Clases.Hilo;
import java.util.List;
import java.util.Optional;

@CacheConfig(cacheNames="hilos")
public interface HiloRepository extends JpaRepository<Hilo, Long>{

	@CacheEvict(allEntries=true)
	Hilo save(Hilo hilo);	
	
	@Cacheable
	Optional<Hilo> findById(Long id);
	
	@Cacheable
	List<Hilo> findAll();

}
