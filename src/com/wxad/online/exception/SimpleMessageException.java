package com.wxad.online.exception;

/**
 * simple message exception
 * @author <a href="mailto:xuzhenqin@gmail.com">Calvin Pang</a>
 */
public class SimpleMessageException extends RuntimeException{
    public SimpleMessageException(String message) {
        super(message);
    }
}
