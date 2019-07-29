package com.liyulin.protobuf;

import java.util.List;

import com.google.protobuf.InvalidProtocolBufferException;
import com.liyulin.protobuf.proto.GradeDTO;
import com.liyulin.protobuf.proto.GradeDTO.Grade;
import com.liyulin.protobuf.proto.StudentDTO;
import com.liyulin.protobuf.proto.StudentDTO.Student;

import junit.framework.TestCase;

public class ProtoBufTest extends TestCase {

	public void test() {
		StudentDTO.Student.Builder builder = StudentDTO.Student.newBuilder();
		builder.setName("张三");
		builder.setSex("男");
		builder.setAge(18);
		
		Student student = builder.build();
		byte[] data = student.toByteArray();
		
		Student studentForm = null;
		try {
			studentForm = StudentDTO.Student.parseFrom(data);
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}
		System.err.println("name=" + studentForm.getName());
		System.err.println("sex=" + studentForm.getSex());
		System.err.println("age=" + studentForm.getAge());
	}

	
	public void testList() {
		GradeDTO.Grade.Builder grade = GradeDTO.Grade.newBuilder();
		for (int i = 0; i < 10; i++) {
			Student student = Student.newBuilder()
									 .setName("张三" + i)
									 .setAge(i + 10)
									 .setSex(((i | 1) == 1) ? "男" : "女")
									 .build();
			grade.addStudent(student);
		}

		byte[] formBytes = grade.build().toByteArray();

		Grade gradeForm = null;
		try {
			gradeForm = Grade.parseFrom(formBytes);
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}

		List<Student> students = gradeForm.getStudentList();
		for (Student student : students) {
			System.err.printf("%10s%10s%10d\n" , student.getName(), student.getSex(), student.getAge());
		}
	}

}