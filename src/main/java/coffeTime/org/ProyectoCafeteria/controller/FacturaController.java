package coffeTime.org.ProyectoCafeteria.controller;

import coffeTime.org.ProyectoCafeteria.dao.entity.Factura;
import coffeTime.org.ProyectoCafeteria.dao.entity.Cliente;
import coffeTime.org.ProyectoCafeteria.service.Interface.IFacturaService;
import coffeTime.org.ProyectoCafeteria.service.Interface.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/facturas")
public class FacturaController {

    @Autowired
    private IFacturaService facturaService;

    @Autowired
    private IClienteService clienteService; // Servicio para manejar operaciones de clientes

    // Listar todas las facturas
    @GetMapping
    public String listarFacturas(Model model) {
        model.addAttribute("facturas", facturaService.obtenerTodasLasFacturas());
        return "facturas/lista";
    }

    // Mostrar el detalle de una factura
    @GetMapping("/{id}")
    public String verDetalleFactura(@PathVariable Long id, Model model) {
        Factura factura = facturaService.obtenerPorId(id);
        if (factura == null) {
            // Manejar el caso en que la factura no exista
            return "error"; // Redirigir a una página de error o manejar de otra manera
        }
        model.addAttribute("factura", factura);


        return "facturas/detalle-factura";
    }


    // Mostrar formulario para crear una nueva factura
    @GetMapping("/nueva")
    public String mostrarFormularioDeNuevaFactura(Model model) {
        model.addAttribute("factura", new Factura());
        return "facturas/formulario";
    }

    // Procesar el formulario de nueva factura

    @PostMapping("/nueva")
    public String procesarFormularioDeNuevaFactura(@ModelAttribute Factura factura) {
        Cliente cliente = factura.getCliente();

        if (cliente != null) {
            if (cliente.getId() == null) {
                // El cliente es nuevo, guárdalo primero
                cliente = clienteService.guardarCliente(cliente);
            } else {
                // El cliente ya existe, obtén la entidad persistida
                cliente = clienteService.obtenerClientePorId(cliente.getId());
            }

            factura.setCliente(cliente); // Asigna el cliente a la factura
        }

        facturaService.guardarFactura(factura); // Guarda la factura
        return "redirect:/facturas";
    }


    // Mostrar formulario para editar una factura
    @GetMapping("/editar/{id}")
    public String mostrarFormularioDeEditarFactura(@PathVariable Long id, Model model) {
        Factura factura = facturaService.obtenerFacturaPorId(id);
        if (factura == null) {
            throw new IllegalArgumentException("Factura inválida con id: " + id);
        }
        model.addAttribute("factura", factura);
        return "facturas/formulario";
    }

    // Procesar el formulario de editar factura
    @PostMapping("/editar/{id}")
    public String procesarFormularioDeEditarFactura(@PathVariable Long id, @ModelAttribute Factura factura) {
        facturaService.guardarFactura(factura);
        return "redirect:/facturas";
    }

    // Eliminar una factura
    @GetMapping("/eliminar/{id}")
    public String eliminarFactura(@PathVariable Long id) {
        facturaService.eliminarFactura(id);
        return "redirect:/facturas";
    }
}
