package net.wyun.dianxiao.repository.primary;

import net.wyun.dianxiao.model.primary.ATC1;
import net.wyun.dianxiao.model.primary.ATC1PK;
import net.wyun.dianxiao.model.primary.OCLG;
import net.wyun.dianxiao.model.primary.PrimaryModel;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface ATC1Repository extends CrudRepository<ATC1, ATC1PK>{

}
