package com.xmasworking.tis.controller.app;

import com.xmasworking.tis.controller.modle.AjaxResponseModel;
import com.xmasworking.tis.entity.SelectElementDetailEntity;
import com.xmasworking.tis.entity.SelectElementMainEntity;
import com.xmasworking.tis.service.SelectElementDetailService;
import com.xmasworking.tis.service.SelectElementMainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author XmasPiano
 * @date 2018/10/12 - 下午5:23
 * Created by IntelliJ IDEA.
 */
@Slf4j
@Controller
@RequestMapping("/app/selectelement")
public class SelectElementController {
    @Autowired
    SelectElementDetailService DetailService;
    @Autowired
    SelectElementMainService MainService;

    @RequestMapping
    public String index(){
        return "app/selectelement";
    }

    @PostMapping("/get/detail.json")
    @ResponseBody
    public List<SelectElementDetailEntity> getDetailById(Long id){
        return DetailService.findAllByMainId(id);
    }


    @PostMapping("/main.json")
    @ResponseBody
    public List<SelectElementMainEntity> searchAllSelect(){
        return MainService.findAll();
    }

    @PostMapping("/detail.json")
    @ResponseBody
    public List<SelectElementDetailEntity> searchDetailByMainId(Long id){
        if(id == null){
            return new ArrayList<>();
        }
        return DetailService.findAllByMainId(id);
    }

    @RequestMapping("/main/add")
    public String mainAdd(Model model){
        model.addAttribute("mainObject", new SelectElementMainEntity());
        return "app/selectMainSave";
    }

    @RequestMapping("/main/edit")
    public String mainEdit(Long id,Model model){
        SelectElementMainEntity mainEntity = MainService.findById(id);
        model.addAttribute("mainObject",mainEntity);
        return "app/selectMainSave";
    }

    @PostMapping("/main/save")
    @ResponseBody
    public AjaxResponseModel mainSave(SelectElementMainEntity mainEntity){
        MainService.save(mainEntity);
        return AjaxResponseModel.sendSuccess();
    }

    @PostMapping("/main/del")
    @ResponseBody
    public AjaxResponseModel mainDel(Long id){
        MainService.delete(id);
        return AjaxResponseModel.sendSuccess();
    }

    @RequestMapping("/detail/add")
    public String detailAdd(Long MainId,Model model){
        SelectElementDetailEntity detailEntity = new SelectElementDetailEntity();
        SelectElementMainEntity mainEntity = MainService.findById(MainId);

        detailEntity.setMainid(MainId);
        model.addAttribute("detailObject", detailEntity);
        model.addAttribute("mainName", mainEntity.getSelectname());
        return "app/selectDetailSave";
    }

    @RequestMapping("/detail/edit")
    public String detailEdit(Long id,Model model){
        SelectElementDetailEntity detailEntity = DetailService.findById(id);
        SelectElementMainEntity mainEntity = MainService.findById(detailEntity.getMainid());

        model.addAttribute("detailObject",detailEntity);
        model.addAttribute("mainName", mainEntity.getSelectname());
        return "app/selectDetailSave";
    }

    @RequestMapping("/detail/save")
    @ResponseBody
    public AjaxResponseModel detailSave(SelectElementDetailEntity detailEntity){
        DetailService.save(detailEntity);
        return AjaxResponseModel.sendSuccess();
    }

    @RequestMapping("/detail/del")
    @ResponseBody
    public AjaxResponseModel detailDel(Long id){
        DetailService.delete(id);
        return AjaxResponseModel.sendSuccess();
    }
}
