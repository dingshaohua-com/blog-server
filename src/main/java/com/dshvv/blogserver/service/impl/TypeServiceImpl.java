package com.dshvv.blogserver.service.impl;

import com.dshvv.blogserver.entity.Type;
import com.dshvv.blogserver.mapper.TypeMapper;
import com.dshvv.blogserver.service.ITypeService;
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
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements ITypeService {

}
