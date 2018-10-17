package com.xmasworking.tis.service;

import com.xmasworking.tis.entity.SelectElementDetailEntity;
import com.xmasworking.tis.entity.SelectElementMainEntity;
import org.springframework.data.domain.Example;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author XmasPiano
 * @date 2018/10/12 - 下午5:22
 * Created by IntelliJ IDEA.
 */
public interface SelectElementDetailService {
    List<SelectElementDetailEntity> findAllByMainId(Long mainId);

    List<SelectElementDetailEntity> findAllByEntity(SelectElementDetailEntity selectElementDetailEntity);

    List<SelectElementDetailEntity> findAllByExample(Example<SelectElementDetailEntity> example);

    SelectElementDetailEntity save(SelectElementDetailEntity detailEntity);

    SelectElementDetailEntity findById(Long id);

    void delete(Long id);
}
