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
            // financeManager.payStipend(student);
        } else {
            System.err.println("Student not found: " + studentId);
        }
    }

    public void processSalaryPayment(String employeeId) {
        Employee employee = dataStore.getEmployeeById(employeeId);
        if (employee != null) {
            // financeManager.paySalary(employee);
        } else {
            System.err.println("Employee not found: " + employeeId);
        }
    }
}