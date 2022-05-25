package com.lzh.graduationdesign.entity;

import com.sun.org.apache.bcel.internal.classfile.Code;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName PlayerPage
 * @Author HackerLZH
 * @Date 2022/4/10 22:56
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerPage<T> {
    private Integer code;
    private String msg;
    private Long count;
    private List<T> data;

    /**
     * 只有总条数和分页数据的构造方法
     *
     * @param count 总条数
     * @param data  分页数据
     */
    public PlayerPage(Long count, List<T> data) {
        this.code = 0;
        this.msg = "";
        this.count = count;
        this.data = data;
    }
}
