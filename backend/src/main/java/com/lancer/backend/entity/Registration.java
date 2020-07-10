package com.lancer.backend.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.sql.Date;

/**
 * @Description  
 * @Author  Hunter
 * @Date 2020-07-01 
 */

@Setter
@Getter
@ToString
@Entity
@Table ( name ="Registration" )
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
public class Registration  implements Serializable {

	private static final long serialVersionUID =  4870507446319109727L;

	@Id
   	@Column(name = "DispatchId" )
	private String dispatchId;

   	@Column(name = "DriverId" )
	private String driverId;

   	@Column(name = "CarId" )
	private String carId;

   	@Column(name = "DispatchDate" )
	private Date dispatchDate;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getDispatchId() {
		return dispatchId;
	}

	public void setDispatchId(String dispatchId) {
		this.dispatchId = dispatchId;
	}

	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public Date getDispatchDate() {
		return dispatchDate;
	}

	public void setDispatchDate(Date dispatchDate) {
		this.dispatchDate = dispatchDate;
	}
}
