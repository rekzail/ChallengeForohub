package zaildev.forohub.model;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "curso")
@Entity(name = "Curso")
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private String categoria;

    public Curso(DatosCurso curso) {
        this.nombre= curso.getNombre();
        this.categoria= curso.getCategoria();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
