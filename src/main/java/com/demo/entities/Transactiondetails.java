package com.demo.entities;
// Generated Aug 29, 2021, 4:25:35 PM by Hibernate Tools 5.4.30.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "transactiondetails", catalog = "testhibernate")
public class Transactiondetails implements java.io.Serializable {

	private Integer transId;
	private String accId;
	private Double tranMoney;
	private Integer transType;
	private Date dateOfTrans;

	public Transactiondetails() {
	}

	public Transactiondetails(String accId, Double tranMoney, Integer transType, Date dateOfTrans) {
		this.accId = accId;
		this.tranMoney = tranMoney;
		this.transType = transType;
		this.dateOfTrans = dateOfTrans;
	}
	@Id
	@GeneratedValue(strategy = IDENTITY)
	public Integer getTransId() {
		return this.transId;
	}

	public void setTransId(Integer transId) {
		this.transId = transId;
	}
	
	public String getAccId() {
		return this.accId;
	}

	public void setAccId(String accId) {
		this.accId = accId;
	}

	public Double getTranMoney() {
		return this.tranMoney;
	}

	public void setTranMoney(Double tranMoney) {
		this.tranMoney = tranMoney;
	}
	
	public Integer getTransType() {
		return this.transType;
	}

	public void setTransType(Integer transType) {
		this.transType = transType;
	}

	public Date getDateOfTrans() {
		return this.dateOfTrans;
	}

	public void setDateOfTrans(Date dateOfTrans) {
		this.dateOfTrans = dateOfTrans;
	}

}
