package com.yicheejia.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * 同步用户
 * 
 * @author hunk
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SynUser {
    /**
     * 动作: 新增，修改，删除
     *
     */
    public enum Action { ADD,UPDATE,DELETE};
    
    /**
     * 默认新增
     * @return
     */
    Action value () default Action.ADD;}
