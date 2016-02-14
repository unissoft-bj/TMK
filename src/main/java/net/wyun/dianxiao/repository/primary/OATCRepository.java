package net.wyun.dianxiao.repository.primary;

import net.wyun.dianxiao.model.primary.OATC;
import net.wyun.dianxiao.model.primary.OCLG;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface OATCRepository extends CrudRepository<OATC, Integer>{

	@Query("select coalesce(max(o.absEntry), '0') from OATC o")
	int findMaxAbsEntry();
}
