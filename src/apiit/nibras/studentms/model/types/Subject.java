package apiit.nibras.studentms.model.types;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import apiit.nibras.studentms.model.people.Student;

public class Subject implements Comparable<Subject> {
	private String code;
	private String name;
	private Program program;
	private Stream stream;

	private URL details;

	private ArrayList<Student> students;

	public Subject(String code) {
		this.code = code;
	}

	public Subject(String code, String name, Program program, Stream stream) {
		this.code = code;
		this.name = name;
		this.program = program;
		this.stream = stream;
		this.students = new ArrayList<Student>();
	}

	public Subject(String code, String name, Program program, Stream stream,
			URL details) {
		this.code = code;
		this.name = name;
		this.program = program;
		this.stream = stream;
		this.details = details;
		this.students = new ArrayList<Student>();
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Program getProgram() {
		return this.program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}

	public Stream getStream() {
		return this.stream;
	}

	public void setStream(Stream stream) {
		this.stream = stream;
	}

	public URL getDetails() {
		return this.details;
	}

	public void setDetails(URL details) {
		this.details = details;
	}

	public List<Student> getStudents() {
		return this.students;
	}

	public void addStudent(Student student) {
		this.students.add(student);
	}

	public void removeStudent(Student student) {
		this.students.remove(student);
	}

	@Override
	public int compareTo(Subject subject) {
		return this.getCode().compareTo(subject.getCode());
	}

	public String toString() {
		return this.name + "(" + this.code + ")";
	}
}
