/**
 *
 *接口返回对象
 */

package com.yicheejia.modules.webservices.model;

import java.util.HashMap;
import java.util.Map;

import com.yicheejia.common.constants.ComConstants;

/**
 * 返回数据
 * 
 * @date 2018年7月12日
 */
public class RspVO extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	
	public RspVO() {
		put("respCode", ComConstants.SUCCESS_CODE);
		put("respMsg", "success");
	}
	
	public static RspVO error() {
		return error(ComConstants.ERROR_CODE, "未知异常，请联系管理员");
	}
	
	public static RspVO error(String msg) {
		return error(ComConstants.ERROR_CODE, msg);
	}
	
	public static RspVO error(String code, String msg) {
		RspVO r = new RspVO();
		r.put("respCode", code);
		r.put("respMsg", msg);
		return r;
	}

	public static RspVO ok(String msg) {
		RspVO r = new RspVO();
		r.put("respMsg", msg);
		return r;
	}
	
	public static RspVO ok(Map<String, Object> map) {
		RspVO r = new RspVO();
		r.putAll(map);
		return r;
	}
	
	public static RspVO ok() {
		return new RspVO();
	}

	@Override
	public RspVO put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
