package com.xmasworking.tis.repository;

import com.xmasworking.tis.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by IntelliJ IDEA.
 *
 * @author XmasPiano
 * @date 2018/10/11 - 上午10:20
 * Created by IntelliJ IDEA.
 */
public interface AccountRepository
        extends JpaRepository<AccountEntity, Long>{

}
