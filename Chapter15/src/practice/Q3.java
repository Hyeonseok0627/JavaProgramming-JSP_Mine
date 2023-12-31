package practice;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Q3 {

	public static void main(String[] args) {

		//try-with-resource문법 사용
		//finally(close() 매서드 사용) 사용을 하지 않는 간단하여 효율적인 코드 방식
		try(FileOutputStream fos = new FileOutputStream("a.txt");
				OutputStreamWriter osw = new OutputStreamWriter(fos)){
			osw.write("지금까지 자바 정말 재미있게 공부했어요^^");
		}catch(IOException e) {
			System.out.println(e);
		}
		
		System.out.println("a.txt 파일을 열어보세요");
	}
}
