package com.lancer.backend.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lancer.backend.entity.Car;
import com.lancer.backend.entity.Dispatch;
import com.lancer.backend.entity.Order;
import com.lancer.backend.entity.Transportation;
import com.lancer.backend.message.DistributePlan;
import com.lancer.backend.service.Impl.CarServImpl;
import com.lancer.backend.service.Impl.DispatchServImpl;
import com.lancer.backend.service.Impl.OrderServImpl;
import com.lancer.backend.service.Impl.TransportationServImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderInfoController {

    @Autowired
    private OrderServImpl orderServImpl;
    @Autowired
    private CarServImpl CarServ;
    @Autowired
    private DispatchServImpl dispatchServImpl;
    @Autowired
    private TransportationServImpl transportationServImpl;

    List<Long> capacityList = new ArrayList<>();
    List<Integer> numList = new ArrayList<>();
    List<Integer> tempList = new ArrayList<>();
    int totalCost = -1;
    DistributePlan distributePlan = new DistributePlan();
    List<Car> carListforPlant = new ArrayList<>();
    List<Car> carList = new ArrayList<>();
    int weight = 0;

    /***
     * 根据参数获取订单信息
     * 
     * @param info
     * @return
     */
    @GetMapping("/orderinfo")
    private List<Order> findAll(@RequestParam(name = "query") String info) {
        if (info != null && info.length() != 0) {
            return orderServImpl.findByOrderIdLike(info);
        } else {
            return orderServImpl.findAll();
        }
    }

    /**
     * 添加订单
     * 
     * @param entity
     * @return
     */
    @PostMapping(value = "/orderinfo/addorderinfo")
    private String AddOne(@RequestBody Order entity) {
        String orderId = String.valueOf(new Date().getTime());
        entity.setOrderId(orderId);
        if (orderServImpl.AddOne(entity) != null) {
            return "成功";
        } else {
            return "失败";
        }
    }

    /**
     * 根据id获取订单信息
     * 
     * @param param
     * @return
     */
    @GetMapping(value = "/orderinfo/edit/{id}")
    private Order getOneforEdit(@PathVariable("id") String param) {
        if (param == null) {
            return null;
        } else {
            return orderServImpl.getOnebyId(param);
        }
    }

    /**
     * 编辑订单信息
     * 
     * @param entity
     * @return
     */
    @PostMapping(value = "/orderinfo/editinfo")
    public String updateOrderInfo(@RequestBody Order entity) {
        if (orderServImpl.update(entity) != null) {
            return "成功";
        } else {
            return "失败";
        }
    }

    /**
     * 删除订单
     * 
     * @param param
     * @return
     */
    @DeleteMapping(value = "/orderinfo/delete/{id}")
    public String deleteOrder(@PathVariable("id") String param) {
        if (orderServImpl.delete(param)) {
            return "成功";
        } else {
            return "失败";
        }
    }

    /**
     * 执行选择的方案
     */
    @PostMapping(value = "/orderinfo/chooseplan")
    public String choosePlan(@RequestBody Map<String, Object> datas) {
        List<Car> tempcars = (List<Car>) datas.get("cars");
        ObjectMapper mapper = new ObjectMapper();
        List<Car> cars = mapper.convertValue(tempcars, new TypeReference<List<Car>>() {
        });
        String id = (String) datas.get("id");
        boolean flag = false;
        Dispatch dispatch = new Dispatch();
        Transportation transportation=new Transportation();
        Order order = orderServImpl.getOnebyId(id);
        if (order == null) {
            return "失败";
        }
        for (Car car : cars) {
            dispatch.setDispatchId(String.valueOf(new Date().getTime()));
            dispatch.setOrderId(order.getOrderId());
            dispatch.setShipper(car.getDriverId());
            dispatch.setDestination(order.getUnloadingAddress());
            dispatchServImpl.AddOne(dispatch);
            transportation.setTransportationId(String.valueOf(new Date().getTime()));
            transportation.setOrderId(order.getOrderId());
            transportationServImpl.AddOne(transportation);

        }
        return "成功";
    }

    /**
     * 返回针对订单计算出来的分配方案
     */
    @PostMapping(value = "/orderinfo/distribute")
    public DistributePlan distributeOrder(@RequestBody Order entity) {
        distributePlan.list.clear();
        distributePlan.setTotalCost(-1);
        capacityList.clear();
        numList.clear();
        tempList.clear();
        carListforPlant.clear();
        carList.clear();
        weight = entity.getWeight();
        Car car = new Car();
        car.setCarId((long) 2);
        car.setHasOut(false);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnorePaths("carId");
        Example<Car> example = Example.of(car, exampleMatcher);
        Sort sort = Sort.by(Direction.ASC, "loss");
        carList = CarServ.findAllbyExample(example, sort);

        for (Car eCar : carList) {
            long capacity = eCar.getCapacity();
            int i = -1;
            for (; i < capacityList.size(); i++) {
                if (i >= 0) {
                    if (capacityList.get(i) == capacity) {
                        numList.set(i, numList.get(i) + 1);
                        break;
                    }
                }
            }
            if (i == capacityList.size()) {
                capacityList.add(capacity);
                numList.add(1);
            }
        }
        for (int i = 0; i < numList.size(); i++) {
            tempList.add(0);
        }
        caculate(0);
        return distributePlan;
    }

    public void caculate(int index) {
        tempList.set(index, 0);
        for (; tempList.get(index) < numList.get(index); tempList.set(index, tempList.get(index) + 1)) {
            if (index == numList.size() - 1) {
                computeCost();
            } else {
                caculate(index + 1);
            }
        }
    }

    public void computeCost() {
        List<Car> tempResult = new ArrayList<>();
        int tempCost = 0;
        int tempWeight = 0;
        for (int i = 0; i < tempList.size(); i++) {
            tempWeight += capacityList.get(i) * tempList.get(i);
        }
        if (tempWeight >= weight) {
            for (int i = 0; i < tempList.size(); i++) {
                int num = tempList.get(i);
                long capacity = capacityList.get(i);
                int index = 0;
                for (int j = 0; j < carList.size() && index < num; j++) {
                    if (carList.get(j).getCapacity() == capacity) {
                        tempResult.add(carList.get(j));
                        tempCost += carList.get(j).getLoss().intValue();
                        index++;
                    }
                }
            }
            if (distributePlan.totalCost == -1 || tempCost == totalCost) {
                distributePlan.list.add(tempResult);
                distributePlan.totalCost = tempCost;
            } else if (tempCost < totalCost) {
                distributePlan.list.clear();
                distributePlan.list.add(tempResult);
                distributePlan.totalCost = tempCost;
            }
            System.out.println();
        }
    }
}
