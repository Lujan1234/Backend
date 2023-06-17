package com.proyecto.integrador.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proyecto.integrador.entidades.Transacciones;


public interface TransaccionRepository extends JpaRepository<Transacciones, Long>{
	@Query(value="SELECT t.* FROM  cuentasbancarias as c join transacciones as t \r\n"
			+ "on c.id_cuenta_bancaria = t.id_cuenta_bancaria where usuario_id = :idUsu and t.id_cuenta_bancaria=:idCuenta\r\n"
			+ "order by c.id_cuenta_bancaria ;", nativeQuery = true)
	List<Transacciones> listarTransaccionxIdCuentaBancaria(@Param("idUsu") long idUsu,@Param("idCuenta") long idTipo); 
	
	@Query(value="SELECT t.* FROM  cuentasbancarias as c join transacciones as t on c.id_cuenta_bancaria = t.id_cuenta_bancaria where usuario_id = :idUsu order by c.id_cuenta_bancaria",nativeQuery = true)
	List<Transacciones> listarTransaccionxIdUsuario(@Param("idUsu") long idUsu); 
}
