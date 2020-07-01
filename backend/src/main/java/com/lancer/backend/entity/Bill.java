package com.lancer.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;

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
public class Bill  implements Serializable {

	private static final long serialVersionUID =  5433740632854985169L;
	@Id
   	@Column(name = "DriverId" )
	private String driverId;

   	@Column(name = "CarId" )
	private String carId;

   	@Column(name = "ReturnDate" )
	private Date returnDate;

   	@Column(name = "OilCost" )
	private String oilCost;

   	@Column(name = "LoadMileage" )
	private String loadMileage;

   	@Column(name = "NoloadMileage" )
	private String noloadMileage;

   	@Column(name = "FuelConsumption" )
	private String fuelConsumption;

   	@Column(name = "Cost" )
	private String cost;

	public static long getSerialVersionUID() {
		return serialVersionUID;
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

	public String getOilCost() {
		return oilCost;
	}

	public void setOilCost(String oilCost) {
		this.oilCost = oilCost;
	}

	public String getLoadMileage() {
		return loadMileage;
	}

	public void setLoadMileage(String loadMileage) {
		this.loadMileage = loadMileage;
	}

	public String getNoloadMileage() {
		return noloadMileage;
	}

	public void setNoloadMileage(String noloadMileage) {
		this.noloadMileage = noloadMileage;
	}

	public String getFuelConsumption() {
		return fuelConsumption;
	}

	public void setFuelConsumption(String fuelConsumption) {
		this.fuelConsumption = fuelConsumption;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}
}
