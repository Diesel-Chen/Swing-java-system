package com.neuedu.exception;

/**
 * ID号重复异常
 *
 * @author 高军
 * @date 2021-7-8
 */
public class IDRepeatException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public IDRepeatException(String msg) {
        super(msg);
    }
}
