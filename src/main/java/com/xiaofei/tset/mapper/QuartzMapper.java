package com.xiaofei.tset.mapper;

import com.xiaofei.tset.entity.QuartzBean;
import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * @author xiaofei
 */
@Mapper
public interface QuartzMapper {

    /**
     * 保存定时任务的信息
     * @param quartzBean
     * @return
     */
    int save(QuartzBean quartzBean);

    int delete(Integer id);

    IPage<QuartzBean> list(Page page, QuartzBean quartzBean);
}
