package zaildev.forohub.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import zaildev.forohub.usuarios.Usuario;

public record DatosActualizarTopico(
		 Long id, 
		@NotBlank String titulo,
		String mensaje, 
		Usuario usuario, 
		Curso curso) {
}
