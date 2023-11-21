package coffeTime.org.ProyectoCafeteria.repository;

import coffeTime.org.ProyectoCafeteria.dao.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Buscar un cliente por su nombre
    Optional<Cliente> findByNombre(String nombre);

    // Buscar un cliente por su correo electrónico
    Optional<Cliente> findByEmail(String email);

    // Otros métodos personalizados pueden ser añadidos aquí
}