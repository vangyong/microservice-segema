package cn.segema.cloud.contract.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "CON_CONTRACT")
@Entity
public class Contract {
	@Id
	@Column(name="CONTRACTID")
	private String contractId;
	
	@Column(name="CONTRACTNAME")
	private String contractName;
	
	@Column(name="CONTRACTCODE")
	private String contractCode;
	
	@Column(name="CONTRACTTYPE")
	private String contractType;
	
	@Column(name = "STARTTIME")
	private LocalDateTime startTime;
	
	@Column(name = "ENDTIME")
	private LocalDateTime endTime;
	
	@Column(name = "SIGINTIME")
	private LocalDateTime signTime;
	
	@Column(name="TOTALMONEY")
	private BigDecimal totalMoney;
	
	@Column(name="TOTALMONEYUP")
	private String totalMoneyUp;
	
	@Column(name="DISCOUNT")
	private BigDecimal discount;
	
	@Column(name="PAYMENTTYPE")
	private Integer paymentType;
	
	@Column(name="MARGIN")
	private BigDecimal margin;
	
	@Column(name = "LASTPAYTIME")
	private LocalDateTime lastPayTime;
	
	@Column(name="ADVERTISINGTYPE")
	private Integer advertisingType;
	
	@Column(name="URGENTLEVEL")
	private Integer urgentLevel;
	
	@Column(name="IMPORTANTLEVEL")
	private Integer importantLevel;
	
	@Column(name="FUNDSSOURCE")
	private Integer fundsSource;
	
	@Column(name="STATE")
	private Integer state;
	
	@Column(name="ONMEDIA")
	private Integer onMedia;

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public LocalDateTime getSignTime() {
		return signTime;
	}

	public void setSignTime(LocalDateTime signTime) {
		this.signTime = signTime;
	}

	public BigDecimal getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}

	public String getTotalMoneyUp() {
		return totalMoneyUp;
	}

	public void setTotalMoneyUp(String totalMoneyUp) {
		this.totalMoneyUp = totalMoneyUp;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public Integer getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
	}

	public BigDecimal getMargin() {
		return margin;
	}

	public void setMargin(BigDecimal margin) {
		this.margin = margin;
	}

	public LocalDateTime getLastPayTime() {
		return lastPayTime;
	}

	public void setLastPayTime(LocalDateTime lastPayTime) {
		this.lastPayTime = lastPayTime;
	}

	public Integer getAdvertisingType() {
		return advertisingType;
	}

	public void setAdvertisingType(Integer advertisingType) {
		this.advertisingType = advertisingType;
	}

	public Integer getUrgentLevel() {
		return urgentLevel;
	}

	public void setUrgentLevel(Integer urgentLevel) {
		this.urgentLevel = urgentLevel;
	}

	public Integer getImportantLevel() {
		return importantLevel;
	}

	public void setImportantLevel(Integer importantLevel) {
		this.importantLevel = importantLevel;
	}

	public Integer getFundsSource() {
		return fundsSource;
	}

	public void setFundsSource(Integer fundsSource) {
		this.fundsSource = fundsSource;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getOnMedia() {
		return onMedia;
	}

	public void setOnMedia(Integer onMedia) {
		this.onMedia = onMedia;
	}
	
}
