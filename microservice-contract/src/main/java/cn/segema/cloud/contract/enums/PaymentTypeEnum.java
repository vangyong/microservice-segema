package cn.segema.cloud.contract.enums;

/**
 * 结算方式
 * 
 * @author wangyong
 */
public enum PaymentTypeEnum {

	CASH("现金"), CHEQUE("支票"), TRANSFER("转账"), DRAFT("汇票");

	private String typeName;

	PaymentTypeEnum(String typeName) {
		this.typeName = typeName;
	}

	public String getValue() {
		return typeName;
	}

	public static PaymentTypeEnum valueOf(Integer type) {
		if (type == null) {
			return null;
		}

		for (PaymentTypeEnum item : values()) {
			if (item.ordinal() == type) {
				return item;
			}
		}
		return null;
	}
}
