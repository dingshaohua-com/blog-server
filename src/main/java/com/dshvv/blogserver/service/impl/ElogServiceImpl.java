package com.dshvv.blogserver.service.impl;

import com.dshvv.blogserver.entity.Elog;
import com.dshvv.blogserver.mapper.ElogMapper;
import com.dshvv.blogserver.service.IElogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 丁少华
 * @since 2022-03-22
 */
@Service
public class ElogServiceImpl extends ServiceImpl<ElogMapper, Elog> implements IElogService {

}
