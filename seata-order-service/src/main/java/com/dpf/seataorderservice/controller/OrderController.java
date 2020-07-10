package com.dpf.seataorderservice.controller;

import com.dpf.seataorderservice.domain.CommonResult;
import com.dpf.seataorderservice.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public CommonResult createOrder(@RequestParam("userId") Long userId,
                                    @RequestParam("productId") Long productId,
                                    @RequestParam("price") Integer price) throws Exception {
        logger.info("[createOrder] 收到下单请求,用户:{}, 商品:{}, 价格:{}", userId, productId, price);
        Integer count = orderService.createOrder(userId, productId, price);
        return count >0 ? new CommonResult(200,"添加成功") : new CommonResult(444,"添加失败");
    }


}