//	版本号 Version 必须 固定为 20，如版本升级，能向前兼容
//	消息类型 CmdId 变长 String 必须 每一种消息类型代表一种交易，此处为 AddBidInfo
//	商户客户号 MerCustId 变长 16 位的String 必须 商户的唯一标识
//	项目 ID ProId 变长 16 位的String 必须 标的的唯一标识，为英文和数字组合
//	标的名称 BidName 变长 50 位的 String 必须
 //	标的类型 BidType 定长 2 位的 String 必须
	 /* 01：信用 
		02：抵押
		03：债权转让
		99：其他 */
//	发标金额 BorrTotAmt 变长 14 位的 String 必须 单位为元，精确到分，例如 1000.01
//	发标年化利率 YearRate 变长 14 位的String 必须 百分比，保留 2 位小数，例如 24.55
//	应还款总利息 RetInterest 变长 16 位的 String 必须 单位为元，精确到分，例如 1000.01（商户可传根据发标金额和利率计算的值）
//	最后还款日期 LastRetDate 定长 8 位的 String 必须 格式 yyyymmdd
//	计划投标开始日期 BidStartDate 定长 14 位的 String 必须 格式 yyyyMMddHHmmss
//	计划投标截止日期 BidEndDate 定长 14 位的 String 必须 格式 yyyyMMddHHmmss
//	借款期限 LoanPeriod 变长 20 位的 String 必须 例如：XX 天、 XX 月、 XX 年
//	还款方式 RetType 定长 2 位的 String 必须
	  /*01：一次 还本付息
		02：等额本金
		03：等额本息
		04：按期付息到期还本
		99：其他*/
//	应还款日期 RetDate 定长8位String 必须 格式 yyyymmdd
//	本息保障 GuarantType 定长 2 位的 String 可选
		/*01：保本保息
		02：保本不保息
		03：不保本不保息*/
//	标的产品类型 BidProdType 定长 2 位的 String 必须
		/*01：房贷类
		02：车贷类
		03：收益权转让类
		04：信用贷款类
		05：股票配资类
		06：银行承兑汇票
		07：商业承兑汇票
		08：消费贷款类
		09：供应链类
		99：其他*/
//	风险控制方式 RiskCtlType 定长 2 位的 String 可选
		/*01：抵（质）押
		02：共管账户
		03：担保
		04：信用无担保 
		99：其他*/
//	推荐机构 Recommer 变长 150 位的String 可选 文本
//	限定最低投标份数 LimitMinBidAmt 变长 7 位的 String 可选 整数
//	限定每份投标金额 LimitBidSum 变长 16 位的 String 可选 单位为元，精确到分，例如 1000.01
//	限定最多投标金额 LimitMaxBidSum 变长 16 位的 String 可选 单位为元，精确到分，例如 1000.01
//	限定最少投标金额 LimitMinBidSum 变长 16 位的 String 可选 单位为元，精确到分，例如 1000.01
//	逾期概率 OverdueProba 变长 5 位的 String 可选 保留 2 位小数
//	逾期是否垫资 BidPayforState 定长 1 位的 String 可选
		/*1：是
		2：否*/
//	借款人类型 BorrType 定长 1 位的 String 必须
		/*01：个人
		02：企业*/
//	借款人 ID BorrCustId 变长 16 位的 String 必须 借款人的唯一标识
//	借款人名称 BorrName 变长 50 位的 String 必须 文本，借款人真实姓名或者借款企业名称
//	借款企业营业执照编号 BorrBusiCode 变长 30 位的 String 可选 借款人类型为企业时为必填
//	借款人证件类型 BorrCertType 定长 2 位的 String 可选 00：身份证（暂只支持身份证）借款人类型为“ 01：个人”时为必须参数
//	借款人证件号码 BorrCertId 变长 18 位的 String 可选 借款人类型为“ 01：个人”时为必须参数
//	借款人手机号码 BorrMobiPhone 变长 11 位的 String 必须
//	借款人固定电话 BorrPhone变长 12 位的String可选
//	借款人工作单位 BorrWork 变长 150 位的String 可选 文本
//	借款人工作年限 BorrWorkYear 变长 3 位的String 可选 单位为年，整数
//	借款人税后月收入 BorrIncome 变长 16 位的String可选 单位为元，保留 2 位小数
//	借款人学历 BorrEdu变长 20 位的String可选 文本
//	借款人婚姻状况 BorrMarriage定长 1 位的String 可选
		/*Y：已婚
		N：未婚*/
 //	借款人地址 BorrAddr变长 150 位的String可选 文本
