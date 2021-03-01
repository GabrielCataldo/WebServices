package com.ws.integration.entities.abstratos;

/***************************************************************
 * @author : Gabriel Henrique Cataldo Nascimento Paes
 * @since : 27 de fev de 2021
 ***************************************************************/
public class LocalTime {
	
	private Number hour;
	private Number minute;
	private Number nano;
	private Number second;
	
	
	public Number getHour() {
		return hour;
	}
	public Number getMinute() {
		return minute;
	}
	public Number getNano() {
		return nano;
	}
	public Number getSecond() {
		return second;
	}
	public void setHour(Number hour) {
		this.hour = hour;
	}
	public void setMinute(Number minute) {
		this.minute = minute;
	}
	public void setNano(Number nano) {
		this.nano = nano;
	}
	public void setSecond(Number second) {
		this.second = second;
	}
	
	
	
	
}
