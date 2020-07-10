package com.dpf.seataorderservice.feign;


import com.dpf.seataorderservice.feign.dto.AccountReduceBalanceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * `account-service` 服务的 Feign 客户端
 */
@FeignClient(name = "seata-account-service")
public interface AccountServiceFeignClient {

    @PostMapping("/account/reduce-balance")
    void reduceBalance(@RequestBody AccountReduceBalanceDTO accountReduceBalanceDTO);

}
