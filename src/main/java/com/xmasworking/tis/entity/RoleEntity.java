package com.xmasworking.tis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 *
 * @author XmasPiano
 * @date 2018/10/16 - 下午2:50
 * Created by IntelliJ IDEA.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RoleEntity {
    public static final String USER="user";
    public static final String MANAGER="manager";
}
