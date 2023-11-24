package coffeTime.org.ProyectoCafeteria.service.implementation;

import coffeTime.org.ProyectoCafeteria.dao.Dto.UsuarioRegistroDto;
import coffeTime.org.ProyectoCafeteria.dao.entity.Usuario;
import coffeTime.org.ProyectoCafeteria.repository.UsuarioRepo;
import coffeTime.org.ProyectoCafeteria.service.Interface.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
        if (registroDto.getArchivoAdjunto() == null || registroDto.getArchivoAdjunto().isEmpty()) {
            throw new IllegalArgumentException("El archivo adjunto es obligatorio");
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(registroDto.getNombre());
        usuario.setApellido(registroDto.getApellido());
        usuario.setEmail(registroDto.getEmail());
        usuario.setPassword(registroDto.getPassword());

        // Manejar el archivo adjunto
        MultipartFile adjunto = registroDto.getArchivoAdjunto();
        if (!esTipoArchivoValido(adjunto)) {
            throw new IllegalArgumentException("El tipo de archivo adjunto no es válido");
        }

        try {
            byte[] archivoAdjunto = adjunto.getBytes();
            usuario.setArchivoAdjunto(archivoAdjunto);
        } catch (IOException e) {
            throw new RuntimeException("Error al procesar el archivo adjunto");
        }

        // Aquí podrías agregar lógica para manejar roles u otros detalles del usuario

        return repositorio.save(usuario);
    }

    private boolean esTipoArchivoValido(MultipartFile adjunto) {
        // Ejemplo de validación del tipo de archivo
        String contentType = adjunto.getContentType();
        // Aquí podrías verificar si el tipo es el esperado

        return contentType != null;
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
