package coffeTime.org.ProyectoCafeteria.dao.Dto;
import coffeTime.org.ProyectoCafeteria.dao.entity.Usuario;
import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.constraints.*;

public class UsuarioRegistroDto {
    private Long id;

    @NotBlank(message = "el nombre no puede estar vacio")
    @NotEmpty(message = "el nombre no debe ser espacios")
    @Size(min = 3, max = 30)
    private String nombre;

    @NotEmpty(message = "El apellido no puede estar vacio")
    @NotBlank(message = "El apellido no debe ser espacios")
    @Size(min = 3, max = 30)
    private String apellido;

    @Email(message = "El formato del correo electrónico no es válido")
    @Pattern(
            regexp = "^[A-Za-z0-9_\\-+]+(\\.[A-Za-z0-9_\\-+]+)*@(gmail\\.com|hotmail\\.com|outlook\\.com)$",
            message = "El correo electrónico no es válido"
    )
    private String email;

    @NotEmpty(message = "El contraseña no puede estar vacio")
    @NotBlank(message = "El contraseña no debe ser espacio")
    @Size(min = 4, max = 15)
    private String password;

    private MultipartFile archivoAdjunto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public MultipartFile getArchivoAdjunto() {
        return archivoAdjunto;
    }

    public void setArchivoAdjunto(MultipartFile archivoAdjunto) {
        this.archivoAdjunto = archivoAdjunto;
    }
    public Usuario convertirAUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNombre(this.nombre);
        usuario.setApellido(this.apellido);
        usuario.setEmail(this.email);
        // Establece otros campos del usuario si es necesario

        return usuario;
    }
}