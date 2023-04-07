package Aplicacion.Repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Aplicacion.Clases.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
