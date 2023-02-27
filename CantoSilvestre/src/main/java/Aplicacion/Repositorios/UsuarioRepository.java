package Aplicacion.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import Aplicacion.Clases.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
