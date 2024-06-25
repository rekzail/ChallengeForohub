package zaildev.forohub.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import zaildev.forohub.model.*;
import zaildev.forohub.repository.CursoRepository;
import zaildev.forohub.repository.PerfilRepository;
import zaildev.forohub.repository.TopicoRepository;
import zaildev.forohub.repository.UsuarioRepository;
import zaildev.forohub.topico.DatosRegistroTopico;
import zaildev.forohub.topico.Estado;
import zaildev.forohub.topico.Topico;
import zaildev.forohub.usuarios.Usuario;

//import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name ="bearer-key")
@Tag(name = "Topicos", description = "Operaciones relacionadas con topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private PerfilRepository perfilRepository;


    @PostMapping
    @Transactional
    @Operation(summary = "Registrar un nuevo Topico", security = @SecurityRequirement(name = "bearer-key"))
    public ResponseEntity <DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico, UriComponentsBuilder uriComponentsBuilder) {
        // Verificar si el estado es ACTIVO
        if (datosRegistroTopico.estado() != Estado.ACTIVO) {
            throw new IllegalArgumentException("El estado del tópico debe ser ACTIVO.");
        }

        // Verificar si el tópico con el mismo título y mensaje ya existe
        if (topicoRepository.findByTituloAndMensaje(datosRegistroTopico.titulo(), datosRegistroTopico.mensaje()).isPresent()) {
            throw new IllegalArgumentException("El tópico con el mismo título y mensaje ya existe.");
        }

        // Obtener el usuario
        Usuario usuario = usuarioRepository.findByNombre(datosRegistroTopico.usuario())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado."));

        // Obtener el curso
        Curso curso = cursoRepository.findByNombre(datosRegistroTopico.curso())
                .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado."));

        // Crear el tópico y asignar usuario, curso y fecha de creación
        Topico topico = new Topico(datosRegistroTopico);
        topico.setUsuario(usuario);
        topico.setCurso(curso);
        topico.setFechaCreacion(LocalDateTime.now()); // Asignar la fecha de creación automáticamente

        // Guardar el tópico
        topicoRepository.save(topico);

        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                java.sql.Timestamp.valueOf(topico.getFechaCreacion()),
                topico.getEstado(),
                topico.getUsuario().getNombre(),
                topico.getCurso().getNombre()
        );
        URI url = uriComponentsBuilder.path("/topicos/datos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopico);
    }

    @GetMapping
    @Operation(summary = "Obtener el listado de Tópicos registrados", security = @SecurityRequirement(name = "bearer-key"))
    public ResponseEntity<List<DatosListadoTopicos>> listadoTopicos(
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sort", defaultValue = "titulo") String[] sort
    ) {
        // Crear objeto de paginación con orden ascendente por el campo especificado en 'sort'
        PageRequest pageRequest = PageRequest.of(page != null ? page : 0, size, Sort.by(sort));

        // Obtener la página de resultados
        Page<Topico> topicosPage = topicoRepository.findAll(pageRequest);

        // Mapear los resultados a la clase DatosListadoTopicos y devolver la lista en un ResponseEntity
        List<DatosListadoTopicos> datosListadoTopicos = topicosPage.getContent().stream()
//                .map(topico -> DatosListadoTopicos.fromTopico(topico))
                .map(DatosListadoTopicos::fromTopico)
                .collect(Collectors.toList());

        return ResponseEntity.ok(datosListadoTopicos);
    }

@GetMapping("/{id}")
@Operation(summary = "Obtener un tópico por ID", security = @SecurityRequirement(name = "bearer-key"))
public ResponseEntity<DatosListadoTopicos> obtenerTopicoPorId(@PathVariable Long id) {
    Topico topico = topicoRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró un tópico con el ID: " + id));

    DatosListadoTopicos datosListadoTopicos = DatosListadoTopicos.fromTopico(topico);
    return ResponseEntity.ok(datosListadoTopicos);
}


    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Actualizar un Tópico", security = @SecurityRequirement(name = "bearer-key"))
    public ResponseEntity<DatosRespuestaTopico> actualizarTopico(@PathVariable Long id, @RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {
        Optional<Topico> optionalTopico = topicoRepository.findById(id);
        if (optionalTopico.isPresent()) {
            Topico topico = optionalTopico.get();
            topico.actualizarDatos(datosActualizarTopico);
            DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(
                    topico.getId(),
                    topico.getTitulo(),
                    topico.getMensaje(),
                    java.sql.Timestamp.valueOf(topico.getFechaCreacion()),
                    topico.getEstado(),
                    topico.getUsuario().getNombre(),
                    topico.getCurso().getNombre()
            );
            return ResponseEntity.ok(datosRespuestaTopico);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró un tópico con el ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Eliminar un Tópico", security = @SecurityRequirement(name = "bearer-key"))
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        Optional<Topico> optionalTopico = topicoRepository.findById(id);
        if (optionalTopico.isPresent()) {
            topicoRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró un tópico con el ID: " + id);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping ("/datos/{id}")
    @Operation(summary = "Consultar los datos de un Tópico", security = @SecurityRequirement(name = "bearer-key"))
    public ResponseEntity<DatosRespuestaTopico> retornaDatosTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        var datosTopico = new  DatosRespuestaTopico(topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                java.sql.Timestamp.valueOf(topico.getFechaCreacion()),
                topico.getEstado(),
                topico.getUsuario().getNombre(),
                topico.getCurso().getNombre()
        );
        return ResponseEntity.ok(datosTopico);
    }

}









