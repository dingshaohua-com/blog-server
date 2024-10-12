package com.dshvv.blogserver.controller;

import com.dshvv.blogserver.entity.Type;
import com.dshvv.blogserver.service.ITypeService;
import com.dshvv.blogserver.utils.JsonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 丁少华
 * @since 2022-01-18
 */
@RestController
@RequestMapping("/type")
public class TypeController {
    @Autowired
    private ITypeService typeService;

    @ApiOperation("类型列表")
    @GetMapping
    private JsonResult<List<Type>> getList(){
        List<Type> types = typeService.list();
        return JsonResult.success(types);
    }

    @ApiOperation("类型详情")
    @GetMapping("/{id}")
    private JsonResult<Type> getById(@PathVariable int id){
        Type type = typeService.getById(id);
        return JsonResult.success(type);
    }

    @ApiOperation("新增类型")
    @PostMapping
    private JsonResult<Boolean> save(@RequestBody Type type){
        boolean result = typeService.save(type);
        return JsonResult.success(result);
    }

    @ApiOperation("更新类型")
    @PutMapping
    private JsonResult<Boolean> updateById(@RequestBody Type type){
        boolean result = typeService.updateById(type);
        return JsonResult.success(result);
    }

    @ApiOperation("删除类型")
    @DeleteMapping("/{id}")
    private JsonResult<Boolean> removeById(@PathVariable int id){
        boolean result = typeService.removeById(id);
        return JsonResult.success(result);
    }
}
