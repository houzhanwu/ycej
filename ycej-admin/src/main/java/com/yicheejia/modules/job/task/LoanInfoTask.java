package com.yicheejia.modules.job.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yicheejia.modules.order.entity.LoanInfoEntity;
import com.yicheejia.modules.order.entity.YcejOrderDetailEntity;
import com.yicheejia.modules.order.entity.YcejOrderEntity;
import com.yicheejia.common.exception.RRException;
import com.yicheejia.modules.order.service.LoanInfoService;
import com.yicheejia.modules.order.service.YcejOrderService;

/**
 *
 *查询融资风控信息表，根据最新时间的风控信息更新订单状态
 *仅限与定金已支付状态下的订单更改到下一步资源待匹配
 *2018-07-27
 */
@Component("loanInfoTask")
public class LoanInfoTask {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
    private LoanInfoService loanInfoService;
	@Autowired
    private YcejOrderService ycejOrderService;
	
	//秒 	分 	时 	日 	月 	星期（7为周六） 	年（可选）
	@Scheduled(cron = "0 0/2 * * * ?")//一小时执行一次
	//@Scheduled(cron = "0/60 * * * * ?")
	@Transactional
	public void loanInfoTask(){
		logger.info("loanInfoTask正在被执行");
		try {
			List<LoanInfoEntity> loanInfolist = loanInfoService.queryLoaninfo();
			List<LoanInfoEntity> infolist = new ArrayList<LoanInfoEntity>();
			List<YcejOrderEntity> orderlist = new ArrayList<YcejOrderEntity>();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("status", "05");//定金已支付状态下 修改为资源匹配环节
			List<YcejOrderEntity> order = ycejOrderService.selectByMap(params);
			boolean flag = true;
			Map<String,String> map = new HashMap<String,String>();
			if(order.size() > 0){
				for(int i = 0; i < order.size();i++){
					String orderIdNo = order.get(i).getIdNo();//身份证号
					String name = order.get(i).getCustomerName();//姓名
					String orderId = order.get(i).getId()+"";
					if(loanInfolist.size()>0){
						for(LoanInfoEntity info :loanInfolist){
							LoanInfoEntity loanInfo = new LoanInfoEntity();
							YcejOrderEntity ycejorder = new YcejOrderEntity();
							String idNo = info.getIdNo();//身份证号
							String customerName = info.getCustomerName();
							String loanInfoId = info.getLoanInfoId();
							Integer risk = info.getRiskMngRst();
							if(risk != null ){//风控匹配完毕  资源取消与通过
								if(idNo.equals(orderIdNo) && customerName.equals(name)){
									//风控信息
									loanInfo.setOrderId(orderId);
									loanInfo.setLoanInfoId(loanInfoId);
									infolist.add(loanInfo);
									if((risk == 1 || risk == 3) && !map.containsKey(orderId)){//一个订单id只塞入一次通过
										map.put(orderId, orderId);
										//订单信息
										ycejorder.setId(orderId);
										ycejorder.setStatus("05");//资源待匹配
										orderlist.add(ycejorder);
										flag = false;
									}
								}
							}
						}
					}
				}
			}
			if(!flag){
				loanInfoService.updateBatchById(infolist);//批量更新风控信息表的订单主键
				for(YcejOrderEntity ycejOrder : orderlist ){
					params.clear();
					params.put("id", ycejOrder.getId());
					params.put("status", ycejOrder.getStatus());
					//ycejOrderService.updateOrderBatch(params);//更新订单的状态
				}
			}
			logger.info("loanInfoTask执行完毕");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RRException("任务执行异常！");
		}
	}
}
