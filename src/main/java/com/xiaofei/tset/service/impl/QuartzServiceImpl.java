package com.xiaofei.tset.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaofei.tset.entity.QuartzBean;
import com.xiaofei.tset.mapper.QuartzMapper;
import com.xiaofei.tset.service.IQuartzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiaofei
 */
@Service
public class QuartzServiceImpl implements IQuartzService {


    @Autowired
    private QuartzMapper quartzMapper;

    @Override
    public boolean save(QuartzBean quartzBean) {

        int save = quartzMapper.save(quartzBean);
        if (save > 0) {
            return true;
        }
        return false;
    }

    @Override
    public int delete(Integer id) {
        int delete = quartzMapper.delete(id);
        return delete;
    }

    @Override
    public IPage<QuartzBean> list(Page page, QuartzBean quartzBean) {
        IPage<QuartzBean> list = quartzMapper.list(page, quartzBean);
        return list;
    }
}
