package coffeTime.org.ProyectoCafeteria.controller;

import coffeTime.org.ProyectoCafeteria.dao.Dto.UsuarioRegistroDto;
import coffeTime.org.ProyectoCafeteria.dao.entity.Usuario;
import coffeTime.org.ProyectoCafeteria.service.Interface.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/api/usuarios")
public class RegistroUsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public RegistroUsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/registro")
    public ResponseEntity<String> registrarUsuario(@Validated @ModelAttribute("usuario") UsuarioRegistroDto registroDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            // Manejar errores de validación, si los hay
            return ResponseEntity.badRequest().body("Error de validación en los datos del usuario");
        }

        Usuario usuarioExistente = usuarioService.BuscarPorEmail(registroDto.getEmail());
        if (usuarioExistente != null) {
            model.addAttribute("mensajeError", "El correo electrónico ya está registrado.");
            // Retornar ResponseEntity con mensaje de correo ya registrado
            return ResponseEntity.badRequest().body("El correo electrónico ya está registrado");
        }

        usuarioService.guardarUsuario(registroDto);
        // Retornar ResponseEntity con mensaje de éxito
        return ResponseEntity.ok("Usuario registrado con éxito");
    }

    @GetMapping("/registro")
    public String mostrarFormularioRegistro() {
        return "registro"; // Asegúrate de que la vista Thymeleaf tenga la ruta "/registro"
    }


}

