package service;

import data.DataStore;
import model.people.Employee;
import model.people.Student;

public class FinanceManagerService {
    private final DataStore dataStore;

    public FinanceManagerService(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    public void processStipendPayment(String studentId) {
        Student student = dataStore.getStudentById(studentId);
        if (student != null) {
            // Assuming FinanceManager has a method to check eligibility and calculate stipend
            // This is a placeholder for the actual logic you would implement
            // financeManager.payStipend(student);
        } else {
            System.err.println("Student not found: " + studentId);
        }
    }

    public void processSalaryPayment(int employeeId) {
        Employee employee = dataStore.getEmployeeById(employeeId);
        if (employee != null) {
            // Assuming FinanceManager has a method to process salary payment
            // This is a placeholder for the actual logic you would implement
            // financeManager.paySalary(employee);
        } else {
            System.err.println("Employee not found: " + employeeId);
        }
    }
}