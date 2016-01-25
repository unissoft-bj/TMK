package net.wyun.dianxiao.model.primary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PRIMARY_MODEL")
public class PrimaryModel {
	
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	//@SequenceGenerator(name = "PRIMARY_MODEL_ID", sequenceName = "PRIMARY_MODEL_SEQ_ID", allocationSize = 1)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRIMARY_MODEL_ID")
	private int id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
