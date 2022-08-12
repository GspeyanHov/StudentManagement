package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import static util.DateUtil.dateToString;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    private String name;
    private String surName;
    private String phoneNumber;
    private int age;
    private String city;
    private Lesson lesson;
    private User registeredUser;
    private Date registeredDate;


}
