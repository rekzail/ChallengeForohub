package zaildev.forohub.model;

import zaildev.forohub.topico.Estado;
import zaildev.forohub.topico.Topico;


public record DatosListadoTopicos(
        Long id,
        String titulo,
        String mensaje,
        java.sql.Timestamp fechaCreacion,
        Estado estado,
        String nombreUsuario,
        String nombreCurso
) {

    public static DatosListadoTopicos fromTopico(Topico topico) {
        return new DatosListadoTopicos(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                java.sql.Timestamp.valueOf(topico.getFechaCreacion()), // Convertir LocalDateTime a Timestamp
                topico.getEstado(),
                topico.getUsuario() != null ? topico.getUsuario().getNombre() : "Usuario no disponible",
                topico.getCurso() != null ? topico.getCurso().getNombre() : "Curso no disponible"
        );
    }
}


