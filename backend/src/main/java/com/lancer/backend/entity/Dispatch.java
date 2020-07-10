package com.lancer.backend.entity;

import javax.persistence.*;

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
@Table ( name ="Dispatch" )
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
public class Dispatch  implements Serializable {

	private static final long serialVersionUID =  832942006391123198L;

	@Id
   	@Column(name = "DispatchId" )
	private String dispatchId;

   	@Column(name = "OrderId" )
	private String orderId;

   	@Column(name = "Destination" )
	private String destination;

   	@Column(name = "AppointTime" )
	private Date appointTime;

   	@Column(name = "Consignee" )
	private String consignee;

   	@Column(name = "Shipper" )
	private String shipper;

   	@Column(name = "Freight" )
	private BigDecimal freight;

   	@Column(name = "Scheduled" )
	private Long scheduled;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getDispatchId() {
		return dispatchId;
	}

	public void setDispatchId(String dispatchId) {
		this.dispatchId = dispatchId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Date getAppointTime() {
		return appointTime;
	}

	public void setAppointTime(Date appointTime) {
		this.appointTime = appointTime;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getShipper() {
		return shipper;
	}

	public void setShipper(String shipper) {
		this.shipper = shipper;
	}

	public BigDecimal getFreight() {
		return freight;
	}

	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}

	public Long getScheduled() {
		return scheduled;
	}

	public void setScheduled(Long scheduled) {
		this.scheduled = scheduled;
	}
}
