package com.example.university.Service;

import com.example.university.Model.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CourseService {

    ArrayList<Course> courses=new ArrayList<>();

    public void addCourse(Course course){
        courses.add(course);
    }
    public ArrayList<Course> getCourses(){
        return courses;
    }
    public boolean removeCourse(String id){
        for (Course c:courses){
        if (c.getId().equals(id)){
            courses.remove(c);
            return true;
        };
    }
        return false;
    }
    public boolean updateCourse(String id ,Course course){
        for (Course c:courses){
            if (c.getId().equals(id)){
                courses.set(courses.indexOf(c),course);
                return true;
            }
        }
        return false;
    }

    public boolean increaseCapacity(String id,int capacity){
        for (Course c:courses){
            if (c.getId().equals(id)){
                c.setCapacity(c.getCapacity()+capacity);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Course> getRangeofCapacity(int min, int max){
        ArrayList<Course> range=new ArrayList<>();
        for (Course c:courses){
            if (c.getCapacity()>=min && c.getCapacity()<=max){
                range.add(c);
            }
        }
        if (range.isEmpty()){return null;
        }else return range;
    }

    public ArrayList<Course> getCoursesbySubject(String subject){
        ArrayList<Course> courses1=new ArrayList<>();
        for (Course c:courses){
            if (c.getSubject().equals(subject)){
                courses1.add(c);
            }
        }
        if (courses1.isEmpty()){return null;
        }else return courses1;
    }

}