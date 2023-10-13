package com.zwyue.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 后台用户表
 * </p>
 *
 * @author qcc
 * @since 2022-06-27
 */
@Data
@Accessors(chain = true)
@TableName("ums_admin")
@NoArgsConstructor
public class UmsAdmin implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(condition = SqlCondition.LIKE)
    private String username;

    private String password;

    private String ssoPassword;

    private String icon;

    private String email;

    private String phone;

    @TableField(condition = SqlCondition.LIKE)
    private String nickName;

    private String realName;

    private String note;

    private Date createTime;

    private Date loginTime;

    private Integer status;

    private String type;

    private String loginType;

    private String unionid;

    @TableField(exist = false)
    private List<Long> roleIds;
}
