package net.wyun.dianxiao.repository.primary;

import java.util.List;

import net.wyun.dianxiao.model.primary.OUSR;

import org.springframework.data.repository.CrudRepository;


public interface OUSRRepository extends CrudRepository<OUSR, Integer>{

	OUSR findByUName(String u_name);
	List<OUSR> findByFax(String fax);
}
