package edu.miu.cs.cs489appsd.lab1b.employeepensionsplanningsys;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import edu.miu.cs.cs489appsd.lab1b.employeepensionsplanningsys.model.Employee;
import edu.miu.cs.cs489appsd.lab1b.employeepensionsplanningsys.model.EmployeePension;
import edu.miu.cs.cs489appsd.lab1b.employeepensionsplanningsys.model.PensionPlan;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeePensionsPlanningApp {
    public static void main(String[] args) throws JsonProcessingException {
        List<Employee> employeeList = new ArrayList<>();
        Employee e1 = new Employee(2018011000L, "Daniel","Agar", LocalDate.of(2018, 1, 17), 105945.50);
        Employee e2 = new Employee(2018011001L, "Benard","Shaw", LocalDate.of(2018, 10, 3), 197750.00);
        Employee e3 = new Employee(2018011002L, "Carly","Agar", LocalDate.of(2014, 5, 16), 842000.75);
        Employee e4 = new Employee(2018011003L, "Wesley","Schneider", LocalDate.of(2018, 11, 2), 74500.00);
        employeeList.add(e1);
        employeeList.add(e2);
        employeeList.add(e3);
        employeeList.add(e4);

        List<PensionPlan> pensionPlanList = new ArrayList<>();
//        PensionPlan p1 = new PensionPlan("EX1089");
//        PensionPlan p2 = new PensionPlan("SM2307");
//        pensionPlanList.add(p1);
//        pensionPlanList.add(p2);

        List<EmployeePension> employeePensionList = new ArrayList<>();
        EmployeePension ep1 = new EmployeePension(e1, new PensionPlan("EX1089", LocalDate.of(2023,1,17), 100.00));
        EmployeePension ep2 = new EmployeePension(e2);
        EmployeePension ep3 = new EmployeePension(e3,new PensionPlan("SM2307", LocalDate.of(2019,11,14), 1555.50));
        EmployeePension ep4 = new EmployeePension(e4);
        employeePensionList.add(ep1);
        employeePensionList.add(ep2);
        employeePensionList.add(ep3);
        employeePensionList.add(ep4);

        printQuarterlyUpcomingEnrollees(employeePensionList);
        System.out.println("----------------------------");
        printEmployeePensionList(employeePensionList);


    }

    public static void printEmployeePensionList(List<EmployeePension> employeePensionList) throws JsonProcessingException {
        System.out.println("---LIST OF EMPLOYEES---");

        // Sort employee's yearlySalary by Descending then lastName by Ascending
        employeePensionList.sort(Comparator.comparing((EmployeePension ep) -> ep.getEmployee().getYearlySalary()).reversed()
                .thenComparing(ep -> ep.getEmployee().getLastName()));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // To support LocalDate serialization
        String jsonOutput = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(employeePensionList);
        System.out.println(jsonOutput);
    }

    public static void printQuarterlyUpcomingEnrollees (List<EmployeePension> employeePensionList) throws JsonProcessingException {
        System.out.println("---QUARTERLY UPCOMING ENROLLEES REPORT---");

        LocalDate today = LocalDate.now();

        // Determine the first and last day of the next quarter
        int currentQuarter = (today.getMonthValue() - 1) / 3 + 1; // Current quarter (1-4)
        int nextQuarter = currentQuarter % 4 + 1; // Next quarter
        int nextQuarterStartMonth = (nextQuarter - 1) * 3 + 1; // Start month of the next quarter

        LocalDate firstDayOfNextQuarter = LocalDate.of(today.getYear(), nextQuarterStartMonth, 1);
        LocalDate lastDayOfNextQuarter = firstDayOfNextQuarter.plusMonths(3).minusDays(1);

        List<Employee> upcomingEnrollees = employeePensionList.stream()
                .filter(ep -> ep.getPensionPlan() == null)
                .map(EmployeePension::getEmployee)
                .filter(emp -> emp.getEmploymentDate().plusYears(3).isBefore(today)) // Must be employed for at least 3 years
                .filter(emp -> {
                    LocalDate eligibleDate = emp.getEmploymentDate().plusYears(3);
                    boolean fallsWithinRange = (eligibleDate.isEqual(firstDayOfNextQuarter) ||
                            (eligibleDate.isAfter(firstDayOfNextQuarter) && eligibleDate.isBefore(lastDayOfNextQuarter)));
                    return fallsWithinRange;
                })
                .sorted(Comparator.comparing(Employee::getEmploymentDate).reversed())
                .collect(Collectors.toList());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String jsonOutput = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(upcomingEnrollees);

        System.out.println(jsonOutput);
    }



}
