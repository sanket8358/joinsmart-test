package com.programming.techie.springngblog.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "UserRegistration")
public class User {
    @Id
    @Column(name = "UserRegistrationId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userRegistrationId;
    @Column(name = "RoleId")
    private Long roleId;
    @Column(name = "Phone")
    private String phone;
    @Column(name = "FirstName")
    private String firstName;
    @Column(name = "LastName")
    private String lastName;
    @Column(name = "Password")
    private String password;
    @Column(name = "CreatedDate")
    private Date createdDate;
    @Column(name = "ModifiedDate")
    private Date modifiedDate;
    @Column(name = "Email")
    private String email;

    public Long getUserRegistrationId() {
        return userRegistrationId;
    }

    public void setUserRegistrationId(Long userRegistrationId) {
        this.userRegistrationId = userRegistrationId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Long getId() {
        return userRegistrationId;
    }

    public void setId(Long id) {
        this.userRegistrationId = userRegistrationId;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
