package com.recruitkart.employee.response;

public class OnBoardEmployeeResponseDTO {

    private String employeeId;
    private String employeeName;


    public OnBoardEmployeeResponseDTO(String employeeName, String employeeId) {
        this.employeeName = employeeName;
        this.employeeId = employeeId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
}
