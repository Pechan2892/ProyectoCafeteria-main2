package coffeTime.org.ProyectoCafeteria.service.Interface;

import coffeTime.org.ProyectoCafeteria.dao.entity.Cliente;
import java.util.List;

public interface IClienteService {
    Cliente guardarCliente(Cliente cliente);
    Cliente obtenerClientePorId(Long id);
    List<Cliente> obtenerTodosLosClientes();
    void eliminarCliente(Long id);
}