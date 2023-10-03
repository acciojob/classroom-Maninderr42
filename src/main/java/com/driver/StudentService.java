package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepositoryObj;

    public void seStReObj(Student student){
        studentRepositoryObj.addStudent(student);
    }

    public void seteReObj(Teacher teacher){
        studentRepositoryObj.addTeacher(teacher);
    }

    public void addteacherStudentPair(String student, String teacher){
        Optional<Student> student1=studentRepositoryObj.getstudent(student);
        Optional<Teacher> teacher1=studentRepositoryObj.getTeacher(teacher);

        if(student1.isEmpty()){
            throw new RuntimeException("Student not present in database......");
        }
        if(teacher1.isEmpty()){
            throw new RuntimeException("Teacher not present in the database........");
        }
     Teacher teacherobj=teacher1.get();
        teacherobj.setNumberOfStudents(teacherobj.getNumberOfStudents()+1);
        studentRepositoryObj.addTeacher(teacherobj);
        studentRepositoryObj.tAndsPair(student,teacher);
    }

    public Student getStudentName(String name){

        Optional<Student> optionalStudent=studentRepositoryObj.getstudent(name);
        if(optionalStudent.isPresent()){
            return optionalStudent.get();
        }
        throw new RuntimeException("Student not be present");

    }

    public Teacher getTeacherName(String name){
        Optional<Teacher> optionalTeacher=studentRepositoryObj.getTeacher(name);
        if(optionalTeacher.isPresent()){
            return optionalTeacher.get();
        }
        throw new RuntimeException("Teacher not be present");
    }

    public List<String> getStudentByTeacherName(String teacher){
        return studentRepositoryObj.getStudentByTeacherName(teacher);
    }

    public List<String> getAllStudent(){
        return studentRepositoryObj.getAllStudent();
    }

    public void deleteByNameTeacher(String teacher){
        List<String> delele=getStudentByTeacherName(teacher);
        studentRepositoryObj.deleteTeacher(teacher);
        for(String de:delele){
            studentRepositoryObj.deleteStudent(de);
        }
    }
    public void deleteAllTeacher(){
        List<String> obj=studentRepositoryObj.getAllTeacher();
        for(String de:obj){
            deleteByNameTeacher(de);
        }
    }

}
