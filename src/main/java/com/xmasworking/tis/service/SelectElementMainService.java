package com.xmasworking.tis.service;

import com.xmasworking.tis.entity.SelectElementMainEntity;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author XmasPiano
 * @date 2018/10/12 - 下午5:22
 * Created by IntelliJ IDEA.
 */
public interface SelectElementMainService {
    List<SelectElementMainEntity> findAll();

    SelectElementMainEntity save(SelectElementMainEntity selectElementMainEntity);

    SelectElementMainEntity findById(Long id);

    void delete(Long id);
}
