package coffeTime.org.ProyectoCafeteria.repository;

import coffeTime.org.ProyectoCafeteria.dao.entity.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.List;

public interface FacturaRepository extends JpaRepository<Factura,Long> {
    List<Factura>findByClienteNombre(String nombre);
}
