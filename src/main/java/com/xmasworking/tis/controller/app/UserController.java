package com.xmasworking.tis.controller.app;

import com.xmasworking.tis.controller.modle.AjaxResponseModel;
import com.xmasworking.tis.entity.AccountEntity;
import com.xmasworking.tis.entity.UserEntity;
import com.xmasworking.tis.entity.UserStatusEntity;
import com.xmasworking.tis.service.UserService;
import com.xmasworking.tis.service.UserStatusService;
import com.xmasworking.tis.util.RoleType;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author XmasPiano
 * @date 2018/10/11 - 下午2:11
 * Created by IntelliJ IDEA.
 */
@Slf4j
@Controller
@RequestMapping("/app/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserStatusService userStatusService;

    @RequestMapping
    @RequiresAuthentication
    public String index(Model model){
        Long accountId;
        //非管理员查询当前用户信息
        AccountEntity accountEntity = (AccountEntity) SecurityUtils.getSubject().getPrincipal();
        accountId = accountEntity.getId();
        UserEntity userEntity = userService.findByAccountId(accountId);
        model.addAttribute("userEntity",userEntity);

        /* 判断学员信息状态：0：已提交（可修改）、1：审核中（锁定）、2：退回（可修改）、3：通过（锁定）、4：归档（锁定） */
        UserStatusEntity userStatusEntity = userStatusService.findByAccountId(accountId);
        model.addAttribute("disabled",userStatusEntity.isLock());

        return "app/user";
    }

    /**
     * 管理员操其他人员信息
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/info")
    @RequiresRoles(value = "manager")
    public String index(@NotNull Long id, Model model){
        UserEntity userEntity = userService.findById(id);
        //无数据
        if(userEntity.getId() == null){
            throw new RuntimeException("无数据信息...");
        }

        model.addAttribute("userEntity",userEntity);
        /* 判断学员信息状态：0：已提交（可修改）、1：审核中（锁定）、2：退回（可修改）、3：通过（锁定）、4：归档（锁定） */
        UserStatusEntity userStatusEntity = userStatusService.findByAccountId(userEntity.getAccountid());
        System.out.println(userStatusEntity);
        //归档资料进行锁定
        model.addAttribute("disabled",userStatusEntity.isEnd());
        //获取状态名称
        model.addAttribute("statusName",userStatusEntity.getStatusName());
        return "app/user";
    }

    @PostMapping("/save")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxResponseModel save(UserEntity userEntity){
        Long accountId = userEntity.getAccountid();
        //管理员查询
        if(SecurityUtils.getSubject().hasRole(RoleType.manager.name())){
            //保存指定用户的信息
            if(accountId == null){
                throw new RuntimeException("用户信息异常，请退出或刷新后重新保存...");
            }
        }else {
            //非管理员保存当前用户信息
            AccountEntity accountEntity = (AccountEntity) SecurityUtils.getSubject().getPrincipal();
            accountId = accountEntity.getId();
            userEntity.setAccountid(accountId);
        }

        //状态查询
        UserStatusEntity userStatusEntity = userStatusService.findByAccountId(accountId);
        System.out.println(userStatusEntity.getStatus());
        System.out.println(userStatusEntity.isLock());
        if(userStatusEntity.getStatus() != null && userStatusEntity.isLock()){
            throw new RuntimeException("当前信息已被锁定，请等待审核...");
        }
        userStatusEntity.setAccountid(accountId);
        userStatusEntity.setStatusType(UserStatusEntity.StatusType._apply);
        userStatusService.save(userStatusEntity);

        userService.save(userEntity);
        return AjaxResponseModel.sendSuccess();
    }

    @PostMapping("/savechange")
    @ResponseBody
    @RequiresRoles(value = "manager")
    public AjaxResponseModel saveChange(UserEntity userEntity){
        Long accountId = userEntity.getAccountid();
        //保存指定用户的信息
        if(accountId == null){
            throw new RuntimeException("用户信息异常，请退出或刷新后重新保存...");
        }
        userService.save(userEntity);
        return AjaxResponseModel.sendSuccess();
    }

    @RequestMapping("/manager")
    public ModelAndView indexManager(){

        ModelAndView modelAndView = new ModelAndView("app/userDataGrid");

        String titleName = "信息管理";
        String dataUrl = "/app/user/getUserInfoData.json";

        String columns =
                "[[{field: 'id', title: 'ID', width: 60, hidden:true}," +
                        "{field: 'accountid', title: '工号'}," +
                        "{field: 'pxjd', title: '培训基地'}," +
                        "{field: 'sbzt', title: '上报状态'}," +
                        "{field: 'shzt', title: '审核状态'}," +
                        "{field: 'shwtgyy', title: '审核未通过原因'}," +
                        "{field: 'name', title: '姓名'}," +
                        "{field: 'sex', title: '性别'}," +
                        "{field: 'birth', title: '出生日期'}," +
                        "{field: 'idtype', title: '身份证件类型'}," +
                        "{field: 'idcardno', title: '身份证件号码'}," +
                        "{field: 'country', title: '国家或地区'}," +
                        "{field: 'nation', title: '民族'}," +
                        "{field: 'tel', title: '手机号'}," +
                        "{field: 'mail', title: '邮箱'}," +
                        "{field: 'qq', title: 'QQ（非必填）'}," +
                        "{field: 'iswj', title: '往届/应届'}," +
                        "{field: 'pxzy', title: '培训专业'}," +
                        "{field: 'istgqgyszgks', title: '是否通过全国医师资格考试'}," +
                        "{field: 'zyyszgzh', title: '执业医师资格证号'}," +
                        "{field: 'zsnd', title: '招收年度'}," +
                        "{field: 'pxkssj', title: '实际培训开始时间（yyyy-mm）'}," +
                        "{field: 'isdkzyjhzyys', title: '是否为对口支援计划住院医师'}," +
                        "{field: 'dkzyjhzyysscdw', title: '对口支援计划住院医师送出单位（请填全称）'}," +
                        "{field: 'pxnxhd', title: '培训年限核定'}," +
                        "{field: 'xylx', title: '学员类型'}," +
                        "{field: 'bk_byxx', title: '毕业学校（本科）'}," +
                        "{field: 'bk_bynf', title: '毕业年份（本科）'}," +
                        "{field: 'bk_byzy', title: '毕业专业（本科）'}," +
                        "{field: 'isqkdddxxy', title: '是否全科订单定向学员'}," +
                        "{field: 'bk_xw', title: '学位（本科）'}," +
                        "{field: 'isssyjs', title: '是否硕士研究生'}," +
                        "{field: 'ss_byyx', title: '毕业院校（硕士）'}," +
                        "{field: 'ss_bynf', title: '毕业年份（硕士）'}," +
                        "{field: 'ss_byzy', title: '毕业专业（硕士）'}," +
                        "{field: 'ss_xw', title: '学位（硕士）'}," +
                        "{field: 'ss_xwlx', title: '学位类型（硕士）'}," +
                        "{field: 'isbsyjs', title: '是否博士研究生'}," +
                        "{field: 'bs_byyx', title: '毕业院校（博士）'}," +
                        "{field: 'bs_bynf', title: '毕业年份（博士）'}," +
                        "{field: 'bs_byzy', title: '毕业专业（博士）'}," +
                        "{field: 'bs_xw', title: '学位（博士）'}," +
                        "{field: 'bs_xwlx', title: '学位类型（博士）'}," +
                        "{field: 'workcompany', title: '工作单位（与单位公章对应的官方全称）'}," +
                        "{field: 'companylevel', title: '医院级别'}," +
                        "{field: 'yxdc', title: '医院等次'}," +
                        "{field: 'ylwsjgtype', title: '医疗卫生机构类别'}," +
                        "{field: 'ylwsjglsgx', title: '医疗卫生机构隶属关系'}," +
                        "{field: 'isxtdwpx', title: '是否在协同单位培训'}," +
                        "{field: 'xtdwname', title: '协同单位（与单位公章对应的官方全称）'}," +
                        "{field: 'xrdwlevel', title: '协同医院级别'}," +
                        "{field: 'xtyydc', title: '协同医院等次'}]]";

        modelAndView.addObject("titleName", titleName);
        modelAndView.addObject("dataUrl", dataUrl);
        modelAndView.addObject("columns", columns);
        return modelAndView;
    }

    @PostMapping("getUserInfoData.json")
    @ResponseBody
    public List<UserEntity> getUserInfoData(){
        return userService.findAll();
    }
}
