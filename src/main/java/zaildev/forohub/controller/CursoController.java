package zaildev.forohub.controller;

import jakarta.validation.Valid;
import zaildev.forohub.model.Curso;
import zaildev.forohub.model.DatosCurso;
import zaildev.forohub.repository.CursoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/curso")
public class CursoController {
    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    public void registrarCurso(@RequestBody @Valid DatosCurso datosCurso){
        cursoRepository.save(new Curso(datosCurso));
    }
}

