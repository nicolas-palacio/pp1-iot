<div align="center">
  <img src="https://i.ibb.co/XV6SRnj/logo-IOT.png" alt="logo" width="200" height="auto" />
  <h1>IOTeam</h1>
</div>

## Acerca del proyecto

Este proyecto consiste en la implementación de una API REST para persistir y disponibilizar las mediciones de diversos tipos de sensores que estan ubicados en diferentes partes de una escuela. Además, se incluye una gestion de autorizaciones de acuerdo al rol del usuario que realice las diversas peticiones.

## Documentación en Swagger

[Acceso a la documentación detallada de la API](https://pp1-iot.herokuapp.com/iot-api.html).

## Features

- CRUD de las siguientes entidades: Area, Sensor, Registro, Usuario, Solicitud.
- Para realizar un Alta, Baja o Modificación de un Sensor, se debe realizar una Solicitud, completando los datos que se detallan en la documentacion de la API.
- Cada rol tiene diferentes accesos.

  - Directivo:

    - [x] Visualizar los registros de los sensores.
    - [x] Visualizar las solicitudes de ABM de sensores.
    - [x] Realizar solicitudes de ABM de sensores.

  - Técnico:

    - [x] Visualizar los registros de los sensores.
    - [x] Visualizar las solicitudes de ABM de sensores.
    - [x] Realizar solicitudes de ABM de sensores.
    - [x] Gestionar las solicites de ABM de sensores.

  - Alumno:

    - [x] Visualizar los registros de los sensores.

  - Administrador:
    - [x] Visualizar los registros de los sensores.
    - [x] Visualizar las solicitudes de ABM de sensores.
    - [x] Realizar solicitudes de ABM de sensores.
    - [x] Gestionar las solicites de ABM de sensores.

## Modelo físico de la base de datos

<img src="https://i.ibb.co/5WTK8h5/bd-diagram.jpg" alt="bd-diagram"/>

## Demo

Se cuenta con un portal web en el que se puede visualizar las funcionalidades del proyecto.

- [Ingresar al portal como Directivo](https://portalsensores-iot.github.io/home/?usuario=prueba&email=pd@gmail.com&categoria=directivo&instituto=La%20Manzana%20de%20Isaac&ultimoanio=no&estecnico=no).
- [Ingresar al portal como Tecnico](https://portalsensores-iot.github.io/home/?usuario=test&email=ejemplo@ejemplo.com&categoria=directivo&instituto=La%20Manzana%20de%20Isaac&ultimoanio=no&estecnico=si).

## Documentación del proyecto

- [Plan de proyecto](https://drive.google.com/file/d/1UaLlHKapKY8LNc5yGALP8YhBjY57AYSr/view?usp=sharing)
- [Manual técnico](https://drive.google.com/file/d/1fi4kpVXi5GGSD3jdzVrp2AXYIIuZF1ZH/view?usp=sharing)
