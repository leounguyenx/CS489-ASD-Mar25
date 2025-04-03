package edu.miu.cs.cs489appsd.lab1b.employeepensionsplanningsys.model;

public class EmployeePension {

    Employee employee;
    PensionPlan pensionPlan;

    public EmployeePension(){};
    public EmployeePension(Employee employee, PensionPlan pensionPlan) {
        this.employee = employee;
        this.pensionPlan = pensionPlan;
    }
    public EmployeePension(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public PensionPlan getPensionPlan() {
        return pensionPlan;
    }

    public void setPensionPlan(PensionPlan pensionPlan) {
        this.pensionPlan = pensionPlan;
    }

}
