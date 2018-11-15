package com.yicheejia.workflow.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.springframework.stereotype.Service;

import com.yicheejia.workflow.service.SynUserService;

/**
 * activiti 工作流  用户角色与系统同步类
 * @author hunk
 * @date 2018年7月1日
 *
 */
@Service
public class SynUserServiceImpl implements SynUserService{

    @Resource
    private ProcessEngine engine;

    /**
     * 保存用户
     * 
     * @param is
     * @param id
     * @return
     */
    public User saveUser(String id) {
        IdentityService is = engine.getIdentityService();
        List<User> list = is.createUserQuery().userId(id).list();
        if (list.isEmpty()) {
            User u = is.newUser(id);
            u.setPassword(id);
            is.saveUser(u);
            return u;
        }
        return null;
    }
    
    /**
     * 删除用户
     * 
     * @param is
     * @param id
     * @return
     */
    public User deleteUser(String id) {
        IdentityService is = engine.getIdentityService();
        List<User> list = is.createUserQuery().userId(id).list();
        if (!list.isEmpty()) {
            is.deleteUser(id);
            return list.get(0);
        }
        return null;
    }

    /**
     * 保存组
     * 
     * @param is
     * @param id
     * @param name
     * @return
     */
    public Group saveGroup(String id, String name) {
        IdentityService is = engine.getIdentityService();
        List<Group> group = is.createGroupQuery().groupId(id).list();
        if (group.isEmpty()) {
            Group g = is.newGroup(id);
            g.setName(name);
            is.saveGroup(g);
            return g;
        }
        return null;
    }
    /**
     * 绑定用户与组得关系
     * @param is
     * @param u
     * @param g
     */
    public void saveRelation(String userID, String groupID) {
        IdentityService is = engine.getIdentityService();
        is.deleteMembership(userID, groupID);//简单处理
        is.createMembership(userID, groupID);
    }
    /**
     * 删除用户与组得关系
     * @param userID
     * @param groupID
     */
    public void deleteRelation(String userID, String groupID) {
        IdentityService is = engine.getIdentityService();
        is.deleteMembership(userID, groupID);
    }

}
