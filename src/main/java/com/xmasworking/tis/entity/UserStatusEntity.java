package com.xmasworking.tis.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @author XmasPiano
 * @date 2018/10/11 - 上午9:44
 * Created by IntelliJ IDEA.
 */
@Entity
@Data
@Table(name = "user_status")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class UserStatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long accountid;
    Integer status;

    /**
     * 判断学员信息状态：
     * _null(0): 空值（可修改）、
     * _apply(1)：已提交（可修改）、
     * _pending(2)：审核中（锁定）、
     * _back(3)：退回（可修改）、
     * _pass(4)：通过（锁定）、
     * _end(5)：归档（锁定）
     * @return
     */
    public String getStatusName(){
        if(status != null) {
            switch (StatusType.getType(status)) {
                case _null:
                    return "空值";
                case _apply:
                    return "已提交";
                case _pending:
                    return "审核中";
                case _back:
                    return "退回";
                case _pass:
                    return "通过";
                case _end:
                    return "归档";
                default:
                    return "无状态数据...";
            }
        }
        return "无状态数据...";
    }

    public boolean isLock(){
        if(status != null) {
            switch (StatusType.getType(status)) {
                case _null:
                    return false;
                case _apply:
                    return false;
                case _pending:
                    return true;
                case _back:
                    return false;
                case _pass:
                    return true;
                case _end:
                    return true;
                default:
                    return true;
            }
        }
        return false;
    }

    /**
     * 是否归档
     * @return
     */
    public boolean isEnd(){
        if(status != null) {
            return StatusType.getType(status) == StatusType._end;
        }
        return false;
    }

    /**
     * 判断学员信息状态：0:空值、1：已提交（可修改）、2：审核中（锁定）、3：退回（可修改）、4：通过（锁定）、5：归档（锁定）
     */
    public enum StatusType {
        _null(0),_apply(1),_pending(2),_back(3),_pass(4),_end(5);

        private final int val;

        StatusType(int val)
        {
            this.val = val;
        }

        public static int getValue(StatusType statusType) {
            return statusType.val;
        }

        public static StatusType getType(int value) {
            for(StatusType statusType:StatusType.values()){
                if(statusType.val == value){
                    return statusType;
                }
            }
            return _null;
        }
    }

    public void setStatusType(StatusType status) {
        this.status = status.val;
    }
}
