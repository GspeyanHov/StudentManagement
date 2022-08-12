import commands.Commands;
import exception.LessonNotFoundException;
import model.Lesson;
import model.Student;
import model.User;
import model.UserType;
import storage.LessonList;
import storage.StudentList;
import storage.UserList;

import java.util.Date;
import java.util.Scanner;

import static util.DateUtil.stringToDate;

public class StudentDemo implements Commands {


    private static Scanner scanner = new Scanner(System.in);
    private static StudentList studentList = new StudentList();
    private static LessonList lessonList = new LessonList();
    private static UserList userList = new UserList();
    private static User currentUser = null;

    public static void main(String[] args) {

        initData();

        boolean run = true;
        while (run) {
            Commands.printLoginCommands();
            int command;
            try {
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                command = -1;
            }
            switch (command) {
                case EXIT:
                    run = false;
                    break;
                case LOGIN:
                    login();
                    break;
                case REGISTER:
                    register();
                    break;
                default:
                    System.out.println("Invalid command ");
                    break;
            }
        }
    }

    private static void loginUser() {
        System.out.println("Welcome " + currentUser.getName());
        boolean run = true;
        while (run) {
            Commands.printUserCommands();
            int command;
            try {
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                command = -1;
            }

            switch (command) {
                case EXIT:
                    run = false;
                    break;
                case ADD_STUDENT:
                    addStudent();
                    break;
                case PRINT_ALL_STUDENTS:
                    StudentList.printArray();
                    break;
                case DELETE_STUDENT_BY_INDEX:
                    deleteByIndex();
                    break;
                case PRINT_STUDENT_BY_LESSON:
                    printStudentByLessonName();
                    break;
                case COUNT:
                    System.out.println("Student's count: " + StudentList.getSize());
                    break;
                case PRINT_ALL_LESSONS:
                    LessonList.printArray();
                    break;
                default:
                    System.out.println("Invalid command ");
                    break;
            }
        }
    }

    private static void loginAdmin() {
        System.out.println("Welcome " + currentUser.getName());
        boolean run = true;
        while (run) {
            Commands.printAdminCommands();
            int command;
            try {
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                command = -1;
            }

            switch (command) {
                case EXIT:
                    run = false;
                    break;
                case ADD_STUDENT:
                    addStudent();
                    break;
                case PRINT_ALL_STUDENTS:
                    StudentList.printArray();
                    break;
                case DELETE_STUDENT_BY_INDEX:
                    deleteByIndex();
                    break;
                case PRINT_STUDENT_BY_LESSON:
                    printStudentByLessonName();
                    break;
                case COUNT:
                    System.out.println("Student's count: " + StudentList.getSize());
                    break;
                case CHANGE_STUDENTS_LESSON:
                    changeStudentLessonName();
                    break;
                case ADD_LESSON:
                    addLesson();
                    break;
                case PRINT_ALL_LESSONS:
                    LessonList.printArray();
                    break;
                default:
                    System.out.println("Invalid command ");
                    break;
            }
        }
    }

    private static void login() {
        System.out.println("Please input email, password ");
        String emailPasswordStr = scanner.nextLine();
        String[] emailPassword = emailPasswordStr.split(",");
        User user = userList.getUserByEmail(emailPassword[0]);
        if (user == null) {
            System.out.println("User with " + emailPassword[0] + " does not exist! ");
        } else if (user.getPassword().equals(emailPassword[1])) {
            currentUser = user;
            if (user.getUserType() == UserType.ADMIN) {
                loginAdmin();
            } else if (user.getUserType() == UserType.USER) {
                loginUser();
            }
        } else {
            System.out.println("Wrong password! ");
        }
    }

    private static void register() {
        System.out.println("Please input name, surname, email, password");
        String userDataStr = scanner.nextLine();
        String[] userData = userDataStr.split(",");
        if (userData.length < 4) {
            System.out.println("please input correct data! ");
        } else {
            if (userList.getUserByEmail(userData[0]) == null) {
                User user = new User();
                user.setName(userData[0]);
                user.setSurname(userData[1]);
                user.setEmail(userData[2]);
                user.setPassword(userData[3]);
                user.setUserType(UserType.USER);
                userList.add(user);
                System.out.println("User created ");
            } else {
                System.out.println("user with " + userData[0] + " already exist's! ");
            }
        }
    }

