package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import school.School;
import school.Score;
import school.Student;
import school.Subject;
import school.report.GenerateGradeReport;
import utils.Define;

public class GradeTest {
	
	School goodSchool = School.getInstance();
	Subject korean;
	Subject math;
	Subject dance;
	
	String subjectFirstSpell;
	String subjectName;
	int subjectId; 

	GenerateGradeReport gradeReport = new GenerateGradeReport();
	
	public static void main(String[] args) throws FileNotFoundException {

		GradeTest test = new GradeTest();
		test.creatSubject2();
		test.createStudent2();
		
		String report = test.gradeReport.getReport(); //성적 결과 생성
		test.createResultInfo(report);
		System.out.println(report); // 출력		
		
	}
	
	public void creatSubject2() throws FileNotFoundException{
		Scanner scanner = new Scanner(new FileInputStream("subjectInfo.txt"));
		
		while(scanner.hasNextLine()) {
			subjectFirstSpell = scanner.next();
			subjectName = scanner.next();
			subjectId = scanner.nextInt();
			
			Subject sub = new Subject(subjectName, subjectId);
			if(subjectFirstSpell.equals("K")) {
				korean = sub;
			}else if(subjectFirstSpell.equals("M")){
				math = sub;
			}else if(subjectFirstSpell.equals("D")) {
				dance = sub;
			}
			goodSchool.addSubject(sub);
		}
		scanner.close();
	}
	
	public void createStudent2() throws FileNotFoundException {
		
		Scanner scanner = new Scanner(new FileInputStream("studentinfo.txt"));
		String name;
		int id;
		int koreanScore;
		int mathScore;
		Integer danceScore;
		int majorCode;
		
		Student student = null;
		while(scanner.hasNextLine()) {
			//sacnner클래스로 데이터 받기
			name = scanner.next();
			id = scanner.nextInt();
			List<Integer> scoreList = new ArrayList<Integer>();
			scoreList.add(scanner.nextInt());
			
			koreanScore = scanner.nextInt();
			mathScore = scanner.nextInt();
			danceScore = scanner.nextInt();
			if(danceScore.toString().length() == 4) {
				majorCode = danceScore;
			}else {
				majorCode = scanner.nextInt();
			}
			
			//전공과목에 맞는 subject를 student에 저장
			if(majorCode == Define.KOREAN) {
				student = new Student(id, name, korean );
			}
			else if(majorCode == Define.MATH) {
				student = new Student(id, name, math );
			}
			else if(majorCode == Define.DANCE) {
				student = new Student(id, name, dance );
			}
			else {
				System.out.println("전공 과목 오류 입니다");
				return;
			}
			
			goodSchool.addStudent(student);
			korean.register(student);
			math.register(student);
			addScoreForStudent(student, korean, koreanScore);
			addScoreForStudent(student, math, mathScore);
			
			if(danceScore<=100) {
				dance.register(student);
				addScoreForStudent(student, dance, danceScore);
			}
						
		}
		scanner.close();
	}
	
	//과목별 성적 입력
	public void addScoreForStudent(Student student, Subject subject, int point){
			
		Score score = new Score(student.getStudentId(), subject, point);
		student.addSubjectScore(score);
			
	}
	
	public void createResultInfo(String report) {
		try(FileOutputStream fos = new FileOutputStream("resultInfo.txt");
				OutputStreamWriter osw = new OutputStreamWriter(fos)){
			osw.write(report);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}





