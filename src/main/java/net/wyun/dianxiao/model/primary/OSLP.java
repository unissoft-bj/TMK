/**
 * 
 */
package net.wyun.dianxiao.model.primary;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author michael
 *
 */
@Entity
@Table(name = "OSLP")
public class OSLP {
	@Id
	@Column(name = "SlpCode", unique = true, nullable = false)
	short slpCode; //  smallint NOT NULL ,
	
	String slpName; //  varchar(32) NOT NULL ,
	String memo; //  varchar(50) NULL ,
	BigDecimal commission; //  decimal(19,6) NULL ,
	short groupCode; //  smallint NULL ,
	String locked; //  char(1) NULL ,
	String dataSource; //  char(1) NULL ,
	short userSign; //  smallint NULL ,
	
	Integer empID; //  int NULL ,
	public short getSlpCode() {
		return slpCode;
	}
	public void setSlpCode(short slpCode) {
		this.slpCode = slpCode;
	}
	public String getSlpName() {
		return slpName;
	}
	public void setSlpName(String slpName) {
		this.slpName = slpName;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public BigDecimal getCommission() {
		return commission;
	}
	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}
	public short getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(short groupCode) {
		this.groupCode = groupCode;
	}
	public String getLocked() {
		return locked;
	}
	public void setLocked(String locked) {
		this.locked = locked;
	}
	public String getDataSource() {
		return dataSource;
	}
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	public short getUserSign() {
		return userSign;
	}
	public void setUserSign(short userSign) {
		this.userSign = userSign;
	}
	public int getEmpID() {
		return empID;
	}
	public void setEmpID(int empID) {
		this.empID = empID;
	}
	@Override
	public String toString() {
		return "OSLP [slpCode=" + slpCode + ", slpName=" + slpName + ", memo="
				+ memo + ", commission=" + commission + ", groupCode="
				+ groupCode + ", locked=" + locked + ", dataSource="
				+ dataSource + ", userSign=" + userSign + ", empID=" + empID
				+ "]";
	}
	
	

}
