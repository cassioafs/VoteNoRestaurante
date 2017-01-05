package br.com.dbserver.votenorestaurante.util;

import java.util.Calendar;

public class DateUtil {

	public static Calendar resolvePrimeiroUltimo(boolean isPrimeiro) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.SUNDAY);
		calendar.setTime(calendar.getTime());
		
		if (isPrimeiro) {
			calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		} else {
			calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		}
		
		return calendar;
	}

	
	public static Calendar getPrimeiroDiaDaSemana() {
		return resolvePrimeiroUltimo(true);
	}
	
	public static Calendar getUltimoDiaDaSemana() {
		return resolvePrimeiroUltimo(false);
	}

}