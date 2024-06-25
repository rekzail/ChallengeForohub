package zaildev.forohub.controller;

import jakarta.validation.Valid;
import zaildev.forohub.model.DatosPerfil;
import zaildev.forohub.model.Perfil;
import zaildev.forohub.repository.PerfilRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Perfil")
public class PerfilController {
    @Autowired
    private PerfilRepository perfilRepository;

    @PostMapping
    public void registrarPerfil(@RequestBody @Valid DatosPerfil datosPerfil){
        perfilRepository.save(new Perfil(datosPerfil));
    }
}