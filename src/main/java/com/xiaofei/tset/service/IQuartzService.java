package com.xiaofei.tset.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaofei.tset.entity.QuartzBean;



public interface IQuartzService {

     boolean save(QuartzBean quartzBean);

     int delete(Integer id);

     IPage<QuartzBean> list(Page page, QuartzBean quartzBean);
}
