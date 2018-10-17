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
 * @date 2018/10/11 - 上午10:03
 * Created by IntelliJ IDEA.
 */
@Entity
@Data
@Table(name = "user")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    /**
     * 登陆账户ID
     */
    Long accountid;
    /**
     * 工号，同登陆账户ID
     */
    Long jobNumber;
    /**
     * 培训基地
     */
    String pxjd;
    /**
     * 上报状态
     */
    String sbzt;
    /**
     * 审核状态
     */
    String shzt;
    /**
     * 审核未通过原因
     */
    String shwtgyy;
    /**
     * 姓名
     */
    String name;
    /**
     * 性别
     */
    Long sex;
    /**
     * 出生日期
     */
    String birth;
    /**
     * 身份证件类型
     */
    Long idtype;
    /**
     * 身份证件号码
     */
    String idcardno;
    /**
     * 国家或地区
     */
    Long country;
    /**
     * 民族
     */
    Long nation;
    /**
     * 手机号
     */
    String tel;
    /**
     * 邮箱
     */
    String mail;
    /**
     * QQ
     */
    String qq;
    /**
     * 往届/应届
     */
    Long iswj;
    /**
     * 培训专业
     */
    Long pxzy;
    /**
     * 招收年度
     */
    Long zsnd;
    /**
     * 实际培训开始时间
     */
    String pxkssj;
    /**
     * 培训年限核定
     */
    String pxnxhd;
    /**
     * 最高学历
     */
    Long zgxl;
    /**
     * 毕业学校（专科）
     */
    String zk_byxx;
    /**
     * 毕业年份（专科）
     */
    String zk_bynf;
    /**
     * 毕业专业（专科）
     */
    String zk_byzy;
    /**
     * 毕业学校（本科）
     */
    String bk_byxx;
    /**
     * 毕业年份（本科）
     */
    String bk_bynf;
    /**
     * 毕业专业（本科）
     */
    String bk_byzy;
    /**
     * 学位（本科）
     */
    Long bk_xw;
    /**
     * 是否全科定向学员
     */
    String isqkdddxxy;
    /**
     * 是否硕士研究生
     */
    Long isssyjs;
    /**
     * 毕业院校（硕士）
     */
    String ss_byyx;
    /**
     * 毕业年份（硕士）
     */
    String ss_bynf;
    /**
     * 毕业专业（硕士）
     */
    String ss_byzy;
    /**
     * 学位（硕士）
     */
    Long ss_xw;
    /**
     * 学位类型（硕士）
     */
    Long ss_xwlx;
    /**
     * 是否博士研究生
     */
    Long isbsyjs;
    /**
     * 毕业院校（博士）
     */
    String bs_byyx;
    /**
     * 毕业年份（博士）
     */
    String bs_bynf;
    /**
     * 毕业专业（博士）
     */
    String bs_byzy;
    /**
     * 学位（博士）
     */
    Long bs_xw;
    /**
     * 学位类型（博士）
     */
    Long bs_xwlx;
    /**
     * 人员类型
     */
    Long xylx;
    /**
     * 工作单位
     */
    String workcompany;
    /**
     * 医院级别
     */
    String companylevel;
    /**
     * 医院等次
     */
    String yxdc;
    /**
     * 医疗卫生机构类别
     */
    String ylwsjgtype;
    /**
     * 医疗卫生机构隶属关系
     */
    String ylwsjglsgx;
    /**
     * 是否通过全国医师资格考试
     */
    Long istgqgyszgks;
    /**
     * 执业医师资格证号
     */
    String zyyszgzh;
    /**
     * 是否为对口支援计划住院医师
     */
    Long isdkzyjhzyys;
    /**
     * 对口支援计划住院医师送出单位
     */
    String dkzyjhzyysscdw;
    /**
     * 是否在协同单位培训
     */
    Long isxtdwpx;
    /**
     * 协同单位
     */
    String xtdwname;
    /**
     * 协同医院级别
     */
    String xrdwlevel;
    /**
     * 协同医院等次
     */
    String xtyydc;




}
