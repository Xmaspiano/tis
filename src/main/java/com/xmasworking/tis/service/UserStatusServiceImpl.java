package com.xmasworking.tis.service;

import com.xmasworking.tis.entity.UserStatusEntity;
import com.xmasworking.tis.repository.UserStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 *
 * @author XmasPiano
 * @date 2018/10/11 - 上午10:33
 * Created by IntelliJ IDEA.
 */
@Service
public class UserStatusServiceImpl implements UserStatusService {

    @Autowired
    UserStatusRepository userStatusRepository;

    @Override
    public UserStatusEntity save(UserStatusEntity userStatusEntity){
        return userStatusRepository.save(userStatusEntity);
    }

    @Override
    public UserStatusEntity findById(Long id){
        Optional<UserStatusEntity> optional = userStatusRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }else {
            return new UserStatusEntity();
        }
    }

    @Override
    public UserStatusEntity findByAccountId(Long accountId){
        UserStatusEntity userStatusEntity = new UserStatusEntity();
        if(accountId == null){
            return userStatusEntity;
        }
        userStatusEntity.setAccountid(accountId);
        return findOneByEntity(userStatusEntity);
    }

    public UserStatusEntity findOneByEntity(UserStatusEntity userStatusEntity){
        Example<UserStatusEntity> entityExample = Example.of(userStatusEntity);
        Optional<UserStatusEntity> optional = userStatusRepository.findOne(entityExample);
        return getOptionalNullEntity(optional);
    }

    private UserStatusEntity getOptionalNullEntity(Optional<UserStatusEntity> optional){
        if(optional.isPresent()){
            return optional.get();
        }else {
            return new UserStatusEntity();
        }
    }
}
