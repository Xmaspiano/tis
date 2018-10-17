package com.xmasworking.tis.service;

import com.xmasworking.tis.entity.UserEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author XmasPiano
 * @date 2018/10/11 - 上午10:33
 * Created by IntelliJ IDEA.
 */
public interface UserService {
    UserEntity findById(Long id);

    UserEntity findByAccountId(Long accountId);

//    UserEntity findOneByEntity(UserEntity userEntity);

    UserEntity save(UserEntity userEntity);

    Page<UserEntity> findAll(Example<UserEntity> entityExample, Pageable pageable);

    UserEntity clean(UserEntity userEntity);

    List<UserEntity> findAll();
}
