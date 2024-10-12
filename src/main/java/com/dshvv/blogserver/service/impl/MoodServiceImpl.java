package com.dshvv.blogserver.service.impl;

import com.dshvv.blogserver.entity.Mood;
import com.dshvv.blogserver.mapper.MoodMapper;
import com.dshvv.blogserver.service.IMoodService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 丁少华
 * @since 2022-01-18
 */
@Service
public class MoodServiceImpl extends ServiceImpl<MoodMapper, Mood> implements IMoodService {

}
