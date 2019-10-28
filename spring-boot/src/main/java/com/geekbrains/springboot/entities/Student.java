package com.geekbrains.springboot.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.geekbrains.springboot.validation.ValidEmail;
import com.geekbrains.springboot.validation.ValidPhone;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "is required")
    @Size(min = 2, max = 50, message = "size should be from 2 to 50 symbols")
    @Column(name = "firstname")
    private String firstName;

    @NotNull(message = "is required")
    @Size(min = 2, max = 50, message = "size should be from 2 to 50 symbols")
    @Column(name = "lastname")
    private String lastName;

    @ValidPhone
    @NotNull(message = "is required")
    @Size(min = 6, message = "size should be at least 6 symbols")
    @Column(name = "phone")
    private String phone;

    @ValidEmail
    @NotNull(message = "is required")
    @Size(min = 1, message = "size should be at least 1 symbol")
    @Column(name = "email")
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User addedUser;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "add_date")
    private Date addedDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "students_courses",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> courses;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getAddedUser() {
        return addedUser;
    }

    public void setAddedUser(User addedUser) {
        this.addedUser = addedUser;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", addedUser=" + addedUser +
                ", addedDate=" + addedDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(firstName, student.firstName) &&
                Objects.equals(lastName, student.lastName) &&
                Objects.equals(phone, student.phone) &&
                Objects.equals(email, student.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, phone, email);
    }
}
