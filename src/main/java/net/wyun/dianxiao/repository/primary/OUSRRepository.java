package net.wyun.dianxiao.repository.primary;

import net.wyun.dianxiao.model.primary.OUSR;

import org.springframework.data.repository.CrudRepository;


public interface OUSRRepository extends CrudRepository<OUSR, Integer>{

	OUSR findByUName(String u_name);
}