//	借款人电子邮箱 BorrEmail变长 30 位的String可选 文本
//	借款用途 BorrPurpose变长 150 位的String必须 文本
//	编码集 CharSet 变长 String 必须 加签验签的时候，商户需告知汇付其系统编码，汇付在验签的时候进行相应的编码转换验签。因字段中有中文，应为：GBK 或者 UTF-8如果是空，默认 UTF-8
//	商户后台应答地址 BgRetUrl变长 128 位的String必须通过后台异步通知，商户网站都应在应答接收页面输出 RECV_ORD_ID 字样的字符串，表明商户已经收到该笔交易结果。
//	商户私有域 MerPriv变长 120 位的String可选为商户的自定义字段，该字段在交易完成后由本平台原样返回。
//	入参扩展域 ReqExt变长 512 位的String可选 用于扩展请求参数
//	签名 ChkValue
/*Version+CmdId+MerCustId+ProId+BidType+BorrTotAmt+YearRate+RetInterest+LastRetDate+
 * BidStartDate+BidEndDate+RetType+RetDate+GuarantType+BidProdType+RiskCtlType+
 * LimitMinBidAmt+LimitBidSum+LimitMaxBidSum+LimitMinBidSum+BidPayforState+BorrType
 *+BorrCustId+BorrBusiCode+BorrCertType+BorrCertId+BorrMobiPhone+BorrPhone+
 *BorrWorkYear+BorrIncome+BorrMarriage+BorrEmail+CharSet+BgRetUrl+MerPriv+ReqExt*/



/****投标******/
//版本号 Version定长 2 位的String必须2.0 接口中此字段的值为 20，如版本升级，能向前兼容
//消息类型 CmdId 变长 String 必须每一种消息类型代表一种交易，此处为InitiativeTender
//商户客户号 MerCustId变长 16 位的String必须 由汇付生成，商户的唯一性标识
//订单号 OrdId变长 30 位的String必须 由商户的系统生成，必须保证唯一，请使用纯数字
//订单日期 OrdDate定长 8 位的String必须 格式为 YYYYMMDD，例如：20130307
//交易金额 TransAmt变长 14 位的String必须1) 泛指交易金额，如充值、支付、取现、冻结和解冻金额（金额格式必须是###.00）比如 2.00，2.012) 如果使用代金券，则此金额包含代金券的金额，为投资人实际投资金额
//用户客户号 UsrCustId变长 16 位的String必须 由汇付生成，用户的唯一性标识
//最大投资手续费率MaxTenderRate变长 6 位的String 必须
//借款人信息BorrowerDetails变长 array 必须
//支持传送多个借款人信息，使用 json 格式传送
//	/*[{"BorrowerCustId":"6000010000000014"，
//	"BorrowerAmt": "20.01"，
//	"BorrowerRate":"0.18"，
//	"ProId":"0000000000000001" }，
//	{"BorrowerCustId": "6000010000000014"，
//	"BorrowerAmt":"20.01"， "BorrowerRate":"0.18" ,
//	"ProId":"0000000000000002" }，
//	{"BorrowerCustId":"6000010000000014"，
//	"BorrowerAmt": "20.01"， "BorrowerRate":
//	"0.18" , "ProId":"0000000000000003" }]*/
//借款人客户号BorrowerCustId变长 16 位的String必须 BorrowerDetails 参数下的二级参数借款人客户号，由汇付生成，用户的唯一性标识
//借款金额BorrowerAmt变长 12 位的String必须BorrowerDetails 参数下的二级参数借款金额借款手续费率BorrowerRate变长 6 位的String必须
 //项目 ID ProId变长 16 位的String必须BorrowerDetails 参数下的二级参数
//是否冻结 IsFreeze定长 1 位的String必须Y--冻结N--不冻结
//冻结订单号FreezeOrdId变长 30 位的String可选
//如果 IsFreeze 参数传 Y，那么该参数不能为空
//页面返回 URL RetUrl变长 128 位的 String可选
 //商户后台应答地址BgRetUrl变长 128 位的 String必须
//商户私有域 MerPriv变长 120 位的 String可选
//入参扩展域 ReqExt变长 512 位的 String可选用于扩展请求参数
//	/*{"Vocher":{"AcctId":"MDT000001","Vo
//	*/cherAmt":"5.00"}}
//代金券出账子账户 AcctId变长 12 位的String可选 商户为用户代金券出账的子账户号
//代金券金额 VocherAmt变长 12 位的String可选
//代金券金额（金额格式必须是###.00）比如 2.00,2.01







