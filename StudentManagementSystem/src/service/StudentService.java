package service;

import dao.StudentDAO;
import model.Student;
import java.util.List;

public class StudentService {

    StudentDAO dao = new StudentDAO();

    public void addStudent(Student s) { dao.addStudent(s); }

    public void updateStudent(Student s) { dao.updateStudent(s); }

    public void deleteStudent(int id) { dao.deleteStudent(id); }

    public List<Student> getStudents() { return dao.getAllStudents(); }

    public Student searchStudent(int id) { return dao.getStudentById(id); }
}
