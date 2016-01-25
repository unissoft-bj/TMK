CREATE TABLE PRIMARY_MODEL (
	ID 	 INT 		   PRIMARY KEY AUTO_INCREMENT,
	NAME VARCHAR(255)  NOT NULL
);

/*
Navicat SQL Server Data Transfer

Source Server         : sql server
Source Server Version : 90000
Source Host           : 127.0.0.1:1433
Source Schema         : dbo

Target Server Type    : MYSQL
Target Server Version : 50599
File Encoding         : 936

Date: 2016-01-08 20:47:45
*/


-- ----------------------------
-- Table structure for OCLG
-- ----------------------------
DROP TABLE IF EXISTS `OCLG`;
CREATE TABLE `OCLG` (
`ClgCode`  int NOT NULL ,
`CardCode`  varchar(15) NULL ,
`Notes`  longtext NULL ,
`CntctDate`  datetime NULL ,
`CntctTime`  int NULL ,
`Recontact`  datetime NULL ,
`Closed`  char(1) NULL ,
`CloseDate`  datetime NULL ,
`ContactPer`  varchar(90) NULL ,
`Tel`  varchar(20) NULL ,
`Fax`  varchar(20) NULL ,
`CntctSbjct`  smallint NULL ,
`Transfered`  char(1) NULL ,
`DocType`  varchar(20) NULL ,
`DocNum`  varchar(20) NULL ,
`DocEntry`  varchar(20) NULL ,
`Attachment`  longtext NULL ,
`DataSource`  char(1) NULL ,
`AttendUser`  smallint NULL ,
`CntctCode`  int NULL ,
`UserSign`  smallint NULL ,
`SlpCode`  smallint NULL ,
`Action`  char(1) NULL ,
`Details`  varchar(60) NULL ,
`CntctType`  smallint NULL ,
`Location`  smallint NULL ,
`BeginTime`  int NULL ,
`Duration`  decimal(19,6) NULL ,
`DurType`  char(1) NULL ,
`ENDTime`  int NULL ,
`Priority`  char(1) NULL ,
`Reminder`  char(1) NULL ,
`RemQty`  decimal(19,6) NULL ,
`RemType`  char(1) NULL ,
`OprId`  int NULL ,
`OprLine`  smallint NULL ,
`RemDate`  datetime NULL ,
`RemTime`  smallint NULL ,
`RemSented`  char(1) NULL ,
`Instance`  smallint NULL ,
`endDate`  datetime NULL ,
`status`  int NULL ,
`personal`  char(1) NULL ,
`inactive`  char(1) NULL ,
`tentative`  char(1) NULL ,
`street`  varchar(100) NULL ,
`city`  varchar(100) NULL ,
`country`  varchar(3) NULL ,
`state`  varchar(3) NULL ,
`room`  varchar(50) NULL ,
`parentType`  varchar(20) NULL ,
`parentId`  int NULL ,
`prevActvty`  int NULL ,
`AtcEntry`  int NULL ,
PRIMARY KEY (`ClgCode`)
)charset gbk

;

-- ----------------------------
-- Records of OCLG
-- ----------------------------

-- ----------------------------
-- Table structure for OSLP
-- ----------------------------
DROP TABLE IF EXISTS `OSLP`;
CREATE TABLE `OSLP` (
`SlpCode`  smallint NOT NULL ,
`SlpName`  varchar(32) NOT NULL ,
`Memo`  varchar(50) NULL ,
`Commission`  decimal(19,6) NULL ,
`GroupCode`  smallint NULL ,
`Locked`  char(1) NULL ,
`DataSource`  char(1) NULL ,
`UserSign`  smallint NULL ,
`EmpID`  int NULL ,
PRIMARY KEY (`SlpCode`)
)charset gbk

;

-- ----------------------------
-- Records of OSLP
-- ----------------------------

