package coffeTime.org.ProyectoCafeteria.service.implementation;

import coffeTime.org.ProyectoCafeteria.dao.entity.Cliente;
import coffeTime.org.ProyectoCafeteria.repository.ClienteRepository;
import coffeTime.org.ProyectoCafeteria.service.Interface.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements IClienteService {
    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente obtenerClientePorId(Long id) {
        if (id == null) {
            // Manejar el caso en el que el ID es nulo
            return null;
        }
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    public List<Cliente> obtenerTodosLosClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}