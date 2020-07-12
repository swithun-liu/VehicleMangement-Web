package com.lancer.backend.service.Impl;

import java.util.List;

import com.lancer.backend.Dao.CarInfoRepository;
import com.lancer.backend.entity.Car;
import com.lancer.backend.service.CarServ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CarServImpl implements CarServ {

    @Autowired
    private CarInfoRepository carInfoRepository;

    /** 返回所有司机信息 */
    @Override
    public List<Car> findAll() {
        return carInfoRepository.findAll();
    }

    @Override
    public Car AddOne(Car entity) {
        return carInfoRepository.save(entity);
    }

    @Override
    public Car getOnebyId(Long id) {
        return carInfoRepository.getOne(id);
    }

    @Override
    public Car update(Car entity) {
        return carInfoRepository.save(entity);
    }

    @Override
    public boolean delete(Long id) {
        try {
            carInfoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Car> findByCarIdLike(Long id) {
        return carInfoRepository.findByCarIdLike(id);
    }

    @Override
    public Page<Car> findAllbyPage(Example<Car> example, PageRequest pageRequest) {
        return carInfoRepository.findAll(example,pageRequest);
    }

    @Override
    public Page<Car> findAll(PageRequest pageRequest) {
        return carInfoRepository.findAll(pageRequest);
    }

    @Override
    public List<Car> findAllbyExample(Example<Car> example,Sort sort) {
        return carInfoRepository.findAll(example,sort);
    }

}
