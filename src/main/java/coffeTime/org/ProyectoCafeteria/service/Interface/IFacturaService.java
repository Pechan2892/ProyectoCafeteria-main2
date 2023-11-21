package coffeTime.org.ProyectoCafeteria.service.Interface;

import coffeTime.org.ProyectoCafeteria.dao.entity.Factura;

import java.util.List;

public interface IFacturaService {
    Factura guardarFactura(Factura factura);
    List<Factura> obtenerTodasLasFacturas();
    Factura obtenerFacturaPorId(Long id);
    void  eliminarFactura(Long id);

    Factura obtenerPorId(Long id);
}

