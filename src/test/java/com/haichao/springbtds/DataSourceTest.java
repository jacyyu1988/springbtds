package com.haichao.springbtds;

import com.haichao.SpringbtdsApplication;
import com.haichao.model.Orders;
import com.haichao.model.Users;
import com.haichao.service.IOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * <p>
 *     多数据源测试
 * </p>
 * @Author yusj
 * @Date 2019/3/14
 * @Version 1.0
 */
@SpringBootTest(classes = {SpringbtdsApplication.class})
@RunWith(SpringRunner.class)
public class DataSourceTest {

    @Resource
    IOrderService  iOrderService;

    @Test
    public void test(){
        Users users = new Users();
        users.setUser("yusj");
        users.setPassword("123");
        users.setId(1);

        Orders orders = new Orders();
        orders.setAccount(12);
        orders.setName("book");
        orders.setUserId(1);
        iOrderService.addOrder(orders,users);
    }
}
