package com.xmasworking.tis.service;

import com.xmasworking.tis.entity.SelectElementDetailEntity;
import com.xmasworking.tis.repository.SelectElementDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 *
 * @author XmasPiano
 * @date 2018/10/12 - 下午5:22
 * Created by IntelliJ IDEA.
 */
@Service
public class SelectElementDetailServiceImpl implements SelectElementDetailService {

    @Autowired
    SelectElementDetailRepository selectElementDetailRepository;

    @Override
    public List<SelectElementDetailEntity> findAllByMainId(Long mainId){
        SelectElementDetailEntity selectElementDetailEntity = new SelectElementDetailEntity();
        selectElementDetailEntity.setMainid(mainId);
        return findAllByEntity(selectElementDetailEntity);
    }

    @Override
    public List<SelectElementDetailEntity> findAllByEntity(SelectElementDetailEntity selectElementDetailEntity){
        Example<SelectElementDetailEntity> example =Example.of(selectElementDetailEntity);
        return findAllByExample(example);
    }

    @Override
    public List<SelectElementDetailEntity> findAllByExample(Example<SelectElementDetailEntity> example){
        return selectElementDetailRepository.findAll(example);
    }

    @Override
    public SelectElementDetailEntity save(SelectElementDetailEntity detailEntity){
        return selectElementDetailRepository.save(detailEntity);
    }

    @Override
    public SelectElementDetailEntity findById(Long id){
        Optional<SelectElementDetailEntity> optional = selectElementDetailRepository.findById(id);
        return optional.get();
    }

    @Override
    public void delete(Long id){
        selectElementDetailRepository.deleteById(id);
    }
}
