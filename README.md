# Monorepo de Microservicios con Spring Boot y Spring Cloud 🚀

Este repositorio contiene una arquitectura completa de microservicios construida con el ecosistema de Spring, diseñada para ser robusta, escalable y segura.

## 📝 Descripción General

Este proyecto es un **monorepo** que implementa un sistema modular basado en microservicios. La arquitectura incluye patrones esenciales como **Configuración Centralizada**, **Descubrimiento de Servicios**, un **API Gateway** y **Seguridad Centralizada con Keycloak**.

El dominio de negocio se centra en una aplicación bancaria, separando responsabilidades en diferentes módulos como `ms-bank-account` y `ms-customer`, y compartiendo lógica común a través de `bank-common` y `business-domain`.

***

## 📂 Arquitectura y Estructura del Repositorio

El monorepo está organizado en módulos funcionales y de infraestructura, siguiendo las mejores prácticas de diseño de software.