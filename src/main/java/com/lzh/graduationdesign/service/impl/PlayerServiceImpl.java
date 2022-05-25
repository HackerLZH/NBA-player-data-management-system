package com.lzh.graduationdesign.service.impl;

import com.lzh.graduationdesign.entity.Player;
import com.lzh.graduationdesign.entity.User;
import com.lzh.graduationdesign.mapper.PlayerMapper;
import com.lzh.graduationdesign.service.IPlayerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author HackerLZH
 * @since 2022-04-04
 */
@Service
public class PlayerServiceImpl extends ServiceImpl<PlayerMapper, Player> implements IPlayerService {
    @Autowired
    private PlayerMapper playerMapper;

    @Override
    public Player findByName(String name){
        return playerMapper.findByName(name);
    }

    @Override
    public List<Player> selectAll() {
        return playerMapper.selectAll();
    }

    @Override
    public List<Player> selectTenOrderByScoreDesc(){
        return playerMapper.selectTenOrderByScoreDesc();
    }

    @Override
    public List<Integer> selectNumByPlayedTime() {
        return playerMapper.selectNumByPlayedTime();
    }
}
