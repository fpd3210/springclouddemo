package com.dpf.hystrix.config;

import com.netflix.hystrix.HystrixCommand;
import org.springframework.web.client.RestTemplate;

/**
 * @author dpf
 * @create 2020-03-28 15:15
 * @email 446933040@qq.com
 */
public class HelloCommand extends HystrixCommand<String>{
    RestTemplate restTemplate;

    public HelloCommand(Setter setter, RestTemplate restTemplate) {
        super(setter);
        this.restTemplate = restTemplate;
    }

    @Override
    protected String run() throws Exception{
//        int i = 10 / 0;
        return restTemplate.getForObject("http://eureka-provider/hello",String.class);
    }

    /**
     * 方法请求失败的回调
     * @return
     */
    @Override
    protected String getFallback() {
        return "error-extends"+getExecutionException().getMessage();
    }
}
