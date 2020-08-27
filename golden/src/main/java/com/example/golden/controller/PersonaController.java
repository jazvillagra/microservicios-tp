package com.example.golden.controller;

import com.example.golden.service.PersonaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.MalformedParametersException;

@RestController
public class PersonaController {

    private Logger LOGGER = LoggerFactory.getLogger(PersonaController.class);

    @Autowired
    PersonaService personaService;

    /**
     * Retorna un Objeto de Persona con el ID proveido, valida que el id tenga la longitud apropiada
     *
     * @param id Id de la persona a obtener, no puede ser mayor a un máximo de 10 caracteres
     */
    @GetMapping("/persona/{id}")
    public String obtenerPersona(@PathVariable(value = "id") String id) throws MalformedParametersException, InterruptedException {
        if (id == null || id.length() > 10 || id.length() < 1) {
            LOGGER.error("Campo 'id' debe tener una longitud entre 1 y 10 caracteres");
            throw new MalformedParametersException("Campo 'id' debe tener una longitud entre 1 y 10 caracteres");
        }

        return personaService.obtenerPersona(id);
    }

}
