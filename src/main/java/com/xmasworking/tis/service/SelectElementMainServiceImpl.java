package com.xmasworking.tis.service;

import com.xmasworking.tis.entity.SelectElementMainEntity;
import com.xmasworking.tis.repository.SelectElementMainRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
public class SelectElementMainServiceImpl implements SelectElementMainService {
    @Autowired
    SelectElementMainRepository selectElementMainRepository;

    @Override
    public List<SelectElementMainEntity> findAll(){
        return selectElementMainRepository.findAll();
    }

    @Override
    public SelectElementMainEntity save(SelectElementMainEntity selectElementMainEntity){
        return selectElementMainRepository.save(selectElementMainEntity);
    }

    @Override
    public SelectElementMainEntity findById(Long id){
        Optional<SelectElementMainEntity> optional = selectElementMainRepository.findById(id);
        return optional.get();
    }

    @Override
    public void delete(Long id){
        selectElementMainRepository.deleteById(id);
    }
}
