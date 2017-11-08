package com.example.administrator.androidmap.vo;

import net.minidev.json.JSONObject;

/**
 * @author sloanwu 2011-7-7 下午03:18:32
 * 
 */
public class Response extends BaseVo {
	/**
	 */
	private static final long serialVersionUID = 1L;
	public static final String COLUMN_RESP_CODE = "RespCode";
	public static final String COLUMN_RESP_DESC = "RespDesc";
	public static final String COLUMN_STATUS = "status";
	public static final String COLUMN_RESP_TOTAL_COUNT = "totalCount";
	public static final String COLUMN_RESP_RESULT = "Results";
	public static final String COLUMN_RESP_PAGE_INFO = "PageInfo";
	public static final String COLUMN_RESP_TOTAL_NUM = "TotalNum";
	public static final String COLUMN_RESP_PER_PAGE_NUM = "PerPageNum";
	public static final String COLUMN_RESP_PAGER_INDEX = "pagerIndex";

	public static final String COLUMN_RESP_PROPERTIES = "properties";
	public static final String COLUMN_RESP_CURRENTPAGE = "currentPage";
	public static final String COLUMN_RESP_TOTALPAGE = "totalPage";
	public static final int CAN_NOT_DELETE = 0;
	public static final int CAN_DELETE = 1;

	protected String respCode;
	protected String respDesc;
//	protected int totalCount;
//	protected int currentPage;
//	protected int totalPage;
	protected int totalNum;
	protected int perPageNum;
	protected int pagerIndex;
	
	//protected String tipMsg;
	
//	public int getCurrentPage() {
//		return currentPage;
//	}
//
//	public void setCurrentPage(int currentPage) {
//		this.currentPage = currentPage;
//	}
//
//	public int getTotalPage() {
//		return totalPage;
//	}
//
//	public void setTotalPage(int totalPage) {
//		this.totalPage = totalPage;
//	}

	public JSONObject jObj;
	protected JSONObject propertiesBoby;
	protected JSONObject resultBody;


	
	public static Response parse(Object result) {
		Response response = new Response();
		JSONObject jObj = (JSONObject) result;
		response.jObj = jObj;
		basicParse(response, jObj);
		return response;
	}
	
	public static Response parseOther(Object result) {
		Response response = new Response();
		JSONObject jObj = (JSONObject) result;
		response.jObj = jObj;
		response.setRespCode(getStringValue(COLUMN_RESP_CODE, jObj));
		response.setRespDesc(getStringValue(COLUMN_RESP_DESC, jObj));
		return response;
	}

	protected static void basicParse(Response response, JSONObject jObj) {
		response.setRespCode(getStringValue(COLUMN_RESP_CODE, jObj));
		response.setRespDesc(getStringValue(COLUMN_RESP_DESC, jObj));
//		response.setTotalCount(getIntValue(COLUMN_RESP_TOTAL_COUNT, jObj));
//		response.setCurrentPage(getIntValue(COLUMN_RESP_CURRENTPAGE, jObj));
//		response.setTotalPage(getIntValue(COLUMN_RESP_TOTALPAGE, jObj));
		//response.setTipMsg(getStringValue("tipMsg", jObj));
		try {
			if (jObj.containsKey(COLUMN_RESP_RESULT)) {
				response.setResultBody((JSONObject) jObj.get(COLUMN_RESP_RESULT));
				if(response.getResultBody().containsKey(COLUMN_RESP_PAGE_INFO)){
					JSONObject jPageInfo = (JSONObject) response.getResultBody().get(COLUMN_RESP_PAGE_INFO);
					response.setTotalNum(getIntValue(COLUMN_RESP_TOTAL_NUM, jPageInfo));
					response.setPerPageNum(getIntValue(COLUMN_RESP_PER_PAGE_NUM, jPageInfo));
					response.setPagerIndex(getIntValue(COLUMN_RESP_PAGER_INDEX, jPageInfo));
				}
			}
		} catch (Exception e) {
		}
		try {
			if(jObj.containsKey(COLUMN_RESP_PROPERTIES)){
				response.setPropertiesBoby((JSONObject)jObj.get(COLUMN_RESP_PROPERTIES));
			}
		} catch (Exception e) {
		}
	}

	public Object getParam(String name) {
		return this.jObj.get(name);
	}



	public boolean isSuccess() {
		return ResponseCode.isSuccess(respCode);
	}
	public boolean isLoginOtherPhone(){
		return ResponseCode.isLoginOtherPhone(respCode);
	}

	public boolean isHasMorePage(){
		if(perPageNum == 0){
			return  false;
		}
		return (totalNum/perPageNum) + 1 > pagerIndex;
	}

	public boolean hasResults() {
		return null != resultBody;
	}
	public boolean hasProperties(){
		return null != propertiesBoby;
	}

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getRespDesc() {
		return respDesc;
	}

	public void setRespDesc(String respDesc) {
		this.respDesc = respDesc;
	}

//	public int getTotalCount() {
//		return totalCount;
//	}
//
//	public void setTotalCount(int totalCount) {
//		this.totalCount = totalCount;
//	}

	public JSONObject getResultBody() {
		return resultBody;
	}

	public void setResultBody(JSONObject resultBody) {
		this.resultBody = resultBody;
	}

	//public String getTipMsg() {
	//	return tipMsg;
	//}

	//public void setTipMsg(String tipMsg) {
	//	this.tipMsg = tipMsg;
	//}

	public JSONObject getPropertiesBoby() {
		return propertiesBoby;
	}

	public void setPropertiesBoby(JSONObject propertiesBoby) {
		this.propertiesBoby = propertiesBoby;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public int getPerPageNum() {
		return perPageNum;
	}

	public void setPerPageNum(int perPageNum) {
		this.perPageNum = perPageNum;
	}

	public int getPagerIndex() {
		return pagerIndex;
	}

	public void setPagerIndex(int pagerIndex) {
		this.pagerIndex = pagerIndex;
	}
}
