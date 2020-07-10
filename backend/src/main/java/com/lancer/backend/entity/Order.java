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
@Table ( name ="OrderInfo" )
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
public class Order  implements Serializable {

	private static final long serialVersionUID =  8760104972440892214L;

	@Id
   	@Column(name = "OrderId" )
	private String orderId;

   	@Column(name = "Shipper" )
	private String shipper;

   	@Column(name = "FreightPayer" )
	private String freightPayer;

   	@Column(name = "Freight" )
	private BigDecimal freight;

   	@Column(name = "Contact" )
	private String contact;

   	@Column(name = "LoadingAddress" )
	private String loadingAddress;

   	@Column(name = "UnloadingAddress" )
	private String unloadingAddress;

   	@Column(name = "ChargeMode" )
	private String chargeMode;

   	@Column(name = "ClearingForm" )
	private String clearingForm;

	@Column(name = "Distribute")
	private int distribute;

	@Column(name = "Distance")
	private int distance;

	@Column(name = "Weight")
	private int weight;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getShipper() {
		return shipper;
	}

	public void setShipper(String shipper) {
		this.shipper = shipper;
	}

	public String getFreightPayer() {
		return freightPayer;
	}

	public void setFreightPayer(String freightPayer) {
		this.freightPayer = freightPayer;
	}

	public BigDecimal getFreight() {
		return freight;
	}

	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getLoadingAddress() {
		return loadingAddress;
	}

	public void setLoadingAddress(String loadingAddress) {
		this.loadingAddress = loadingAddress;
	}

	public String getUnloadingAddress() {
		return unloadingAddress;
	}

	public void setUnloadingAddress(String unloadingAddress) {
		this.unloadingAddress = unloadingAddress;
	}

	public String getChargeMode() {
		return chargeMode;
	}

	public void setChargeMode(String chargeMode) {
		this.chargeMode = chargeMode;
	}

	public String getClearingForm() {
		return clearingForm;
	}

	public void setClearingForm(String clearingForm) {
		this.clearingForm = clearingForm;
	}

	public int getDistribute() {
		return distribute;
	}

	public void setDistribute(int distribute) {
		this.distribute = distribute;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Order() {
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

}
