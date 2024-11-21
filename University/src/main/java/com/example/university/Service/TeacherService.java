package com.example.university.Service;

import com.example.university.Model.Student;
import com.example.university.Model.Teacher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TeacherService {

    ArrayList<Teacher> teachers=new ArrayList<>();

    public void addTeacher(Teacher teacher){

        teachers.add(teacher);
    }

    public ArrayList<Teacher> getTeachers(){
        return teachers;
    }

    public boolean deleteTeacher(String id){
        for (Teacher t: teachers){
            if (t.getId().equals(id)){
                teachers.remove(t);
                return true;
            }
        }
        return false;
    }

    public boolean updateTeacher(String id, Teacher teacher){

        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getId().equals(id)){
                teachers.set(i, teacher);
                return true;
            }
        }
        return false;
    }

    public boolean takeDayOff(String id,int days){

        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getId().equals(id)){
                if (teachers.get(i).getDaysOff() > 0){
                    teachers.get(i).setDaysOff(teachers.get(i).getDaysOff()-days);
                    teachers.get(i).setAvailable(false);
                    return  true;
                }
            }
        }
        return false;
    }
    public ArrayList<Teacher> getAvailableTeacher(){
        ArrayList<Teacher> availableTeachers=new ArrayList<>();
        for (Teacher t: teachers) {
            if (t.isAvailable()) {availableTeachers.add(t);}
        }
        if (availableTeachers.isEmpty()){return null;
        }else return availableTeachers;
    }
    public ArrayList<Teacher> getRangeTeachersbyAge(int min, int max){
        ArrayList<Teacher> teachers1 = new ArrayList<>();
        for (Teacher t: teachers){
            if (t.getAge() >= min && t.getAge() <= max){teachers1.add(t);}
        }
        if (teachers1.isEmpty()){return null;
        }else return teachers1;
    }
}
