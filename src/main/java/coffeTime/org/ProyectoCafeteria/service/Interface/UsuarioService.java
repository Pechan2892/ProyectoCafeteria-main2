package coffeTime.org.ProyectoCafeteria.service.Interface;

import coffeTime.org.ProyectoCafeteria.dao.Dto.UsuarioRegistroDto;
import coffeTime.org.ProyectoCafeteria.dao.entity.Usuario;

public interface UsuarioService{
    Usuario guardar(Usuario usuario);

    Usuario guardarUsuario(UsuarioRegistroDto registroDto);

    Usuario BuscarPorEmail(String email);
    Usuario obtenerPorId(Long id);


}
