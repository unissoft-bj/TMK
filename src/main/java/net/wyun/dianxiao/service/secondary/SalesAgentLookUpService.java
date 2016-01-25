package net.wyun.dianxiao.service.secondary;


public interface SalesAgentLookUpService extends CallDirectionDetector{

	public abstract String getUserNameByPhoneExt(String phoneExt);

}