package com.yicheejia.workflow.service;

import java.util.Map;
/**
 * 
 * @author hunk
 *
 */
public interface ActivitiBusinessService {
    
    /**
     * 通过表单ID执行任务
     * @param formid
     * @param userName
     * @param vars
     * @return
     */
    public Object executeTaskByID( String processKey,String formid, String userName, Map vars);
    

}
