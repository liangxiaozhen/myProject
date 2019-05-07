package com.ptpl.model;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class SaveReconciliation {
		private String	Version;
		/**消息类型*/
		private String 	CmdId;
		private String 	MerCustId;
		/**开始时间*/
		private String	BeginDate;
		/**结束时间*/
		private String  EndDate;
		/**页数*/
		private String  PageNum;
		/**条数*/
		private String  PageSize;
		private String	ChkValue;
		/**应答返回码*/
		private String  RespCode;
		/**应答但会描述*/
		private String	RespDesc;
		/**记录总条数*/
		private String  TotalItems;
		/**明文*/
		private String PlainText;
		/**冲值对账结果串*/
		private List<SaveReconciliationDtoList> SaveReconciliationDtoList;
		public String getVersion() {
			return Version;
		}
		@JSONField(name="Version")
		public void setVersion(String version) {
			Version = version;
		}
		public String getCmdId() {
			return CmdId;
		}
		@JSONField(name="CmdId")
		public void setCmdId(String cmdId) {
			CmdId = cmdId;
		}
		public String getMerCustId() {
			return MerCustId;
		}
		@JSONField(name="MerCustId")
		public void setMerCustId(String merCustId) {
			MerCustId = merCustId;
		}
		public String getBeginDate() {
			return BeginDate;
		}
		@JSONField(name="BeginDate")
		public void setBeginDate(String beginDate) {
			BeginDate = beginDate;
		}
		public String getEndDate() {
			return EndDate;
		}
		@JSONField(name="EndDate")
		public void setEndDate(String endDate) {
			EndDate = endDate;
		}
		public String getPageNum() {
			return PageNum;
		}
		@JSONField(name="PageNum")
		public void setPageNum(String pageNum) {
			PageNum = pageNum;
		}
		public String getPageSize() {
			return PageSize;
		}
		@JSONField(name="PageSize")
		public void setPageSize(String pageSize) {
			PageSize = pageSize;
		}
		public String getChkValue() {
			return ChkValue;
		}
		@JSONField(name="ChkValue")
		public void setChkValue(String chkValue) {
			ChkValue = chkValue;
		}
		public String getRespCode() {
			return RespCode;
		}
		@JSONField(name="RespCode")
		public void setRespCode(String respCode) {
			RespCode = respCode;
		}
		public String getRespDesc() {
			return RespDesc;
		}
		@JSONField(name="RespDesc")
		public void setRespDesc(String respDesc) {
			RespDesc = respDesc;
		}
		public String getTotalItems() {
			return TotalItems;
		}
		@JSONField(name="TotalItems")
		public void setTotalItems(String totalItems) {
			TotalItems = totalItems;
		}
		
		public String getPlainText() {
			return PlainText;
		}
		@JSONField(name="PlainText")
		public void setPlainText(String plainText) {
			PlainText = plainText;
		}
		public List<SaveReconciliationDtoList> getSaveReconciliationDtoList() {
			return SaveReconciliationDtoList;
		}
		@JSONField(name="SaveReconciliationDtoList")
		public void setSaveReconciliationDtoList(List<SaveReconciliationDtoList> saveReconciliationDtoList) {
			SaveReconciliationDtoList = saveReconciliationDtoList;
		}
		
		
}
