package com.lvjian.mybatis.cascade.mapper;

import com.lvjian.mybatis.cascade.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {

    List<Order> listOrdersByUserId(@Param("userId") Long userId);

    Order getOrderByNo(@Param("orderNo") String orderNo);

    Order getOrderByNoWithJoin(@Param("orderNo") String orderNo);

}
