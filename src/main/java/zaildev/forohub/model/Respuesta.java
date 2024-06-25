package zaildev.forohub.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import zaildev.forohub.topico.Topico;
import zaildev.forohub.usuarios.Usuario;


@Table(name = "respuesta")
@Entity(name = "Respuesta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false) // Marca la columna 'id' como de solo lectura
    private long id;
    private String mensaje;
    private String fechaCreacion;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @Enumerated(EnumType.STRING)
    private Solucion solucion;

    @ManyToOne
    @JoinColumn(name = "topico_id")
    private Topico topico;


    public Respuesta(DatosRespuesta respuesta) {
        this.mensaje = respuesta.mensaje().toString();
        this.fechaCreacion = respuesta.fechaCreacion();
        this.usuario = respuesta.usuario();
        this.solucion = respuesta.solucion();
        this.topico = respuesta.topico();
    }
}

