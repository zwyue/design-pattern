package com.zwyue.controller;

import com.zwyue.common.BaseController;
import com.zwyue.entity.Term;
import com.zwyue.service.TermService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * TermController class
 * 学期管理
 *
 * @author zwy
 * @date 2018/11/29 14:46
 */
@Controller
@RequestMapping("term")
public class TermController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(TermController.class);

    @Autowired
    private TermService termService ;

    @RequestMapping(value = "toTermPage",method = RequestMethod.GET)
    public String toTermPage(Model model){
        logger.info("........进入学期管理页面........");
        List<Term> terms = termService.queryTermList();
        model.addAttribute("list",terms);
        return "/term/term_manage" ;
    }

    /**
     * 创建新学期
     *
     * @author zwy
     * @date 2018/11/29 15:56
     */
    @RequestMapping(value = "createNewTerm",method = RequestMethod.POST)
    public String createNewTerm(HttpSession httpSession , Term term){
        logger.info("........创建新学期.......");
        term.setUserid(getLoginUser(httpSession).getId());
        term.setUsername(getLoginUser(httpSession).getTname());
        termService.createNewTerm(term);
        return "/term/term_manage" ;
    }

    @RequestMapping(value = "copyTerm",method = RequestMethod.GET)
    public String copyTerm(HttpSession httpSession,Integer id){
        termService.copyTerm(id,getLoginUser(httpSession));
        return "redirect:/term/toTermPage";
    }

    /**
     * 更新学期
     *
     * @author zwy
     * @date 2018/11/30 14:47
     */
    @RequestMapping(value = "updateTerm",method = RequestMethod.POST)
    public String updateTerm(HttpSession httpSession ,Term term){
        termService.updateTerm(term);
        return "/term/term_manage";
    }

    /**
     * 删除学期
     *
     * @author zwy
     * @date 2018/11/30 14:49
     */
    @RequestMapping(value = "deleteTerm",method = RequestMethod.POST)
    public String delete(HttpSession httpSession,@RequestParam(value = "idList[]") List<Integer> idList){
        termService.deleteTerm(idList);
        return "/term/term_manage";
    }
}
