/**
 *
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.yicheejia.common.aspect;


import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yicheejia.common.annotation.DataFilter;
import com.yicheejia.common.exception.RRException;
import com.yicheejia.common.utils.Constant;
import com.yicheejia.modules.sys.entity.SysUserEntity;
import com.yicheejia.modules.sys.service.SysDeptService;
import com.yicheejia.modules.sys.service.SysRoleDeptService;
import com.yicheejia.modules.sys.service.SysUserRoleService;
import com.yicheejia.modules.sys.shiro.ShiroUtils;

import java.util.*;

/**
 * 数据过滤，切面处理类
 *
 *
 * @since 3.0.0 2017-09-17
 */
@Aspect
@Component
public class SaveOptInfAspect {
    @Autowired
    private SysDeptService sysDeptService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysRoleDeptService sysRoleDeptService;

    @Pointcut("@annotation(com.yicheejia.common.annotation.SaveOptInf)")
    public void saveOptInfCut() {

    }

    @Before("saveOptInfCut()")
    public void dataFilter(JoinPoint point) throws Throwable {
        Object[] params = point.getArgs();
        if(params != null && params.length > 0){
            Object argument = params[0];
            BeanWrapper beanWrapper = new BeanWrapperImpl(argument);
        // 设置创建时间和修改时间
        if (beanWrapper.isWritableProperty("")) {
            beanWrapper.setPropertyValue("", new Date());
        }
        if (beanWrapper.isWritableProperty("")) {
            beanWrapper.setPropertyValue("", new Date());
        }
        // 设置创建人和修改人
//        if (beanWrapper.isWritableProperty("") && privilegeInfo != null
//            && StringUtils.isNotBlank(privilegeInfo.getWorkNo())) {
//            beanWrapper.setPropertyValue(CREATOR, privilegeInfo.getWorkNo());
//        }
//        if (beanWrapper.isWritableProperty(MODIFIER) && privilegeInfo != null
//            && StringUtils.isNotBlank(privilegeInfo.getWorkNo())) {
//            beanWrapper.setPropertyValue(MODIFIER, privilegeInfo.getWorkNo());
//        }
    }

        throw new RRException("数据权限接口，只能是Map类型参数，且不能为NULL");
    }

}
