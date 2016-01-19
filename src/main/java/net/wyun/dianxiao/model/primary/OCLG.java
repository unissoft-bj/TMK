/**
 * 
 */
package net.wyun.dianxiao.model.primary;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author michael
 *
 */
@Entity
@Table(name = "OCLG")
public class OCLG {
	
	@Override
	public String toString() {
		return "OCLG [clgCode=" + clgCode + ", cardCode=" + cardCode
				+ ", notes=" + notes + ", cntctDate=" + cntctDate
				+ ", cntctTime=" + cntctTime + ", recontact=" + recontact
				+ ", Closed=" + Closed + ", closeDate=" + closeDate
				+ ", contactPer=" + contactPer + ", tel=" + tel + ", fax="
				+ fax + ", cntctSbjct=" + cntctSbjct + ", transfered="
				+ transfered + ", DocType=" + DocType + ", docNum=" + docNum
				+ ", docEntry=" + docEntry + ", attachment=" + attachment
				+ ", dataSource=" + dataSource + ", attendUser=" + attendUser
				+ ", cntctCode=" + cntctCode + ", userSign=" + userSign
				+ ", slpCode=" + slpCode + ", action=" + action + ", details="
				+ details + ", cntctType=" + cntctType + ", location="
				+ location + ", beginTime=" + beginTime + ", duration="
				+ duration + ", durType=" + durType + ", endTime=" + endTime
				+ ", priority=" + priority + ", reminder=" + reminder
				+ ", remQty=" + remQty + ", remType=" + remType + ", oprId="
				+ oprId + ", oprLine=" + oprLine + ", remDate=" + remDate
				+ ", remTime=" + remTime + ", remSented=" + remSented
				+ ", instance=" + instance + ", endDate=" + endDate
				+ ", status=" + status + ", personal=" + personal
				+ ", inactive=" + inactive + ", tentative=" + tentative
				+ ", street=" + street + ", city=" + city + ", country="
				+ country + ", state=" + state + ", room=" + room
				+ ", parentType=" + parentType + ", parentId=" + parentId
				+ ", prevActvty=" + prevActvty + ", atcEntry=" + atcEntry + "]";
	}
	@Id
	@Column(name = "ClgCode", unique = true, nullable = false)
	int clgCode; //  int NOT NULL ,
	String cardCode; //  varchar(15) NULL ,
	String notes; //  longtext NULL ,
	Date cntctDate; //  datetime NULL ,
	Integer cntctTime; //  int NULL ,
	Date recontact; //  datetime NULL ,
	String Closed; //  char(1) NULL ,
	Date closeDate; //  datetime NULL ,
	String contactPer; //  varchar(90) NULL ,
	String tel; //  varchar(20) NULL ,
	String fax; //  varchar(20) NULL ,
	short cntctSbjct; //  smallint NULL ,
	String transfered; //  char(1) NULL ,
	String DocType; //  varchar(20) NULL ,
	String docNum; //  varchar(20) NULL ,
	String docEntry; //  varchar(20) NULL ,
	String attachment; // longtext NULL ,
	String dataSource; //  char(1) NULL ,
	Short attendUser; //  smallint NULL ,
	Integer cntctCode; //  int NULL ,
    Short userSign; // smallint NULL ,
	short slpCode; //  smallint NULL ,
	String action; //  char(1) NULL ,
	String details; //  varchar(60) NULL ,
	short cntctType; //  smallint NULL ,
	short location; //  smallint NULL ,
	Integer beginTime; //  int NULL ,
	BigDecimal duration; //  decimal(19,6) NULL ,
	String durType; //  char(1) NULL ,
	Integer endTime; //  int NULL ,
	String priority; //  char(1) NULL ,
	String reminder; //  char(1) NULL ,
	BigDecimal remQty; //  decimal(19,6) NULL ,
	String remType; //  char(1) NULL ,
	Integer oprId; //  int NULL ,
	Short oprLine; //  smallint NULL ,
	Date remDate; //  datetime NULL ,
	Short remTime; //  smallint NULL ,
	String remSented; //  char(1) NULL ,
	short instance; //  smallint NULL ,
	Date endDate; //  datetime NULL ,
	Integer status; //  int NULL ,
	String personal; //  char(1) NULL ,
	String inactive; //  char(1) NULL ,
	String tentative; //  char(1) NULL ,
	String street; //  varchar(100) NULL ,
	String city; //  varchar(100) NULL ,
	String country; //  varchar(3) NULL ,
	String state; //  varchar(3) NULL ,
	String room; //  varchar(50) NULL ,
	String parentType; //  varchar(20) NULL ,
	Integer parentId; //  int NULL ,
	Integer prevActvty; //  int NULL ,
	Integer atcEntry; //  int NULL ,
	public int getClgCode() {
		return clgCode;
	}
	public void setClgCode(int clgCode) {
		this.clgCode = clgCode;
	}
	public String getCardCode() {
		return cardCode;
	}
	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public Date getCntctDate() {
		return cntctDate;
	}
	public void setCntctDate(Date cntctDate) {
		this.cntctDate = cntctDate;
	}
	public int getCntctTime() {
		return cntctTime;
	}
	public void setCntctTime(int cntctTime) {
		this.cntctTime = cntctTime;
	}
	public Date getRecontact() {
		return recontact;
	}
	public void setRecontact(Date recontact) {
		this.recontact = recontact;
	}
	public String getClosed() {
		return Closed;
	}
	public void setClosed(String closed) {
		Closed = closed;
	}
	public Date getCloseDate() {
		return closeDate;
	}
	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}
	public String getContactPer() {
		return contactPer;
	}
	public void setContactPer(String contactPer) {
		this.contactPer = contactPer;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public short getCntctSbjct() {
		return cntctSbjct;
	}
	public void setCntctSbjct(short cntctSbjct) {
		cntctSbjct = cntctSbjct;
	}
	public String getTransfered() {
		return transfered;
	}
	public void setTransfered(String transfered) {
		this.transfered = transfered;
	}
	public String getDocType() {
		return DocType;
	}
	public void setDocType(String docType) {
		DocType = docType;
	}
	public String getDocNum() {
		return docNum;
	}
	public void setDocNum(String docNum) {
		this.docNum = docNum;
	}
	public String getDocEntry() {
		return docEntry;
	}
	public void setDocEntry(String docEntry) {
		this.docEntry = docEntry;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	public String getDataSource() {
		return dataSource;
	}
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	public short getAttendUser() {
		return attendUser;
	}
	public void setAttendUser(short attendUser) {
		this.attendUser = attendUser;
	}
	public int getCntctCode() {
		return cntctCode;
	}
	public void setCntctCode(int cntctCode) {
		this.cntctCode = cntctCode;
	}
	public short getUserSign() {
		return userSign;
	}
	public void setUserSign(short userSign) {
		this.userSign = userSign;
	}
	public short getSlpCode() {
		return slpCode;
	}
	public void setSlpCode(short slpCode) {
		this.slpCode = slpCode;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public short getCntctType() {
		return cntctType;
	}
	public void setCntctType(short cntctType) {
		this.cntctType = cntctType;
	}
	public short getLocation() {
		return location;
	}
	public void setLocation(short location) {
		this.location = location;
	}
	public int getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(int beginTime) {
		this.beginTime = beginTime;
	}
	public BigDecimal getDuration() {
		return duration;
	}
	public void setDuration(BigDecimal duration) {
		this.duration = duration;
	}
	public String getDurType() {
		return durType;
	}
	public void setDurType(String durType) {
		this.durType = durType;
	}
	public int geteNDTime() {
		return endTime;
	}
	public void seteNDTime(int eNDTime) {
		this.endTime = eNDTime;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getReminder() {
		return reminder;
	}
	public void setReminder(String reminder) {
		this.reminder = reminder;
	}
	public BigDecimal getRemQty() {
		return remQty;
	}
	public void setRemQty(BigDecimal remQty) {
		this.remQty = remQty;
	}
	public String getRemType() {
		return remType;
	}
	public void setRemType(String remType) {
		this.remType = remType;
	}
	public int getOprId() {
		return oprId;
	}
	public void setOprId(int oprId) {
		this.oprId = oprId;
	}
	public short getOprLine() {
		return oprLine;
	}
	public void setOprLine(short oprLine) {
		this.oprLine = oprLine;
	}
	public Date getRemDate() {
		return remDate;
	}
	public void setRemDate(Date remDate) {
		this.remDate = remDate;
	}
	public short getRemTime() {
		return remTime;
	}
	public void setRemTime(short remTime) {
		this.remTime = remTime;
	}
	public String getRemSented() {
		return remSented;
	}
	public void setRemSented(String remSented) {
		this.remSented = remSented;
	}
	public short getInstance() {
		return instance;
	}
	public void setInstance(short instance) {
		this.instance = instance;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getPersonal() {
		return personal;
	}
	public void setPersonal(String personal) {
		this.personal = personal;
	}
	public String getInactive() {
		return inactive;
	}
	public void setInactive(String inactive) {
		this.inactive = inactive;
	}
	public String getTentative() {
		return tentative;
	}
	public void setTentative(String tentative) {
		this.tentative = tentative;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getParentType() {
		return parentType;
	}
	public void setParentType(String parentType) {
		this.parentType = parentType;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public int getPrevActvty() {
		return prevActvty;
	}
	public void setPrevActvty(int prevActvty) {
		this.prevActvty = prevActvty;
	}
	public int getAtcEntry() {
		return atcEntry;
	}
	public void setAtcEntry(int atcEntry) {
		this.atcEntry = atcEntry;
	}

}
