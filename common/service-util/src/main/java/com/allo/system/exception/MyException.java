package com.allo.system.exception;

/**
 * @author FuYiCheng
 * @date 2023-02-06 16:22
 * @description:自定义异常类
 * @version:
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyException extends RuntimeException {

    private Integer code;
    private String msg;
}
