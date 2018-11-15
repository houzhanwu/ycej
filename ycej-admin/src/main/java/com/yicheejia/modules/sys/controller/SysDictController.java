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
import org.springframework.web.bind.annotation.*;

import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.PageUtils;
import com.yicheejia.common.utils.R;
import com.yicheejia.common.validator.ValidatorUtils;
import com.yicheejia.modules.sys.entity.SysDictEntity;
import com.yicheejia.modules.sys.service.SysDictService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 数据字典
 *
 *
 * @since 3.1.0 2018-01-27
 */
@RestController
@RequestMapping("sys/dict")
public class SysDictController {
    @Autowired
    private SysDictService sysDictService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:dict:list")
    public LayuiPage list(@RequestParam Map<String, Object> params){
        LayuiPage page = sysDictService.queryPage(params);

        return page;
    }
    
    @RequestMapping("/queryValue")
    public R queryValue(@RequestParam Map<String, Object> params){
    	List<SysDictEntity> dict = sysDictService.queryValue(params); 

        return R.ok().put("dict",dict);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:dict:info")
    public R info(@PathVariable("id") Long id){
        SysDictEntity dict = sysDictService.selectById(id);

        return R.ok().put("dict", dict);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:dict:save")
    public R save(@RequestBody SysDictEntity dict){
        //校验类型
        ValidatorUtils.validateEntity(dict);

        sysDictService.insert(dict);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:dict:update")
    public R update(@RequestBody SysDictEntity dict){
        //校验类型
        ValidatorUtils.validateEntity(dict);

        sysDictService.updateAllColumnById(dict);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:dict:delete")
    public R delete(@RequestBody Long[] ids){
        sysDictService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 根据type获取code,value集合
     * @param type
     * @return
     */
    @RequestMapping("/stateListByType")
    public R stateListByType(@RequestParam String type){

        List<Map<String,Object>> stateList = sysDictService.queryStates(type);

        return R.ok().put("dicList",stateList);
    }

}
