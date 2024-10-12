package com.dshvv.blogserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author 丁少华
 * @since 2022-03-22
 */
@ApiModel(value = "Elog对象", description = "")
public class Elog implements Serializable {

    private static final long serialVersionUID = 1L;

    private String info;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Elog{" +
            "info=" + info +
            ", id=" + id +
        "}";
    }
}
