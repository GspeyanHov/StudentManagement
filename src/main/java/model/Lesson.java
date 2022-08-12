package model;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

import static util.DateUtil.dateToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {

    private String name;
    private String teacherName;
    private int duration;
    private double price;
    private Date startDate;


}
