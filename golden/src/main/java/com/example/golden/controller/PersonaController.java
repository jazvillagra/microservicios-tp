package com.example.golden.controller;

import com.example.golden.dto.PersonaDTO;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.MalformedParametersException;

@RestController
@RefreshScope
public class PersonaController {

    private Logger LOGGER = LoggerFactory.getLogger(PersonaController.class);


    public static final String MAXIMO_TIEMPO_DE_TOLERANCIA = "5000";

    @Value("${timeoutInMilliseconds:100}")
    private Long timeout;

    /**
     * Retorna un Objeto de Persona con el ID proveido, valida que el id tenga la longitud apropiada
     *
     * @param id Id de la persona a obtener, no puede ser mayor a un máximo de 10 caracteres
     */
    @GetMapping("/persona/{id}")
    @HystrixCommand(fallbackMethod = "fallbackObtenerPersona", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",
                    value = MAXIMO_TIEMPO_DE_TOLERANCIA)
    })
    public String obtenerPersona(@PathVariable(value = "id") String id) throws MalformedParametersException, InterruptedException {
        // Validación
        if (id.length() > 10 || id.length() < 1) {
            LOGGER.error("Campo 'id' debe tener una longitud entre 1 y 10 caracteres");
            throw new MalformedParametersException("Campo 'id' debe tener una longitud entre 1 y 10 caracteres");
        }

        // Respuesta utiliza el id proveido pero el resto de los datos estan en duro
        PersonaDTO personaDTO = new PersonaDTO();
        personaDTO.setId(id);
        personaDTO.setApellidos("Apellidos Prueba");
        personaDTO.setNombres("Nombres Prueba");
        personaDTO.setImagen(new byte[0]);

        if (timeout != null) {
            LOGGER.info("timeout: {}", timeout);
            Thread.sleep(timeout);
        } else {
            LOGGER.info("Sin timeout configurado");
            Thread.sleep(3000);
        }

        return personaDTO.toString();
    }

    public String fallbackObtenerPersona(String id) {
        return "Error Error Error, Fallback fue ejecutado";
    }

}
