package com.zwyue.controller;

import com.zwyue.common.BaseController;
import com.zwyue.entity.Identitys;
import com.zwyue.entity.Roster;
import com.zwyue.entity.Teacher;
import com.zwyue.service.IdentityService;
import com.zwyue.service.RosterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 花名册管理
 * @author yangli
 * @date 2018/12/25
 */
@Controller
@RequestMapping("/roster")
public class RosterController extends BaseController {

    @Autowired
    private RosterService rosterService;

    @Autowired
    private IdentityService identityService;

    /**
     * 查询花名册学生列表
     * @author yangli
     * @date 2018/12/26
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(Model model,HttpSession httpSession){
        Teacher teacher = getLoginUser(httpSession);
        String s = teacher.getClassid();
        String[] data = s.split(",");
        List<Roster> list = rosterService.getList(Integer.parseInt(data[0]));
        model.addAttribute("list",list);
        System.out.print("------1-----"+list.size());
        return "";
    }

    /**
     * 根据班级查询学生列表
     * @author yangli
     * @date 2018/12/26
     */
    @RequestMapping(value = "query",method = RequestMethod.GET)
    public String query(Model model,Integer classid){
        List<Roster> list = rosterService.getList(classid);
        model.addAttribute("list",list);
        return "";
    }

    /**
     * 查询学生身份信息列表
     * @author yangli
     * @date 2018/12/26
     */
    @RequestMapping(value = "identity",method = RequestMethod.GET)
    public List<Identitys> identity(){
        List<Identitys> list = identityService.getList();
        return list;
    }

    /**
     * 设置班委会
     * @author yangli
     * @date 2018/12/26
     */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    @ResponseBody
    public String save(Integer classid,Roster roster){
        Roster rosters = rosterService.selectByIsleader(roster.getIsleader(),classid);
        if (roster==null){
            rosterService.updateByPrimaryKey(roster);
        }else{
            rosters.setIsleader("学生");
            rosterService.updateByPrimaryKey(rosters);
            rosterService.updateByPrimaryKey(roster);
        }
        return "";
    }

    /**
     * 查询职务是否已被设置
     * @author yangli
     * @date 2018/12/26
     */
    @RequestMapping(value = "jundge",method = RequestMethod.GET)
    public String jundge(Integer classid,String duties){
        Roster roster = rosterService.selectByIsleader(duties,classid);
        if (roster==null){
            return "";
        }else{
            return "";
        }
    }

}
