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

package com.yicheejia.modules.sys.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yicheejia.common.utils.ExcelUtils;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.modules.sys.excel.SysLogBean;
import com.yicheejia.modules.sys.service.SysLogService;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;


/**
 * 系统日志
 * 
 *
 *
 * @date 2017-03-08 10:40:56
 */
@Controller
@RequestMapping("/sys/log")
public class SysLogController {
	@Autowired
	private SysLogService sysLogService;
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	//@RequiresPermissions("sys:log:list")
	public LayuiPage list(@RequestParam Map<String, Object> params){
		LayuiPage page = sysLogService.queryPage(params);

		return page;
	}

	/**
	 * 导出
	 */
	@RequestMapping("/export")
	@RequiresPermissions("sys:log:list")
	public void export(@RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
		LayuiPage page = sysLogService.queryPage(params);

		ExcelUtils.exportExcelToTarget(response, "系统日志", page.getData(), SysLogBean.class);
	}
	
}
