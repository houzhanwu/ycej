package com.yicheejia.modules.webservices;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yicheejia.common.utils.ApiJson;
import com.yicheejia.common.utils.JsonUtil;
import com.yicheejia.common.validator.ValidatorUtils;
import com.yicheejia.modules.order.service.RepaymentService;
import com.yicheejia.modules.order.service.YcejOrderService;
import com.yicheejia.modules.webservices.model.QueryRepaymentForm;
import com.yicheejia.modules.webservices.model.RepaymentForm;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * 银盛POS支付接口
 * @author fkm
 * 2018-06-25
 */
@RestController
@RequestMapping("/pay")
@Api(tags="POS支付接口")
public class RepaymentController {
	@Autowired
    private RepaymentService repaymentService;
	//日志
    protected Logger logger = LoggerFactory.getLogger(RepaymentController.class);
	@PostMapping("queryRepayment")
    @ApiOperation("支付查询")
    public Map QueryRepayment(HttpServletResponse response,@RequestBody QueryRepaymentForm form){
		logger.info("---------支付查询交易开始------------");
        //表单校验
        ValidatorUtils.validateEntity(form);
        ApiJson j = new ApiJson();
        //支付查询
        Map<String, Object> map = new HashMap<String, Object>();
        j = repaymentService.queryRepayment(form);
        if(j.getSuccess()){
        	map.put("resp_code", "000000");
        	map.put("resp_msg", "查询成功");
        	map.put("sign_type","MD5");
            String signature = JsonUtil.createSignature(j.getObject(),map);
            map.put("signature", signature);
        	map.put("data", j.getObject());
        	logger.info("---------支付查询，查询成功------------");
        }else{
        	map.put("resp_code", "101");
        	map.put("resp_msg", j.getMsg());
        	map.put("data", "");
        	logger.info("---------支付查询"+j.getMsg()+"------------");
        }
        return map;
    }
 	
	@PostMapping("repayment")
    @ApiOperation("支付")
    public Map Repayment(HttpServletResponse response,@RequestBody RepaymentForm form){
		logger.info("---------支付确认,交易开始------------");
        //表单校验
        ValidatorUtils.validateEntity(form);
        ApiJson j = new ApiJson();
        //支付
        j = repaymentService.repayment(form);
        Map<String, Object> map = new HashMap();
        if(j.getSuccess()){
        	logger.info("----------------入账成功,交易结束-----------------");
        	map.put("resp_code", "000000");
        	map.put("resp_msg", "入账成功");
        	String signature = JsonUtil.createSignature(j.getObject(),map);
            map.put("signature", signature);
        	map.put("data", j.getObject());
        	logger.info("--------入账成功,交易结束--------------");
        }else{
        	map.put("resp_code", "101");
        	map.put("resp_msg", j.getMsg());
        	map.put("data", "");
        	logger.info("--------交易错误"+j.getMsg()+"--------------");
        }
        //JsonUtil.writeResponse(response, map);
        return map;
    }
}
