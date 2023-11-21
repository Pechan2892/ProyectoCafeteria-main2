package coffeTime.org.ProyectoCafeteria.service.implementation;


import coffeTime.org.ProyectoCafeteria.dao.entity.Factura;
import coffeTime.org.ProyectoCafeteria.repository.FacturaRepository;
import coffeTime.org.ProyectoCafeteria.service.Interface.IFacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaServiceImpl implements IFacturaService {
    private final FacturaRepository facturaRepository;

    @Autowired
    public FacturaServiceImpl(FacturaRepository facturaRepository) {
        this.facturaRepository = facturaRepository;
    }

    @Override
    public Factura guardarFactura(Factura factura) {
        return facturaRepository.save(factura);
    }

    @Override
    public List<Factura> obtenerTodasLasFacturas() {
        return facturaRepository.findAll();
    }

    @Override
    public Factura obtenerFacturaPorId(Long id) {
        Optional<Factura> factura = facturaRepository.findById(id);
        return factura.orElse(null);
    }

    @Override
    public void eliminarFactura(Long id) {
        facturaRepository.deleteById(id);
    }

    @Override
    public Factura obtenerPorId(Long id) {
        return null;
    }
}
