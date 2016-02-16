/**
 * 
 */
package net.wyun.dianxiao.model.primary;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

/**
 * @author michael
 *
 */
@Entity
@IdClass(value=ATC1PK.class)
public class ATC1 {

	@Id
	int absEntry;    // `AbsEntry` INT NOT NULL,
	
	@Id
	int line;        // `Line` INT NOT NULL,
	
	String srcPath;  // `srcPath` LONGTEXT CHARACTER SET utf8,
	String trgtPath; // `trgtPath` LONGTEXT CHARACTER SET utf8,
	String fileName; // `FileName` VARCHAR(254) CHARACTER SET utf8,
	String fileExt;  // `FileExt` VARCHAR(8) CHARACTER SET utf8,
	Date date;   // `Date` DATETIME,
	Integer usrID;      // `UsrID` INT,
	String copied;   // `Copied` VARCHAR(1) CHARACTER SET utf8 DEFAULT 'N',
	String overRide; // `Override` VARCHAR(1) CHARACTER SET utf8 DEFAULT 'N',
	                 // PRIMARY KEY (`AbsEntry`, `Line`)
	public int getAbsEntry() {
		return absEntry;
	}
	public void setAbsEntry(int absEntry) {
		this.absEntry = absEntry;
	}
	public int getLine() {
		return line;
	}
	public void setLine(int line) {
		this.line = line;
	}
	public String getSrcPath() {
		return srcPath;
	}
	public void setSrcPath(String srcPath) {
		this.srcPath = srcPath;
	}
	public String getTrgtPath() {
		return trgtPath;
	}
	public void setTrgtPath(String trgtPath) {
		this.trgtPath = trgtPath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileExt() {
		return fileExt;
	}
	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}
	public Date getDateTime() {
		return date;
	}
	public void setDateTime(Date dateTime) {
		this.date = dateTime;
	}
	public Integer getUserId() {
		return usrID;
	}
	public void setUserId(Integer userId) {
		this.usrID = userId;
	}
	public String getCopied() {
		return copied;
	}
	public void setCopied(String copied) {
		this.copied = copied;
	}
	public String getOverRide() {
		return overRide;
	}
	public void setOverRide(String overRide) {
		this.overRide = overRide;
	}
	
	
	

}
