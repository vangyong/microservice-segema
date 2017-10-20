package cn.segema.cloud.contract.enums;

public enum FundsSourceEnum {
	
	FINANCIAL("财政资金"),
	NOFINANCIAL("非财政资金"),
	MANAGEINCOME("经营收入"),
	ADVERTISEMENTINCOME("广告收入");

	private String typeName;

	FundsSourceEnum(String typeName) {
		this.typeName = typeName;
	}

	public String getValue() {
		return typeName;
	}

    public static FundsSourceEnum valueOf(Integer type) {
        if (type == null) {
            return null;
        }

        for (FundsSourceEnum item : values()) {
            if (item.ordinal() == type) {
                return item;
            }
        }
        return null;
    }
}
