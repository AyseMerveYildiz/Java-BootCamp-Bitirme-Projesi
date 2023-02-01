package myticket.exception;

public class MyTicketException extends RuntimeException {
	private String key;

	public MyTicketException(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

}
