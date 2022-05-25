package com.lzh.graduationdesign.exception;

import com.lzh.graduationdesign.entity.CodeMsg;

/**
 * 统一异常
 */
public class GlobalException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private CodeMsg cm;
    public GlobalException(CodeMsg cm) {
        super(cm.toString());
        this.cm = cm;
    }
    public CodeMsg getCm() {
        return cm;
    }
}