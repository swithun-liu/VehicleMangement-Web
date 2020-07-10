package com.lancer.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
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
@Table ( name ="VehicleCost" )
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
public class VehicleCost  implements Serializable {

	private static final long serialVersionUID =  5926093365466388930L;

	@Id
   	@Column(name = "CarId" )
	private Long carId;

   	@Column(name = "CarType" )
	private String carType;

   	@Column(name = "TotalCost" )
	private BigDecimal totalCost;

   	@Column(name = "TotalMiles" )
	private double totalMiles;

   	@Column(name = "TotalOilCost" )
	private BigDecimal totalOilCost;

   	@Column(name = "TotalFuelConsumption" )
	private double totalFuelConsumption;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Long getCarId() {
		return carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public BigDecimal getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}

	public double getTotalMiles() {
		return totalMiles;
	}

	public void setTotalMiles(double totalMiles) {
		this.totalMiles = totalMiles;
	}

	public BigDecimal getTotalOilCost() {
		return totalOilCost;
	}

	public void setTotalOilCost(BigDecimal totalOilCost) {
		this.totalOilCost = totalOilCost;
	}

	public double getTotalFuelConsumption() {
		return totalFuelConsumption;
	}

	public void setTotalFuelConsumption(double totalFuelConsumption) {
		this.totalFuelConsumption = totalFuelConsumption;
	}
}
