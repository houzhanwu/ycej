package com.yicheejia.modules.sys.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yicheejia.modules.sys.entity.CodeConfigEntity;
import com.yicheejia.modules.sys.service.CodeConfigService;
import com.yicheejia.workflow.service.impl.ActivitiBaseServiceImpl;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.R;


/**
 * 
 *
 * @author hunk
 * @email 
 * @date 2018-07-30 15:48:51
 */
@RestController
@RequestMapping("sys/codeconfig")
public class CodeConfigController {
    @Autowired
    private CodeConfigService codeConfigService;
    protected Logger logger = LoggerFactory.getLogger(CodeConfigController.class);
    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:codeconfig:list")
    public LayuiPage list(@RequestParam Map<String, Object> params){
        LayuiPage page = codeConfigService.queryPage(params);

        return page;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{codeConfigId}")
    @RequiresPermissions("sys:codeconfig:info")
    public R info(@PathVariable("codeConfigId") String codeConfigId){
			CodeConfigEntity codeConfig = codeConfigService.selectById(codeConfigId);

        return R.ok().put("codeConfig", codeConfig);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:codeconfig:save")
    public R save(@RequestBody CodeConfigEntity codeConfig) {
        if (codeConfig.getSerialValue() == null) {
            //codeConfig.setSerialValue(0);
        }
        try {
            codeConfigService.insert(codeConfig);
        } catch (Exception e) {
            logger.error("--------:", e);
        }
        logger.error("---x----");
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:codeconfig:update")
    public R update(@RequestBody CodeConfigEntity codeConfig){
			codeConfigService.updateById(codeConfig);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:codeconfig:delete")
    public R delete(@RequestBody String[] codeConfigIds){
			codeConfigService.deleteBatchIds(Arrays.asList(codeConfigIds));

        return R.ok();
    }


}
