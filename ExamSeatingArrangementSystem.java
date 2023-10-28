import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

class Student {
    private String name;
    private int rollNumber;

    public Student(String name, int rollNumber) {
        this.name = name;
        this.rollNumber = rollNumber;
    }

    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }
}

class Classroom {
    private int roomNumber;
    private int capacity;
    private List<Student> students;

    public Classroom(int roomNumber, int capacity) {
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.students = new ArrayList<>();
    }

    public boolean addStudent(Student student) {
        if (students.size() < capacity) {
            students.add(student);
            return true;
        }
        return false;
    }

    public List<Student> getStudents() {
        return students;
    }

    public int getRoomNumber() {
        return roomNumber;
    }
}

class SeatingArrangementGenerator {
    public static void generateSeatingArrangement(List<Classroom> classrooms, List<Student> students) {
        Collections.shuffle(students, new Random());

        int currentClassroomIndex = 0;
        for (Student student : students) {
            Classroom classroom = classrooms.get(currentClassroomIndex);
            boolean added = classroom.addStudent(student);
            if (!added) {
                currentClassroomIndex = (currentClassroomIndex + 1) % classrooms.size();
                classrooms.get(currentClassroomIndex).addStudent(student);
            }
        }
    }
}

public class ExamSeatingArrangementSystem {
    public static void main(String[] args) {
        List<Classroom> classrooms = new ArrayList<>();
        classrooms.add(new Classroom(101, 30));
        classrooms.add(new Classroom(102, 40));
        classrooms.add(new Classroom(103, 25));

        List<Student> students = new ArrayList<>();
        students.add(new Student("Student 1", 1));
        students.add(new Student("Student 2", 2));

        SeatingArrangementGenerator.generateSeatingArrangement(classrooms, students);
        
        for (Classroom classroom : classrooms) {
            System.out.println("Classroom " + classroom.getRoomNumber() + ":");
            List<Student> classroomStudents = classroom.getStudents();
            for (Student student : classroomStudents) {
                System.out.println("  " + student.getName() + " (Roll No. " + student.getRollNumber() + ")");
            }
        }
    }
}

