package com.dpf.alibabaconsumer2.services;


import com.dpf.alibabaconsumer2.pojo.CommonResult;
import com.dpf.alibabaconsumer2.pojo.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**

 *
 * @author zzyy
 * @version 1.0
 * @date 2020/03/07
 */
@FeignClient(value = "nacos-payment-provider", fallback = PaymentFallbackService.class)
public interface PaymentService {

    @GetMapping("/paymentSQL/{id}")
    CommonResult<Payment> paymentSQL(@PathVariable("id") Long id);
}
