package echo;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws IOException{
		try (//서버소켓 생성
		ServerSocket serverSocket = new ServerSocket()) {
			//바인딩
			//serverSocket.bind("192.168.0.31",10001);
			serverSocket.bind(new InetSocketAddress("192.168.0.31",10001));
			//InetSocketAddress aaa = new InetSocketAddress();
			
			//서버시작
			System.out.println("<서버시작>");
			System.out.println("==============================");
			
			
			//반복
			while(true) {
				
				System.out.println("[연결을 기다리고 있습니다]");
				
				//클라이언트가 connect(접속)를 시도하면 accept()가 실행됨
				Socket socket = serverSocket.accept();//소켓을 만듦.(힙에..주소만들어짐)
				
				//Socket출장!!!!!!!! thread.start()
				
				Thread thread = new ServerThread(socket);
				thread.start();
			}
		}
		
		
		/* 계속 반복해야하니까 아래는 없앰... 서버는 계속 켜져있음.
		
		System.out.println("=======================================");
		System.out.println("<서버 종료>");
		
		
		//닫기
		//br.close();  
		//bw.close();  
		socket.close();
		serverSocket.close();
		*/
		
		
	}

}
