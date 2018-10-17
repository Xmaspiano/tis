package com.xmasworking.tis.Exception;

import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 *
 * @author XmasPiano
 * @date 2018/10/13 - 下午5:22
 * Created by IntelliJ IDEA.
 */
@Data
public class ReturnTemplate {
    int code;
    String status;
    String msg;
}
