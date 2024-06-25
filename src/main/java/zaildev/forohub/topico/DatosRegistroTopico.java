package zaildev.forohub.topico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import zaildev.forohub.model.*;

import java.util.List;

public record DatosRegistroTopico(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
       // @NotBlank
 //       String fechaCreacion,
        @NotNull
        Estado estado,
        @NotNull
        @Valid
        String usuario,
        @NotNull
        @Valid
        String curso,
        List<DatosRespuesta> respuestas) {
}
