package com.xmasworking.tis.controller.app;

import com.xmasworking.tis.controller.modle.AjaxResponseModel;
import com.xmasworking.tis.entity.UserStatusEntity;
import com.xmasworking.tis.service.UserService;
import com.xmasworking.tis.service.UserStatusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by IntelliJ IDEA.
 *
 * @author XmasPiano
 * @date 2018/10/11 - 下午2:11
 * Created by IntelliJ IDEA.
 */
@Slf4j
@Controller
@RequestMapping("/app/userstatus")
public class UserStatusController {
    @Autowired
    UserStatusService userStatusService;

    @RequestMapping("/change")
    @ResponseBody
    public AjaxResponseModel changeStatus(Long accountId,Integer status){
        UserStatusEntity userStatusEntity = userStatusService.findByAccountId(accountId);
        if(userStatusEntity.getId() == null){
            throw new RuntimeException("无审批数据，请核对...");
        }
        userStatusEntity.setStatus(status);
        userStatusService.save(userStatusEntity);
        return AjaxResponseModel.sendSuccess();
    }
}
