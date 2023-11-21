package coffeTime.org.ProyectoCafeteria.controller;

import coffeTime.org.ProyectoCafeteria.dao.entity.Cliente;
import coffeTime.org.ProyectoCafeteria.service.Interface.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    // Listar todos los clientes
    @GetMapping
    public String listarClientes(Model model) {
        model.addAttribute("clientes", clienteService.obtenerTodosLosClientes());
        return "clientes/lista";
    }

    // Mostrar formulario para crear un nuevo cliente
    @GetMapping("/nuevo")
    public String mostrarFormularioDeNuevoCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clientes/formulario";
    }

    // Guardar nuevo cliente
    @PostMapping("/nuevo")
    public String procesarFormularioDeNuevoCliente(@ModelAttribute Cliente cliente) {
        clienteService.guardarCliente(cliente);
        return "redirect:/clientes";
    }

    // Mostrar formulario para editar un cliente
    @GetMapping("/editar/{id}")
    public String mostrarFormularioDeEditarCliente(@PathVariable Long id, Model model) {
        Cliente cliente = clienteService.obtenerClientePorId(id);
        if (cliente == null) {
            // Manejar el caso de cliente no encontrado
            return "redirect:/clientes";
        }
        model.addAttribute("cliente", cliente);
        return "clientes/formulario";
    }

    // Procesar el formulario de editar cliente
    @PostMapping("/editar/{id}")
    public String procesarFormularioDeEditarCliente(@PathVariable Long id, @ModelAttribute Cliente cliente) {
        clienteService.guardarCliente(cliente);
        return "redirect:/clientes";
    }

    // Ver detalles de un cliente
    @GetMapping("/{id}")
    public String verDetallesDelCliente(@PathVariable Long id, Model model) {
        Cliente cliente = clienteService.obtenerClientePorId(id);
        if (cliente == null) {
            // Manejar el caso de cliente no encontrado
            return "redirect:/clientes";
        }
        model.addAttribute("cliente", cliente);
        return "clientes/detalles";
    }

    // Eliminar un cliente
    @GetMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return "redirect:/clientes";
    }

}
