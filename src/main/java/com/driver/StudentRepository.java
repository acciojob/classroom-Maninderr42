package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;


import java.util.HashMap;
@Repository
public class StudentRepository {
HashMap<String,Student> db1=new HashMap<>();
HashMap<String, Teacher> db2=new HashMap<>();

HashMap<String, ArrayList<String>> tsDb=new HashMap<>();

public String addStudent(Student student){
    String name=student.getName();
    db1.put(name, student);
return "Student data Success!!!";
}

public String addTeacher(Teacher teacher){
    String name=teacher.getName();
    db2.put(name,teacher);
    return "Teacher Data Success Added>>>>>>>>>>";
}

public String tAndsPair(String student, String teacher){
    ArrayList <String> liststudent=tsDb.getOrDefault(teacher, new ArrayList<String>());
    liststudent.add(student);
    tsDb.put(teacher,liststudent);
    return "Teacher- Student Data Success Added..................";
}
public Optional<Student> getstudent(String student){
if(db1.containsKey(student)){
    return Optional.of(db1.get(student));
}
return Optional.empty();
}
public Optional<Teacher> getTeacher(String teacher){
if(db2.containsKey(teacher)){
    return Optional.of(db2.get(teacher));
}
return Optional.empty();
}

public List<String> getStudentByTeacherName(String teacher){
    return tsDb.getOrDefault(teacher,new ArrayList<>());
}

public List<String> getAllStudent(){
    return new ArrayList<>(db1.keySet());
}

public void deleteTeacher(String teacher){
    db2.remove(teacher);
    tsDb.remove(teacher);
}
public void deleteStudent(String student){
    db1.remove(student);
}
public List<String> getAllTeacher(){
    return new ArrayList<>(db2.keySet());
}
}
