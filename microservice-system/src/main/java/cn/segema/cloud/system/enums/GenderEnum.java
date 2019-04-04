package cn.segema.cloud.system.enums;

public enum GenderEnum {
	
	MALE("男"),
	FEMALE("女"),
	SECURITY("保密");

	private String typeName;

	GenderEnum(String typeName) {
		this.typeName = typeName;
	}

	public String getValue() {
		return typeName;
	}

    public static GenderEnum valueOf(Integer type) {
        if (type == null) {
            return null;
        }

        for (GenderEnum item : values()) {
            if (item.ordinal() == type) {
                return item;
            }
        }
        return null;
    }
}
