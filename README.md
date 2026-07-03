# "Kleinstanzeigen" Web-Application

A full-stack web application for managing advertisements,
developed as part of the **Web-Based Applications** module in the Media
computer science program.

The application consists of a **Vue.js frontend** and a **Spring Boot
backend** that communicate via REST APIs. Additionally, changes to
advertisements are broadcast to all connected clients in real time using
**WebSockets (STOMP)**.

------------------------------------------------------------------------

## Screenshots

> *Screenshots of the login page, advertisement overview, and live
> updates can be added here.*

------------------------------------------------------------------------

## Features

-   User authentication with Pinia state management
-   Advertisement overview with search functionality
-   Instant filtering of advertisements while typing
-   Loading advertisements via a REST API
-   Real-time advertisement updates using WebSockets (STOMP)
-   User-friendly error messages displayed in information boxes
-   Client-side navigation with Vue Router
-   Reactive state management using Pinia

------------------------------------------------------------------------

## Tech Stack

![Vue.js](https://img.shields.io/badge/Vue.js-35495E?style=for-the-badge&logo=vuedotjs&logoColor=4FC08D)
![TypeScript](https://img.shields.io/badge/TypeScript-3178C6?style=for-the-badge&logo=typescript&logoColor=white)
![Pinia](https://img.shields.io/badge/Pinia-FFD859?style=for-the-badge)
![Vue
Router](https://img.shields.io/badge/Vue_Router-42B883?style=for-the-badge&logo=vuedotjs&logoColor=white)
![Vite](https://img.shields.io/badge/Vite-646CFF?style=for-the-badge&logo=vite&logoColor=white)
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring
Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Spring Data
JPA](https://img.shields.io/badge/Spring_Data_JPA-6DB33F?style=for-the-badge)
![Gradle](https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white)
![WebSocket](https://img.shields.io/badge/WebSocket-STOMP-blue?style=for-the-badge)
![H2
Database](https://img.shields.io/badge/H2_Database-0078D4?style=for-the-badge)

------------------------------------------------------------------------

## Getting Started

### Prerequisites

-   Java 21
-   Node.js
-   Gradle

### Clone the Repository

``` bash
git clone https://github.com/<your-username>/<repository>.git
cd <repository>
```

### Start the Backend

``` bash
./gradlew bootRun
```

### Start the Frontend

``` bash
cd frontend
npm install
npm run dev
```

Frontend:

``` text
http://localhost:5173
```

Backend:

``` text
http://localhost:8080
```

------------------------------------------------------------------------

## Project Highlights

-   Development of a full-stack web application using Vue.js and Spring
    Boot
-   RESTful communication between frontend and backend
-   Reactive state management with Pinia
-   Real-time updates via WebSockets (STOMP)
-   Integration of the Vite proxy for frontend-backend communication
-   Event-driven architecture using Spring Events
-   Clean separation of frontend, backend, and persistence layers

------------------------------------------------------------------------

Developed as part of the **Web-Based Applications** course during the
**4th semester** of the **Media Informatics** program at **Hochschule
RheinMain University of Applied Sciences**.
