package com.lancer.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
@Table(name = "Driver")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer"})
public class Driver  implements Serializable {

	private static final long serialVersionUID =  3044601500408960807L;

	@Id
   	@Column(name = "DriverId" )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long driverId;

   	@Column(name = "DriverName" )
	private String driverName;

   	@Column(name = "Sex" )
	private String sex;

   	@Column(name = "CarId" )
	private String carId;

   	@Column(name = "EntryDate" )
	private Date entryDate;

   	@Column(name = "Tel" )
	private String tel;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}


	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public long getDriverId() {
		return driverId;
	}

	public void setDriverId(long driverId) {
		this.driverId = driverId;
	}

	public Driver(){

	}
}
