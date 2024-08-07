import java.util.LinkedHashMap;

class StudentGrades {
    private LinkedHashMap<Integer, Student> students;

    public StudentGrades() {
        students = new LinkedHashMap<>();
    }

    public void addStudent(Student student) {
        if (students.containsKey(student.getId())) {
            System.out.println("Student ID already exists: " + student.getId());
        } else {
            students.put(student.getId(), student);
            System.out.println("Student added: " + student);
        }
    }

    public void removeStudent(int studentId) {
        if (students.containsKey(studentId)) {
            Student removedStudent = students.remove(studentId);
            System.out.println("Student removed: " + removedStudent);
        } else {
            System.out.println("Student not found with ID: " + studentId);
        }
    }

    public void updateStudentGrade(int studentId, char newGrade) {
        Student student = students.get(studentId);
        if (student != null) {
            student.setGrade(newGrade);
            System.out.println("Student updated: " + student);
        } else {
            System.out.println("Student not found with ID: " + studentId);
        }
    }

    public void displayStudents() {
        System.out.println("Student List:");
        for (Student student : students.values()) {
            System.out.println(student);
        }
    }
}
class Student {
    private int id;
    private String name;
    private char grade;

    public Student(int id, String name, char grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public char getGrade() {
        return grade;
    }

    public void setGrade(char grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student ID: " + id + ", Name: " + name + ", Grade: " + grade;
    }
}
public class StudentGradesTest {
    public static void main(String[] args) {
        StudentGrades grades = new StudentGrades();

        Student student1 = new Student(1, "Allen", 'A');
        Student student2 = new Student(2, "Bob", 'B');
        Student student3 = new Student(3, "Carl", 'C');

        grades.addStudent(student1);
        grades.addStudent(student2);
        grades.addStudent(student3);
        grades.displayStudents();
        grades.updateStudentGrade(2, 'A');
        grades.removeStudent(1);
        grades.displayStudents();
    }
}


