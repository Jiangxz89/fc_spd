/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.hys.pd.web;

import com.thinkgem.hys.pd.entity.PdEncodingRule;
import com.thinkgem.hys.pd.service.PdEncodingRuleService;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 编码规则表Controller
 * @author zxh
 * @version 2019-04-22
 */
@Controller
@RequestMapping(value = "${adminPath}/pd/pdEncodingRule")
public class PdEncodingRuleController extends BaseController {

    @Autowired
    private PdEncodingRuleService pdEncodingRuleService;

    @ModelAttribute
    public PdEncodingRule get(@RequestParam(required=false) String id) {
        PdEncodingRule entity = null;
        if (StringUtils.isNotBlank(id)){
            entity = pdEncodingRuleService.get(id);
        }
        if (entity == null){
            entity = new PdEncodingRule();
        }
        return entity;
    }

    @RequiresPermissions("pd:pdEncodingRule:view")
    @RequestMapping(value = {"list", ""})
    public String list(PdEncodingRule pdEncodingRule, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<PdEncodingRule> page = pdEncodingRuleService.findPageTwo(new Page<PdEncodingRule>(request, response), pdEncodingRule);
        model.addAttribute("page", page);
        return "hys/pd/pdEncodingRuleList";
    }

    @RequiresPermissions("pd:pdEncodingRule:view")
    @RequestMapping(value = "form")
    public String form(PdEncodingRule pdEncodingRule, Model model,HttpServletRequest request) {
        String flag = request.getParameter("flag");
        model.addAttribute("flag", flag);
        model.addAttribute("pdEncodingRule", pdEncodingRule);
        return "hys/pd/pdEncodingRuleForm";
    }

    //产品添加编码规则页面
    @RequestMapping(value = "getList")
    public String getList(PdEncodingRule pdEncodingRule, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<PdEncodingRule> page = pdEncodingRuleService.findPageTwo(new Page<PdEncodingRule>(request, response), pdEncodingRule);
        model.addAttribute("page", page);
        return "hys/pd/box/pdEncodingRuleBox";
    }

    @RequiresPermissions("pd:pdEncodingRule:view")
    @RequestMapping(value = "toUpdate")
    public String toUpdate(PdEncodingRule pdEncodingRule, Model model,HttpServletRequest request) {
        String flag = request.getParameter("flag");
        pdEncodingRule = pdEncodingRuleService.getpdEncodingRule(pdEncodingRule);
        model.addAttribute("flag", flag);
        model.addAttribute("pdEncodingRule", pdEncodingRule);
        return "hys/pd/pdEncodingRuleForm";
    }

    @RequiresPermissions("pd:pdEncodingRule:edit")
    @RequestMapping(value = "save")
    public String save(PdEncodingRule pdEncodingRule, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request) {
        if (!beanValidator(model, pdEncodingRule)){
            return form(pdEncodingRule, model,request);
        }
        List<PdEncodingRule> pdEncodingRules = pdEncodingRuleService.verify(pdEncodingRule);
        if(pdEncodingRules!=null && pdEncodingRules.size()>0){
            addMessage(redirectAttributes, "保存编码规则失败,编码规则名称已存在");
        }else{
            pdEncodingRuleService.savePdEncodingRule(pdEncodingRule);
            addMessage(redirectAttributes, "保存编码规则成功");
        }
        return "redirect:"+Global.getAdminPath()+"/pd/pdEncodingRule/?repage";
    }

    @RequestMapping(value = "update")
    public String update(PdEncodingRule pdEncodingRule, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request) {
        if (!beanValidator(model, pdEncodingRule)){
            return form(pdEncodingRule, model,request);
        }
        List<PdEncodingRule> pdEncodingRules = pdEncodingRuleService.verify(pdEncodingRule);
        if(pdEncodingRules!=null && pdEncodingRules.size()>0){
            addMessage(redirectAttributes, "保存编码规则失败,编码规则名称已存在");
        }else{
            pdEncodingRuleService.updatePdEncodingRule(pdEncodingRule);
            addMessage(redirectAttributes, "保存编码规则成功");
        }
        return "redirect:"+Global.getAdminPath()+"/pd/pdEncodingRule/?repage";
    }

    @RequiresPermissions("pd:pdEncodingRule:edit")
    @RequestMapping(value = "delete")
    public String delete(PdEncodingRule pdEncodingRule, RedirectAttributes redirectAttributes) {
        pdEncodingRuleService.deletePdEncodingRule(pdEncodingRule);
        addMessage(redirectAttributes, "删除编码规则表成功");
        return "redirect:"+Global.getAdminPath()+"/pd/pdEncodingRule/?repage";
    }

}