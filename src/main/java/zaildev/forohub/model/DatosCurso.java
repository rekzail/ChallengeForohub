package zaildev.forohub.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosCurso(
        @NotBlank
        String nombre,
        @NotBlank
        String categoria) {

        public String getNombre() {
                return this.nombre;
        }

        public String getCategoria() {
                return this.categoria;
        }
}
