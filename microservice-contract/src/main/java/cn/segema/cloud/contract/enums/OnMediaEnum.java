package cn.segema.cloud.contract.enums;

/**
 * 投放平台
 * @author wangyong
 */
public enum OnMediaEnum {
	
	CDTV1("CDTV1"),
	CDTV2("CDTV2"), 
	CDTV3("CDTV3"),
	CDTV4("CDTV4"),
	CDTV5("CDTV5"), 
	CDTV6("CDTV6"),
	FM1017("文艺之声"),
	FM0890("交通广播"),
	FM0910("经济之声");

	private String typeName;

	OnMediaEnum(String typeName) {
		this.typeName = typeName;
	}

	public String getValue() {
		return typeName;
	}

    public static OnMediaEnum valueOf(Integer type) {
        if (type == null) {
            return null;
        }

        for (OnMediaEnum item : values()) {
            if (item.ordinal() == type) {
                return item;
            }
        }
        return null;
    }
}
