package net.wyun.dianxiao.model;

public enum CallDirection {
	IN((short) 1),
	OUT((short) 2),
	INTERNAL((short) 3);
	
	private short code;
	private CallDirection(short code){
		this.code = code;
	}
	
	public Short getCode(){
		return code;
	}
	
	public static CallDirection getCallDirection(Short code) {
		if (code == null) {
			return null;
		}

		for (CallDirection direction : CallDirection.values()) {
			if (code.equals(direction.getCode())) {
				return direction;
			}
		}
		throw new IllegalArgumentException("No matching type for id " + code);
	}

}
