package net.wyun.dianxiao.repository.primary;

import net.wyun.dianxiao.model.primary.OCLG;
import net.wyun.dianxiao.model.primary.PrimaryModel;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface OCLGRepository extends CrudRepository<OCLG, Integer>{

	@Query("select coalesce(max(o.clgCode), '0') from OCLG o")
	int findMaxClgCode();
}
