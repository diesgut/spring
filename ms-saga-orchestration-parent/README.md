# Microservicios con Patrón Saga (Orquestación)

Este proyecto es una implementación de una arquitectura de microservicios que utiliza el patrón **Saga de Orquestación** para gestionar transacciones distribuidas. La comunicación entre los servicios se realiza de forma asíncrona a través de **Apache Kafka**.

El objetivo es simular el proceso de creación de una orden, desde su solicitud hasta su aprobación final, coordinando diferentes servicios (órdenes, productos, pagos) para mantener la consistencia de los datos en todo el sistema.

---

## 🚀 Tecnologías Utilizadas

* **Lenguaje**: Java
* **Framework**: Spring Boot
* **Persistencia**: Spring Data JPA / Hibernate
* **Mensajería**: Apache Kafka
* **Contenerización**: Docker & Docker Compose

---

## 🏛️ Arquitectura

El sistema se compone de varios microservicios, cada uno con una responsabilidad única:

* `ms-order-service`: Gestiona todo el ciclo de vida de las órdenes y actúa como el **orquestador** de la saga.
* `ms-product-service`: Administra el inventario y las reservas de productos.
* `ms-payment-service`: Procesa los pagos.
* `ms-payment-processor-service`: Simula un procesador de pagos externo.
* `saga-commons`: Módulo común con DTOs y eventos compartidos.

La coordinación sigue el patrón de **Saga de Orquestación**, donde el `ms-order-service` centraliza la lógica del flujo, enviando comandos a otros servicios y reaccionando a los eventos que estos emiten.


---

## ✅ Flujo Principal (Happy Path)

El "camino feliz" describe el flujo de una transacción exitosa desde la creación de la orden hasta su aprobación.

1.  **Crear Orden (`ms-order-service`)**
    * Un cliente envía una petición `POST /orders`.
    * El servicio crea la orden en su base de datos con estado `CREATED`.
    * Publica el evento `OrderCreatedEvent` en el topic `orders-events`.

2.  **Iniciar Saga y Reservar Producto (`ms-order-service`)**
    * El orquestador (Saga) consume el `OrderCreatedEvent`.
    * Registra el inicio de la saga y guarda el historial con estado `CREATED`.
    * Publica el comando `ReserveProductCommand` en el topic `products-commands`.

3.  **Confirmar Reserva de Producto (`ms-product-service`)**
    * El servicio consume el `ReserveProductCommand`.
    * Verifica y actualiza el stock del producto en la base de datos.
    * Publica el evento `ProductReservedEvent` en el topic `products-events`.

4.  **Procesar Pago (`ms-order-service`)**
    * El orquestador consume el `ProductReservedEvent`.
    * Publica el comando `ProcessPaymentCommand` en el topic `payments-commands`.

5.  **Procesar Pago (`ms-payment-service`)**
    * El servicio consume el `ProcessPaymentCommand`.
    * Se comunica con `ms-payment-processor-service` para procesar el pago.
    * Guarda el pago en su base de datos.
    * Publica el evento `PaymentProcessedEvent` en el topic `payments-events`.

6.  **Aprobar Orden (`ms-order-service`)**
    * El orquestador consume el `PaymentProcessedEvent`.
    * Publica el comando `ApproveOrderCommand` en el topic `orders-commands`.

7.  **Confirmar Aprobación de Orden (`ms-order-service`)**
    * El microservicio (no la saga) consume el `ApproveOrderCommand`.
    * Actualiza el estado de la orden en la base de datos a `APPROVED`.
    * Publica el evento `OrderApprovedEvent` en el topic `orders-events`.

8.  **Finalizar Saga (`ms-order-service`)**
    * El orquestador consume el `OrderApprovedEvent`.
    * Guarda el historial de la saga como `APPROVED` y finaliza el flujo.

---

## ❌ Manejo de Errores y Flujo de Compensación

Cuando un paso en la saga falla, el orquestador es responsable de ejecutar **transacciones de compensación** para revertir las operaciones previas y mantener la consistencia de los datos.

### Escenario 1: Fallo en la Reserva de Producto

* En el paso 3 del camino feliz, Si `ms-product-service` no puede reservar el producto (por ejemplo, falta de stock), publica un evento de error: `ProductReservationFailedEvent`.

#### Acciones de Compensación:

**Rechazar Orden**

1.  **Trigger (`ms-order-service`)**
    * El orquestador consume el `ProductReservationFailedEvent`.
    * Publica el comando `CancelProductReservationCommand` en el topic `products-commands`.
    * Guarda el historial de la saga como `REJECTED`.

2.  **Rechazar Orden (`ms-order-service`)**
    * El servicio consume el `RejectOrderCommand`.
    * Actualiza el estado de la orden a `REJECTED`.

### Escenario 2: Falla en el Procesamiento del Pago

* En el paso 5 del camino feliz, Si `ms-payment-service` no puede procesar el pago (por ejemplo, fallta la comunicación con `ms-payment-processor-service`), publica un evento de error: `PaymentFailedEvent`.

#### Acciones de Compensación (en orden inverso):

**Revertir Reserva de Producto**

1.  **Trigger (`ms-order-service`)**
    * El orquestador consume el `PaymentFailedEvent`.
    * Publica el comando `CancelProductReservationCommand` en el topic `products-commands`.

2.  **Cancelar Reserva (`ms-product-service`)**
    * El servicio consume el `CancelProductReservationCommand`.
    * Restaura el stock del producto en la base de datos.
    * Publica el evento `ProductReservationCancelledEvent` en el topic `products-events`.

**Cancelar Orden**

1.  **Trigger (`ms-order-service`)**
    * El orquestador consume el `ProductReservationCancelledEvent`.
    * Publica el comando `RejectOrderCommand` en el topic `orders-commands`.
    * Guarda el historial de la saga como `REJECTED`.
    
2.  **Rechazar Orden (`ms-order-service`)**
    * El servicio consume el `RejectOrderCommand`.
    * Actualiza el estado de la orden a `REJECTED`.