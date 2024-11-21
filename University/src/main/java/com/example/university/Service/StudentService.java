package com.example.university.Service;

import com.example.university.Model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StudentService {

    ArrayList<Student> students = new ArrayList<>();

    public void addStudent(Student student){

        students.add(student);
    }

    public ArrayList<Student> getStudent(){
        return students;
    }

    public boolean deleteStudent(String id){
        for (Student u: students){
            if (u.getID().equals(id)){
                students.remove(u);
                return true;
            }
        }
        return false;
    }

    public boolean updateStudent(String id, Student student){

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getID().equals(id)){
                students.set(i, student);
                return true;
            }
        }
        return false;
    }

    public boolean addCourseHour(String id,int hours){

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getID().equals(id)){
                if (students.get(i).getRegisterdHours()+hours <=24){
                    students.get(i).setRegisterdHours(students.get(i).getRegisterdHours()+hours);
                    return  true;
                }
            }
        }
        return false;
    }
    public boolean deleteCourseHour(String id, int hours){
        for (Student student : students) {
            if (student.getID().equals(id)) {
                if (student.getRegisterdHours() - hours >= 12) {
                    student.setRegisterdHours(student.getRegisterdHours() - hours);
                return true;}
            }
        }
        return false;
    }
    public ArrayList<Student> getRangeStudentsbyHours(int min, int max){
        ArrayList<Student> universities1 = new ArrayList<>();
        for (Student u: students){
            if (u.getRegisterdHours() >= min && u.getRegisterdHours() <= max){universities1.add(u);}
        }
        if (universities1.isEmpty()){return null;
        }else return universities1;
    }
}