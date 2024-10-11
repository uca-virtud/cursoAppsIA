# Creación de aplicaciones enriquecidas con IA

## Descripción
Este repositorio contiene los materiales de ejemplo creados para el curso de *Creación de aplicaciones enriquecidas 
con IA*.

### Contenidos del curso
Durante el curso se trabajan los siguientes módulos:

0. [Bienvenida](./slides/0-Creacion-de-aplicaciones-enriquecidas-con-IA.pdf)
1. [Introducción a la IA](./slides/1-Inteligencia-Artificial.pdf)
2. [Modelos de lenguaje](./slides/2-Modelos-del-lenguaje.pdf)
3. [Integrando LLM en aplicaciones](./slides/3-Integrando-LLM-en-Aplicaciones.pdf)
4. [Prompt Engineering](./slides/4-Prompt-Engineering.pdf)
5. Retrieval-augmented generation
6. Agentes
7. LLMOps
8. Cierre


## Contenido del repositorio
En este repositorio va a encontrar el código fuente de un proyecto web, basado en tecnologías Java.
El proyecto se ha creado utilizando los frameworks Spring Boot, Vaadin y LangChain4J.

La aplicación ofrece las siguientes funcionalidades:
1. Generador de texto
2. Generador de imágenes
3. Descriptor de imágenes (multimodal)
4. Traductor de texto
5. "Resumidor" de texto
6. Analizador de sentimientos
7. Extractor de datos
8. Chat simple
9. Chat con memoria
10. Asistente
11. Servicio web del chat


## Instrucciones de uso

### Modificar la aplicación
Puede hacer uso de este proyecto de dos maneras diferentes: 
- Desde su máquina de desarrollo, haciendo un *git clone* del proyecto y trabajando de forma local con su IDE. 
- Usando un *codespace* de GitHub. Un codespace es un entorno de desarrollo basado en la nube que se puede usar 
  directamente sin necesidad de realizar ninguna configuración adicional.


### Ejecutando la aplicación
El proyecto es un proyecto estándar de Maven. Para ejecutarlo desde la línea de comandos, escribe mvnw (Windows) o ./mvnw (Mac y Linux), luego abre http://localhost:8080 en tu navegador.

También puedes importar el proyecto a tu IDE de preferencia como lo harías con cualquier proyecto de Maven. Lee más sobre cómo importar proyectos de Vaadin a diferentes IDEs (Eclipse, IntelliJ IDEA, NetBeans y VS Code).

### Estructura del proyecto
- MainLayout.java en src/main/java contiene la configuración de navegación (es decir, la barra lateral/superior y el 
menú principal). Esta configuración utiliza App Layout.
- El paquete views en src/main/java contiene las vistas Java del lado del servidor de tu aplicación.
- La carpeta views en src/main/frontend contiene las vistas JavaScript del lado del cliente de tu aplicación.
- La carpeta themes en src/main/frontend contiene los estilos CSS personalizados.

## Enlaces útiles
- [Spring](https://spring.io)
- [Vaadin](https://vaadin.com)
- [LangChain4J](https://docs.langchain4j.dev/)

## Autoría
Este proyecto ha sido desarrollado por [Iván Ruiz Rube](https://www.linkedin.com/in/iv%C3%A1n-ruiz-rube-0970331a).



