package com.lancer.backend.entity;

import javax.persistence.*;

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
@Table ( name ="Transportation" )
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
public class Transportation  implements Serializable {

	private static final long serialVersionUID =  3050652422013542385L;

	@Id
   	@Column(name = "TransportationId" )
	private String transportationId;

   	@Column(name = "OrderId" )
	private String orderId;

   	@Column(name = "Price" )
	private BigDecimal price;

   	@Column(name = "ChargeMode" )
	private String chargeMode;

   	@Column(name = "LossRario" )
	private double lossRario;

   	@Column(name = "LossAmount" )
	private BigDecimal lossAmount;

   	@Column(name = "RoundTrip" )
	private boolean roundTrip;

   	@Column(name = "OilCost" )
	private BigDecimal oilCost;

   	@Column(name = "LoadMieage" )
	private double loadMieage;

   	@Column(name = "NoloadMileage" )
	private double noloadMileage;

   	@Column(name = "FuelConsumption" )
	private double fuelConsumption;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getTransportationId() {
		return transportationId;
	}

	public void setTransportationId(String transportationId) {
		this.transportationId = transportationId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getChargeMode() {
		return chargeMode;
	}

	public void setChargeMode(String chargeMode) {
		this.chargeMode = chargeMode;
	}

	public double getLossRario() {
		return lossRario;
	}

	public void setLossRario(double lossRario) {
		this.lossRario = lossRario;
	}

	public BigDecimal getLossAmount() {
		return lossAmount;
	}

	public void setLossAmount(BigDecimal lossAmount) {
		this.lossAmount = lossAmount;
	}

	public boolean getRoundTrip() {
		return roundTrip;
	}

	public void setRoundTrip(boolean roundTrip) {
		this.roundTrip = roundTrip;
	}

	public BigDecimal getOilCost() {
		return oilCost;
	}

	public void setOilCost(BigDecimal oilCost) {
		this.oilCost = oilCost;
	}

	public double getLoadMieage() {
		return loadMieage;
	}

	public void setLoadMieage(double loadMieage) {
		this.loadMieage = loadMieage;
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
}
