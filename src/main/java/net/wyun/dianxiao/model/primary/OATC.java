package net.wyun.dianxiao.model.primary;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OATC {
	
	public OATC(){}
	
	public OATC(int absEntry) {
		this.absEntry = absEntry;
	}

	@Id
	private int absEntry;

	public int getAbsEntry() {
		return absEntry;
	}

	public void setAbsEntry(int absEntry) {
		this.absEntry = absEntry;
	}
	
}
