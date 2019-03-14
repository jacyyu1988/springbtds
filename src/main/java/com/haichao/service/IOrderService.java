package com.haichao.service;

import com.haichao.model.Orders;
import com.haichao.model.Users;

/**
 * <p>
 *
 * </p>
 * @Author yusj
 * @Date 2019/3/14
 * @Version 1.0
 */
public interface IOrderService {
    void addOrder(Orders orders, Users users);
}
