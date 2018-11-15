package com.yicheejia.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 同步角色，放在group中
 * 
 * @author hunk
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SynRole {
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
