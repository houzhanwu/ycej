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

package com.yicheejia.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yicheejia.datasources.DataSourceNames;
import com.yicheejia.datasources.annotation.DataSource;
import com.yicheejia.modules.operate.entity.InventoryEntity;
import com.yicheejia.modules.operate.service.InventoryService;
import com.yicheejia.modules.product.entity.ProductInfoEntity;
import com.yicheejia.modules.product.service.ProductInfoService;
import com.yicheejia.modules.sys.entity.SysUserEntity;
import com.yicheejia.modules.sys.service.SysUserService;

/**
 * 测试多数据源
 *
 *
 * @since 3.1.0 2018-01-28
 */
@Service
public class DataSourceTestService {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private ProductInfoService prodectInfoService; 

    public SysUserEntity queryUser(Long userId){
        return sysUserService.selectById(userId);
    }

    @DataSource(name = DataSourceNames.SECOND)
    public SysUserEntity queryUser2(Long userId){
        return sysUserService.selectById(userId);
    }
    
    
	public List<InventoryEntity> getlist(){
		Map<String, Object> map = new HashMap<>();
		map.put("vin", "644444");
		List<InventoryEntity> inventoryList=inventoryService.selectByMap(map);
		return inventoryList;
	}
	public List<ProductInfoEntity> getPeList(){
		 Map<String, Object> columnMap = new HashMap<>();
	        columnMap.put("product_solution_id", "111421");
	        List<ProductInfoEntity> pe=prodectInfoService.selectByMap(columnMap);
		return pe;
		
	}
	
}
