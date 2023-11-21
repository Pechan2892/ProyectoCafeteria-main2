package coffeTime.org.ProyectoCafeteria.service.implementation;

import coffeTime.org.ProyectoCafeteria.dao.Dto.UsuarioRegistroDto;
import coffeTime.org.ProyectoCafeteria.dao.entity.Rol;
import coffeTime.org.ProyectoCafeteria.dao.entity.Usuario;
import coffeTime.org.ProyectoCafeteria.repository.UsuarioRepo;
import coffeTime.org.ProyectoCafeteria.service.Interface.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;


@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepo repositorio;

    @Override
    public Usuario guardar(Usuario usuario) {
        return repositorio.save(usuario);
    }

    @Override
    public Usuario guardarUsuario(UsuarioRegistroDto registroDto) {
        Usuario usuario=new Usuario(
                registroDto.getNombre(),
                registroDto.getApellido(),
                registroDto.getEmail(),
                registroDto.getPassword(),
                Arrays.asList(new Rol("ROLE_ADMIN")));
        return repositorio.save(usuario);
    }

    @Override
    public Usuario BuscarPorEmail(String email) {
        return repositorio.findByEmail(email);
    }

    @Override
    public Usuario obtenerPorId(Long id) {
        return repositorio.findById(id).orElse(null);
    }
}
