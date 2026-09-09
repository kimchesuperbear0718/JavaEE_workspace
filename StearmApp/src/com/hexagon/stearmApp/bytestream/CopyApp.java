package com.hexagon.stearmApp.bytestream;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

//복사과정에서 굳이 해당 데이터를 문자형 데이터로 변환하여,사람의 육안으로 확인하려고 할때,바이트 기반 일 경우 영문은 깨지지 않지만
//한글의 경우 깨져보인다..
//해결책?현재 바이트 기반 스트림에 문자기반 스트림을 덧붙이자!!!(분자기반으로 업그레이드 하자)
//문자 기반 스트림의 특징
//흐르는 데이터를 문자로 이해할 수 있는 능력이 있음(한글이던, 영문이던 상관없이..)
//[클래스명 규칙]
/*바이트기반 입력 스트림~~InPutStream
 * 바이트 기반 출력스트림 ~~Writer로 끝남
 * 
 * 3.버퍼 기잔 스트림들의 API의 명칭
 * 버퍼처리된 업럽스트림은 Buffer~~로 시작 ex)buffer*/

//1.문자기반 스트림들이 API 의 명칭

//버퍼 처리 스트림(채팅에 아주 압도적으로 많이 씀)
 BufferReader buffer;
BuffereredWriter buffw;

public class CopyApp {
	// 입력스트림은 대상 소스에 따라 여러가지 유형으로 나누어지는데,우리의 경우 입력 소스가 file 이므로,
	// 아래의 틀래스를 이용할 수 있다..
	FileInputStream fls;
	FileOutputStream fos;
	String path = "C:/Users/user/eclipse-workspace/StearmApp/data";

	InputStreamReader reader;
	OutputStreamWriter writer;

	public void copy() {
		// System.out.println("나 불렀어?");
		// 문법적으로 문제가 있어서가 아니라,sun 에서 아래의 코드를 대상으로 에러가 발생할 가능성이 있으므로,
		// 아래의 클래스를 이용할 수 있다..

		try {
			fls = new FileInputStream(path + "/ori.text");
			reader = new InputStreamReader(fls);

			fos = new FileOutputStream(path + "/copy.txt");
			writer = new OutputStreamWriter(fos);
			buffw = new BufferedWriter(writer);
			

			String data=null;
			int count=0;

			while (true) {
				data = fls.read();

				if (data == -1)
					break;
				System.out.print((char) data);
				count++;
			}

			fos.write(data);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			try {
				if (fos != null)
					fos.close();// 입력스트림이 존재할때만 닫는다
				if (fls != null)
					fls.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		CopyApp copyApp = new CopyApp();
		copyApp.copy();

	}

}
