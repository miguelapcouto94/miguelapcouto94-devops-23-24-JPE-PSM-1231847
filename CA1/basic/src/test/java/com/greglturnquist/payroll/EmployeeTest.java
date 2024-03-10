package com.greglturnquist.payroll;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EmployeeTest {

    @Test
    void testEmployeeConstructorValidParamathers() {
        //act and assert
        Employee employee = new Employee("Frodo", "Baggins", "ring bearer", 1, "frodo@gmail.com");
        assertEquals("Frodo", employee.getFirstName());
        assertEquals("Baggins", employee.getLastName());
        assertEquals("ring bearer", employee.getDescription());
        assertEquals(1, employee.getJobYears());
    }

    @Test
    void testEmployeeConstructorInvalidFirstName() {
        //Arrange
        String firstName = null;
        String lastName = "Baggins";
        String description = "ring bearer";
        int jobYears = 1;
        String email = "frodo@gmail.com";
        //Act and Assert
        assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, description, jobYears, email));
    }

    @Test
    void testEmployeeConstructorInvalidLastName() {
        //Arrange
        String firstName = "Frodo";
        String lastName = null;
        String description = "ring bearer";
        int jobYears = 1;
        String email = "frodo@gmail.com";
        //Act and Assert
        assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, description, jobYears, email));
    }

    @Test
    void testEmployeeConstructorInvalidDescription() {
        //Arrange
        String firstName = "Frodo";
        String lastName = "Baggins";
        String description = null;
        int jobYears = 1;
        String email = "frodo@gmail.com";
        //Act and Assert
        assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, description, jobYears, email));
    }

    @Test
    void testEmployeeConstructorNegativeJobYears() {
        //Arrange
        String firstName = "Frodo";
        String lastName = "Baggins";
        String description = "ring bearer";
        int jobYears = -1;
        String email = "frodo@gmail.com";
        //Act and Assert
        assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, description, jobYears, email));
    }

    @Test
    void testEmployeeConstructorEmptyFirstName() {
        //Arrange
        String firstName = "";
        String lastName = "Baggins";
        String description = "ring bearer";
        int jobYears = 1;
        String email = "frodo@gmail.com";
        //Act and Assert
        assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, description, jobYears, email));
    }

    @Test
    void testEmployeeConstructorEmptyLastName() {
        //Arrange
        String firstName = "Frodo";
        String lastName = "";
        String description = "ring bearer";
        int jobYears = 1;
        String email = "frodo@gmail.com";
        //Act and Assert
        assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, description, jobYears, email));
    }

    @Test
    void testEmployeeConstructorEmptyDescription() {
        //Arrange
        String firstName = "Frodo";
        String lastName = "Baggins";
        String description = "";
        int jobYears = 1;
        String email = "frodo@gmail.com";
        //Act and Assert
        assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, description, jobYears, email));
    }

    @Test
    void testEmployeeConstructor0JobYears() {
        //Arrange
        String firstName = "Frodo";
        String lastName = "Baggins";
        String description = "ring bearer";
        int jobYears = 0;
        String email = "frodo@gmail.com";
        //Act
        Employee employee = new Employee(firstName, lastName, description, jobYears, email);
        //Assert
        assertEquals(0, employee.getJobYears());
    }

    @Test
    void testEmployeeConstructorEmptyEmail() {
        //Arrange
        String firstName = "Frodo";
        String lastName = "Baggins";
        String description = "ring bearer";
        int jobYears = 1;
        String email = "";
        //Act and Assert
        assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, description, jobYears, email));
    }

    @Test
    void testEmployeeConstructorNullEmail() {
        //Arrange
        String firstName = "Frodo";
        String lastName = "Baggins";
        String description = "ring bearer";
        int jobYears = 1;
        String email = null;
        //Act and Assert
        assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, description, jobYears, email));
    }


    @Test
    void testEmployeeValidation() {
        //Arrange
        String firstName = "John";
        String lastName = "Doe";
        String description = "Developer";
        int jobYears = 5;
        String email = "frodo@gmail.com";
        //Act
        Employee employee = new Employee(firstName, lastName, description, jobYears, email);
        //Assert
        assertNotNull(employee.getFirstName());
        assertNotNull(employee.getLastName());
        assertNotNull(employee.getDescription());
        assertTrue(employee.getJobYears() >= 0);
        assertNotNull(employee.getEmail());
    }

    @Test
    void getId() {
        //Arrange
        Employee employee = new Employee("Frodo", "Baggins", "ring bearer", 1, "frodo@gmail.com");
        //Act
        employee.setId(1L);
        //Assert
        assertEquals(1L, employee.getId());
    }

    @Test
    void getFirstName() {
        //Arrange
        String expected = "Frodo";
        Employee employee = new Employee("Frodo", "Baggins", "ring bearer", 1, "frodo@gmail.com");
        //Act
        String result = employee.getFirstName();
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getLastName() {
        //Arrange
        String expected = "Baggins";
        Employee employee = new Employee("Frodo", "Baggins", "ring bearer", 1, "frodo@gmail.com");
        //Act
        String result = employee.getLastName();
        //Assert
        assertEquals(expected, result);
    }


    @Test
    void setLastName() {
        //Arrange
        String expected = "Silva";
        Employee employee = new Employee("Frodo", "Baggins", "ring bearer", 1, "frodo@gmail.com");
        //Act
        employee.setLastName("Silva");
        //Assert
        assertEquals(expected, employee.getLastName());
    }

    @Test
    void getDescription() {
        //Arrange
        String expected = "ring bearer";
        Employee employee = new Employee("Frodo", "Baggins", "ring bearer", 1, "frodo@gmail.com");
        //Act
        String result = employee.getDescription();
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void setDescription() {
        //Arrange
        String expected = "barber";
        Employee employee = new Employee("Frodo", "Baggins", "ring bearer", 1, "frodo@gmail.com");
        //Act
        employee.setDescription("barber");
        //Assert
        assertEquals(expected, employee.getDescription());

    }

    @Test
    void setJobYears() {
        //Arrange
        int expected = 2;
        Employee employee = new Employee("Frodo", "Baggins", "ring bearer", 1, "frodo@gmail.com");
        //Act
        employee.setJobYears(2);
        //Assert
        assertEquals(expected, employee.getJobYears());

    }

    @Test
    void getEmail() {
        //Arrange
        String expected = "frodo@gmail.com";
        Employee employee = new Employee("Frodo", "Baggins", "ring bearer", 1, "frodo@gmail.com");
        //Act
        employee.getEmail();
        //Assert
        assertEquals(expected, employee.getEmail());
}

    @Test
    void setEmail() {
        //Arrange
        String expected = "artur@gmail.com";
        Employee employee = new Employee("Frodo", "Baggins", "ring bearer", 1, "frodo@gmail.com");
        //Act
        employee.setEmail(expected);
        //Assert
        assertEquals(expected, employee.getEmail());

    }}

