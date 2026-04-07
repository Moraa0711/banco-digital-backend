# 🏦 Sistema de Banco Digital - Backend (Sprint 1)

Este proyecto constituye la base tecnológica del **Banco Digital**, desarrollada con el objetivo de gestionar la identidad de los clientes y asegurar la persistencia de datos en una infraestructura basada en la nube.

---

##  Logros del Sprint 1
En este primer ciclo de desarrollo, el equipo se enfocó en establecer la arquitectura base y cumplir con las siguientes funcionalidades:

* **HU-1: Registro de Clientes:** Implementación de endpoint para la creación de nuevos usuarios con validación de tipo de documento.
* **HU-2: Consulta de Clientes:** Endpoint para la recuperación de información detallada de un cliente mediante su identificador único.
* **CRUD Completo:** Adicionalmente, se habilitaron las operaciones de actualización (PUT) y eliminación (DELETE).

---

##  Tecnologías Utilizadas
El proyecto ha sido construido bajo estándares empresariales modernos:

* **Lenguaje:** Java 21
* **Framework:** Spring Boot 3.2.4
* **Gestión de Dependencias:** Maven
* **Persistencia:** Spring Data JPA con Hibernate
* **Base de Datos:** PostgreSQL hospedada en **Neon Cloud DB**
* **Documentación:** Swagger UI (OpenAPI 3.0)

---

##  Estructura del Proyecto
```text
src/main/java/com/bancodigital/backend/
├── BackendApplication.java    # Clase principal de arranque
└── ClienteController.java     # Lógica de negocio (Modelos, Repositorios y Controladores)
