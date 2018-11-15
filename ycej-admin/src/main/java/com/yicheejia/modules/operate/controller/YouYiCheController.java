package com.yicheejia.modules.operate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yicheejia.common.utils.R;
import com.yicheejia.modules.operate.entity.YouYiCheEntity;
import com.yicheejia.modules.operate.service.YouYiCheService;

/**
 * 优壹车产品计算器
 *
 * @author lw
 * @email 
 * @date 2018-09-05 10:00:00
 */
@RestController
@RequestMapping("operate/youYiChe")
public class YouYiCheController {
	
	@Autowired
	private YouYiCheService youYiCheService;
	
	@RequestMapping("/controller")
    public R insurance(@RequestBody YouYiCheEntity youYiCheEntity){
    	youYiCheEntity = youYiCheService.calculate(youYiCheEntity);

        return R.ok().put("youYiCheEntity", youYiCheEntity);
    }

}
