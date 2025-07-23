# 🦷 Sistema de Gestión de Clínica Dental

Cátedra Sistemas Distribuidos, Universidad del Aconcagua, Mendoza (2025).

## 🧑‍🎓 Autores

- **Germán Hidalgo**
- **Gabriel Pérez Diez**

## 📋 Descripción del Proyecto

Este proyecto universitario implementa un **Sistema de Gestión de Clínica Dental** desarrollado con Spring Boot y autenticación Auth0. El sistema permite la administración completa de una clínica odontológica, incluyendo la gestión de pacientes, dentistas, turnos, especialidades y disponibilidades.

### 🎯 Objetivo Académico

Desarrollar un sistema distribuido robusto que demuestre la implementación de:

- Arquitectura de microservicios con Spring Boot
- Autenticación y autorización OAuth2 con Auth0
- Gestión de bases de datos relacionales
- APIs RESTful documentadas
- Containerización con Docker
- Patrones de diseño empresariales

## 🛠️ Tecnologías Utilizadas

### Backend

- **Java 21** - Lenguaje de programación principal
- **Spring Boot 3.5.3** - Framework de aplicación
- **Spring Security** - Seguridad y autenticación
- **Spring Data JPA** - Persistencia de datos
- **OAuth2 Resource Server** - Integración con Auth0

### Base de Datos

- **PostgreSQL 15.13** - Base de datos relacional
- **Hibernate** - ORM (Object-Relational Mapping)

### Herramientas de Desarrollo

- **Lombok** - Reducción de código boilerplate
- **MapStruct 1.6.3** - Mapeo automático entre DTOs y entidades
- **OpenAPI/Swagger** - Documentación de API
- **Gradle** - Gestión de dependencias y construcción

### Infraestructura

- **Docker & Docker Compose** - Containerización
- **Auth0** - Servicio de autenticación y autorización

## 🏗️ Arquitectura del Sistema

### Estructura de Capas

```
📦 backend-auth0
├── 🎯 presentation/     # Capa de presentación
│   ├── controllers/     # Controladores REST
│   ├── dto/            # DTOs de request/response
│   └── config/         # Configuración de seguridad
├── 🧠 domain/          # Capa de dominio
│   ├── dto/            # DTOs de dominio
│   └── services/       # Lógica de negocio
└── 💾 data/            # Capa de datos
    ├── entities/       # Entidades JPA
    ├── repository/     # Repositorios
    ├── mapper/         # Mappers MapStruct
    └── enums/          # Enumeraciones
```

### Modelo de Datos

El sistema maneja las siguientes entidades principales:

- **👤 Usuario**: Información base de todos los usuarios del sistema
- **🏥 Paciente**: Datos específicos de pacientes (obra social, teléfono de emergencia)
- **👨‍⚕️ Dentista**: Información de dentistas (matrícula, especialidad)
- **👔 Administrador**: Usuarios con permisos administrativos
- **📅 Turno**: Citas médicas entre pacientes y dentistas
- **⚕️ Especialidad**: Especialidades odontológicas
- **🕐 DisponibilidadDentista**: Horarios disponibles de cada dentista

## 🚀 Funcionalidades Principales

### 👤 Gestión de Usuarios

- Registro y autenticación via Auth0
- Manejo de roles (Paciente, Dentista, Administrador)
- Perfil de usuario personalizable

### 📅 Sistema de Turnos

- Programación de citas médicas
- Estados de turno (Programado, Terminado, Cancelado)
- Notas de tratamiento y evolución
- Gestión de disponibilidades de dentistas

### 🏥 Administración Clínica

- Gestión de especialidades médicas
- Control de disponibilidades por dentista
- Reportes y seguimiento de tratamientos

### 🔐 Seguridad

- Autenticación OAuth2 con Auth0
- Autorización basada en roles
- Validación de tokens JWT
- Protección CORS configurable

## ⚙️ Configuración e Instalación

### Prerrequisitos

- Java 21 o superior
- Docker y Docker Compose
- Cuenta de Auth0 configurada
- IDE compatible con Java (IntelliJ IDEA, Eclipse, VS Code)

### Variables de Entorno

Crear un archivo `.env` en la raíz del proyecto con las siguientes variables:

```env
# Base de datos PostgreSQL
POSTGRES_DB=distribuidos_db
POSTGRES_USER=distribuidos
POSTGRES_PASSWORD=distribuidos_pass

# Configuración Auth0
AUTH0_DOMAIN=tu-dominio.auth0.com
AUTH0_AUDIENCE=https://tu-api-identifier
AUTH0_ISSUER_URI=https://tu-dominio.auth0.com/
AUTH0_CLIENT=tu-client-id
AUTH0_CLIENT_SECRET=tu-client-secret

# CORS
CORS_ALLOWED_ORIGINS=http://localhost:3000,http://localhost:4200
```

