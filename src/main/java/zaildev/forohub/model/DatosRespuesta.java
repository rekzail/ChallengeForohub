package zaildev.forohub.model;

import zaildev.forohub.topico.Topico;
import zaildev.forohub.usuarios.Usuario;

public record DatosRespuesta(String mensaje, Topico topico, String fechaCreacion, Usuario usuario, Solucion solucion) {
}
