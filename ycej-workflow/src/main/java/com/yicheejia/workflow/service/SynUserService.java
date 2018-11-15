package com.yicheejia.workflow.service;

import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;

/**
 * 同步用户相关信息
 * @author hunk
 *
 */
public interface SynUserService {
    /**
     * 保存用户
     * 
     * @param is
     * @param id
     * @return
     */
    User saveUser(String id);
    /**
     * 保存组
     * 
     * @param is
     * @param id
     * @param name
     * @return
     */
    Group saveGroup(String id, String name);
    
    /**
     * 绑定用户与组得关系
     * @param is
     * @param u
     * @param g
     */
    void saveRelation(String userID, String groupID);
}
