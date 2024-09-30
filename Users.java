package com.example.Applicationjava.Entity;



import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data

@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false)
    @NotBlank(message = "First name is required")
    @Pattern(regexp = "^[A-Za-z]+$", message = "First name can only contain letters, no spaces or special characters")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String fname;

    @Pattern(regexp = "^[A-Za-z]+$", message = "Last name can only contain letters, no spaces or special characters")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lname;

    @Column(unique=true,nullable = false)
     @NotBlank(message = "Email Address cannot be blank")
    @Pattern(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$", message = "Email Address must be a valid email format")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be exactly 10 digits, no letters or spaces allowed")
    @Column(unique=true,nullable = false)
    private String phonenumber;

    @Past(message = "Date of birth cannot be in the future")

    private LocalDate dob;

   @Pattern(regexp = "^[A-Za-z][A-Za-z0-9 ]{0,20}[0-9]{0,4}$", message = "Address must start with a letter, allow up to 4 digits, and allow up to 5 spaces")
    private String addressLine;

    @Pattern(regexp = "^[A-Za-z]+( [A-Za-z]+){0,2}$", message = "City can only contain letters and up to 2 spaces")
    private String city;

    @Pattern(regexp = "^[A-Za-z]+( [A-Za-z]+){0,2}$", message = "State can only contain letters and up to 2 spaces")
    private String state;

    @Pattern(regexp = "^[0-9]{6}$", message = "Postal code must be exactly 6 digits, no letters or spaces allowed")
    private String postalCode;

    @Pattern(regexp = "^(Grade|Year) [0-9]{1,2}$", message = "Grade or year level must be in format 'Grade X' or 'Year Y'")
    private String gradeOrYearLevel;
