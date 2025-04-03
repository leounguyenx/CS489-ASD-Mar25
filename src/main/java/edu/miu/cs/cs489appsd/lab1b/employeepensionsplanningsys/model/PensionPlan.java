package edu.miu.cs.cs489appsd.lab1b.employeepensionsplanningsys.model;

import java.time.LocalDate;
import java.util.List;

public class PensionPlan {
    String planReferenceNumber;
    LocalDate enrollmentDate;
    Double monthlyContribution;
    List<Employee> employeeList;

    // Constructors
    public PensionPlan(){};
    public PensionPlan(String planReferenceNumber, LocalDate enrollmentDate, Double monthlyContribution) {
        this.planReferenceNumber = planReferenceNumber;
        this.enrollmentDate = enrollmentDate;
        this.monthlyContribution = monthlyContribution;
    }

    public PensionPlan(String planReferenceNumber) {
        this.planReferenceNumber = planReferenceNumber;
    }

    // Getters and Setters
    public String getPlanReferenceNumber() {
        return planReferenceNumber;
    }

    public void setPlanReferenceNumber(String planReferenceNumber) {
        this.planReferenceNumber = planReferenceNumber;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public Double getMonthlyContribution() {
        return monthlyContribution;
    }

    public void setMonthlyContribution(Double monthlyContribution) {
        this.monthlyContribution = monthlyContribution;
    }
}
