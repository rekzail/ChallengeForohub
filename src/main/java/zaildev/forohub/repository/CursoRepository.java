package zaildev.forohub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import zaildev.forohub.model.Curso;

import java.util.Optional;


public interface CursoRepository extends JpaRepository<Curso, Long> {
    Optional<Curso> findByNombre(String nombre);
}
