package com.in6k.form;

import java.util.ArrayList;
import java.util.List;

public class EmployeeForm {
    private String id;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String passwordConfirmation;
    private String birthDate;

    private static final String NAME_PATTERN = "^[a-z0-9_-]{3,15}$";
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PASSWORD_PATTERN = "^[a-z0-9_-]{3,8}$";
    private static final String BIRTH_DATE_PATTERN = "^[a-z0-9_-]{3,15}$";

    protected boolean isValid(String value, String pattern) {
        return value.matches(pattern);
    }

    public List<String> validate(){
        List<String> errors = new ArrayList<String>();

        if (!isValid(name, NAME_PATTERN)) {
            errors.add("Name is not valid");
        }
        if (!isValid(lastName, NAME_PATTERN)) {
            errors.add("Last Name is not valid");
        }
        if (!isValid(email, EMAIL_PATTERN)) {
            errors.add("Email is not valid");
        }
        if (isValid(password, PASSWORD_PATTERN) && password == passwordConfirmation) {
            errors.add("Password is not valid");
        }
        if (!isValid(birthDate, BIRTH_DATE_PATTERN)) {
            errors.add("Birth Date is not valid");
        }

        return errors;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }


    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

}