# Taller de Arquitecturas de Servidores de Aplicaciones, Meta protocolos de objetos, Patrón IoC, Reflexión

Manejo de Spring 

## Clonacion del repositorio 

Para clonar el proyecto usar el siguiente comando:

```
git clone https://github.com/mariahv9/SpringAREP.git
```

### Ejecucion App

```
cd SpringAREP
mvn package
java -cp target/classes edu.escuelaing.arep.microSpring.MicroSpringBoot edu.escuelaing.arep.componentTests.HelloController 
```

### Web

Las siguientes son las paginas web después de que el puerto este escuchando:
```
localhost:36000/Apps/pi
localhost:36000/Apps/hello
localhost:36000/Apps/web 
```

## CircleCI
[![CircleCI](https://circleci.com/gh/mariahv9/SpringAREP.svg?style=svg&circle-token=fa8c51f3bedd926b133267148a5e3c22e1617f4a)](https://app.circleci.com/pipelines/github/mariahv9/SpringAREP?branch=master)

## Construido con 

* [Java 8](https://www.java.com/es/about/whatis_java.jsp)
* [Maven](https://maven.apache.org/) - Manejador de dependencias
* [CircleCI](https://circleci.com/) - Control 

## Autor

* **Maria Fernanda Hernandez Vargas** - [mariahv9](https://github.com/mariahv9)


## Licencia

Este proyecto está bajo la Licencia GNU General Public License - mira el archivo [LICENSE.txt](LICENSE.txt) para detalles

