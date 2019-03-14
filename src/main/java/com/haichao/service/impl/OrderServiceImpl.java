package com.haichao.service.impl;

import com.haichao.dao.order.OrdersMapper;
import com.haichao.dao.user.UsersMapper;
import com.haichao.model.Orders;
import com.haichao.model.Users;
import com.haichao.service.IOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *
 * @Desc </p>
 * @Author yusj
 * @Date 2019/3/14
 * @Version 1.0
 */
@Service
public class OrderServiceImpl implements IOrderService {
    @Resource
    private OrdersMapper ordersMapper;

    @Resource
    private UsersMapper usersMapper;

    public void addOrder(Orders orders, Users users) {
        usersMapper.insertSelective(users);
        ordersMapper.insertSelective(orders);
    }
}