//
    @DecimalMin(value = "0.0", inclusive = true, message = "GPA score must be positive")
    @DecimalMax(value = "4.0", message = "GPA score cannot exceed 4.0")
    private double gpaScore;

    @DecimalMin(value = "0.0", inclusive = true, message = "GWA percentile must be positive")
    @DecimalMax(value = "100.0", message = "GWA percentile cannot exceed 100.0")
    private double gwaPercentile;

    @FutureOrPresent(message = "Expected graduation date cannot be in the past")

    private LocalDate expectedGraduationDate;

    @Pattern(regexp = "^[A-Za-z]+( [A-Za-z]+){0,5}$", message = "School name can only contain letters and allow up to 5 spaces")
    private String schoolName;

    @Pattern(regexp = "^[A-Za-z]+( [A-Za-z]+){0,5}$", message = "Department can only contain letters and allow up to 5 spaces")
    private String department;

    @NotBlank(message = "Password is required")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{8,}$",
            message = "Password must be at least 8 characters long and contain at least one uppercase letter, one digit, and one special character"
    )
    @Column(nullable = false)
    private String password;
    private String resetPasswordToken;
    private LocalDateTime TokenExpiry;

    public Users() {
    }

    public Users(int id, String fname, String lname, String email, String phonenumber, LocalDate dob, String addressLine, String city, String state, String postalCode, String gradeOrYearLevel, double gpaScore, double gwaPercentile, LocalDate expectedGraduationDate, String schoolName, String department, String password, String resetPasswordToken, LocalDateTime tokenExpiry) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phonenumber = phonenumber;
        this.dob = dob;
        this.addressLine = addressLine;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.gradeOrYearLevel = gradeOrYearLevel;
        this.gpaScore = gpaScore;
        this.gwaPercentile = gwaPercentile;
        this.expectedGraduationDate = expectedGraduationDate;
        this.schoolName = schoolName;
        this.department = department;
        this.password = password;
        this.resetPasswordToken = resetPasswordToken;
        TokenExpiry = tokenExpiry;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotBlank(message = "First name is required") @Pattern(regexp = "^[A-Za-z]+$", message = "First name can only contain letters, no spaces or special characters") @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters") String getFname() {
        return fname;
    }

    public void setFname(@NotBlank(message = "First name is required") @Pattern(regexp = "^[A-Za-z]+$", message = "First name can only contain letters, no spaces or special characters") @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters") String fname) {
        this.fname = fname;
    }

    public @Pattern(regexp = "^[A-Za-z]+$", message = "Last name can only contain letters, no spaces or special characters") @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters") String getLname() {
        return lname;
    }

    public void setLname(@Pattern(regexp = "^[A-Za-z]+$", message = "Last name can only contain letters, no spaces or special characters") @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters") String lname) {
        this.lname = lname;
    }

    public @NotBlank(message = "Email Address cannot be blank") @Pattern(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$", message = "Email Address must be a valid email format") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email Address cannot be blank") @Pattern(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$", message = "Email Address must be a valid email format") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Phone number is required") @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be exactly 10 digits, no letters or spaces allowed") String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(@NotBlank(message = "Phone number is required") @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be exactly 10 digits, no letters or spaces allowed") String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public @Past(message = "Date of birth cannot be in the future") LocalDate getDob() {
        return dob;
    }

    public void setDob(@Past(message = "Date of birth cannot be in the future") LocalDate dob) {
        this.dob = dob;
    }

    public @Pattern(regexp = "^[A-Za-z]+( [A-Za-z]+){0,2}$", message = "City can only contain letters and up to 2 spaces") String getCity() {
        return city;
    }

    public void setCity(@Pattern(regexp = "^[A-Za-z]+( [A-Za-z]+){0,2}$", message = "City can only contain letters and up to 2 spaces") String city) {
        this.city = city;
    }

    public @Pattern(regexp = "^[A-Za-z][A-Za-z0-9 ]{0,20}[0-9]{0,4}$", message = "Address must start with a letter, allow up to 4 digits, and allow up to 5 spaces") String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(@Pattern(regexp = "^[A-Za-z][A-Za-z0-9 ]{0,20}[0-9]{0,4}$", message = "Address must start with a letter, allow up to 4 digits, and allow up to 5 spaces") String addressLine) {
        this.addressLine = addressLine;
    }

    public @Pattern(regexp = "^[A-Za-z]+( [A-Za-z]+){0,2}$", message = "State can only contain letters and up to 2 spaces") String getState() {
        return state;
    }

    public void setState(@Pattern(regexp = "^[A-Za-z]+( [A-Za-z]+){0,2}$", message = "State can only contain letters and up to 2 spaces") String state) {
        this.state = state;
    }

    public @Pattern(regexp = "^[0-9]{6}$", message = "Postal code must be exactly 6 digits, no letters or spaces allowed") String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(@Pattern(regexp = "^[0-9]{6}$", message = "Postal code must be exactly 6 digits, no letters or spaces allowed") String postalCode) {
        this.postalCode = postalCode;
    }

    public @Pattern(regexp = "^(Grade|Year) [0-9]{1,2}$", message = "Grade or year level must be in format 'Grade X' or 'Year Y'") String getGradeOrYearLevel() {
        return gradeOrYearLevel;
    }

    public void setGradeOrYearLevel(@Pattern(regexp = "^(Grade|Year) [0-9]{1,2}$", message = "Grade or year level must be in format 'Grade X' or 'Year Y'") String gradeOrYearLevel) {
        this.gradeOrYearLevel = gradeOrYearLevel;
    }

    @DecimalMin(value = "0.0", inclusive = true, message = "GPA score must be positive")
    @DecimalMax(value = "4.0", message = "GPA score cannot exceed 4.0")
    public double getGpaScore() {
        return gpaScore;
    }

    public void setGpaScore(@DecimalMin(value = "0.0", inclusive = true, message = "GPA score must be positive") @DecimalMax(value = "4.0", message = "GPA score cannot exceed 4.0") double gpaScore) {
        this.gpaScore = gpaScore;
    }

    @DecimalMin(value = "0.0", inclusive = true, message = "GWA percentile must be positive")
    @DecimalMax(value = "100.0", message = "GWA percentile cannot exceed 100.0")
    public double getGwaPercentile() {
        return gwaPercentile;
    }

    public void setGwaPercentile(@DecimalMin(value = "0.0", inclusive = true, message = "GWA percentile must be positive") @DecimalMax(value = "100.0", message = "GWA percentile cannot exceed 100.0") double gwaPercentile) {
        this.gwaPercentile = gwaPercentile;
    }

    public @FutureOrPresent(message = "Expected graduation date cannot be in the past") LocalDate getExpectedGraduationDate() {
        return expectedGraduationDate;
    }

    public void setExpectedGraduationDate(@FutureOrPresent(message = "Expected graduation date cannot be in the past") LocalDate expectedGraduationDate) {
        this.expectedGraduationDate = expectedGraduationDate;
    }

    public @Pattern(regexp = "^[A-Za-z]+( [A-Za-z]+){0,5}$", message = "School name can only contain letters and allow up to 5 spaces") String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(@Pattern(regexp = "^[A-Za-z]+( [A-Za-z]+){0,5}$", message = "School name can only contain letters and allow up to 5 spaces") String schoolName) {
        this.schoolName = schoolName;
    }

    public @Pattern(regexp = "^[A-Za-z]+( [A-Za-z]+){0,5}$", message = "Department can only contain letters and allow up to 5 spaces") String getDepartment() {
        return department;
    }

    public void setDepartment(@Pattern(regexp = "^[A-Za-z]+( [A-Za-z]+){0,5}$", message = "Department can only contain letters and allow up to 5 spaces") String department) {
        this.department = department;
    }

    public @NotBlank(message = "Password is required") @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{8,}$",
            message = "Password must be at least 8 characters long and contain at least one uppercase letter, one digit, and one special character"
    ) String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password is required") @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{8,}$",
            message = "Password must be at least 8 characters long and contain at least one uppercase letter, one digit, and one special character"
    ) String password) {
        this.password = password;
    }

    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }

    public LocalDateTime getTokenExpiry() {
        return TokenExpiry;
    }

    public void setTokenExpiry(LocalDateTime tokenExpiry) {
        TokenExpiry = tokenExpiry;
    }
}
