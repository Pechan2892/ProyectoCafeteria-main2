package coffeTime.org.ProyectoCafeteria.controller;

import coffeTime.org.ProyectoCafeteria.dao.entity.Usuario;
import coffeTime.org.ProyectoCafeteria.service.Interface.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class RegistroController {

    private final UsuarioService userService;

    @Autowired
    public RegistroController(UsuarioService userService) {
        this.userService = userService;
    }

    @GetMapping("/registro")
    public String registro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute("usuario") @Valid Usuario usuario,
                                   BindingResult bindingResult,
                                   @RequestParam("imagenPerfil") MultipartFile imagenPerfil) {
        if (bindingResult.hasErrors()) {
            return "registro";
        }

        if (!imagenPerfil.isEmpty()) {
            try {
                usuario.setImagenPerfil(imagenPerfil.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        userService.guardar(usuario);
        return "redirect:/inicio-sesion";
    }
    @GetMapping("/imagenPerfil/{userId}")
    public ResponseEntity<byte[]> obtenerImagenPerfilUsuario(@PathVariable Long userId) {
        Usuario usuario = userService.obtenerPorId(userId);
        if (usuario != null && usuario.getImagenPerfil() != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // Ajusta el tipo de contenido según sea necesario

            return new ResponseEntity<>(usuario.getImagenPerfil(), headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
