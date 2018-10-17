package com.xmasworking.tis.service;

import com.xmasworking.tis.entity.AccountEntity;
import com.xmasworking.tis.entity.UserEntity;
import com.xmasworking.tis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 *
 * @author XmasPiano
 * @date 2018/10/11 - 上午10:33
 * Created by IntelliJ IDEA.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserEntity findById(Long id){
        Optional<UserEntity>  optional = userRepository.findById(id);
        return getOptionalNullEntity(optional);
    }

    @Override
    public  UserEntity findByAccountId(Long accountId){
        if(accountId == null){
            return new UserEntity();
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setAccountid(accountId);
        return findOneByEntity(userEntity);
    }

//    @Override
    private UserEntity findOneByEntity(UserEntity userEntity){
        Example<UserEntity> entityExample = Example.of(userEntity);
        Optional<UserEntity> optional = userRepository.findOne(entityExample);
        return getOptionalNullEntity(optional);
    }

    @Override
    public UserEntity save(UserEntity userEntity){
        UserEntity dbEntity = findByAccountId(userEntity.getAccountid());
        //存在数据则覆盖ID进行更新
        if(dbEntity.getId() != null){
            userEntity.setId(dbEntity.getId());
        }else{
            userEntity.setId(null);
        }
        return userRepository.save(userEntity);
    }

    @Override
    public Page<UserEntity> findAll(Example<UserEntity> entityExample, Pageable pageable){
        return userRepository.findAll(entityExample,pageable);
    }

    @Override
    public UserEntity clean(UserEntity userEntity){
        UserEntity cleanEntity = new UserEntity();
        cleanEntity.setId(userEntity.getId());
        cleanEntity.setAccountid(userEntity.getAccountid());
        cleanEntity.setJobNumber(userEntity.getJobNumber());
        return userRepository.save(cleanEntity);
    }

    @Override
    public List<UserEntity> findAll(){
        return userRepository.findAll();
    }

    private UserEntity getOptionalNullEntity(Optional<UserEntity> optional){
        if(optional.isPresent()){
            return optional.get();
        }else {
            return new UserEntity();
        }
    }
}