### 🐳 Instalación con Docker

1. **Clonar el repositorio**

```bash
git clone <url-del-repositorio>
cd backend-auth0
```

2. **Configurar variables de entorno**

```bash
cp .env.example .env
# Editar .env con tus configuraciones
```

3. **Ejecutar con Docker Compose**

```bash
docker-compose up -d
```

### 🛠️ Instalación Manual

1. **Configurar base de datos PostgreSQL**

```sql
CREATE DATABASE distribuidos_db;
CREATE USER distribuidos WITH PASSWORD 'distribuidos_pass';
GRANT ALL PRIVILEGES ON DATABASE distribuidos_db TO distribuidos;
```

2. **Ejecutar la aplicación**

```bash
./gradlew bootRun
```

## 📡 Endpoints de la API

### Autenticación

- `POST /auth/login` - Iniciar sesión
- `POST /auth/register` - Registrar usuario
- `PUT /auth/change-role` - Cambiar rol de usuario

### Usuarios y Perfiles

- `GET /usuarios` - Listar usuarios
- `GET /usuarios/{id}` - Obtener usuario por ID
- `PUT /usuarios/{id}` - Actualizar usuario

### Pacientes

- `GET /pacientes` - Listar pacientes
- `POST /pacientes` - Crear paciente
- `PUT /pacientes/{id}` - Actualizar paciente
- `DELETE /pacientes/{id}` - Eliminar paciente

### Dentistas

- `GET /dentistas` - Listar dentistas
- `PUT /dentistas/{id}` - Actualizar dentista
- `GET /dentistas/{id}/disponibilidades` - Obtener disponibilidades

### Turnos

- `GET /turnos` - Listar turnos
- `POST /turnos` - Crear turno
- `PUT /turnos/{id}` - Actualizar turno
- `PUT /turnos/{id}/estado` - Cambiar estado del turno

### Especialidades

- `GET /especialidades` - Listar especialidades
- `POST /especialidades` - Crear especialidad

## 📖 Documentación de la API

Una vez ejecutada la aplicación, la documentación interactiva de Swagger estará disponible en:

```
http://localhost:8080/swagger-ui/index.html
```

## 🧪 Testing

Ejecutar las pruebas unitarias:

```bash
./gradlew test
```

## 📁 Estructura de Archivos Importantes

```
backend-auth0/
├── 📄 build.gradle                    # Configuración de Gradle
├── 📄 docker-compose.yml              # Configuración de Docker
├── 📄 Dockerfile                      # Imagen de la aplicación
├── 📁 database/
│   └── 📄 init.sql                     # Script de inicialización de BD
├── 📁 src/main/java/com/example/backend_auth0/
│   ├── 📄 BackendAuth0Application.java # Clase principal
│   ├── 📁 data/                        # Capa de datos
│   ├── 📁 domain/                      # Lógica de negocio
│   └── 📁 presentation/                # Controladores y configuración
└── 📁 src/main/resources/
    └── 📄 application.properties       # Configuración de la aplicación
```

## 🔧 Configuración de Auth0

### 1. Crear Aplicación en Auth0

- Tipo: Single Page Application + API
- Configurar Allowed Callback URLs
- Configurar Allowed Web Origins

### 2. Configurar API

- Crear API con identificador único
- Habilitar RBAC (Role-Based Access Control)
- Definir scopes necesarios

### 3. Roles y Permisos

- `PACIENTE`: Gestión de perfil personal y turnos
- `DENTISTA`: Gestión de disponibilidades y turnos asignados
- `ADMINISTRADOR`: Acceso completo al sistema

## 📊 Casos de Uso Principales

1. **Registro de Paciente**: Un nuevo paciente se registra y completa su perfil
2. **Programación de Turno**: Un paciente agenda una cita con un dentista disponible
3. **Gestión de Disponibilidades**: Un dentista configura sus horarios disponibles
4. **Seguimiento de Tratamiento**: Registro de evolución y notas durante las consultas
5. **Administración**: Gestión de usuarios, reportes y configuración del sistema

## 🚀 Despliegue

### Producción con Docker

1. **Build de la imagen**

```bash
docker build -t dental-clinic-backend .
```

2. **Deploy en entorno productivo**

```bash
docker run -p 8080:8080 --env-file .env dental-clinic-backend
```
