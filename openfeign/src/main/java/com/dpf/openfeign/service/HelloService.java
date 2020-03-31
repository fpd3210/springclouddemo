package com.dpf.openfeign.service;

import com.dpf.api.IUserService;
import com.dpf.commmons.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author dpf
 * @create 2020-03-31 20:54
 * @email 446933040@qq.com
 */
@FeignClient(value = "eureka-provider",fallbackFactory = HelloServiceFallbackFactory.class)
public interface HelloService extends IUserService {

}
