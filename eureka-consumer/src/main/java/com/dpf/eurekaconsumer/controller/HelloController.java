package com.dpf.eurekaconsumer.controller;

import com.dpf.commmons.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author dpf
 * @create 2020-03-30 15:50
 * @email 446933040@qq.com
 */
@RestController
public class HelloController {

    /**
     * http调用，调用地址写死
     * @return
     */
    @GetMapping("/hello1")
    public String hello1(){
        HttpURLConnection con = null;
        try {
            URL url = new URL("http://localhost:1113/hello");
            con = (HttpURLConnection) url.openConnection();
            if(con.getResponseCode()==200){
                BufferedReader br =  new BufferedReader(new InputStreamReader(con.getInputStream()));
                String s = br.readLine();
                br.close();
                return s;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "error";
    }


    @Autowired
    DiscoveryClient discoveryClient;
    /**
     * 动态获取调用地址
     * @return
     */
    @GetMapping("/hello2")
    public String hello2(){
        HttpURLConnection con = null;
        List<ServiceInstance> instances = discoveryClient.getInstances("provider");
        ServiceInstance serviceInstance = instances.get(0);
        String host = serviceInstance.getHost();
        int port = serviceInstance.getPort();

        StringBuilder sb = new StringBuilder();
        sb.append("http://")
                .append(host)
                .append(":")
                .append(port)
                .append("/")
                .append("hello");
        try {
            URL url = new URL(sb.toString());
            con = (HttpURLConnection) url.openConnection();
            if(con.getResponseCode()==200){
                BufferedReader br =  new BufferedReader(new InputStreamReader(con.getInputStream()));
                String s = br.readLine();
                br.close();
                return s;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "error";
    }

    /**
     * 实现线性负载均衡
     */
    int count=0;
    @GetMapping("/hello3")
    public String hello3(){
        HttpURLConnection con = null;
        List<ServiceInstance> instances = discoveryClient.getInstances("provider");
        ServiceInstance serviceInstance = instances.get((count++)%instances.size());
        String host = serviceInstance.getHost();
        int port = serviceInstance.getPort();

        StringBuilder sb = new StringBuilder();
        sb.append("http://")
                .append(host)
                .append(":")
                .append(port)
                .append("/")
                .append("hello");
        try {
            URL url = new URL(sb.toString());
            con = (HttpURLConnection) url.openConnection();
            System.out.println(con);
            if(con.getResponseCode()==200){
                BufferedReader br =  new BufferedReader(new InputStreamReader(con.getInputStream()));
                String s = br.readLine();
                br.close();
                return s;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "error";
    }

    @Autowired
    @Qualifier("restTemplateOne")
    RestTemplate restTemplateOne;
    /**
     *
     * 没有负载均衡的RestTemplate必须完整地址
     * @return
     */
    @GetMapping("/hello4")
    public String hello4(){
        HttpURLConnection con = null;
        List<ServiceInstance> instances = discoveryClient.getInstances("provider");
        ServiceInstance serviceInstance = instances.get(0);
        String host = serviceInstance.getHost();
        int port = serviceInstance.getPort();

        StringBuilder sb = new StringBuilder();
        sb.append("http://")
                .append(host)
                .append(":")
                .append(port)
                .append("/")
                .append("hello");
        String s = restTemplateOne.getForObject(sb.toString(), String.class);

        return s;
    }

    @Autowired
    @Qualifier("restTemplate")
    RestTemplate restTemplate;

    /**
     * Ribbon实现负载均衡
     *
     * @return
     */
    @GetMapping("/hello5")
    public String hello5(){
        return restTemplate.getForObject("http://provider/hello",String.class);
    }


    /**
     * get方法
     * RestTemplate 两种方法体验
     *  getForObject：返回具体值
     *  getForEntity：返回带Http响应头的具体值
     *  两种方法都有三个不同的重载方法分别表示不同的传参方式
     *
     */
    @GetMapping("/hello6")
    public void hello6(){
        String s = restTemplate.getForObject("http://provider/hello2?name={1}", String.class, "runn");
        System.out.println(s);

        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://provider/hello2?name={1}", String.class, "runn");
        HttpStatus statusCode = responseEntity.getStatusCode();
        System.out.println("HttpStatus:"+statusCode);
        int statusCodeValue = responseEntity.getStatusCodeValue();
        System.out.println("statusCodeValue:"+statusCodeValue);
        String body = responseEntity.getBody();
        System.out.println("body:"+body);
        HttpHeaders headers = responseEntity.getHeaders();
        System.out.println("----------header---");
        Set<String> keySet = headers.keySet();
        for (String key : keySet){
            System.out.println(key+"--"+headers.get(key));
        }
    }
    /**
     * get方法
     * RestTemplate方法的三种传值方法
     */
    @GetMapping("/hello7")
    public void hello7(){
        //占位符传值
        String s1 = restTemplate.getForObject("http://provider/hello2?name={1}", String.class, "runn");
        System.out.println(s1);

        //map传值
        Map<String ,Object> map = new HashMap<>();
        map.put("name","runn");
        String s2 = restTemplate.getForObject("http://provider/hello2?name={name}", String.class, map);
        System.out.println(s2);

        //uri传值
        try {
            //如果是中文需要转码，不然可能乱码
            String url = "http://provider/hello2?name="+ URLEncoder.encode("张三","UTF-8");
            URI uri = URI.create(url);
            String s3 = restTemplate.getForObject(uri, String.class);
            System.out.println(s3);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    /**
     * post方法
     * RestTemplate的post方法和get方法基本类似，多了一个postForLocation
     */
    @GetMapping("/hello8")
    public void hello8(){
        //key/value传值
        MultiValueMap<String,Object> map = new LinkedMultiValueMap<>();
        map.add("username","runn");
        map.add("password","123");
        map.add("id",999);
        User user1 = restTemplate.postForObject("http://provider/user1", map, User.class);
        System.out.println(user1);

        //json传值
        user1.setId(998);
        User user2 = restTemplate.postForObject("http://provider/user2", user1, User.class);
        System.out.println(user2);
    }
    /**
     * 测试postForLocation：用于页面重定向(注册后重定向到登录页面)
     */
    @GetMapping("/hello9")
    public void hello9(){
        MultiValueMap<String,Object> map = new LinkedMultiValueMap<>();
        map.add("username","runn");
        map.add("password","123");
        map.add("id",999);
        URI uri = restTemplate.postForLocation("http://provider/register", map);
        String s = restTemplate.getForObject(uri, String.class);
        System.out.println(s);
    }

    /**
     * 测试put请求
     */
    @GetMapping("/hello10")
    public void hello10(){
        MultiValueMap<String,Object> map = new LinkedMultiValueMap<>();
        map.add("username","runn");
        map.add("password","123");
        map.add("id",999);
        restTemplate.put("http://provider/user1",map);

        User user = new User();
        user.setId(998);
        user.setPassword("123");
        user.setUsername("runn");
        restTemplate.put("http://provider/user2",user);

    }

    /**
     * 测试delete请求
     */
    @GetMapping("/hello11")
    public void hello11(){
        restTemplate.delete("http://provider/user1?id={1}",998);
        restTemplate.delete("http://provider/user2/{1}",999);
    }

}
