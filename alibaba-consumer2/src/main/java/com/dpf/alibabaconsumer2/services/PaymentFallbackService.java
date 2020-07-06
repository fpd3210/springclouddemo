package com.dpf.alibabaconsumer2.services;


import com.dpf.alibabaconsumer2.pojo.CommonResult;
import com.dpf.alibabaconsumer2.pojo.Payment;
import org.springframework.stereotype.Component;

/**

 *
 * @author zzyy
 * @version 1.0
 * @date 2020/03/07
 */
@Component
public class PaymentFallbackService implements PaymentService {
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(444, "fallback");
    }
}
