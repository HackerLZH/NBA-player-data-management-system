package com.lzh.graduationdesign.mapper;

import com.lzh.graduationdesign.entity.Player;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author HackerLZH
 * @since 2022-04-04
 */
public interface PlayerMapper extends BaseMapper<Player> {
    @Select("select * from `player` where name = #{name}")
    Player findByName(String name);

    @Select("select * from `player`")
    List<Player> selectAll();

    /*服务于echart*/

    //查询得分前十的球员
    @Select("select * from `player` order by `score` desc limit 10")
    List<Player> selectTenOrderByScoreDesc();
    //查询打球时间分别在0~10, 11~15, 16~20, 20+的人数
    @Select("select count(*) as total from (\n" +
            "select played_time,\n" +
            "case\n" +
            "when played_time < 10 then '10-'\n" +
            "when played_time between 10 and 15 then '10-15'\n" +
            "when played_time between 16 and 20 then '16-20'\n" +
            "when played_time > 20 then '20+'\n" +
            "end as played_time_temp\n" +
            "from player\n" +
            ")t_player group by played_time_temp;")
    List<Integer> selectNumByPlayedTime();
}
