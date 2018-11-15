package com.yicheejia.modules.sys.controller;

import com.yicheejia.common.utils.DateUtils;
import com.yicheejia.common.utils.R;
import com.yicheejia.modules.sys.service.ShiroService;
import com.yicheejia.modules.sys.shiro.ShiroUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 首页
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年11月24日 下午11:05:27
 */
@RestController
public class SysIndexController {
	@Autowired
	private ShiroService shiroService;

	@RequestMapping("sys/info")
	public R info(){
		//OperatingSystemMXBean osmx = (OperatingSystemMXBean)ManagementFactory.getOperatingSystemMXBean();

		Map<String, Object> map = new HashMap<>();
		map.put("sysTime", DateUtils.format(new Date(), "yyyy年MM月dd日 HH:mm:ss"));
		map.put("osName", System.getProperty("os.name"));
		map.put("osArch", System.getProperty("os.arch"));
		map.put("osVersion", System.getProperty("os.version"));
		map.put("userLanguage", System.getProperty("user.language"));
		map.put("userDir", System.getProperty("user.dir"));
		//map.put("totalPhysical", osmx.getTotalPhysicalMemorySize()/1024/1024);
		//map.put("freePhysical", osmx.getFreePhysicalMemorySize()/1024/1024);
		//map.put("memoryRate", BigDecimal.valueOf((1-osmx.getFreePhysicalMemorySize()*1.0/osmx.getTotalPhysicalMemorySize())*100).setScale(2, RoundingMode.HALF_UP));
		//map.put("processors", osmx.getAvailableProcessors());
		map.put("jvmName", System.getProperty("java.vm.name"));
		map.put("javaVersion", System.getProperty("java.version"));
		map.put("javaHome", System.getProperty("java.home"));
		map.put("javaTotalMemory", Runtime.getRuntime().totalMemory()/1024/1024);
		map.put("javaFreeMemory", Runtime.getRuntime().freeMemory()/1024/1024);
		map.put("javaMaxMemory", Runtime.getRuntime().maxMemory()/1024/1024);
		map.put("userName", System.getProperty("user.name"));
		//map.put("systemCpuLoad", BigDecimal.valueOf(osmx.getSystemCpuLoad()*100).setScale(2, RoundingMode.HALF_UP));
		map.put("userTimezone", System.getProperty("user.timezone"));

		return R.ok().put("info", map);
	}

	@RequestMapping("sys/permissions")
	public R permissions(){
		Set<String> permissions = shiroService.getUserPermissions(ShiroUtils.getUserId());

		return R.ok().put("permissions", permissions);
	}

}
