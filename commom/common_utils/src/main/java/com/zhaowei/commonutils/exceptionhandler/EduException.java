package com.zhaowei.commonutils.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 自定义类异常
 */
@Data
@AllArgsConstructor  //有参数构造器
@NoArgsConstructor   //生成无参数构造
public class EduException extends RuntimeException{
    private Integer code;//状态码
    private String msg;//输出消息
}
