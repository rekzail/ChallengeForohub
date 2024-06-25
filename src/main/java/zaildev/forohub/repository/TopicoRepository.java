    package zaildev.forohub.repository;

    import org.springframework.data.jpa.repository.JpaRepository;

import zaildev.forohub.topico.Topico;

import java.util.Optional;

    public interface TopicoRepository extends JpaRepository<Topico, Long> {
        Optional<Topico> findByTituloAndMensaje(String titulo, String mensaje);

    }
