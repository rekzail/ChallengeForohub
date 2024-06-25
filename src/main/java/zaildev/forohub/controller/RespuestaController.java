package zaildev.forohub.controller;

import jakarta.validation.Valid;
import zaildev.forohub.model.Curso;
import zaildev.forohub.model.DatosCurso;
import zaildev.forohub.model.DatosRespuesta;
import zaildev.forohub.model.Respuesta;
import zaildev.forohub.repository.CursoRepository;
import zaildev.forohub.repository.RespuestaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/respuesta")
public class RespuestaController {
    @Autowired
    private RespuestaRepository respuestaRepository;

    @PostMapping
    public void registrarRespuesta(@RequestBody @Valid DatosRespuesta datosRespuesta){
        respuestaRepository.save(new Respuesta(datosRespuesta));
    }
}