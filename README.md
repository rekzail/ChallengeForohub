# Challenger - Foro Hub

![Java](https://img.shields.io/badge/Java-17-blue)
![Maven](https://img.shields.io/badge/Maven-3.6.3-C71A36?logo=apache-maven)
![Spring Security](https://img.shields.io/badge/Spring%20Security-5.3.3-brightgreen?logo=spring)
![MySQL](https://img.shields.io/badge/MySQL-8.0.21-orange?logo=mysql)

Esta aplicacion corresponde al desarrollo del Challenge 3 de la especializacion en BACK END en el programa de Alura Latam ONEG6, que consiste en desarrollar una API para un Foro donde es posible realizar las operaciones del CRUD (Crear, Listar, Actualizar y Eliminar) con tópicos.

Documentare los endpoins para que se pueda testear de manera mas sencilla la app.



## Registrar

![Imagen Registro](assets/registrar.jpg)


### Descripción

Este endpoint permite registrar un nuevo usuario en el sistema.

Ruta: http://localhost:8080/register

Metodo: <strong>POST</strong> 

```json
{
"nombre": "zail",
"correoElectronico": "zail@example.com",
"contrasena": "admin123"
}
```

<hr/>

## Login 

![Imagen login](assets/registrar.jpg)

### Descripción
Este endpoint permite autenticarse en el sistema.

Ruta: http://localhost:8080/register

Metodo: <strong>POST</strong> 

```json
{
"nombre": "zail",
"contrasena": "admin123"
}
```

<hr/>

## Registrar un curso 

![Imagen Registro Curso](assets/agregarCursos.jpg)

### Descripción
Este endpoint permite registrar un nuevo curso en el sistema.

Ruta: http://localhost:8080/curso

Metodo: <strong>POST</strong> 

```json
{
  "nombre": "JAVA",
  "categoria": "Programacion"
}
```

<hr/>

## Registrar un Topico

![Imagen Registro Curso](assets/crear.jpg)

### Descripción
Este endpoint permite crear un nuevo topico en el sistema.

Ruta: http://localhost:8080/topicos

Metodo: <strong>POST</strong> 

```json
{
    "titulo": "Mi nuevo tópico numero 4 modificado x1",
    "mensaje": "Este es el mensaje del tópico 4",
    "fechaCreacion": "2024-06-25T15:28:13.000+00:00",
    "estado": "ACTIVO",
    "nombreUsuario": "zail",
    "nombreCurso": "JAVA"
}
```

<hr/>

## Actualizar un Topico

![Imagen Registro Curso](assets/actualizar.jpg)

### Descripción
Este endpoint permite actualizar un nuevo topico en el sistema.

Ruta: http://localhost:8080/topicos/{id}

Metodo: <strong>PUT</strong> 

```json
{
    "titulo": "Mi nuevo tópico numero 4 modificado y actualizado",
    "mensaje": "Este es el mensaje del tópico 4 actualizado",
    "fechaCreacion": "2024-06-25T15:28:13.000+00:00",
    "estado": "ACTIVO",
    "nombreUsuario": "zail",
    "nombreCurso": "JAVA"
}
```

<hr/>


## Borrar un Topico

![Imagen Registro Curso](assets/borrar.jpg)

### Descripción
Este endpoint permite borrar un topico en el sistema.

Ruta: http://localhost:8080/topicos/{id}

Metodo: <strong>DELETE</strong> 

```json
{
// no necesita cuerpo.
}
```

<hr/>

## Listar todo Topico

![Imagen Registro Curso](assets/obtener.jpg)

### Descripción
Este endpoint permite obtener todos los topicos en el sistema.

Ruta: http://localhost:8080/topicos

Metodo: <strong>GET</strong> 

```json
{
// no necesita cuerpo.
}
```

<hr/>

## Listar Topico por id

![Imagen Registro Curso](assets/obtenerPorId.jpg)

### Descripción
Este endpoint permite obtener un topicos por id en el sistema.

Ruta: http://localhost:8080/topicos/{id}

Metodo: <strong>GET</strong> 

```json
{
// no necesita cuerpo.
}
```

<hr/>




  
