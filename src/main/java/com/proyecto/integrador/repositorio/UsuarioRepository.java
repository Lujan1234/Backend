package com.proyecto.integrador.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proyecto.integrador.entidades.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	public Usuario findByUsername(String username);

	@Query(value = "SELECT COUNT(*) FROM usuarios u WHERE u.username = :username and u.id != :idUsu", nativeQuery = true)
	int existeUsername(@Param("username") String username, @Param("idUsu") long idUsu);

	@Query(value = "SELECT COUNT(*) FROM usuarios u WHERE u.correo = :correo and u.id != :idUsu", nativeQuery = true)
	int existeCorreo(@Param("correo") String correo, @Param("idUsu") long idUsu);

	@Query(value = "SELECT COUNT(*) FROM usuarios u WHERE u.dni = :dni and u.id != :idUsu", nativeQuery = true)
	int existeDni(@Param("dni") String dni, @Param("idUsu") long idUsu);

	@Query("select e from Usuario e where e.dni = ?1 and e.id <> ?2")
	public List<Usuario> listaPorDniDiferenteSiMismo(String dni, long idUsu);

	@Query(value = "select * from usuarios as u join usuario_rol as ur on u.id= ur.usuario_id where u.id = :idUsu", nativeQuery = true)
	public Usuario usuarioPorRol(@Param("idUsu") long idUsu);

	public abstract Optional<Usuario> findByDni(String dni);

	public abstract Optional<Usuario> findByCorreo(String correo);
	
	List<Usuario> findByEnableNot(String noActivo);

}
