# Creación de aplicaciones enriquecidas con IA

## Descripción
Este repositorio contiene los materiales de ejemplo creados para el curso de *Creación de aplicaciones enriquecidas 
con IA*.

### Contenidos del curso
Durante el curso se trabajan los siguientes módulos:

1. Introducción a la IA
2. Modelos de lenguaje
3. Creación de aplicaciones enriquecidas
4. Retrieval-augmented generation
5. Agentes
6. LLMOps
7. Cierre

Y se utilizan las siguientes herramientas:
1. ollama
2. OpenWebUI
3. LangChain4J
4. Milvus
5. Langfuse

## Contenido del repositorio
En este repositorio va a encontrar el código fuente de un proyecto web, basado en tecnologías Java.
El proyecto se ha creado con Spring Boot, Vaadin y LangChain4J.

Desde esta aplicación web se podrán acceder a los siguientes servicios de IA:
1. Servicio de chatbot
2. Servicio de chatbot en streaming
3. Servicio de análisis de sentimientos
4. Servicio analizador de prioridades en issues
5. Servicio extractor de datos de personas
6. Servicio generador de imágenes
7. Servicio generador de textos alternativos para imágenes
8. Servicio de chatbot con RAG naive
9. Servicio de chatbot con RAG avanzado
10. Servicio de chatbot con RAG modular
11. Servicio de chatbot/copiloto


## Instrucciones de uso
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



