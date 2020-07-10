package com.lancer.backend.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table ( name ="Bill" )
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
public class Bill implements Serializable {

	private static final long serialVersionUID =  5433740632854985169L;

    @Id
   	@Column(name = "BillId" )
    private String billId;
    
   	@Column(name = "DriverId" )
	private String driverId;

   	@Column(name = "CarId" )
	private String carId;

   	@Column(name = "ReturnDate" )
	private Date returnDate;

   	@Column(name = "OilCost" )
	private BigDecimal oilCost;

   	@Column(name = "LoadMileage" )
	private double loadMileage;

   	@Column(name = "NoloadMileage" )
	private double noloadMileage;

   	@Column(name = "FuelConsumption" )
	private double fuelConsumption;

   	@Column(name = "Cost" )
	private BigDecimal cost;


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

    public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
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

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public BigDecimal getOilCost() {
		return oilCost;
	}

	public void setOilCost(BigDecimal oilCost) {
		this.oilCost = oilCost;
	}

	public double getLoadMileage() {
		return loadMileage;
	}

	public void setLoadMileage(double loadMileage) {
		this.loadMileage = loadMileage;
	}

	public double getNoloadMileage() {
		return noloadMileage;
	}

	public void setNoloadMileage(double noloadMileage) {
		this.noloadMileage = noloadMileage;
	}

	public double getFuelConsumption() {
		return fuelConsumption;
	}

	public void setFuelConsumption(double fuelConsumption) {
		this.fuelConsumption = fuelConsumption;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
}
