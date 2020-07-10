package com.dpf.seataorderservice.service;

/**
 * @author dpf
 * @create 2020-07-10 3:20 下午
 * @email 446933040
 */

/**
 * 订单 Service
 */
public interface OrderService {

    /**
     * 创建订单
     *
     * @param userId 用户编号
     * @param productId 产品编号
     * @param price 价格
     * @return 订单编号
     * @throws Exception 创建订单失败，抛出异常
     */
    Integer createOrder(Long userId, Long productId, Integer price) throws Exception;

}

