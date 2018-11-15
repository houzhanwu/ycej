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

package com.yicheejia.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.Query;
import com.yicheejia.modules.sys.dao.SysLogDao;
import com.yicheejia.modules.sys.entity.SysLogEntity;
import com.yicheejia.modules.sys.service.SysLogService;
import com.yicheejia.modules.sys.service.SysUserService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service("sysLogService")
public class SysLogServiceImpl extends ServiceImpl<SysLogDao, SysLogEntity> implements SysLogService {
	@Autowired
	private SysUserService sysUserservice;
    @Override
    public LayuiPage queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");
        EntityWrapper<SysLogEntity> wrapper = new EntityWrapper<>();
        wrapper.like(StringUtils.isNotBlank(key),"username", key);
        /**订单操作日志新增   add  by 冯坤明  20180716  begin*/
        String id = (String)params.get("id");
        if (StringUtils.isNotBlank(id)) {
            wrapper.eq("source_bill_id", id);
        }
        /**订单操作日志新增   add  by 冯坤明  20180716  end*/
        Page<SysLogEntity> page = this.selectPage(
            new Query<SysLogEntity>(params).getPage(),
            wrapper.orderBy("create_date",false)
        );
        List<String> userList = new ArrayList<String>();
        for (SysLogEntity syslog : page.getRecords()) {
            userList.add(syslog.getUsername());
        }
        Map<String,String> map = sysUserservice.getUserName(userList);
        for (SysLogEntity syslog : page.getRecords()) {
            if (map.containsKey(syslog.getUsername())) {
            	syslog.setUsername(map.get(syslog.getUsername()));
            }
        }
    
        return new LayuiPage(page.getRecords(), page.getTotal());
    }
    
}
