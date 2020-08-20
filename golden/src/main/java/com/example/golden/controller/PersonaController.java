package com.example.golden.controller;

// import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
// import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import com.example.golden.dto.PersonaDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

// import org.springframework.cloud.client.ServiceInstance;
// import org.springframework.cloud.client.discovery.DiscoveryClient;
// import org.springframework.cloud.context.config.annotation.RefreshScope;

@RestController
// @RefreshScope
public class PersonaController {

    private Logger LOGGER = LoggerFactory.getLogger(PersonaController.class);

    /*
    public static final String MAXIMO_TIEMPO_DE_TOLERANCIA = "5000";

    @Value("${timeoutInMilliseconds:1000}")
    private Long timeout;

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/service-instances/{applicationName}")
    public List<ServiceInstance> serviceInstancesByApplicationName(
            @PathVariable String applicationName) {
        return this.discoveryClient.getInstances(applicationName);
    }
    */

    /**
     * Retorna un Objeto de Persona con el ID proveido, valida que el id tenga la longitud apropiada
     *
     * @param id Id de la persona a obtener, no puede ser mayor a un máximo de 10 caracteres
     */
    @GetMapping("/persona/{id}")
    public PersonaDTO obtenerPersona(@PathVariable(value = "id") String id) throws Exception {
        if (id.length() > 10 || id.length() < 1) {
            LOGGER.error("Campo 'id' debe tener una longitud entre 1 y 10 caracteres");
            throw new Exception("Campo 'id' debe tener una longitud entre 1 y 10 caracteres");
        }

        // Respuesta utiliza el id proveido pero el resto de los datos estan en duro
        PersonaDTO personaDTO = new PersonaDTO();
        personaDTO.setId(id);
        personaDTO.setApellidos("Apellidos Prueba");
        personaDTO.setNombres("Nombres Prueba");
        personaDTO.setImagen(new byte[0]);

        return personaDTO;
    }

    /**
     * Registra una persona en el sistema y retorna el objeto de la Persona
     *
     * @param personaDTO Datos de la Persona que se registrará en el sistema
     */
    @PostMapping("/persona/registrar")
    public PersonaDTO registrarPersona(@RequestBody PersonaDTO personaDTO) throws Exception {
        // Validación
        if (personaDTO.getId() == null) {
            throw new Exception("Campo 'id' en el Body es obligatorio");
        }
        if (personaDTO.getId().length() > 10 || personaDTO.getId().length() < 1) {
            LOGGER.error("Campo 'id' debe tener una longitud entre 1 y 10 caracteres");
            throw new Exception("Campo 'id' debe tener una longitud entre 1 y 10 caracteres");
        }

        if (personaDTO.getNombres() == null) {
            throw new Exception("Campo 'nombres' en el Body es obligatorio");
        }
        if (personaDTO.getNombres().length() > 100 || personaDTO.getNombres().length() < 1) {
            LOGGER.error("Campo 'nombres' debe tener una longitud entre 1 y 100 caracteres");
            throw new Exception("Campo 'nombres' debe tener una longitud entre 1 y 100 caracteres");
        }

        if (personaDTO.getApellidos() == null) {
            throw new Exception("Campo 'apellidos' en el Body es obligatorio");
        }
        if (personaDTO.getApellidos().length() > 200 || personaDTO.getApellidos().length() < 1) {
            LOGGER.error("Campo 'apellidos' debe tener una longitud entre 1 y 200 caracteres");
            throw new Exception("Campo 'apellidos' debe tener una longitud entre 1 y 200 caracteres");
        }

        // No se guarda nada en una BD por lo que solo imprimimos los datos pasados que se guardaron y ya
        LOGGER.info("Se guardo a la persona con los datos " + personaDTO.toString());
        return (personaDTO);
    }
}
