package com.miaosha.validator;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;


/**
 * @program: miaosha
 * @description: validator实现类, 在实现验证数据是否合规是,要对需要验证的数据对应的2实体类中的属性添加注解,表明判断的依据,以及验证数据不合规时的报错信息
 * @author: cxy
 * @create: 2019-09-20 16:19
 */
@Component
public class ValidateImpl implements InitializingBean {
  private Validator validator;
  //实现校验方法并返回校验结果
  public  ValidationResult validate(Object bean){
    ValidationResult validationResult = new ValidationResult();
    Set<ConstraintViolation<Object>> validate = validator.validate(bean);
    if(validate.size()>0){
      //有错误
      validationResult.setHasError(true);
      validate.forEach(constraintViolation->{
        String errMsg=constraintViolation.getMessage();
        String propertyName=constraintViolation.getPropertyPath().toString();
        validationResult.getErrorMsgMap().put(propertyName,errMsg);
      });
    }
  return validationResult;
  }
  @Override

  public void afterPropertiesSet() throws Exception {
    //将hibernate validator通过工厂的初始化方法使其实例化
    this.validator= Validation.buildDefaultValidatorFactory().getValidator();


  }
}
