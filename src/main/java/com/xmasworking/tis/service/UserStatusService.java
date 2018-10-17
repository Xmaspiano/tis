package com.xmasworking.tis.service;

import com.xmasworking.tis.entity.UserStatusEntity;

/**
 * Created by IntelliJ IDEA.
 *
 * @author XmasPiano
 * @date 2018/10/11 - 上午10:33
 * Created by IntelliJ IDEA.
 */
public interface UserStatusService {
    UserStatusEntity save(UserStatusEntity userStatusEntity);

    UserStatusEntity findById(Long id);

    UserStatusEntity findByAccountId(Long accountId);
}
