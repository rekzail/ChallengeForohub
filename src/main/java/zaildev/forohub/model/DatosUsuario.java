package zaildev.forohub.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DatosUsuario(
        @NotBlank
        String nombre,
        @NotBlank
                @Email
        String correoElectronico,
        @NotBlank
        String contrasena,
        @NotNull
        List<DatosPerfil> perfiles) {

        public String getNombre() {
                return this.nombre;
        }

        public String getCorreoElectronico() {
                return this.correoElectronico;
        }

        public String getContrasena() {
                return this.contrasena;
        }
}
