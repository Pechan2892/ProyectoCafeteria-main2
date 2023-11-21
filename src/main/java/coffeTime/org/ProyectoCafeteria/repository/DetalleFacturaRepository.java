package coffeTime.org.ProyectoCafeteria.repository;

import coffeTime.org.ProyectoCafeteria.dao.entity.DetalleFactura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetalleFacturaRepository extends JpaRepository<DetalleFactura,Long> {
    List<DetalleFactura>findByFacturaId(Long factura);
}
