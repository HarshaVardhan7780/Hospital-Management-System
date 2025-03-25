# Testing Guide

This guide provides information about testing the Hospital Management System.

## Testing Framework

The project uses the following testing frameworks and tools:
- JUnit 5 for unit testing
- Mockito for mocking dependencies
- Spring Boot Test for integration testing

## Test Structure

### Unit Tests

Unit tests are located in `src/test/java/com/hms/` and follow the same package structure as the main code.

#### Naming Conventions
- Test class names should end with "Test"
- Test method names should follow the pattern: `testMethodName_Scenario_ExpectedResult`

Example:
```java
@Test
void createPatient_WithValidData_Success() {
    // test implementation
}
```

### Test Categories

1. **Service Layer Tests**
   - Test business logic
   - Mock repository dependencies
   - Verify method calls and return values

2. **Controller Layer Tests**
   - Test REST endpoints
   - Verify HTTP status codes
   - Test request/response handling

3. **Repository Layer Tests**
   - Test database operations
   - Use test database configuration
   - Verify query results

## Running Tests

### Running All Tests
```bash
mvn test
```

### Running Specific Test Class
```bash
mvn test -Dtest=PatientServiceTest
```

### Running Specific Test Method
```bash
mvn test -Dtest=PatientServiceTest#createPatient_Success
```

## Test Coverage

To generate test coverage reports:
```bash
mvn verify
```

Coverage reports will be generated in `target/site/jacoco/`

## Best Practices

1. **Test Independence**
   - Each test should be independent
   - Use `@BeforeEach` for test setup
   - Clean up after tests

2. **Meaningful Assertions**
   - Test specific outcomes
   - Use appropriate assertion methods
   - Include meaningful error messages

3. **Mocking Guidelines**
   - Mock external dependencies
   - Use `@Mock` for dependencies
   - Use `@InjectMocks` for the class under test
   - Verify mock interactions when necessary

4. **Test Data**
   - Use meaningful test data
   - Consider edge cases
   - Test both positive and negative scenarios

## Example Test Case

```java
@Test
void createPatient_WithDuplicateEmail_ThrowsException() {
    // Arrange
    Patient patient = createTestPatient();
    when(patientRepository.findByEmail(patient.getEmail()))
        .thenReturn(Optional.of(patient));

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () -> {
        patientService.createPatient(patient);
    });
}
```

## Common Testing Patterns

1. **Happy Path Testing**
   - Test successful scenarios
   - Verify expected outcomes

2. **Error Path Testing**
   - Test error conditions
   - Verify error handling

3. **Boundary Testing**
   - Test edge cases
   - Test input limits

4. **Integration Testing**
   - Test component interactions
   - Use test database
   - Test complete workflows

## Troubleshooting

Common issues and solutions:

1. **Test Failures**
   - Check test data setup
   - Verify mock configurations
   - Review assertion messages

2. **Database Issues**
   - Ensure test database configuration
   - Check transaction management
   - Verify cleanup procedures

3. **Mock Issues**
   - Verify mock setup
   - Check method signatures
   - Review mock interactions 