package storage;

import exception.LessonNotFoundException;
import model.Lesson;

public class LessonList {

    private static Lesson[] array = new Lesson[10];
    private static int size = 0;


    public Lesson getLessonByIndex(int index) throws LessonNotFoundException {
        if (index >= size || index < 0) {
            throw new LessonNotFoundException("Lesson with that " + index + "does not exist ");
        }
        return array[index];
    }

    private void extend() {
        Lesson[] lesson = new Lesson[array.length + 10];
        for (int i = 0; i < array.length; i++) {
            lesson[i] = array[i];
        }
        array = lesson;
    }

    public void add(Lesson lesson) {
        if (array.length == size) {
            extend();
        }
        array[size++] = lesson;
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
        Lesson lesson = array[index];
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;

    }

    public static int getSize() {
        return size;
    }
}
