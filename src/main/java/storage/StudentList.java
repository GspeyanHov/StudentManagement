package storage;

import model.Student;

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
