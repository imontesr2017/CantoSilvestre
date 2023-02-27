package Aplicacion.Repositorios;
import org.springframework.data.jpa.repository.JpaRepository;

import Aplicacion.Clases.Mensaje;


public interface MensajeRepository extends JpaRepository<Mensaje, Long>{

}
