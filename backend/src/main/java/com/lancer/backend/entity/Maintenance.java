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
@Table ( name ="Maintenance" )
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
public class Maintenance  implements Serializable {

	private static final long serialVersionUID =  2943838216889196034L;

	@Id
   	@Column(name = "MaintenanceId" )
	private String maintenanceId;

   	@Column(name = "CarId" )
	private String carId;

   	@Column(name = "DeliveryMan" )
	private String deliveryMan;

   	@Column(name = "RepairDate" )
	private Date repairDate;

   	@Column(name = "RepairItem" )
	private String repairItem;

   	@Column(name = "RepairCost" )
	private BigDecimal repairCost;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getMaintenanceId() {
		return maintenanceId;
	}

	public void setMaintenanceId(String maintenanceId) {
		this.maintenanceId = maintenanceId;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public String getDeliveryMan() {
		return deliveryMan;
	}

	public void setDeliveryMan(String deliveryMan) {
		this.deliveryMan = deliveryMan;
	}

	public Date getRepairDate() {
		return repairDate;
	}

	public void setRepairDate(Date repairDate) {
		this.repairDate = repairDate;
	}

	public String getRepairItem() {
		return repairItem;
	}

	public void setRepairItem(String repairItem) {
		this.repairItem = repairItem;
	}

	public BigDecimal getRepairCost() {
		return repairCost;
	}

	public void setRepairCost(BigDecimal repairCost) {
		this.repairCost = repairCost;
	}
}
