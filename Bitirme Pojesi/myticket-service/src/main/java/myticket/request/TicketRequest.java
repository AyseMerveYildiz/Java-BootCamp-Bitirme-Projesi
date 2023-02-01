package myticket.request;

import myticket.model.User;

public class TicketRequest {
	private Integer id;
	private String no;
	private String expeditionInformation;
	private Integer userId;
	private User userType;

	public TicketRequest() {
		super();

	}

	public TicketRequest(Integer id, String no, String expeditionInformation, User userType, Integer userId) {
		super();
		this.id = id;
		this.no = no;
		this.expeditionInformation = expeditionInformation;
		this.userType = userType;
		this.userId=userId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getExpeditionInformation() {
		return expeditionInformation;
	}

	public void setExpeditionInformation(String expeditionInformation) {
		this.expeditionInformation = expeditionInformation;
	}

	public User getUserType() {
		return userType;
	}

	public void setUserType(User userType) {
		this.userType = userType;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
