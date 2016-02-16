/**
 * 
 */
package net.wyun.dianxiao.model.primary;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 * @author michael
 * variables name are the same 
 * as the field name from table schema.
 *
 */
@Entity
public class OUSR {
	@Id
	int INTERNAL_K; //  int NOT NULL ,
	
	String PASSWORD; //  varchar(254) NULL ,
	String PASSWORD1; //  varchar(8) NULL ,
	String PASSWORD2; //  varchar(8) NULL ,
	short USERID; //  smallint NOT NULL ,
	String USER_CODE; //  varchar(8) NOT NULL ,
	@Column(name="U_NAME")
	String uName; //  varchar(30) NULL ,
	short GROUPS; //  smallint NULL ,
	String PASSWORD4; //  varchar(254) NULL ,
	String ALLOWENCES; //  longtext NULL ,
	String SUPERUSER; //  char(1) NULL ,
	BigDecimal DISCOUNT; //  decimal(19,6) NULL ,
	String PASSWORD3; //  varchar(8) NULL ,
	String Info1File; //  varchar(4) NULL ,
	Short Info1Field; //  smallint NULL ,
	String Info2File; //  varchar(4) NULL ,
	Short Info2Field; //  smallint NULL ,
	String Info3File; //  varchar(4) NULL ,
	Short Info3Field; //  smallint NULL ,
	String Info4File; //  varchar(4) NULL ,
	Short Info4Field; //  smallint NULL ,
	String dType; //  char(1) NULL ,
	String E_Mail; //  varchar(100) NULL ,
	String PortNum; //  varchar(50) NULL ,
	String OutOfOffic; //S$print  char(1) NULL ,
	String SendEMail; // char(1) NULL ,
	String SendSMS; //  char(1) NULL ,
	String DfltsGroup; //  varchar(8) NULL ,
	String CashLimit; //  char(1) NULL ,
	BigDecimal MaxCashSum; //  decimal(19,6) NULL ,
	@Column(name="Fax")
	String fax; //  varchar(20) NULL ,
	String SendFax; //  char(1) NULL ,
	String Locked; //  char(1) NULL ,
	short Department; //  smallint NULL ,
	short Branch; //  smallint NULL ,
	@Lob
	@Column(name="UserPrefs", length=100000)
	byte[] UserPrefs; //  longblob NULL ,
	Short Language; //  smallint NULL ,
	Short Charset; //  smallint NULL ,
	String OpenCdt; //  char(1) NULL ,
	int CdtPrvDays; //  int NULL ,
	String DsplyRates; //  char(1) NULL ,
	String AuImpRates; //  char(1) NULL ,
	String OpenDps; //  char(1) NULL ,
	String RcrFlag;  //char(1) NULL ,
	String CheckFiles; //  char(1) NULL ,
	String OpenCredit; //  char(1) NULL ,
	short CreditDay1; //  smallint NULL ,
	short CreditDay2; //  smallint NULL ,
	String WallPaper; //  longtext NULL ,
	short WllPprDsp; //  smallint NULL ,
	String AdvImagePr; //  char(1) NULL ,
	String ContactLog; //  char(1) NULL ,
	Date LastWarned; //  datetime NULL ,
	short AlertPolFr; //  smallint NULL ,
	short ScreenLock; //  smallint NULL ,
	String ShowNewMsg; //  char(1) NULL ,
	String Picture; //  varchar(200) NULL ,
	String Position; //  varchar(90) NULL ,
	String Address; //  varchar(100) NULL ,
	String Country; //  varchar(3) NULL ,
	String Tel1; //  varchar(20) NULL ,
	String Tel2; //  varchar(20) NULL ,
	String GENDER; //  char(1) NULL ,
	Date Birthday; //  datetime NULL ,
	String EnbMenuFlt; //  char(1) NULL ,
	String objType; //  varchar(20) NULL ,
	Short logInstanc; //  smallint NULL ,
	Short userSign; //  smallint NULL ,
	Date createDate; //  datetime NULL ,
	Short userSign2; //  smallint NULL ,
	Date updateDate; //  datetime NULL ,
	String OneLogPwd; //  char(1) NULL ,
	Date lastLogin; //  datetime NULL ,
	String LastPwds; //  varchar(254) NULL ,
	String LastPwds2; //  varchar(254) NULL ,
	Date LastPwdSet; //  datetime NULL ,
	int FailedLog; //  int NULL ,
	String PwdNeverEx; //  char(1) NULL ,
	public int getINTERNAL_K() {
		return INTERNAL_K;
	}
	public void setINTERNAL_K(int iNTERNAL_K) {
		INTERNAL_K = iNTERNAL_K;
	}
	public String getPASSWORD() {
		return PASSWORD;
	}
	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}
	public String getPASSWORD1() {
		return PASSWORD1;
	}
	public void setPASSWORD1(String pASSWORD1) {
		PASSWORD1 = pASSWORD1;
	}
	public String getPASSWORD2() {
		return PASSWORD2;
	}
	public void setPASSWORD2(String pASSWORD2) {
		PASSWORD2 = pASSWORD2;
	}
	public short getUSERID() {
		return USERID;
	}
	public void setUSERID(short uSERID) {
		USERID = uSERID;
	}
	public String getUSER_CODE() {
		return USER_CODE;
	}
	public void setUSER_CODE(String uSER_CODE) {
		USER_CODE = uSER_CODE;
	}
	public String getUName() {
		return uName;
	}
	public void setUName(String u_NAME) {
		this.uName = u_NAME;
	}
	public short getGROUPS() {
		return GROUPS;
	}
	public void setGROUPS(short gROUPS) {
		GROUPS = gROUPS;
	}
	public String getPASSWORD4() {
		return PASSWORD4;
	}
	public void setPASSWORD4(String pASSWORD4) {
		PASSWORD4 = pASSWORD4;
	}
	public String getALLOWENCES() {
		return ALLOWENCES;
	}
	public void setALLOWENCES(String aLLOWENCES) {
		ALLOWENCES = aLLOWENCES;
	}
	public String getSUPERUSER() {
		return SUPERUSER;
	}
	public void setSUPERUSER(String sUPERUSER) {
		SUPERUSER = sUPERUSER;
	}
	public BigDecimal getDISCOUNT() {
		return DISCOUNT;
	}
	public void setDISCOUNT(BigDecimal dISCOUNT) {
		DISCOUNT = dISCOUNT;
	}
	public String getPASSWORD3() {
		return PASSWORD3;
	}
	public void setPASSWORD3(String pASSWORD3) {
		PASSWORD3 = pASSWORD3;
	}
	public String getInfo1File() {
		return Info1File;
	}
	public void setInfo1File(String info1File) {
		Info1File = info1File;
	}
	public short getInfo1Field() {
		return Info1Field;
	}
	public void setInfo1Field(short info1Field) {
		Info1Field = info1Field;
	}
	public String getInfo2File() {
		return Info2File;
	}
	public void setInfo2File(String info2File) {
		Info2File = info2File;
	}
	public short getInfo2Field() {
		return Info2Field;
	}
	public void setInfo2Field(short info2Field) {
		Info2Field = info2Field;
	}
	public String getInfo3File() {
		return Info3File;
	}
	public void setInfo3File(String info3File) {
		Info3File = info3File;
	}
	public short getInfo3Field() {
		return Info3Field;
	}
	public void setInfo3Field(short info3Field) {
		Info3Field = info3Field;
	}
	public String getInfo4File() {
		return Info4File;
	}
	public void setInfo4File(String info4File) {
		Info4File = info4File;
	}
	public short getInfo4Field() {
		return Info4Field;
	}
	public void setInfo4Field(short info4Field) {
		Info4Field = info4Field;
	}
	public String getdType() {
		return dType;
	}
	public void setdType(String dType) {
		this.dType = dType;
	}
	public String getE_Mail() {
		return E_Mail;
	}
	public void setE_Mail(String e_Mail) {
		E_Mail = e_Mail;
	}
	public String getPortNum() {
		return PortNum;
	}
	public void setPortNum(String portNum) {
		PortNum = portNum;
	}
	public String getOutOfOffic() {
		return OutOfOffic;
	}
	public void setOutOfOffic(String outOfOffic) {
		OutOfOffic = outOfOffic;
	}
	public String getSendEMail() {
		return SendEMail;
	}
	public void setSendEMail(String sendEMail) {
		SendEMail = sendEMail;
	}
	public String getSendSMS() {
		return SendSMS;
	}
	public void setSendSMS(String sendSMS) {
		SendSMS = sendSMS;
	}
	public String getDfltsGroup() {
		return DfltsGroup;
	}
	public void setDfltsGroup(String dfltsGroup) {
		DfltsGroup = dfltsGroup;
	}
	public String getCashLimit() {
		return CashLimit;
	}
	public void setCashLimit(String cashLimit) {
		CashLimit = cashLimit;
	}
	public BigDecimal getMaxCashSum() {
		return MaxCashSum;
	}
	public void setMaxCashSum(BigDecimal maxCashSum) {
		MaxCashSum = maxCashSum;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getSendFax() {
		return SendFax;
	}
	public void setSendFax(String sendFax) {
		SendFax = sendFax;
	}
	public String getLocked() {
		return Locked;
	}
	public void setLocked(String locked) {
		Locked = locked;
	}
	public short getDepartment() {
		return Department;
	}
	public void setDepartment(short department) {
		Department = department;
	}
	public short getBranch() {
		return Branch;
	}
	public void setBranch(short branch) {
		Branch = branch;
	}
	public byte[] getUserPrefs() {
		return UserPrefs;
	}
	public void setUserPrefs(byte[] userPrefs) {
		UserPrefs = userPrefs;
	}
	public short getLanguage() {
		return Language;
	}
	public void setLanguage(short language) {
		Language = language;
	}
	public short getCharset() {
		return Charset;
	}
	public void setCharset(short charset) {
		Charset = charset;
	}
	public String getOpenCdt() {
		return OpenCdt;
	}
	public void setOpenCdt(String openCdt) {
		OpenCdt = openCdt;
	}
	public int getCdtPrvDays() {
		return CdtPrvDays;
	}
	public void setCdtPrvDays(int cdtPrvDays) {
		CdtPrvDays = cdtPrvDays;
	}
	public String getDsplyRates() {
		return DsplyRates;
	}
	public void setDsplyRates(String dsplyRates) {
		DsplyRates = dsplyRates;
	}
	public String getAuImpRates() {
		return AuImpRates;
	}
	public void setAuImpRates(String auImpRates) {
		AuImpRates = auImpRates;
	}
	public String getOpenDps() {
		return OpenDps;
	}
	public void setOpenDps(String openDps) {
		OpenDps = openDps;
	}
	public String getRcrFlag() {
		return RcrFlag;
	}
	public void setRcrFlag(String rcrFlag) {
		RcrFlag = rcrFlag;
	}
	public String getCheckFiles() {
		return CheckFiles;
	}
	public void setCheckFiles(String checkFiles) {
		CheckFiles = checkFiles;
	}
	public String getOpenCredit() {
		return OpenCredit;
	}
	public void setOpenCredit(String openCredit) {
		OpenCredit = openCredit;
	}
	public short getCreditDay1() {
		return CreditDay1;
	}
	public void setCreditDay1(short creditDay1) {
		CreditDay1 = creditDay1;
	}
	public short getCreditDay2() {
		return CreditDay2;
	}
	public void setCreditDay2(short creditDay2) {
		CreditDay2 = creditDay2;
	}
	public String getWallPaper() {
		return WallPaper;
	}
	public void setWallPaper(String wallPaper) {
		WallPaper = wallPaper;
	}
	public short getWllPprDsp() {
		return WllPprDsp;
	}
	public void setWllPprDsp(short wllPprDsp) {
		WllPprDsp = wllPprDsp;
	}
	public String getAdvImagePr() {
		return AdvImagePr;
	}
	public void setAdvImagePr(String advImagePr) {
		AdvImagePr = advImagePr;
	}
	public String getContactLog() {
		return ContactLog;
	}
	public void setContactLog(String contactLog) {
		ContactLog = contactLog;
	}
	public Date getLastWarned() {
		return LastWarned;
	}
	public void setLastWarned(Date lastWarned) {
		LastWarned = lastWarned;
	}
	public short getAlertPolFr() {
		return AlertPolFr;
	}
	public void setAlertPolFr(short alertPolFr) {
		AlertPolFr = alertPolFr;
	}
	public short getScreenLock() {
		return ScreenLock;
	}
	public void setScreenLock(short screenLock) {
		ScreenLock = screenLock;
	}
	public String getShowNewMsg() {
		return ShowNewMsg;
	}
	public void setShowNewMsg(String showNewMsg) {
		ShowNewMsg = showNewMsg;
	}
	public String getPicture() {
		return Picture;
	}
	public void setPicture(String picture) {
		Picture = picture;
	}
	public String getPosition() {
		return Position;
	}
	public void setPosition(String position) {
		Position = position;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}
	public String getTel1() {
		return Tel1;
	}
	public void setTel1(String tel1) {
		Tel1 = tel1;
	}
	public String getTel2() {
		return Tel2;
	}
	public void setTel2(String tel2) {
		Tel2 = tel2;
	}
	public String getGENDER() {
		return GENDER;
	}
	public void setGENDER(String gENDER) {
		GENDER = gENDER;
	}
	public Date getBirthday() {
		return Birthday;
	}
	public void setBirthday(Date birthday) {
		Birthday = birthday;
	}
	public String getEnbMenuFlt() {
		return EnbMenuFlt;
	}
	public void setEnbMenuFlt(String enbMenuFlt) {
		EnbMenuFlt = enbMenuFlt;
	}
	public String getObjType() {
		return objType;
	}
	public void setObjType(String objType) {
		this.objType = objType;
	}
	public short getLogInstanc() {
		return logInstanc;
	}
	public void setLogInstanc(short logInstanc) {
		this.logInstanc = logInstanc;
	}
	public short getUserSign() {
		return userSign;
	}
	public void setUserSign(short userSign) {
		this.userSign = userSign;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public short getUserSign2() {
		return userSign2;
	}
	public void setUserSign2(short userSign2) {
		this.userSign2 = userSign2;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getOneLogPwd() {
		return OneLogPwd;
	}
	public void setOneLogPwd(String oneLogPwd) {
		OneLogPwd = oneLogPwd;
	}
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	public String getLastPwds() {
		return LastPwds;
	}
	public void setLastPwds(String lastPwds) {
		LastPwds = lastPwds;
	}
	public String getLastPwds2() {
		return LastPwds2;
	}
	public void setLastPwds2(String lastPwds2) {
		LastPwds2 = lastPwds2;
	}
	public Date getLastPwdSet() {
		return LastPwdSet;
	}
	public void setLastPwdSet(Date lastPwdSet) {
		LastPwdSet = lastPwdSet;
	}
	public int getFailedLog() {
		return FailedLog;
	}
	public void setFailedLog(int failedLog) {
		FailedLog = failedLog;
	}
	public String getPwdNeverEx() {
		return PwdNeverEx;
	}
	public void setPwdNeverEx(String pwdNeverEx) {
		PwdNeverEx = pwdNeverEx;
	}
	@Override
	public String toString() {
		return "OUSR [INTERNAL_K="
				+ INTERNAL_K
				+ ", "
				+ (PASSWORD != null ? "PASSWORD=" + PASSWORD + ", " : "")
				+ (PASSWORD1 != null ? "PASSWORD1=" + PASSWORD1 + ", " : "")
				+ (PASSWORD2 != null ? "PASSWORD2=" + PASSWORD2 + ", " : "")
				+ "USERID="
				+ USERID
				+ ", "
				+ (USER_CODE != null ? "USER_CODE=" + USER_CODE + ", " : "")
				+ (uName != null ? "U_NAME=" + uName + ", " : "")
				+ "GROUPS="
				+ GROUPS
				+ ", "
				+ (PASSWORD4 != null ? "PASSWORD4=" + PASSWORD4 + ", " : "")
				+ (ALLOWENCES != null ? "ALLOWENCES=" + ALLOWENCES + ", " : "")
				+ (SUPERUSER != null ? "SUPERUSER=" + SUPERUSER + ", " : "")
				+ (DISCOUNT != null ? "DISCOUNT=" + DISCOUNT + ", " : "")
				+ (PASSWORD3 != null ? "PASSWORD3=" + PASSWORD3 + ", " : "")
				+ (Info1File != null ? "Info1File=" + Info1File + ", " : "")
				+ (Info1Field != null ? "Info1Field=" + Info1Field + ", " : "")
				+ (Info2File != null ? "Info2File=" + Info2File + ", " : "")
				+ (Info2Field != null ? "Info2Field=" + Info2Field + ", " : "")
				+ (Info3File != null ? "Info3File=" + Info3File + ", " : "")
				+ (Info3Field != null ? "Info3Field=" + Info3Field + ", " : "")
				+ (Info4File != null ? "Info4File=" + Info4File + ", " : "")
				+ (Info4Field != null ? "Info4Field=" + Info4Field + ", " : "")
				+ (dType != null ? "dType=" + dType + ", " : "")
				+ (E_Mail != null ? "E_Mail=" + E_Mail + ", " : "")
				+ (PortNum != null ? "PortNum=" + PortNum + ", " : "")
				+ (OutOfOffic != null ? "OutOfOffic=" + OutOfOffic + ", " : "")
				+ (SendEMail != null ? "SendEMail=" + SendEMail + ", " : "")
				+ (SendSMS != null ? "SendSMS=" + SendSMS + ", " : "")
				+ (DfltsGroup != null ? "DfltsGroup=" + DfltsGroup + ", " : "")
				+ (CashLimit != null ? "CashLimit=" + CashLimit + ", " : "")
				+ (MaxCashSum != null ? "MaxCashSum=" + MaxCashSum + ", " : "")
				+ (fax != null ? "Fax=" + fax + ", " : "")
				+ (SendFax != null ? "SendFax=" + SendFax + ", " : "")
				+ (Locked != null ? "Locked=" + Locked + ", " : "")
				+ "Department="
				+ Department
				+ ", Branch="
				+ Branch
				+ ", "
				+ (UserPrefs != null ? "UserPrefs="
						+ Arrays.toString(UserPrefs) + ", " : "")
				+ (Language != null ? "Language=" + Language + ", " : "")
				+ (Charset != null ? "Charset=" + Charset + ", " : "")
				+ (OpenCdt != null ? "OpenCdt=" + OpenCdt + ", " : "")
				+ "CdtPrvDays=" + CdtPrvDays + ", "
				+ (DsplyRates != null ? "DsplyRates=" + DsplyRates + ", " : "")
				+ (AuImpRates != null ? "AuImpRates=" + AuImpRates + ", " : "")
				+ (OpenDps != null ? "OpenDps=" + OpenDps + ", " : "")
				+ (RcrFlag != null ? "RcrFlag=" + RcrFlag + ", " : "")
				+ (CheckFiles != null ? "CheckFiles=" + CheckFiles + ", " : "")
				+ (OpenCredit != null ? "OpenCredit=" + OpenCredit + ", " : "")
				+ "CreditDay1=" + CreditDay1 + ", CreditDay2=" + CreditDay2
				+ ", "
				+ (WallPaper != null ? "WallPaper=" + WallPaper + ", " : "")
				+ "WllPprDsp=" + WllPprDsp + ", "
				+ (AdvImagePr != null ? "AdvImagePr=" + AdvImagePr + ", " : "")
				+ (ContactLog != null ? "ContactLog=" + ContactLog + ", " : "")
				+ (LastWarned != null ? "LastWarned=" + LastWarned + ", " : "")
				+ "AlertPolFr=" + AlertPolFr + ", ScreenLock=" + ScreenLock
				+ ", "
				+ (ShowNewMsg != null ? "ShowNewMsg=" + ShowNewMsg + ", " : "")
				+ (Picture != null ? "Picture=" + Picture + ", " : "")
				+ (Position != null ? "Position=" + Position + ", " : "")
				+ (Address != null ? "Address=" + Address + ", " : "")
				+ (Country != null ? "Country=" + Country + ", " : "")
				+ (Tel1 != null ? "Tel1=" + Tel1 + ", " : "")
				+ (Tel2 != null ? "Tel2=" + Tel2 + ", " : "")
				+ (GENDER != null ? "GENDER=" + GENDER + ", " : "")
				+ (Birthday != null ? "Birthday=" + Birthday + ", " : "")
				+ (EnbMenuFlt != null ? "EnbMenuFlt=" + EnbMenuFlt + ", " : "")
				+ (objType != null ? "objType=" + objType + ", " : "")
				+ (logInstanc != null ? "logInstanc=" + logInstanc + ", " : "")
				+ (userSign != null ? "userSign=" + userSign + ", " : "")
				+ (createDate != null ? "createDate=" + createDate + ", " : "")
				+ (userSign2 != null ? "userSign2=" + userSign2 + ", " : "")
				+ (updateDate != null ? "updateDate=" + updateDate + ", " : "")
				+ (OneLogPwd != null ? "OneLogPwd=" + OneLogPwd + ", " : "")
				+ (lastLogin != null ? "lastLogin=" + lastLogin + ", " : "")
				+ (LastPwds != null ? "LastPwds=" + LastPwds + ", " : "")
				+ (LastPwds2 != null ? "LastPwds2=" + LastPwds2 + ", " : "")
				+ (LastPwdSet != null ? "LastPwdSet=" + LastPwdSet + ", " : "")
				+ "FailedLog=" + FailedLog + ", "
				+ (PwdNeverEx != null ? "PwdNeverEx=" + PwdNeverEx : "") + "]";
	}
}
