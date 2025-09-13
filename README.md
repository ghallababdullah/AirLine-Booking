# ✈️ Socatr Airline System
## 📖 Project Overview
SocatraAirline is a comprehensive full-stack flight booking and management system built with Spring Boot and React. This application provides a complete solution for airline operations, allowing users to search for flights, book tickets, manage bookings, and handles complex airline operations including flight scheduling, pilot management, and real-time notifications. The system features robust role-based access control for passengers, pilots, and administrators.

## 🛠️ Technology Stack

### Backend (Spring Boot)
- **Java 17** - Primary programming language  
- **Spring Boot 3.2.4** - Application framework  
- **Spring Security 6.2.3** - Authentication and authorization  
- **JWT (JSON Web Tokens) 0.12.5** - Secure authentication  
- **Spring Data JPA** - Data persistence  
- **Hibernate 6.4.4** - ORM implementation  
- **PostgreSQL 16.2** - Primary relational database  
- **ModelMapper 3.2.0** - Object mapping library  
- **Lombok 1.18.30** - Code reduction through annotations  
- **Thymeleaf 3.1.2** - Email template engine  
- **Java Mail Sender** - Email notification system  
- **Maven** - Dependency management  
- **Spring Validation** - Input validation and sanitization  

### Frontend (React)
- **React 18.2.0** - Frontend library with hooks  
- **React Router DOM 6.20.1** - Navigation and routing  
- **Axios 1.6.2** - HTTP client for API calls  
- **React i18next 13.5.0** - Internationalization support  
- **Custom Hooks** - State management and logic reuse  
- **CSS3 with Flexbox/Grid** - Responsive styling  
- **Context API** - Global state management  

---

## 🔐 Security Implementation
- Custom **JWT authentication filter (AuthFilter)**  
- Role-based authorization with `@PreAuthorize`  
- Custom user details service (**CustomUserDetailsService**)  
- **BCrypt** password encryption  
- **CORS** configuration for frontend integration  
- Custom exception handling for security events  
- **CSRF** protection disabled for APIs  

---

## ✨ Key Features

### 🔐 Authentication & Authorization
- JWT-based stateless authentication (30-day token validity)  
- Role-based access control (**Admin, Pilot, Passenger**)  
- BCrypt password hashing  
- Email verification on registration  
- Custom error handling for authentication failures  

### ✈️ Flight Operations
- Flight creation & management  
- Real-time flight status updates (**Scheduled, Boarding, In_Progress**)  
- Airport management with IATA validation  
- Pilot assignment to flights  
- Flight search with filters  

### 📋 Booking System
- Flight search & booking  
- Multi-passenger support  
- Seat selection with availability  
- Booking reference number generation  
- Status tracking (**Confirmed, Cancelled, Completed**)  
- Price calculation with discounts  

### 📧 Email Notification System
- Welcome emails  
- Booking confirmations  
- Flight status updates  
- Thymeleaf-based templates  
- Delivery tracking & logging  

### 👨‍💼 Administrative Features
- Admin dashboard with analytics  
- User & role management  
- Flight scheduling & monitoring  
- Booking management  
- Airport CRUD operations  

### 🌐 Frontend Features
- React Hooks (`useState`, `useEffect`, `useContext`, custom hooks)  
- i18n with **English/Arabic support**  
- Responsive design (mobile & desktop)  
- Protected routes by role  
- Dynamic forms with validation  

---

## 🗄️ Database Schema Highlights

### Core Entities
- **User** - Authentication, roles, permissions  
- **Flight** - Flight data with pilot assignment  
- **Airport** - Airport details with IATA codes  
- **Booking** - Passenger bookings with payment info  
- **Passenger** - Traveler info with passport details  
- **EmailNotification** - Delivery logs  

### Enumerations
- **FlightStatus** → SCHEDULED, DELAYED, CANCELLED, DEPARTED, ARRIVED  
- **BookingStatus** → CONFIRMED, CANCELLED, COMPLETED  
- **Country** → 16 supported (YEMEN, SAUDI_ARABIA, UAE, …)  
- **City** → Supported cities worldwide  
- **PassengerType** → ADULT, CHILD, INFANT  

---

## 🚀 API Endpoints

### Authentication
- `POST /api/auth/login` - User login  
- `POST /api/auth/register` - Register with email verification  
- `GET /api/auth/me` - Current user info  

### User Management
- `GET /api/users/me` - Current user  
- `PUT /api/users` - Update account  
- `GET /api/users/pilots` - All pilots (Admin/Pilot)  
- `POST /api/admin/users` - Create new users (Admin)  

### Flights
- `GET /api/flights` - Available flights  
- `POST /api/flights` - Create flight (Admin)  
- `GET /api/flights/{id}` - Flight details  
- `PUT /api/flights/{id}` - Update flight  
- `GET /api/flights/search` - Search flights  

### Bookings
- `POST /api/bookings` - Create booking  
- `GET /api/bookings` - All bookings (Admin)  
- `GET /api/bookings/my-bookings` - User bookings  
- `GET /api/bookings/{id}` - Booking details  
- `PUT /api/bookings/{id}/status` - Update status  

### Airports
- `GET /api/airports` - All airports  
- `POST /api/airports` - Create airport (Admin)  
- `PUT /api/airports/{id}` - Update airport  
- `GET /api/airports/{id}` - Airport details  

---

## ⚛️ React Frontend Features

### Custom Hooks
- **useMessage** - Toast notifications  
- **useAuth** - Auth state management  
- **useApi** - API abstraction  
- **useLocalStorage** - Persistent state  

### Key Components
- **AuthPage** - Login/Registration  
- **HomePage** - Flight search  
- **FindFlightsPage** - Search results  
- **BookingPage** - Booking form  
- **AdminDashboardPage** - Admin interface  
- **AdminFlightDetailsPage** - Flight management  
- **SpecialRegistration** - Admin role creation  

### i18n
- English & Arabic support  
- RTL layout for Arabic  
- Dynamic language switching  
- Localized dates & currencies  

---

## 🧪 Quality Assurance

### Exception Handling
- Global handler with JSON responses  
- Custom business exceptions (`NotFoundException`, `BadRequestException`)  
- Security exceptions for auth failures  

### Validation
- Bean Validation annotations  
- Custom error messages  
- Data integrity checks  
- Cross-field validation  

### Logging
- SLF4J + Logback  
- Error tracking & monitoring  
- Email failure logging  

---

## 📦 Installation & Setup

### Prerequisites
- **Java JDK 17+**  
- **Maven 3.6+**  
- **PostgreSQL 12+**  
- **Node.js 16+**  
- **npm or yarn**  

---
## 👥 Development Team myself :)

This project was developed by  as a demonstration of full-stack development capabilities using modern **Java (Spring Boot)** and **React** technologies.  

The system showcases:  
- **Clean Architecture** - Separation of concerns with service layers  
- **RESTful API Design** - Consistent API patterns and status codes  
- **Security Best Practices** - Comprehensive authentication and authorization  
- **Database Design** - Optimized relational database schema  
- **Frontend-Backend Integration** - Seamless API communication  
- **User Experience** - Intuitive interface with responsive design  
- **Code Quality** - Maintainable and well-documented codebase  

---

