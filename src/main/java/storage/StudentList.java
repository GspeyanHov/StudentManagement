package storage;

import model.Student;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class StudentList {

    private static Student[] array = new Student[10];
    private static int size = 0;

    public static void printStudentByLessonName(String lessonName) {
        for (int i = 0; i < size; i++) {
            if (array[i].getLesson().equals(lessonName)) {
                System.out.println(array[i]);
            }
        }
    }

    public static void writeStudentsToExcel(String filDir) throws IOException {
        File directory = new File(filDir);
        if(directory.isFile()){
            throw new RuntimeException("fileDir must be a directory ");
        }
        File excelFile = new File(directory, "students_" + System.currentTimeMillis() + ".xlsx");
        excelFile.createNewFile();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("students");
        Row headerRow = sheet.createRow(0);
        Cell nameCell = headerRow.createCell(0);
        nameCell.setCellValue("name");
        Cell surnameCell = headerRow.createCell(1);
        surnameCell.setCellValue("surname");
        Cell ageCell = headerRow.createCell(2);
        ageCell.setCellValue("age");
        Cell phoneCell = headerRow.createCell(3);
        phoneCell.setCellValue("phoneNumber");
        for (int i = 0; i < size; i++) {
            Student student = array[i];
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(student.getName());
            row.createCell(1).setCellValue(student.getSurName());
            row.createCell(2).setCellValue(student.getAge());
            row.createCell(3).setCellValue(student.getPhoneNumber());
        }
        workbook.write(new FileOutputStream(excelFile));
        System.out.println("Excel file was created successfully! ");
    }


    public Student getByIndex(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        return array[index];
    }

    private void extend() {
        Student[] students = new Student[array.length + 10];
        for (int i = 0; i < array.length; i++) {
            students[i] = array[i];
        }
        array = students;
    }

    public void add(Student student) {
        if (array.length == size) {
            extend();
        }
        array[size++] = student;
    }

    public static void printArray() {
        for (int i = 0; i < size; i++) {
            System.out.println(i + ". " + array[i]);
        }
    }

    public void delete(int index) {
        if (index >= size || index < 0) {
            System.out.println("Error array index! ");
            return;
        }
        Student student = array[index];
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;

    }

    public static int getSize() {
        return size;
    }
}
