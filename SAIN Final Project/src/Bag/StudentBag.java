package Bag;

import java.util.ArrayList;

import Class.Student;

public class StudentBag {

	ArrayList<Student> StudentArray = new ArrayList<>();
	int count;
	
	public Student 	findById(String id){
		return null;
		
	}
	public void addStudent(Student obj){
		
		StudentArray.add(obj);
	}
	public Student findByUserName(String username){
		return null;
		
		// Search List
	}
	public ArrayList<Student> getStudentArray() {
		return StudentArray;
	}
	public void setStudentArray(ArrayList<Student> studentArray) {
		StudentArray = studentArray;
	}
	public int getCount() {
		return StudentArray.size();
	}
}
