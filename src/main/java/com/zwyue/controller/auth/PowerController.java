package com.zwyue.controller.auth;

import com.github.pagehelper.PageInfo;
import com.zwyue.common.BaseController;
import com.zwyue.common.ResultUtils;
import com.zwyue.entity.Power;
import com.zwyue.service.PowerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import java.util.List;
import java.util.Map;

/**
 * copyright    <a href="http://www.qaqavr.com"/>中锐</a>
 * <pre>
 *     @author     zwy
 *     @date       2018/12/1 15:27
 *     email       1092478224@qq.com
 *     desc         权限管理
 * </pre>
 */
@Controller
@RequestMapping("/power")
public class PowerController extends BaseController {

    private final static Logger logger = LoggerFactory.getLogger(PowerController.class);

    @Autowired
    private PowerService powerService ;

    /**
     * 跳转权限管理页面
     *
     * @author zwy
     * @date 2018/12/6 14:24
     */
    @RequestMapping(value = "toPowerPage",method = RequestMethod.GET)
    public String toPowerPage(Model model, HttpSession httpSession){
        List<Power> powers = powerService.queryPowerList();
        model.addAttribute("page",new PageInfo<Power>(powers));
        return "auth/power/power_manage";
    }

    /**
     * 创建新权限
     *
     * @author zwy
     * @date 2018/12/5 15:33
     */
    @RequestMapping(value = "createNewPower",method = RequestMethod.POST)
    public String createNewPower(Power power){
        logger.info("-------||----创建权限------------");
        powerService.createNewPower(power) ;
        return "redirect:/power/toPowerPage";
    }

    /**
     * 分配权限
     *
     * @author zwy
     * @date 2018/12/5 15:33
     */
    @RequestMapping(value = "allocatePower",method = RequestMethod.POST)
    public String allocatePower(Integer roleId,Integer powerId){
        powerService.allocatePower(roleId,powerId);
        return "redirect:../role/toRolePage";
    }

    /**
     * 更新权限信息
     *
     * @author zwy
     * @date 2018/12/11 18:30
     */
    @RequestMapping(value = "updatePower",method = RequestMethod.POST)
    public String updatePower(Power power){
        powerService.updatePower(power);
        return "redirect:../role/toRolePage";
    }

    @RequestMapping("/delete")
    @DELETE
    public String deletePower(@RequestParam(value = "idList[]") List<Integer> idList){
        powerService.deletePowerByIds(idList);
        return "redirect:../role/toRolePage";
    }

    /**
     * 获取权限菜单
     *
     * @author zwy
     * @date 2019/1/2 13:27
     */
    @RequestMapping("/query-menu")
    @GET
    @ResponseBody
    public Map queryMenu(HttpSession httpSession){
        logger.info(".....登陆后请求菜单");
        return ResultUtils.success(getMenuPower(httpSession));
    }
}
