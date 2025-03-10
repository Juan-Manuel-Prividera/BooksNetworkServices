# Proyecto Microservicios con Java + Spring

Este es un proyecto basado en arquitectura de microservicios utilizando Java y Spring Framework. El proyecto está diseñado para utilizarlo como un backend de una app mobile similar a una red social pero con orientación a libros.

## Descripción

Este proyecto está compuesto por múltiples microservicios, cada uno de ellos enfocado en una funcionalidad específica. Los servicios se comunican entre sí mediante APIs REST y utilizan un **Service Registry** para el descubrimiento y registro automático de servicios, lo que facilita la escalabilidad y la administración de los microservicios.

Cada servicio está registrado en un **Service Registry** (usando **Eureka** en este caso) y puede ser descubierto por otros microservicios sin necesidad de configuraciones manuales de direcciones IP o puertos.

## Características

- **Microservicios independientes:** Cada servicio es autónomo, lo que facilita el desarrollo y la escalabilidad.
- **Comunicación entre microservicios:** Los servicios se comunican mediante APIs REST y se encuentran a través de un **Service Registry**.
- **Base de datos independiente por servicio:** Cada microservicio maneja su propia base de datos, lo que promueve la independencia.
- **Seguridad:** Autenticación y autorización implementadas con Spring Security.
- **Configuración centralizada:** Uso de Spring Cloud Config para la gestión centralizada de configuraciones.
- **Escalabilidad:** Cada microservicio puede escalar de manera independiente según las necesidades del sistema.
- **Gestión de fallos y resiliencia:** Implementación de mecanismos de tolerancia a fallos utilizando Spring Cloud Circuit Breaker.
- **Service Registry:** Los microservicios se registran y descubren automáticamente mediante un **Service Registry** (Eureka).

## Tecnologías Utilizadas
- Spring Boot: Framework para la creación de aplicaciones Java basadas en microservicios.
- Spring Cloud: Herramientas para crear aplicaciones distribuidas y microservicios resilientes.
- Spring Security: Para la gestión de seguridad en las aplicaciones.
- Spring Data JPA: Para la persistencia de datos.
- Eureka: Para el descubrimiento de servicios.
- Spring Cloud Config: Para la configuración centralizada.
- PostgreSQL Y MongoDB: Según las necesidades de cada microservicio.
