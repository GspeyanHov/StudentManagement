package storage;

import model.User;

public class UserList {

    private static User[] array = new User[10];
    private static int size = 0;



    public User getByIndex(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        return array[index];
    }
    public User getUserByEmail(String email){
        for (int i = 0; i < size; i++) {
            if(array[i].getEmail().equals(email)){
                return array[i];
            }
        }
        return null;
    }

    private void extend() {
        User[] users = new User[array.length + 10];
        for (int i = 0; i < array.length; i++) {
            users[i] = array[i];
        }
        array = users;
    }

    public void add(User user) {
        if (array.length == size) {
            extend();
        }
        array[size++] = user;
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
        User user = array[index];
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;

    }

    public static int getSize() {
        return size;
    }
}
