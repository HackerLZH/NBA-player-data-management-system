package com.lzh.graduationdesign.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @ClassName Comment
 * @Author HackerLZH
 * @Date 2022/4/17 22:42
 * @Description
 */
@TableName("comment")
@ApiModel(value = "Comment对象", description = "")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("留言id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty("用户id")
    private Integer userId;
    @ApiModelProperty("留言时间")
    private Timestamp commentTime;
    @ApiModelProperty("留言内容")
    private String text;
}
