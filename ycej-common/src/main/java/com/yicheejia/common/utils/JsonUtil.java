package com.yicheejia.common.utils;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;

/**
 * JSON字符串工具类
 */
public class JsonUtil {
	private static ObjectMapper mapper;
	private static JsonGenerator jsonGenerator;
	
	private static final JsonFactory JSONFACTORY = new JsonFactory();
	private static SimpleFilterProvider filter;
	private final static String privateKey="afa66b7ccfb74724a8bdfb281a2ac66a";
	/**
	 * 获取ObjectMapper实例
	 */
	public static synchronized JsonGenerator getJsonGenerator() {
		try {
			jsonGenerator = getMapperInstance().getFactory().createGenerator(System.out, JsonEncoding.UTF8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonGenerator;
	}
	
	/**
	 * 获取ObjectMapper实例
	 */
	public static synchronized JsonGenerator getResponseJsonGenerator(HttpServletResponse response) {
		try {
			jsonGenerator = getMapperInstance().getFactory().createGenerator(System.out, JsonEncoding.UTF8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonGenerator;
	}
	
	public static void setFilter(String filterName, String... property) {
		filter = new SimpleFilterProvider().setFailOnUnknownId(false);
		filter.addFilter(filterName, SimpleBeanPropertyFilter.serializeAllExcept(property));
	}
	
	/**
	 * 获取ObjectMapper实例 Inclusion Inclusion.ALWAYS 全部列入 Inclusion
	 * Inclusion.NON_DEFAULT 字段和对象默认值相同的时候不会列入 Inclusion Inclusion.NON_EMPTY
	 * 字段为NULL或者""的时候不会列入 Inclusion Inclusion.NON_NULL 字段为NULL时候不会列入
	 */
	public static synchronized ObjectMapper getMapperInstance() {
		if (mapper == null) {
			mapper = new ObjectMapper();
			// 当找不到对应的序列化器时 忽略此字段
			mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
			// 使Jackson JSON支持Unicode编码非ASCII字符
			SimpleModule module = new SimpleModule();
			module.addSerializer(String.class, new StringSerializer());
			mapper.registerModule(module);
			mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
			mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true) ; 
			mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS,true);
			
			// 所有日期格式都统一为以下样式
			mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
			// 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
			// mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES,
			// false);
			// 禁止使用int代表Enum的order()來反序列化Enum,非常危險
			// mapper.configure(Feature.FAIL_ON_NUMBERS_FOR_ENUMS, true);
			// 设置输出时包含属性的风格
			// 设置null值不参与序列化(字段不被显示)
			mapper.setSerializationInclusion(Include.ALWAYS);
		}
		return mapper;
	}
	
	/**
	 * 将java对象转换成json字符串
	 */
	public static String beanToJson(Object obj) {
		ObjectMapper objectMapper = getMapperInstance();
		String json = "";
		try {
			json = objectMapper.writeValueAsString(obj);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * 将json对象写入response
	 */
	public static Boolean writeResponse(HttpServletResponse response, Object obj) {
		ObjectMapper objectMapper = getMapperInstance();
		Boolean tag=true;
		try {
			if (filter != null) {
				objectMapper.writeValue(response.getWriter(), obj);
			} else {
				objectMapper.writer(filter).writeValue(response.getWriter(), obj);
			}
		} catch (Exception e) {
			tag=false;
			e.printStackTrace();
		} 
		return tag;
	}
	
	
	@SuppressWarnings("deprecation")
	public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
		ObjectMapper objectMapper = getMapperInstance();
		return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
	}
	
	public static <T> List<T> toList(String json, Class<?> class1) {
		JavaType javaType = getCollectionType(ArrayList.class, class1);
		ObjectMapper objectMapper = getMapperInstance();
		List<T> beanList = getArrayList();
		try {
			beanList = objectMapper.readValue(json, javaType);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return beanList;
		
	}
	
	/**
	 * 转换Json String 为 HashMap
	 */
	@SuppressWarnings("unchecked")
	public static Map jsonToMap(String json, boolean collToString) {
		try {
			Map<String, Object> map = getMapperInstance().readValue(json, HashMap.class);
			if (collToString) {
				for (Map.Entry<String, Object> entry : map.entrySet()) {
					if (entry.getValue() instanceof Collection || entry.getValue() instanceof Map) {
						entry.setValue(beanToJson(entry.getValue()));
					}
				}
			}
			return  map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 转换Java Bean 为 HashMap
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> beanToMap(Object o) {
		try {
			return getMapperInstance().readValue(beanToJson(o), HashMap.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * json 转List
	 * 
	 * @param json
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<?> jsonToList(String json) {
		ObjectMapper objectMapper = getMapperInstance();
		try {
			if (json != null && !"".equals(json.trim())) {
				JsonParser jsonParse = JSONFACTORY.createParser(new StringReader(json));
				ArrayList<Map<String, String>> arrayList = (ArrayList<Map<String, String>>) objectMapper.readValue(jsonParse, ArrayList.class);
				return arrayList;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取List
	 */
	public static <T> List<T> getArrayList() {
		return new ArrayList<T>();
	}
	public static void main(String[] args) {
	}
	
	
//拼接签名 
	public static String createSignature(Object obj,Map<String, Object> map){
		 //list转成json
        // String str=JSON.toJSONString(j.getObject()).toString();//阿里list转换json
		StringBuffer orderBuffer=new StringBuffer();
		if(obj != null){
			JSONArray jsonArray = JSON.parseArray(JSON.toJSONString(obj));
			for(Object mapList : jsonArray){
				for (Object entry : ((Map)mapList).entrySet()){
					orderBuffer.append(((Map.Entry)entry).getKey() + "=" +((Map.Entry)entry).getValue()+"&");
				}
			}
			System.out.println("obj========="+orderBuffer.toString());
		}
		List<Map.Entry<String,Object>> list = new ArrayList<Map.Entry<String,Object>>(map.entrySet());
        for(Map.Entry<String,Object> mapping:list){
        	orderBuffer.append(mapping.getKey()+"="+mapping.getValue()+"&");
          }
        System.out.println("========="+orderBuffer.toString());
        String md5 = orderBuffer.toString();
        md5 = md5.substring(0,md5.lastIndexOf("&"))+privateKey;
		md5=MD5(md5);
		return md5;
	}
	 // MD5加码。32位     
	 public static  String MD5(String inStr) {     
		  MessageDigest md5 = null;     
		  try {     
			  md5 = MessageDigest.getInstance("MD5");     
		  } catch (Exception e) {     
			   System.out.println(e.toString());     
			   e.printStackTrace();     
			   return "";     
		  }     
		  char[] charArray = inStr.toCharArray();     
		  byte[] byteArray = new byte[charArray.length];     
		  for (int i = 0; i < charArray.length; i++)     
		   byteArray[i] = (byte) charArray[i];     
		  byte[] md5Bytes = md5.digest(byteArray);     
		  StringBuffer hexValue = new StringBuffer();     
		  for (int i = 0; i < md5Bytes.length; i++) {     
			   int val = ((int) md5Bytes[i]) & 0xff;     
			   if (val < 16)     
			    hexValue.append("0");     
			   hexValue.append(Integer.toHexString(val));     
		  }     
		  return hexValue.toString();     
	 } 
	 /**
	  * 对object转换成
	  * @param object
	  * @return
	  */
	 public static String dispose(Object object){
		 Field[] field = object.getClass().getDeclaredFields(); // 获取实体类的所有属性，返回Field数组
		 Method m;
		 String result = "";
	         try {
	             for (int j = 0; j < field.length-1; j++) { // 遍历所有属性
	                 String name = field[j].getName(); // 获取属性的名字
	                 name = name.substring(0, 1).toUpperCase() + name.substring(1); // 将属性的首字符大写，方便构造get，set方法
	                 String type = field[j].getGenericType().toString(); // 获取属性的类型
	                 if (type.equals("class java.lang.String")) { // 如果type是类类型，则前面包含"class "，后面跟类名
	                     m = object.getClass().getMethod("get" + name);
	                     String value = (String) m.invoke(object); // 调用getter方法获取属性值
	                     if(value == null){
	                    	 m = object.getClass().getMethod("set"+name,String.class);
	                    	 m.invoke(object, "");
	                     }else{
	                    	 //if(j<=field.length-1){
	                    		 result +=name.substring(0,1).toLowerCase()+name.substring(1)+"="+value+"&";
	                    	// }else{
	                    	//	 break;
	                    	// }
	                     }
	                 }
	                 if (type.equals("class java.lang.Integer")) {
	                     m = object.getClass().getMethod("get" + name);
	                     Integer value = (Integer) m.invoke(object);
	                     if (value == null) {
	                         m = object.getClass().getMethod("set"+name,Integer.class);
	                         m.invoke(object, 1);
	                     }
	                     result +=name+"="+value+"&";
	                 }
	                 if (type.equals("class java.math.BigDecimal")) {
	                     m = object.getClass().getMethod("get" + name);
	                     BigDecimal value = (BigDecimal) m.invoke(object);
	                     if(value == null){
	                    	 m = object.getClass().getMethod("set"+name,BigDecimal.class);
	                    	 m.invoke(object, "");
	                     }else{
                    		 result +=name.substring(0,1).toLowerCase()+name.substring(1)+"="+value+"&";
	                     }
	                 }
	                 if (type.equals("class java.util.Date")) {
	                     m = object.getClass().getMethod("get" + name);
	                     Date value = (Date) m.invoke(object);
	                     if(value == null){
	                    	 m = object.getClass().getMethod("set"+name,Date.class);
	                    	 m.invoke(object, "");
	                     }else{
                    		 result +=name.substring(0,1).toLowerCase()+name.substring(1)+"="+value+"&";
	                     }
	                 }
	                 
	                 // 如果有需要,可以仿照上面继续进行扩充,再增加对其它类型的判断
	             }
	         } catch (NoSuchMethodException e) {
	             e.printStackTrace();
	         } catch (SecurityException e) {
	             e.printStackTrace();
	         } catch (IllegalAccessException e) {
	             e.printStackTrace();
	         } catch (IllegalArgumentException e) {
	             e.printStackTrace();
	         } catch (InvocationTargetException e) {
	             e.printStackTrace();
	         }
	         System.out.println("完成后==========="+result);
			return result.trim();
		 }
		/**
		 * 转换16进制字符串为byte[]
		 * @param ss
		 * @return
		 */
		public static byte[] convertHexString(String ss) {
			byte digest[] = new byte[ss.length() / 2];
			for (int i = 0; i < digest.length; i++) {
				String byteString = ss.substring(2 * i, 2 * i + 2);
				int byteValue = Integer.parseInt(byteString, 16);
				digest[i] = (byte) byteValue;
			}
			return digest;
		}
		private static byte[] newInstance8Key(byte[] key) {
			if ((key != null) && (key.length == 8)) {
				byte[] b = new byte[24];
				System.arraycopy(key, 0, b, 0, 8);
				System.arraycopy(key, 0, b, 8, 8);
				System.arraycopy(key, 0, b, 16, 8);
				key = (byte[]) null;
				return b;
			}
			System.err.println("密钥长度有误,期望值[8]");
			return null;
		}
		private static byte[] newInstance24Key(byte[] key) {
			if ((key != null) && (key.length == 24)) {
				return key;
			}
			System.err.println("密钥长度有误,期望值[24]");
			return null;
		}

		private static byte[] newInstance16Key(byte[] key) {
			if ((key != null) && (key.length == 16)) {
				byte[] b = new byte[24];
				System.arraycopy(key, 0, b, 0, 16);
				System.arraycopy(key, 0, b, 16, 8);
				key = (byte[]) null;
				return b;
			}
			System.err.println("密钥长度有误,期望值[16]");
			return null;
		}

		/**
		 * 转换byte[]为16进制字符串
		 * @param b
		 * @return
		 */
		public static String toHexString(byte b[]) {
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < b.length; i++) {
				String plainText = Integer.toHexString(0xff & b[i]);
				if (plainText.length() < 2)
					plainText = "0" + plainText;
				hexString.append(plainText);
			}
			return hexString.toString();
		}
}
