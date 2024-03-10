package com.greglturnquist.payroll;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EmployeeTest {

    @Test
    void testEmployeeConstructorValidParamathers() {
        //act and assert
        Employee employee = new Employee("Frodo", "Baggins", "ring bearer", 1);
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
        //Act and Assert
        assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, description, jobYears));
    }

    @Test
    void testEmployeeConstructorInvalidLastName() {
        //Arrange
        String firstName = "Frodo";
        String lastName = null;
        String description = "ring bearer";
        int jobYears = 1;
        //Act and Assert
        assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, description, jobYears));
    }

    @Test
    void testEmployeeConstructorInvalidDescription() {
        //Arrange
        String firstName = "Frodo";
        String lastName = "Baggins";
        String description = null;
        int jobYears = 1;
        //Act and Assert
        assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, description, jobYears));
    }

    @Test
    void testEmployeeConstructorNegativeJobYears() {
        //Arrange
        String firstName = "Frodo";
        String lastName = "Baggins";
        String description = "ring bearer";
        int jobYears = -1;
        //Act and Assert
        assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, description, jobYears));
    }

    @Test
    void testEmployeeConstructorEmptyFirstName() {
        //Arrange
        String firstName = "";
        String lastName = "Baggins";
        String description = "ring bearer";
        int jobYears = 1;
        //Act and Assert
        assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, description, jobYears));
    }

    @Test
    void testEmployeeConstructorEmptyLastName() {
        //Arrange
        String firstName = "Frodo";
        String lastName = "";
        String description = "ring bearer";
        int jobYears = 1;
        //Act and Assert
        assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, description, jobYears));
    }

    @Test
    void testEmployeeConstructorEmptyDescription() {
        //Arrange
        String firstName = "Frodo";
        String lastName = "Baggins";
        String description = "";
        int jobYears = 1;
        //Act and Assert
        assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, description, jobYears));
    }

    @Test
    void testEmployeeConstructor0JobYears() {
        //Arrange
        String firstName = "Frodo";
        String lastName = "Baggins";
        String description = "ring bearer";
        int jobYears = 0;
        //Act
        Employee employee = new Employee(firstName, lastName, description, jobYears);
        //Assert
        assertEquals(0, employee.getJobYears());
    }


    @Test
    void testEmployeeValidation() {
        //Arrange
        String firstName = "John";
        String lastName = "Doe";
        String description = "Developer";
        int jobYears = 5;
        //Act
        Employee employee = new Employee(firstName, lastName, description, jobYears);
        //Assert
        assertNotNull(employee.getFirstName());
        assertNotNull(employee.getLastName());
        assertNotNull(employee.getDescription());
        assertTrue(employee.getJobYears() >= 0);
    }

    @Test
    void getId() {
        //Arrange
        Employee employee = new Employee("Frodo", "Baggins", "ring bearer", 1);
        //Act
        employee.setId(1L);
        //Assert
        assertEquals(1L, employee.getId());
    }

    @Test
    void getFirstName() {
        //Arrange
        String expected = "Frodo";
        Employee employee = new Employee("Frodo", "Baggins", "ring bearer", 1);
        //Act
        String result = employee.getFirstName();
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getLastName() {
        //Arrange
        String expected = "Baggins";
        Employee employee = new Employee("Frodo", "Baggins", "ring bearer", 1);
        //Act
        String result = employee.getLastName();
        //Assert
        assertEquals(expected, result);
    }


    @Test
    void setLastName() {
        //Arrange
        String expected = "Silva";
        Employee employee = new Employee("Frodo", "Baggins", "ring bearer", 1);
        //Act
        employee.setLastName("Silva");
        //Assert
        assertEquals(expected, employee.getLastName());
    }

    @Test
    void getDescription() {
        //Arrange
        String expected = "ring bearer";
        Employee employee = new Employee("Frodo", "Baggins", "ring bearer", 1);
        //Act
        String result = employee.getDescription();
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void setDescription() {
        //Arrange
        String expected = "barber";
        Employee employee = new Employee("Frodo", "Baggins", "ring bearer", 1);
        //Act
        employee.setDescription("barber");
        //Assert
        assertEquals(expected, employee.getDescription());

    }

    @Test
    void setJobYears() {
        //Arrange
        int expected = 2;
        Employee employee = new Employee("Frodo", "Baggins", "ring bearer", 1);
        //Act
        employee.setJobYears(2);
        //Assert
        assertEquals(expected, employee.getJobYears());

    }
}

