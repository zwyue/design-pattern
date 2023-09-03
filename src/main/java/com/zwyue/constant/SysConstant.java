package com.zwyue.constant;

/**
 * SysConstant class 系统静态常量
 *
 * @author zwy
 * @date 2018/11/22 13:06
 */
public class SysConstant {

    public static final String ZERO = "0";

    /**
     * 登陆地址jsp
     */
    public static final String LOGIN_URI = "/index/login" ;

    /**
     * 缓存用户名
     */
    public static final String CACHE_USERNAME = "userName" ;

    /**
     * 缓存用户
     *
     * @date 2018/11/23 13:17
     */
    public static final String CACHE_USER = "teacher" ;

    /**
     *缓存角色
     *
     * @date 2018/11/23 13:17
     */
    public static final String CACHE_ROLE = "roles";

    /**
     * 缓存管理员
     *
     * @date 2018/12/1 12:16
     */
    public static final String CACHE_ADMIN = "is_admin" ;

    /**
     * 登陆权限
     *
     * @date 2018/12/3 10:28
     */
    public static final String LOGIN_POWER = "登陆权限" ;

    /**
     * 缓存权限
     *
     * @date 2018/11/23 13:39
     */
    public static final String CACHE_PERMISSION = "permissions" ;

    /**
     *  缓存菜单
     *
     * @date 2019/1/2 13:22
     */
    public static final String CACHE_MENU = "menu" ;

    /**
     * 初始化密码
     *
     * @date 2018/12/3 10:35
     */
    public static final String INIT_PASSWORD = "123456" ;

    /**
     * 如果没有明确的map大小设置，则使用默认的16
     *
     * @date 2018/12/25 10:06
     */
    public static final Integer MAP_DEFAULT_SIZE = 16 ;

    /**
     * 管理员用户名与密码
     *
     * @author zwy
     * @date 2018/11/22 18:07
     */
    public interface Admin{

        Boolean IS_ADMIN = true ;

        Boolean IS_NOT_ADMIN = false ;

        String NAME = "admin" ;

        String PASS = "123456" ;

        String SALT = "salt" ;
    }

    /**
     * 教师是否可用状态
     *
     * @author zwy
     * @date 2018/11/28 13:27
     */
    public interface TeacherStatus{

        /**
         * 启用
         */
        String ACTIVE = "0" ;

        /**
         * 停用
         */
        String FORBIDDEN = "1" ;
    }

    /**
     * 学期是否可用
     *
     * @author zwy
     * @date 2018/11/29 17:03
     */
    public interface IsEnable {

        /**
         *  可用
         */
        String ENABLE = "0" ;

        /**
         *  不可用
         */
        String DISABLED = "1";
    }

    /**
     * 系统标点符号
     *
     * @author zwy
     * @date 2018/12/1 10:18
     */
    public interface Punctuation{

        String COMMA = ",";

        String SHORT_LINE = "-";
    }

    /**
     * 权限类型
     *
     * @author zwy
     * @date 2018/12/27 15:48
     */
    public interface PowerType {

        /**
         *  菜单
         *
         * @date 2018/12/27 15:50
         */
        String MENU = "0" ;

        /**
         *  按钮
         *
         * @date 2018/12/27 15:51
         */
        String BUTTON = "1" ;

    }

    /**
     * excel sheet title
     *
     * @author zwy
     * @date 2019/1/10 16:43
     */
    public interface SheetTitle {

        public String TIME = "时间" ;

        public String CLASS_ROOM = "教室" ;

        String SERIAL_NUMBER = "序号" ;

        String CLAZZ = "班级" ;

        String TEACHER = "教师" ;

        String MONITOR = "班长" ;

        String STUDY_COMMITTEE = "学习委员" ;

        String SAFE_COMMITTEE = "安全委员" ;

        String CONTACT = "联系方式";

        String REMARKS = "备注" ;

        String STUDENT_ID = "学号" ;

        String NAME = "姓名" ;

        String SEX = "性别" ;

        String AGE = "年龄" ;

        String PHONE = "电话" ;

        String FAM_PHONE = "家属电话" ;

    }
}
