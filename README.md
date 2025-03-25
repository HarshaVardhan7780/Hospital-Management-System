# Hospital Management System

A comprehensive hospital management system built with Spring Boot that helps manage patient records and hospital operations.

## Features

- Patient Management (CRUD operations)
- Patient Search functionality
- Active/Inactive patient status tracking
- RESTful API endpoints
- Data validation
- Unit testing

## Tech Stack

- Java 17
- Spring Boot 3.2.3
- Spring Data JPA
- H2 Database (for development)
- Lombok
- JUnit 5
- Mockito

## Prerequisites

- JDK 17 or later
- Maven 3.6 or later

## Setup Instructions

1. Clone the repository:
```bash
git clone <repository-url>
cd hospital-management-system
```

2. Build the project:
```bash
mvn clean install
```

3. Run the application:
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## API Endpoints

### Patient Management

- `POST /api/patients` - Create a new patient
- `GET /api/patients/{id}` - Get patient by ID
- `GET /api/patients` - Get all patients
- `GET /api/patients/active` - Get all active patients
- `PUT /api/patients/{id}` - Update patient
- `DELETE /api/patients/{id}` - Soft delete patient
- `GET /api/patients/search?query={query}` - Search patients by name

## Testing

Run the tests using:
```bash
mvn test
```

## Project Structure

```
HospitalManagementSystem/
│── src/
│   ├── main
│   │   ├── model/                # Entity Classes
│   │   │   ├── Patient.java
│   │   ├── service/              # Business Logic
│   │   │   ├── PatientService.java
│   │   ├── repository/           # Data Access
│   │   │   ├── PatientRepository.java
│   │   ├── controller/           # API Endpoints
│   │   │   ├── PatientController.java
│── src/
│   ├── test
│   │   ├── service/
│   │   │   ├── PatientServiceTest.java
│── docs/
│   ├── test-guide.md
│── pom.xml
│── README.md
```

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.
