package com.zwyue.common;

import com.zwyue.entity.Power;
import com.zwyue.entity.Teacher;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 * BaseController class
 *
 * @author zwy
 * @date 2018/11/22 13:46
 */
public class BaseController {

    /**
     * 缓存用户
     *
     * @date 2018/11/23 13:17
     */
    public static final String CACHE_USER = "teacher" ;

    /**
     *  缓存菜单
     *
     * @date 2019/1/2 13:22
     */
    public static final String CACHE_MENU = "menu" ;

    /**
     * 获取session用户
     *
     * @author zwy
     * @date 2018/11/29 15:59
     */
    protected Teacher getLoginUser(HttpSession httpSession){
        return (Teacher) httpSession.getAttribute(CACHE_USER);
    }

    protected List<Power> getMenuPower (HttpSession httpSession){
        return (List<Power>) httpSession.getAttribute(CACHE_MENU);
    }
}
