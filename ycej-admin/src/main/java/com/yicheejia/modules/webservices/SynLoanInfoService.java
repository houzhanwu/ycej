package com.yicheejia.modules.webservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yicheejia.common.utils.DateUtils;
import com.yicheejia.common.validator.ValidatorUtils;
import com.yicheejia.modules.order.entity.LoanInfoEntity;
import com.yicheejia.modules.order.service.LoanInfoService;
import com.yicheejia.modules.webservices.model.RspVO;

import io.swagger.annotations.Api;

/**
 * 对接租赁系统融资信息
 * 
 * @author hunk
 *
 */
@RestController
@RequestMapping("/webService/loanInfo")
@Api(tags = "租赁系统同步贷款融资明细接口")
public class SynLoanInfoService {
    @Autowired
    private LoanInfoService loanInfoService;
    
    private Logger logger = LoggerFactory.getLogger(getClass());

    @PostMapping("synLoanInfo")
    public RspVO synLoanInfo(@RequestBody LoanInfoEntity loanInfo) {
        ValidatorUtils.validateVO(loanInfo);
        loanInfo.setCreateTime(DateUtils.getDate());
        logger.info("风控融资信息：");
        logger.info(loanInfo.toString());
        loanInfoService.insert(loanInfo);
        return RspVO.ok();
    }
}
