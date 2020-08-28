package com.example.golden.service.Impl;

import com.example.golden.controller.PersonaController;
import com.example.golden.dto.PersonaDTO;
import com.example.golden.service.PersonaService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

@Service
@RefreshScope
public class PersonaServiceImpl implements PersonaService {

    private Logger LOGGER = LoggerFactory.getLogger(PersonaController.class);

    public static final String MAXIMO_TIEMPO_DE_TOLERANCIA = "1000";

    @Value("${timeoutMessage:Mensaje por defecto, no se pudo obtener el de Knowsmore}")
    private String timeoutMessage;

    @Value("${waitTime:0}")
    private Long waitTime;

    @Override
    @HystrixCommand(fallbackMethod = "fallbackObtenerPersona", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",
                    value = MAXIMO_TIEMPO_DE_TOLERANCIA)
    })
    public String obtenerPersona(String id) throws InterruptedException {
        // Se espera una cantidad de tiempo sacada del Config para probar el funcionamiento de Golden
        if (waitTime != null && waitTime > 0) {
            LOGGER.info("waitTime: {}", waitTime);
            Thread.sleep(waitTime);
        }

        // Respuesta utiliza el id proveido pero el resto de los datos estan en duro
        PersonaDTO personaDTO = new PersonaDTO();
        personaDTO.setId(id);
        personaDTO.setApellidos("Apellidos Prueba");
        personaDTO.setNombres("Nombres Prueba");
        personaDTO.setImagen(null);

        return personaDTO.toString();
    }

    public String fallbackObtenerPersona(String id) {
        return timeoutMessage;
    }

}
