package goodee.gdj58.online.service;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DateCompare {
	
	
	// 날짜 구하는 메서드
	public String getDate(int dateGap) {
		// LocalDate 기본 포맷 : yyyy-MM-dd
		LocalDate todayDate = LocalDate.now();
		LocalDate resultDate = todayDate.plusDays(dateGap);
		
		log.debug("\u001B[31m"+todayDate+"<-- getDate todayDate");
		log.debug("\u001B[31m"+resultDate+"<-- getDate resultDate");
		
		return resultDate.toString();
	}

	// 오늘날짜와 비교하는 메서드
	public int todayCompare(String date) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // 날짜 포맷 설정
		
		// 오늘날짜
		LocalDate todayDate = LocalDate.now();
		
		// 비교 날짜 
		LocalDate paramDate = LocalDate.parse(date, format);
		
		log.debug("\u001B[31m"+todayDate+"<-- todayCompare todayDate");
		log.debug("\u001B[31m"+paramDate+"<-- todayCompare paramDate");

		int result = todayDate.compareTo(paramDate);
		
		log.debug("\u001B[31m"+result+"<-- todayCompare result");
		
		return result; // 오늘 == 매개변수 날짜 : 0, 오늘<매개변수날짜 : 1, 오늘>매개변수날짜 : -1
	}
}
