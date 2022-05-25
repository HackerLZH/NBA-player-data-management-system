package com.lzh.graduationdesign.service;

import com.lzh.graduationdesign.entity.Player;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author HackerLZH
 * @since 2022-04-04
 */
public interface IPlayerService extends IService<Player> {
    /**
     * 根据球员名获得Player对象
     * @Param [name]
     * @return com.lzh.graduationdesign.entity.Player
     */
    Player findByName(String name);
    List<Player> selectAll();

    /*服务于echart*/
    List<Player> selectTenOrderByScoreDesc();
    List<Integer> selectNumByPlayedTime();
}
