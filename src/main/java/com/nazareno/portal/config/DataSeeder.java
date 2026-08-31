package com.nazareno.portal.config;

import com.nazareno.portal.model.Rol;
import com.nazareno.portal.model.Usuario;
import com.nazareno.portal.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JdbcTemplate jdbcTemplate;

    public DataSeeder(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, JdbcTemplate jdbcTemplate) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) {
        actualizarRestriccionDeRoles();
        if (usuarioRepository.count() == 0) {
            sembrarUsuariosIniciales();
        } else {
            asignarPasswordsFaltantes();
        }
        crearAdminSiNoExiste();
    }

    private void actualizarRestriccionDeRoles() {
        jdbcTemplate.execute("ALTER TABLE usuarios DROP CONSTRAINT IF EXISTS usuarios_rol_check");
        jdbcTemplate.execute("ALTER TABLE usuarios ADD CONSTRAINT usuarios_rol_check CHECK (rol IN ('PRACTICANTE', 'COORDINADOR', 'ADMIN'))");
    }

    private void crearAdminSiNoExiste() {
        if (usuarioRepository.findByCodigo("A0001").isPresent()) return;

        Usuario admin = new Usuario();
        admin.setCodigo("A0001");
        admin.setNombre("Administrador");
        admin.setApellido("Sistema");
        admin.setDni("00000000");
        admin.setRol(Rol.ADMIN);
        admin.setArea("Sistemas");
        admin.setEstado("ACTIVO");
        admin.setFechaIngreso(LocalDate.now());
        admin.setPassword(passwordEncoder.encode("admin123"));
        usuarioRepository.save(admin);
        System.out.println("Usuario administrador creado: A0001 / admin123 — cámbiala pronto.");
    }

    private void sembrarUsuariosIniciales() {
        usuarioRepository.save(crearPracticante(
                "P2601", "Elizabeth Rocio", "Lopez Moreno", "72385286",
                "973769540", "Los Alamos del Ppao Mz. M Lte 6",
                "elizabethrociolopezmoreno@gmail.com"));

        usuarioRepository.save(crearPracticante(
                "P2602", "Valeria", "Flores Perez", "71105093",
                "912520925", "Bellamar 2da etapa Mz. F2 Lt. 12",
                "floresperezvaleria19@gmail.com"));

        usuarioRepository.save(crearPracticante(
                "P2603", "Adrián", "Avila Sanchez", "73889913",
                "936132497", "PJ San Juan Mz 21 Lt. 5",
                "darkosavilasanchez.26@gmail.com"));

        usuarioRepository.save(crearPracticante(
                "P2604", "Renzo Daladier", "Zavaleta Balta", "71325851",
                "943075308", "El Acero Mz D Lt. 17A",
                "renzozavab@gmail.com"));

        usuarioRepository.save(crearCoordinador(
                "C2601", "Eduardo Mariano Yoshi", "Mochizaki Guimaray", "76055436",
                "900689770", "Av Country 224",
                "yoshiguimaray@gmail.com"));

        System.out.println("Sembrados 4 practicantes y 1 coordinador, con contraseña inicial = su DNI.");
    }

    private void asignarPasswordsFaltantes() {
        List<Usuario> sinPassword = usuarioRepository.findAll().stream()
                .filter(u -> u.getPassword() == null)
                .toList();

        if (sinPassword.isEmpty()) return;

        sinPassword.forEach(u -> u.setPassword(passwordEncoder.encode(u.getDni())));
        usuarioRepository.saveAll(sinPassword);
        System.out.println("Se asignó contraseña inicial (= DNI) a " + sinPassword.size() + " usuario(s) existente(s).");
    }

    private Usuario crearPracticante(String codigo, String nombre, String apellido, String dni,
                                      String telefono, String direccion, String correo) {
        Usuario u = new Usuario();
        u.setCodigo(codigo);
        u.setNombre(nombre);
        u.setApellido(apellido);
        u.setDni(dni);
        u.setTelefono(telefono);
        u.setDireccion(direccion);
        u.setCorreo(correo);
        u.setRol(Rol.PRACTICANTE);
        u.setArea("Tecnología");
        u.setFechaIngreso(LocalDate.now());
        u.setEstado("ACTIVO");
        u.setPassword(passwordEncoder.encode(dni));
        return u;
    }

    private Usuario crearCoordinador(String codigo, String nombre, String apellido, String dni,
                                      String telefono, String direccion, String correo) {
        Usuario u = crearPracticante(codigo, nombre, apellido, dni, telefono, direccion, correo);
        u.setRol(Rol.COORDINADOR);
        return u;
    }
}