    private static void initData() {
        User admin = new User("Kirakos", "Kirakosyan", "kirakos@mail.ru", "admin", UserType.ADMIN);
        Lesson java = new Lesson("java", "Gasparyan", 5, 35, stringToDate("02/06/2021"));
        Lesson php = new Lesson("php", "Poghosyan", 4, 30, stringToDate("05/07/2021"));
        Lesson kotlin = new Lesson("kotlin", "Sargsyan", 3, 25, stringToDate("15/08/2021"));
        userList.add(admin);
        lessonList.add(java);
        lessonList.add(php);
        lessonList.add(kotlin);
        studentList.add(new Student("Poghos", "Poghosyan", "098154578", 18, "Gyumri", java, admin, new Date()));
        studentList.add(new Student("Surik", "Surikyan", "098154579", 24, "Paris", php, admin, new Date()));
        studentList.add(new Student("Levon", "Levonyan", "098154558", 22, "London", kotlin, admin, new Date()));
    }

    private static void addLesson() {
        System.out.println("please input lesson name ");
        String lessonName = scanner.nextLine();
        System.out.println("please input teacher's name ");
        String teacherName = scanner.nextLine();
        System.out.println("please input lesson duration by month ");
        int duration = Integer.parseInt(scanner.nextLine());
        System.out.println("please input lesson price ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.println("please input lesson's start date (dd/MM/yyyy) ");
        String strDate = scanner.nextLine();

        Lesson lesson = new Lesson("name", "teacherName", duration, price, stringToDate(strDate));
        lessonList.add(lesson);
        System.out.println("lesson created! ");

    }

    private static void changeStudentLessonName() {
        StudentList.printArray();
        System.out.println("print student's index for change student's lesson: ");
        int index = Integer.parseInt(scanner.nextLine());
        Student student = studentList.getByIndex(index);
        if (student == null) {
            System.out.println("Wrong index!!! ");
        } else {
            if (LessonList.getSize() != 0) {
                LessonList.printArray();
                System.out.println("Please choose lesson index! ");
                try {
                    int lessonIndex = Integer.parseInt(scanner.nextLine());
                    Lesson lesson = lessonList.getLessonByIndex(lessonIndex);
                    student.setLesson(lesson);
                    System.out.println("lesson changed! ");
                } catch (LessonNotFoundException | NumberFormatException e) {
                    System.out.println("Please choose correct index or number! ");
                    changeStudentLessonName();
                }
            }
        }
    }

    private static void printStudentByLessonName() {
        System.out.println("Print student's lesson ");
        String lessonName = scanner.nextLine();
        StudentList.printStudentByLessonName(lessonName);
    }

    private static void deleteByIndex() {
        StudentList.printArray();
        System.out.println("Please choose student index ");
        int index = Integer.parseInt(scanner.nextLine());
        studentList.delete(index);
    }

    private static void addStudent() {
        if (LessonList.getSize() != 0) {
            LessonList.printArray();
            System.out.println("Please choose lesson index! ");

            Lesson lesson = null;
            try {
                int lessonIndex = Integer.parseInt(scanner.nextLine());
                lesson = lessonList.getLessonByIndex(lessonIndex);
                System.out.println("Please input student's name ");
                String name = scanner.nextLine();
                System.out.println("please input student's surname ");
                String surname = scanner.nextLine();
                System.out.println("please input student's phone number ");
                String phoneNumber = scanner.nextLine();
                System.out.println("please input student's age ");
                int age = Integer.parseInt(scanner.nextLine());
                System.out.println("please input student's city ");
                String city = scanner.nextLine();

                Student student = new Student(name, surname, phoneNumber, age, city, lesson, currentUser, new Date());
                studentList.add(student);
                System.out.println("Student created ");
            } catch (LessonNotFoundException | NumberFormatException e) {
                System.out.println("Please choose correct index or number! ");
                addStudent();
            }
        }
    }
}
