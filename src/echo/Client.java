package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
//import java.util.Scanner; //스트림으로 변경되서

public class Client {

	public static void main(String[] args) throws IOException{
		
		//소켓부터 만듦
		Socket socket = new Socket();
		
		System.out.println("<클라이언트 시작>");
		System.out.println("=======================");
		
		//connect 시도
		System.out.println("[서버에 연결을 요청합니다]");//
		socket.connect(new InetSocketAddress("192.168.0.31",10001));

		System.out.println("[서버에 연결 되었습니다]");
		
		//메세지 보내기
		
		//out
		//OutputStream out = new FileOutputStream("C:\\javaStudy\\MS949-copy.txt");//빨대 꽂는 순간 리셋됨.다 삭제되서 없어짐->파일이름수정-copy
		//OutputStreamWriter osw = new OutputStreamWriter(out,"MS949");
		//BufferedWriter bw = new BufferedWriter(osw);
		
		//OutputStream out = new FileOutputStream("C:\\javaStudy\\song.txt");
		//메세지 보내기용 스트림
		OutputStream os = socket.getOutputStream();
		OutputStreamWriter osw = new OutputStreamWriter(os,"UTF-8");
		BufferedWriter bw = new BufferedWriter(osw);
		
		//메세지 받기용 스트림
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is,"UTF-8");
		BufferedReader br = new BufferedReader(isr);
		
		
		//<<여기부터 반복>>-키보드입력,메세지 보내기,받기
		
		//키보드 준비
		//Scanner sc = new Scanner(System.in);//스캐너도 스트림의 종류!!
		InputStream sc = System.in;
		InputStreamReader scIsr = new InputStreamReader(sc,"UTF-8");
		BufferedReader scbr = new BufferedReader(scIsr);
		
		while(true) {
			
			// 키보드 입력
			String str = scbr.readLine();//스트림으로 바뀌면서 입력된걸 읽으니까 readLine임.
			
			//if(str.equals("/q"))->원래 이거지만 null값이 더 안나오도록(?)하려고 아래처럼 바꿈.
			if("/q".equals(str)) {
				break;
			}
			
			//메세지 보내기
			bw.write(str);
			bw.newLine();
			bw.flush();//강제로 보냄
			
			//메세지 받기용
			String reMsg = br.readLine();
			System.out.println("server: ["+reMsg+"]");
			
		}
		
		//<<여기까지 반복>>
		
		System.out.println("==============================");
		System.out.println("<클라이언트 종료>");
		
		//Println 만들기
		OutputStream Pos = System.out;
		OutputStreamWriter Posw = new OutputStreamWriter(Pos, "UTF-8");
		BufferedWriter pbw = new BufferedWriter(Posw);
		pbw.write("모니터출력하기");
		pbw.newLine();
		pbw.close();
		
		//닫기
		//sc.close();
		br.close();
		bw.close();
		socket.close();
	}

}
