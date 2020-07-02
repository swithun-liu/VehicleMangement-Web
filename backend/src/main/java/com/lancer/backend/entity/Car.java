package com.lancer.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.sql.Date;
import java.math.BigDecimal;

/**
 * @Description  
 * @Author  Hunter
 * @Date 2020-07-01 
 */

@Setter
@Getter
@ToString
@Entity
@Table ( name ="Car" )
@JsonIgnoreProperties(value = { "hibernateLazyInitializer"})
public class Car  implements Serializable {


    private static final long serialVersionUID =  1381262559342657375L;

	@Id
   	@Column(name = "CarId" )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long carId;

   	@Column(name = "DriverId" )
	private String driverId;

   	@Column(name = "CarType" )
	private String carType;

   	@Column(name = "License" )
	private String license;

   	@Column(name = "Capacity" )
	private Long capacity;

   	@Column(name = "Loss" )
	private BigDecimal loss;

   	@Column(name = "Price" )
	private BigDecimal price;

   	@Column(name = "PurchaseDate" )
	private Date purchaseDate;

   	@Column(name = "HasOut" )
	private Boolean hasOut;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public Long getCapacity() {
		return capacity;
	}

	public void setCapacity(Long capacity) {
		this.capacity = capacity;
	}

	public BigDecimal getLoss() {
		return loss;
	}

	public void setLoss(BigDecimal loss) {
		this.loss = loss;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Boolean getHasOut() {
		return hasOut;
	}

	public void setHasOut(Boolean hasOut) {
		this.hasOut = hasOut;
	}

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }
}
