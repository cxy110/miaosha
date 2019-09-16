package com.miaosha;

import com.miaosha.dao.UserInfoMapper;
import com.miaosha.entity.UserInfo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = {"com.miaosha"})
/*通过mapperScan注解,扫描对应包下的dao层(mapper文件),进行动态代理,实现层级直接的调用*/
@MapperScan("com.miaosha.dao")
@Controller
public class MiaoshaApplication {
  @Autowired
  private UserInfoMapper userInfoMapper;
  @RequestMapping("/")
  public String user(){
    UserInfo userInfo = userInfoMapper.selectByPrimaryKey(1);
    if(userInfo==null){
      return "用户不存在";
    }
    return "getUser.html";
  }

  public static void main(String[] args) {
    SpringApplication.run(MiaoshaApplication.class, args);
  }

}
