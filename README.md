# microservicios-tp
* gandalf = Eureka Server
* knowsmore = Config Server
* kangaroo = Microservice - 2 instancias con balanceo de carga
* golden = Microservice - 1 instancia
* arquimedes = Api Gateway

# Pasos para levantar ambiente
* Ejecutar `sh build-jars`
* Levantar serviciós con `docker-compose up --build --force-recreate`
* Utilizar colección de insomnia `Coleccion Endpoints - Insomnia.json

**OBS:** Api-gateway arquimedes puede tardar un tiempo en 
buscar los servicios registrados en Gandalf
