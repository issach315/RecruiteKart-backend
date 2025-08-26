package com.recruitkart.employee.model;

import jakarta.persistence.*;

@Entity
@Table(name = "employee_profile")
public class EmployeeProfile {


    @Id
    private String profileId;

    private String address;

    private String emergencyContact;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public EmployeeProfile() {
    }

    public EmployeeProfile(String address, String emergencyContact, String profileId,Employee employee) {
        this.address = address;
        this.emergencyContact = emergencyContact;
        this.profileId = profileId;
        this.employee = employee;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
