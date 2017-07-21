package cn.segema.cloud.contract.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Contract {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="CONTRACTID")
	private Long contractId;
	@Column(name="CONTRACTNAME")
	private String contractName;
	@Column(name="TOTALMONEY")
	private Integer totalMoney;

	public Long getContractId() {
		return contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public Integer getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Integer totalMoney) {
		this.totalMoney = totalMoney;
	}

}
