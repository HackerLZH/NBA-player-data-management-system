package com.lzh.graduationdesign.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 
 * </p>
 *
 * @author HackerLZH
 * @since 2022-04-04
 */
@TableName("player")
@ApiModel(value = "Player对象", description = "")
@Data
@NoArgsConstructor
public class Player implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("球员id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("年龄")
    private Integer age;

    public Player(String name, Integer age, LocalDate birthday, String birthPlace, String team, Integer enterTime, Integer playedTime, String retired, Integer score, Integer rebound, Integer assist, Integer steal, Integer block) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
        this.birthPlace = birthPlace;
        this.team = team;
        this.enterTime = enterTime;
        this.playedTime = playedTime;
        this.retired = retired;
        this.score = score;
        this.rebound = rebound;
        this.assist = assist;
        this.steal = steal;
        this.block = block;
    }

    @ApiModelProperty("生日")
    private LocalDate birthday;

    @ApiModelProperty("出生地")
    private String birthPlace;

    @ApiModelProperty("效力球队")
    private String team;

    @ApiModelProperty("选秀时间")
    private Integer enterTime;

    @ApiModelProperty("已打NBA时间")
    private Integer playedTime;

    @ApiModelProperty("是否退役")
    private String retired;

    @ApiModelProperty("得分")
    private Integer score;

    @ApiModelProperty("篮板")
    private Integer rebound;

    @ApiModelProperty("助攻")
    private Integer assist;

    @ApiModelProperty("抢断")
    private Integer steal;

    @ApiModelProperty("盖帽")
    private Integer block;
}