-- ----------------------------
-- Table structure for OUSR
-- ----------------------------
DROP TABLE IF EXISTS `OUSR`;
CREATE TABLE `OUSR` (
`INTERNAL_K`  int NOT NULL ,
`PASSWORD`  varchar(254) NULL ,
`PASSWORD1`  varchar(8) NULL ,
`PASSWORD2`  varchar(8) NULL ,
`USERID`  smallint NOT NULL ,
`USER_CODE`  varchar(8) NOT NULL ,
`U_NAME`  varchar(30) NULL ,
`GROUPS`  smallint NULL ,
`PASSWORD4`  varchar(254) NULL ,
`ALLOWENCES`  longtext NULL ,
`SUPERUSER`  char(1) NULL ,
`DISCOUNT`  decimal(19,6) NULL ,
`PASSWORD3`  varchar(8) NULL ,
`Info1File`  varchar(4) NULL ,
`Info1Field`  smallint NULL ,
`Info2File`  varchar(4) NULL ,
`Info2Field`  smallint NULL ,
`Info3File`  varchar(4) NULL ,
`Info3Field`  smallint NULL ,
`Info4File`  varchar(4) NULL ,
`Info4Field`  smallint NULL ,
`dType`  char(1) NULL ,
`E_Mail`  varchar(100) NULL ,
`PortNum`  varchar(50) NULL ,
`OutOfOffic`  char(1) NULL ,
`SendEMail`  char(1) NULL ,
`SendSMS`  char(1) NULL ,
`DfltsGroup`  varchar(8) NULL ,
`CashLimit`  char(1) NULL ,
`MaxCashSum`  decimal(19,6) NULL ,
`Fax`  varchar(20) NULL ,
`SendFax`  char(1) NULL ,
`Locked`  char(1) NULL ,
`Department`  smallint NULL ,
`Branch`  smallint NULL ,
`UserPrefs`  longblob NULL ,
`Language`  smallint NULL ,
`Charset`  smallint NULL ,
`OpenCdt`  char(1) NULL ,
`CdtPrvDays`  int NULL ,
`DsplyRates`  char(1) NULL ,
`AuImpRates`  char(1) NULL ,
`OpenDps`  char(1) NULL ,
`RcrFlag`  char(1) NULL ,
`CheckFiles`  char(1) NULL ,
`OpenCredit`  char(1) NULL ,
`CreditDay1`  smallint NULL ,
`CreditDay2`  smallint NULL ,
`WallPaper`  longtext NULL ,
`WllPprDsp`  smallint NULL ,
`AdvImagePr`  char(1) NULL ,
`ContactLog`  char(1) NULL ,
`LastWarned`  datetime NULL ,
`AlertPolFr`  smallint NULL ,
`ScreenLock`  smallint NULL ,
`ShowNewMsg`  char(1) NULL ,
`Picture`  varchar(200) NULL ,
`Position`  varchar(90) NULL ,
`Address`  varchar(100) NULL ,
`Country`  varchar(3) NULL ,
`Tel1`  varchar(20) NULL ,
`Tel2`  varchar(20) NULL ,
`GENDER`  char(1) NULL ,
`Birthday`  datetime NULL ,
`EnbMenuFlt`  char(1) NULL ,
`objType`  varchar(20) NULL ,
`logInstanc`  smallint NULL ,
`userSign`  smallint NULL ,
`createDate`  datetime NULL ,
`userSign2`  smallint NULL ,
`updateDate`  datetime NULL ,
`OneLogPwd`  char(1) NULL ,
`lastLogin`  datetime NULL ,
`LastPwds`  varchar(254) NULL ,
`LastPwds2`  varchar(254) NULL ,
`LastPwdSet`  datetime NULL ,
`FailedLog`  int NULL ,
`PwdNeverEx`  char(1) NULL ,
PRIMARY KEY (`INTERNAL_K`)
)charset gbk

;

-- ----------------------------
-- Records of OUSR
-- ----------------------------

-- ----------------------------
-- Indexes structure for table OCLG
-- ----------------------------
CREATE INDEX `OCLG_CRD_CODE` ON `OCLG`(`CardCode`) ;
CREATE INDEX `OCLG_OPPORT` ON `OCLG`(`OprId`, `OprLine`) ;

-- ----------------------------
-- Indexes structure for table OSLP
-- ----------------------------
CREATE UNIQUE INDEX `OSLP_SLP_NAME` ON `OSLP`(`SlpName`) ;
CREATE INDEX `OSLP_COM_GROUP` ON `OSLP`(`GroupCode`) ;

-- ----------------------------
-- Indexes structure for table OUSR
-- ----------------------------
CREATE INDEX `OUSR_PASSWORD` ON `OUSR`(`PASSWORD`) ;
CREATE UNIQUE INDEX `OUSR_USER_CODE` ON `OUSR`(`USER_CODE`) ;
CREATE UNIQUE INDEX `OUSR_SIGNATURE` ON `OUSR`(`USERID`) ;

