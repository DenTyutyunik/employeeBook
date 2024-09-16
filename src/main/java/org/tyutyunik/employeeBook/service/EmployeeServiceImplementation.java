package org.tyutyunik.employeeBook.service;

import org.springframework.stereotype.Service;
import org.tyutyunik.employeeBook.model.Employee;
import org.tyutyunik.employeeBook.exceptions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImplementation implements EmployeeService {
    private static final int EMPLOYEES_MAX = 10;
    private Map<String, Employee> employees = new HashMap<>();

    @Override
    public String standardAnswer() {
        return "Добро пожаловать в менеджер сотрудников";
    }

    @Override
    public List<Employee> getData() {
        System.out.println("getData");
        return List.copyOf(employees.values());
    }

    @Override
    public Employee addEmployee(String firstName, String lastName) {
        if (employees.size() >= EMPLOYEES_MAX) {
            throw new EmployeeStorageIsFullException();
        }
        Employee employee = new Employee(firstName, lastName);
        String key = employee.getFullName();
        if (employees.containsKey(key)) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(key, employee);
        return employee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        String key = employee.getFullName();
        Employee removed = employees.remove(key);
        if (removed == null) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        String key = employee.getFullName();
        Employee result = employees.get(key);
        if (result == null) {
            throw new EmployeeNotFoundException();
        }
        return result;
    }
}
