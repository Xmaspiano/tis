package com.xmasworking.tis.controller.modle;

import com.xmasworking.tis.Exception.ReturnTemplate;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 *
 * @author XmasPiano
 * @date 2018/10/11 - 下午8:18
 * Created by IntelliJ IDEA.
 */
@Data
public class AjaxResponseModel<T> extends ReturnTemplate {
    public static final String SUCCESS="SUCCESS";
    public static final String FAILURE="FAILURE";
    public static final String WARNING="WARNING";

    T data;

    public static AjaxResponseModel sendSuccess(){
        AjaxResponseModel ajaxModel = new AjaxResponseModel();
        ajaxModel.setStatus(AjaxResponseModel.SUCCESS);
        ajaxModel.setMsg("操作成功");
        return ajaxModel;
    }

    public static AjaxResponseModel sendFailure(String infoFailure){
        AjaxResponseModel ajaxModel = new AjaxResponseModel();
        ajaxModel.setStatus(AjaxResponseModel.FAILURE);
        ajaxModel.setMsg("操作失败：" + infoFailure);
        return ajaxModel;
    }
}
