package net.wyun.dianxiao.model;

public enum CallDirection {
	IN(""),
	OUT(""),
	INTERNAL("");
	
	String code;
	CallDirection(String code){
		this.code = code;
	}
	
	String getCode(){
		return code;
	}

}
