package com.yicheejia.modules.order.service;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.ApiJson;
import com.yicheejia.modules.order.entity.RepaymentEntity;
import com.yicheejia.modules.webservices.model.QueryRepaymentForm;
import com.yicheejia.modules.webservices.model.RepaymentForm;

public interface RepaymentService extends IService<RepaymentEntity>{
	public ApiJson queryRepayment(QueryRepaymentForm form);
	public ApiJson repayment(RepaymentForm form);
}
