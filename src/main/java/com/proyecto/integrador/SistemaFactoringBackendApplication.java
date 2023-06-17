package com.proyecto.integrador;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.proyecto.integrador.entidades.Bancos;
import com.proyecto.integrador.entidades.Cartera;
import com.proyecto.integrador.entidades.Monedas;
import com.proyecto.integrador.entidades.Riesgo;
import com.proyecto.integrador.entidades.Rol;
import com.proyecto.integrador.entidades.TipoTransaccion;
import com.proyecto.integrador.entidades.Usuario;
import com.proyecto.integrador.servicios.BancosService;
import com.proyecto.integrador.servicios.CarteraService;
import com.proyecto.integrador.servicios.MonedasService;
import com.proyecto.integrador.servicios.RiesgoService;
import com.proyecto.integrador.servicios.RolService;
import com.proyecto.integrador.servicios.TipoTransaccionService;
import com.proyecto.integrador.servicios.UsuarioService;

@SpringBootApplication
public class SistemaFactoringBackendApplication implements CommandLineRunner {
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private CarteraService carteraService;
	@Autowired
	private RolService rolService;
	@Autowired
	private TipoTransaccionService tipoTransService;
	@Autowired
	private BancosService bancosService;
	@Autowired
	private MonedasService monedasService;
	@Autowired
	private RiesgoService riesgoService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SistemaFactoringBackendApplication.class, args);
	}
//agregar admin
	@Override
	public void run(String... args) throws Exception {
		try {
			Rol rolExiste = rolService.buscarporId(1L);
			if (rolExiste == null) {
				// inversionista
				Rol rolInv = new Rol();
				rolInv.setTipo("INVERSIONISTA");
				rolService.insertarRol(rolInv);
				// admin
				Rol rolAdmin = new Rol();
				rolAdmin.setTipo("ADMIN");
				rolService.insertarRol(rolAdmin);
				System.out.println("Roles registrado con exito!");
			}
			Usuario usuExiste = usuarioService.buscarUsuarioPorId(1);
			if (usuExiste == null) {
				Usuario usuario = new Usuario();
				usuario.setNombre("Jeimy");
				usuario.setApellidoPa("Apolaya");
				usuario.setApellidoMa("Jurado");
				usuario.setTelefono("938311721");
				usuario.setCorreo("apolaya@gmail.com");
				usuario.setUsername("jamie");
				usuario.setPassword(bCryptPasswordEncoder.encode("Admin12345"));
				usuario.setFoto("foto.png");
				usuario.setFecha(new Date());
				usuario.setDni("77454558");
				usuario.setEnable("Activo");
				usuario.setIdTipoUsu(2L);
				Usuario usuReg = usuarioService.insertaActualizaUsuario(usuario);
				Cartera cartera = new Cartera();
				cartera.setSaldo(10000000);
				cartera.setIdUsu(usuReg.getId());
				carteraService.insertaActualizaCartera(cartera);
				System.out.println("USUARIO registrado con exito!");
			}
			Optional<TipoTransaccion> tipoTransExiste = tipoTransService.buscarxId(1);
			if (tipoTransExiste.isEmpty()) {
				// deposito
				TipoTransaccion tipoDepo = new TipoTransaccion();
				tipoDepo.setTipo("Deposito");
				tipoTransService.insertarTipoTransaccion(tipoDepo);
				// retiro
				TipoTransaccion tipoReti = new TipoTransaccion();
				tipoReti.setTipo("Retiro");
				tipoTransService.insertarTipoTransaccion(tipoReti);
				System.out.println("Tipos registrado con exito!");
			}
			Optional<Bancos> existeBanco = bancosService.buscarxId(1);
			if (existeBanco.isEmpty()) {
				String[] nombresBancos = { "BBVA", "BCP", "Scotiabank" };
				for (int i = 0; i < nombresBancos.length; i++) {
					Bancos banco = new Bancos();
					banco.setNomBancos(nombresBancos[i]);
					bancosService.insertarBancos(banco);
					System.out.println("Se registro el banco: "+ banco.getNomBancos());
				}
				
			}
			Optional<Monedas> existeMoneda = monedasService.buscarxId(1);
			if (existeMoneda.isEmpty()) {
				String[] nombresMonedas = { "PEN", "USD" };
				String[] valoresMonedas = { "S/.", "$" };
				for (int i = 0; i < nombresMonedas.length; i++) {
					Monedas moneda = new Monedas();
					moneda.setNomMonedas(nombresMonedas[i]);
					moneda.setValorMoneda(valoresMonedas[i]);
					monedasService.insertarMonedas(moneda);
					System.out.println("Se registro la moneda: "+ moneda.getNomMonedas());
				}
			}
			Optional<Riesgo> existeRiesgo = riesgoService.buscarRiesgoxId(1);
			if (existeRiesgo.isEmpty()) {
				String[] rangoRiesgo = { "A", "B", "C" };
				String[] descrpRiesgo = { "El riesgo de inversion es nulo!", "El riesgo de inversion es CASI nulo!",
						"El riesgo de inversion algo elevado" };
				for (int i = 0; i < rangoRiesgo.length; i++) {
					Riesgo riesgo = new Riesgo();
					riesgo.setRango(rangoRiesgo[i]);
					riesgo.setDescripcion(descrpRiesgo[i]);
					riesgoService.insetarRiesgo(riesgo);
					System.out.println("Se registro el riesgo: "+ riesgo.getRango());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
