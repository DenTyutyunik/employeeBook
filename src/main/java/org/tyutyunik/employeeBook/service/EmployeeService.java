package org.tyutyunik.employeeBook.service;

import org.tyutyunik.employeeBook.model.Employee;

import java.util.List;

public interface EmployeeService {
    String standardAnswer();

    List<Employee> getData();

    Employee addEmployee(String firstName, String lastName);

    Employee removeEmployee(String firstName, String lastName);

    Employee findEmployee(String firstName, String lastName);
}
