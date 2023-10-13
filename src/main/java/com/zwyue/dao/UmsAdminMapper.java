package com.zwyue.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zwyue.entity.UmsAdmin;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 后台用户表 Mapper 接口
 * </p>
 *
 * @author qcc
 * @since 2022-06-27
 */
@Mapper
public interface UmsAdminMapper extends BaseMapper<UmsAdmin> {

    List<Map<String, Object>> userSeries();

    List<Long> groupUserId(Long adminId);

    List<Long> getSameGroupUserIds(Long id);
}
