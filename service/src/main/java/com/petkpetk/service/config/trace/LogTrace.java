package com.petkpetk.service.config.trace;


public interface LogTrace {

    TraceStatus begin(String message);
    void end(Object result, TraceStatus status);
    void exception(Object result, TraceStatus status, Exception e);
}
