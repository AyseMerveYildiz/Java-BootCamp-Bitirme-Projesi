package myticket.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import myticket.model.enums.UserType;
import myticket.model.enums.VehicleType;

public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Integer id;
	@Column(name = "No")
	private String name;
	@Column(name = "expeditionInformation")
	private String expeditionInformation;
	@Column(name = "userType")
	@Enumerated(EnumType.STRING)
	private UserType userType;
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
	private User user;
	@Column(name = "vehicleType")
	@Enumerated(EnumType.STRING)
	private VehicleType vehicleType;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime createDate;

	public Ticket() {
		super();
	}

	public Ticket(Integer id, String name, String expeditionInformation, UserType userType, VehicleType vehicleType,
			LocalDateTime createDate) {
		super();
		this.id = id;
		this.name = name;
		this.expeditionInformation = expeditionInformation;
		this.userType = userType;
		this.vehicleType = vehicleType;
		this.createDate = createDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExpeditionInformation() {
		return expeditionInformation;
	}

	public void setExpeditionInformation(String expeditionInformation) {
		this.expeditionInformation = expeditionInformation;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	@Override
	public String toString() {
		return "Ticket [id=" + id + ", name=" + name + ", expeditionInformation=" + expeditionInformation
				+ ", userType=" + userType + ", tvehicleTpe=" + vehicleType + ", createDate=" + createDate + "]";
	}

}
