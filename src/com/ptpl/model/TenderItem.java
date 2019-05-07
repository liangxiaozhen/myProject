
package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;

public class TenderItem {
    private BigDecimal id;//id

    private BigDecimal loanappid;//借款申请ID

    private String tno;//标号

    private String tname;//标的名称

    private Short timode;//标的利息计算方式（即时计，结标再计, 结标第二天，自定义）根据投标时间

    private Date valuedate;//结标日（有时分秒）标状态改变的时候

    private Double tamount;//标的金额

    private Double finishtamount;//已完成投标金额

    private Short tpro;//标的类型：标的类型对象设置（loanTypeObjectQuote）中的SerialNo（对象序号）

    private Date tbegintime;//标的开始日期

    private Date tendtime;//标的结束时间

    private Integer loantime;//借款周期

    private String dayormonth;//借款周期单位

    private Double tinterest;//标的利息(收益)

    private String tdesc;//标的说明描述
    /**
     * 标状态（8种）
     * public static Short T1=1;//待录入
     * public static Short T2=2;//待投标
     * public static Short T3=3;//投标中
     * public static Short T4=4;//已流标
     * public static Short T5=5;//待放款
     * public static Short T6=6;//待生成还款计划
     * public static Short T7=7;//还款中
     * public static Short T8=8;//已完成
     *
     * @author :liuqh
     * @date 2017/6/25 16:24
     */

    private Short tstatus;//(徽商是这样的：1：已发标  2：已投标  9：已撤销)

    private String pmiscpayman;//平台杂项付款人

    private String pmiscrecman;//平台杂项收款人

    private Short onettimes;//单人投标次数限制

    private String crestrict;//投标客户端来源,(d是PC，a是安卓，p是iphone,o是wap,w是微信）

    private Short isfaketender;//是否可以假投标(0表示不可以，1表示可以

    private Short isautotender;//是否可以自动投标

    private Short ttype;//投标类型（及时，冻结）

    private Short isacancel;//投标是否允许撤回（投标类型为冻结投标时才可以设置； 及时0，冻结0、1）

    private String allowcugrade;//允许撤回的会员等级(100000000000000000000000000000)全1为所有会员

    private Short issetgfundsint;//是否设置站岗利息（冻结投标, 投标计息时设置）

    private String gfundsintno;//站岗利息编号

    private Short repaymenttype;//还款类型（1及时，2冻结还款）

    private Short repaymentpro;//计息方式(1一次还本付息  2：等额本金  3：等额本息   4：按期付息到期还本   99：其他)

    private Short repaysetmode;//还款设置方式（0手动，1自动）根据：loanapp中的isautorepay（是否同意自动还款  0:不同意  1：同意）

    private String repayman;//还款人设置（分期还款表可设置每期不同的人来还）根据第三方支付接口是否可以更改,结果是不可以更改，所以默认是借款人本人了

    private Short isapartrepay;//是否支持部分还款

    private String repaytimepoint;//还款时间点

    private Short isappointtender;//是否约标  1:是   0：否

    private String tpass;//约标码

    private Double minoncetamount;//单笔允许最低投资金额


    private Double maxoncetamount;//单笔允许最高投资金额

    private String ugrestrict;//允许投标的会员等级

    private String removenameno;//排除名单表编号（会有多个

    private Short isfailtc;//是否设置流标补偿

    private String failtcno;//流标补偿表no

    private Short isaoverduec;//是否允许逾期代偿

    private String overduecno;//逾期代偿表编号

    private Short isaaheadrepay;//是否允许提前还款

    private String aheadrepayno;//提前还款表编号

    private Short isadebtattorn;//是否允许债权转让

    private String debtattornno;//债权转让表编号

    private Short isaplus;//是否允许使用增益

    private String plusno;//增益设置表编号

    private Short isamediacy;//是否设置居间费

    private String mediacyfeeno;//居间费表编号

    private Short isaguarantee;//是否设置担保

    private String guaranteefeeno;//担保费表编号

    private Short isaintexp;//是否设置利息管理费

    private String intexpno;//利息管理费编号

    private Short isariskgm;//是否设置风险保证金

    private String riskgmno;//风险保证金编号

    private Short repaymode;//还款金额分配方式（1一次分配，2二次分配）

    private Double begininvestmoney;//起投金额

    private Short ismultiple;//是否起投金额整数倍

    private Double totalmoneyrestrict;//累投金额限制

    private Long averagenum;//设置标的份数

    private Double averagemoney;//标的每份金额

    private Short isaudit;//是否审核

    private Short istemplet;//是否模板

    private String auditman;//审核人

    private Date audittime;//审核时间

    private String remark;//备注

    private Short isrecorded;//已经发送徽商系统录入（0否，1是)

    private String bidprodtype;//标的产品类型(01：房贷类 02：车贷类 03：收益权转让类 04：信用贷款类 05：股票配资类 06：银行承兑汇票07：商业承兑汇票 08：消费贷款类 09：供应链类 99：其他

    private Date retdate;//首次还款日期

    private Date lastretdate;//最后还款日期

    private Short iscompensatory;//正常还款代偿开关(1:开,0:关)

    private String compensatoryman;//正常还款代偿人

    private BigDecimal pmiscpaymanid;
    ;//平台杂项付款人id

    private BigDecimal pmiscrecmanid;//平台杂项收款人id

    private String valuepoint;

    private Short valuerule;//起息规则（1结标日当天，2结标日次日，3结标时间点（结标日时间在固定时间点之前，起息日为结标日; 结标日时间在固定时间点之后，起息日为结标日的次日））

    private BigDecimal compensatorymaid;//正常还款代偿人id

    private Integer graceperiod;//逾期宽限期(还款日第二天算起)

    private Double daylatefeerate;//日滞纳金率(补偿投资人)

    private String addman;//添加人

    private Date addtime;//添加时间

    private Short isaheadcompensatory;//提前还款代偿开关(1.开,0关)

    private BigDecimal aheadcompensatorymanid;//提前还款代偿人id

    private String aheadcompensatoryman;//提前还款代偿人

    private Short isintcompensateon;//流标利息方式补偿开关(0关,1开)

    private Short isawardcompensateon;//流标奖品补偿方式开关(0关,1)

    private Short isaocfee;//收取平台追偿费开关(0关,1开)

    private Short ispicompensateon;//提前还款本金利息补偿开关(0关,1开)

    private Short ispluscompensateon;//提前还款增益利息补偿开关(0,关,1开)

    private Short isforplatformon;//提前还款还款人补偿平台开关(0,关,1开)

    private Short isadebtattornfee;//是否收取债权手续费(0关,1开)

    private BigDecimal snlid;//定向名单列表Id

    private Short displaytype;//标的显示类型(1.明标 前台可以显示， 2.暗标 前台不显示)

    private Double tscore;//标的分值给标的评分

    private String tgrade;//标的等级

    private Short aheadperiod;//还款宽限期(还款日往前)单位：天

    private BigDecimal ocmaxday;//逾期滞纳金计算截止天数(逾期后 超过该天数  不再计算滞纳金)

    private Date putontime;//标的上架时间

    private Date pullofftime;//标的下架时间

    private String snlno;//	定向名单编号

    private Short onanddown;//标上架状态（0待上架-1已上架-2已下架）

    private Short investerrange;//投资人设置(1所有人，2新手，3会员等级，4定向名单 )

    private AheadRepayMode aheadRepayMode;//提前还款方式设置，引用类型属性





    private String minoncetamountStr;//用于拼接成“最低xx元”;

    private String progressbar;//投标进度,封装给海网，数据库不存在这个字段

    private String lastmoney;//剩余金额，数据库不存在这个字段

    private String interestrate;//利率，数据库不存在这个字段

    private String tamountstr;//发标金额，数据库不存在这个字段

    private String tendtimestr;//标的结束时间，数据库不存在这个字段

    private String repaymentprostr;//还款方式,封装给海网，数据库不存在这个字段

    private String valuerulestr;//起息规则,封装给海网，数据库不存在这个字段

    private Double tinteresttwo;//标的利息，数据库不存在这个字段

    private String ismultiplestr;//是否整数倍，数据库不存在这个字段

    private String tstatusStr;//用于把标的状态由数字转为字符串传给海网，数据库不存在这个字段

    private Integer tenderItemFrontEndSingleFilled;//此标的单标前端信息已填数量，数据库不存在这个字段

    private Integer tenderItemFrontEndSingleTotal;//此标的单标前端信息总数，数据库不存在这个字段

    private Short isfroze;//是否投标资金是否在冻结中（0否，1是），投标状态为（1.待审核 4.已完成 5.处理中 6.处理失败）都视为冻结中，就不显示手动流标这个按钮，数据库不存在这个字段

    private String loginname;//用户登陆名，用于封装到建标页面中显示的，数据库不存在这个字段

    private String tbegintimeStr;//标的开始时间，封装给海网，数据库不存在这个字段

    public AheadRepayMode getAheadRepayMode() {
        return aheadRepayMode;
    }

    public void setAheadRepayMode(AheadRepayMode aheadRepayMode) {
        this.aheadRepayMode = aheadRepayMode;
    }

    public String getTbegintimeStr() {
        return tbegintimeStr;
    }

    public void setTbegintimeStr(String tbegintimeStr) {
        this.tbegintimeStr = tbegintimeStr;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public Short getIsfroze() {
        return isfroze;
    }

    public void setIsfroze(Short isfroze) {
        this.isfroze = isfroze;
    }

    public Short getInvesterrange() {
        return investerrange;
    }

    public void setInvesterrange(Short investerrange) {
        this.investerrange = investerrange;
    }

    public Short getOnanddown() {
        return onanddown;
    }

    public void setOnanddown(Short onanddown) {
        this.onanddown = onanddown;
    }

    public Integer getTenderItemFrontEndSingleFilled() {
        return tenderItemFrontEndSingleFilled;
    }

    public void setTenderItemFrontEndSingleFilled(Integer tenderItemFrontEndSingleFilled) {
        this.tenderItemFrontEndSingleFilled = tenderItemFrontEndSingleFilled;
    }

    public Integer getTenderItemFrontEndSingleTotal() {
        return tenderItemFrontEndSingleTotal;
    }

    public void setTenderItemFrontEndSingleTotal(Integer tenderItemFrontEndSingleTotal) {
        this.tenderItemFrontEndSingleTotal = tenderItemFrontEndSingleTotal;
    }

    public String getSnlno() {
        return snlno;
    }

    public void setSnlno(String snlno) {
        this.snlno = snlno;
    }

    public String getTstatusStr() {
        return tstatusStr;
    }

    public void setTstatusStr(String tstatusStr) {
        this.tstatusStr = tstatusStr;
    }

    public String getMinoncetamountStr() {
        return minoncetamountStr;
    }

    public void setMinoncetamountStr(String minoncetamountStr) {
        this.minoncetamountStr = minoncetamountStr;
    }

    public String getIsmultiplestr() {
        return ismultiplestr;
    }

    public void setIsmultiplestr(String ismultiplestr) {
        this.ismultiplestr = ismultiplestr;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getLoanappid() {
        return loanappid;
    }

    public void setLoanappid(BigDecimal loanappid) {
        this.loanappid = loanappid;
    }

    public String getTno() {
        return tno;
    }

    public void setTno(String tno) {
        this.tno = tno == null ? null : tno.trim();
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname == null ? null : tname.trim();
    }

    public Short getTimode() {
        return timode;
    }

    public void setTimode(Short timode) {
        this.timode = timode;
    }

    public Date getValuedate() {
        return valuedate;
    }

    public void setValuedate(Date valuedate) {
        this.valuedate = valuedate;
    }

    public Double getTamount() {
        return tamount;
    }

    public void setTamount(Double tamount) {
        this.tamount = tamount;
    }

    public Double getFinishtamount() {
        return finishtamount;
    }

    public void setFinishtamount(Double finishtamount) {
        this.finishtamount = finishtamount;
    }

    public Short getTpro() {
        return tpro;
    }

    public void setTpro(Short tpro) {
        this.tpro = tpro;
    }

    public Date getTbegintime() {
        return tbegintime;
    }

    public void setTbegintime(Date tbegintime) {
        this.tbegintime = tbegintime;
    }

    public Date getTendtime() {
        return tendtime;
    }

    public void setTendtime(Date tendtime) {
        this.tendtime = tendtime;
    }

    public Integer getLoantime() {
        return loantime;
    }

    public void setLoantime(Integer loantime) {
        this.loantime = loantime;
    }

    public String getDayormonth() {
        return dayormonth;
    }

    public void setDayormonth(String dayormonth) {
        this.dayormonth = dayormonth == null ? null : dayormonth.trim();
    }

    public Double getTinterest() {
        return tinterest;
    }

    public void setTinterest(Double tinterest) {
        this.tinterest = tinterest;
    }

    public String getTdesc() {
        return tdesc;
    }

    public void setTdesc(String tdesc) {
        this.tdesc = tdesc == null ? null : tdesc.trim();
    }

    public Short getTstatus() {
        return tstatus;
    }

    public void setTstatus(Short tstatus) {
        this.tstatus = tstatus;
    }

    public String getPmiscpayman() {
        return pmiscpayman;
    }

    public void setPmiscpayman(String pmiscpayman) {
        this.pmiscpayman = pmiscpayman == null ? null : pmiscpayman.trim();
    }

    public String getPmiscrecman() {
        return pmiscrecman;
    }

    public void setPmiscrecman(String pmiscrecman) {
        this.pmiscrecman = pmiscrecman == null ? null : pmiscrecman.trim();
    }

    public Short getOnettimes() {
        return onettimes;
    }

    public void setOnettimes(Short onettimes) {
        this.onettimes = onettimes;
    }

    public String getCrestrict() {
        return crestrict;
    }

    public void setCrestrict(String crestrict) {
        this.crestrict = crestrict == null ? null : crestrict.trim();
    }

    public Short getIsfaketender() {
        return isfaketender;
    }

    public void setIsfaketender(Short isfaketender) {
        this.isfaketender = isfaketender;
    }

    public Short getIsautotender() {
        return isautotender;
    }

    public void setIsautotender(Short isautotender) {
        this.isautotender = isautotender;
    }

    public Short getTtype() {
        return ttype;
    }

    public void setTtype(Short ttype) {
        this.ttype = ttype;
    }

    public Short getIsacancel() {
        return isacancel;
    }

    public void setIsacancel(Short isacancel) {
        this.isacancel = isacancel;
    }

    public String getAllowcugrade() {
        return allowcugrade;
    }

    public void setAllowcugrade(String allowcugrade) {
        this.allowcugrade = allowcugrade == null ? null : allowcugrade.trim();
    }

    public Short getIssetgfundsint() {
        return issetgfundsint;
    }

    public void setIssetgfundsint(Short issetgfundsint) {
        this.issetgfundsint = issetgfundsint;
    }

    public String getGfundsintno() {
        return gfundsintno;
    }

    public void setGfundsintno(String gfundsintno) {
        this.gfundsintno = gfundsintno == null ? null : gfundsintno.trim();
    }

    public Short getRepaymenttype() {
        return repaymenttype;
    }

    public void setRepaymenttype(Short repaymenttype) {
        this.repaymenttype = repaymenttype;
    }

    public Short getRepaymentpro() {
        return repaymentpro;
    }

    public void setRepaymentpro(Short repaymentpro) {
        this.repaymentpro = repaymentpro;
    }

    public Short getRepaysetmode() {
        return repaysetmode;
    }

    public void setRepaysetmode(Short repaysetmode) {
        this.repaysetmode = repaysetmode;
    }

    public String getRepayman() {
        return repayman;
    }

    public void setRepayman(String repayman) {
        this.repayman = repayman == null ? null : repayman.trim();
    }

    public Short getIsapartrepay() {
        return isapartrepay;
    }

    public void setIsapartrepay(Short isapartrepay) {
        this.isapartrepay = isapartrepay;
    }

    public String getRepaytimepoint() {
        return repaytimepoint;
    }

    public void setRepaytimepoint(String repaytimepoint) {
        this.repaytimepoint = repaytimepoint == null ? null : repaytimepoint.trim();
    }

    public Short getIsappointtender() {
        return isappointtender;
    }

    public void setIsappointtender(Short isappointtender) {
        this.isappointtender = isappointtender;
    }

    public String getTpass() {
        return tpass;
    }

    public void setTpass(String tpass) {
        this.tpass = tpass == null ? null : tpass.trim();
    }

    public Double getMinoncetamount() {
        return minoncetamount;
    }

    public void setMinoncetamount(Double minoncetamount) {
        this.minoncetamount = minoncetamount;
    }

    public Double getMaxoncetamount() {
        return maxoncetamount;
    }

    public void setMaxoncetamount(Double maxoncetamount) {
        this.maxoncetamount = maxoncetamount;
    }

    public String getUgrestrict() {
        return ugrestrict;
    }

    public void setUgrestrict(String ugrestrict) {
        this.ugrestrict = ugrestrict == null ? null : ugrestrict.trim();
    }

    public String getRemovenameno() {
        return removenameno;
    }

    public void setRemovenameno(String removenameno) {
        this.removenameno = removenameno == null ? null : removenameno.trim();
    }

    public Short getIsfailtc() {
        return isfailtc;
    }

    public void setIsfailtc(Short isfailtc) {
        this.isfailtc = isfailtc;
    }

    public String getFailtcno() {
        return failtcno;
    }

    public void setFailtcno(String failtcno) {
        this.failtcno = failtcno == null ? null : failtcno.trim();
    }

    public Short getIsaoverduec() {
        return isaoverduec;
    }

    public void setIsaoverduec(Short isaoverduec) {
        this.isaoverduec = isaoverduec;
    }

    public String getOverduecno() {
        return overduecno;
    }

    public void setOverduecno(String overduecno) {
        this.overduecno = overduecno == null ? null : overduecno.trim();
    }

    public Short getIsaaheadrepay() {
        return isaaheadrepay;
    }

    public void setIsaaheadrepay(Short isaaheadrepay) {
        this.isaaheadrepay = isaaheadrepay;
    }

    public String getAheadrepayno() {
        return aheadrepayno;
    }

    public void setAheadrepayno(String aheadrepayno) {
        this.aheadrepayno = aheadrepayno == null ? null : aheadrepayno.trim();
    }

    public Short getIsadebtattorn() {
        return isadebtattorn;
    }

    public void setIsadebtattorn(Short isadebtattorn) {
        this.isadebtattorn = isadebtattorn;
    }

    public String getDebtattornno() {
        return debtattornno;
    }

    public void setDebtattornno(String debtattornno) {
        this.debtattornno = debtattornno == null ? null : debtattornno.trim();
    }

    public Short getIsaplus() {
        return isaplus;
    }

    public void setIsaplus(Short isaplus) {
        this.isaplus = isaplus;
    }

    public String getPlusno() {
        return plusno;
    }

    public void setPlusno(String plusno) {
        this.plusno = plusno == null ? null : plusno.trim();
    }

    public Short getIsamediacy() {
        return isamediacy;
    }

    public void setIsamediacy(Short isamediacy) {
        this.isamediacy = isamediacy;
    }

    public String getMediacyfeeno() {
        return mediacyfeeno;
    }

    public void setMediacyfeeno(String mediacyfeeno) {
        this.mediacyfeeno = mediacyfeeno == null ? null : mediacyfeeno.trim();
    }

    public Short getIsaguarantee() {
        return isaguarantee;
    }

    public void setIsaguarantee(Short isaguarantee) {
        this.isaguarantee = isaguarantee;
    }

    public String getGuaranteefeeno() {
        return guaranteefeeno;
    }

    public void setGuaranteefeeno(String guaranteefeeno) {
        this.guaranteefeeno = guaranteefeeno == null ? null : guaranteefeeno.trim();
    }

    public Short getIsaintexp() {
        return isaintexp;
    }

    public void setIsaintexp(Short isaintexp) {
        this.isaintexp = isaintexp;
    }

    public String getIntexpno() {
        return intexpno;
    }

    public void setIntexpno(String intexpno) {
        this.intexpno = intexpno == null ? null : intexpno.trim();
    }

    public Short getIsariskgm() {
        return isariskgm;
    }

    public void setIsariskgm(Short isariskgm) {
        this.isariskgm = isariskgm;
    }

    public String getRiskgmno() {
        return riskgmno;
    }

    public void setRiskgmno(String riskgmno) {
        this.riskgmno = riskgmno == null ? null : riskgmno.trim();
    }

    public Short getRepaymode() {
        return repaymode;
    }

    public void setRepaymode(Short repaymode) {
        this.repaymode = repaymode;
    }

    public Double getBegininvestmoney() {
        return begininvestmoney;
    }

    public void setBegininvestmoney(Double begininvestmoney) {
        this.begininvestmoney = begininvestmoney;
    }

    public Short getIsmultiple() {
        return ismultiple;
    }

    public void setIsmultiple(Short ismultiple) {
        this.ismultiple = ismultiple;
    }

    public Double getTotalmoneyrestrict() {
        return totalmoneyrestrict;
    }

    public void setTotalmoneyrestrict(Double totalmoneyrestrict) {
        this.totalmoneyrestrict = totalmoneyrestrict;
    }

    public Long getAveragenum() {
        return averagenum;
    }

    public void setAveragenum(Long averagenum) {
        this.averagenum = averagenum;
    }

    public Double getAveragemoney() {
        return averagemoney;
    }

    public void setAveragemoney(Double averagemoney) {
        this.averagemoney = averagemoney;
    }

    public Short getIsaudit() {
        return isaudit;
    }

    public void setIsaudit(Short isaudit) {
        this.isaudit = isaudit;
    }

    public Short getIstemplet() {
        return istemplet;
    }

    public void setIstemplet(Short istemplet) {
        this.istemplet = istemplet;
    }

    public String getAuditman() {
        return auditman;
    }

    public void setAuditman(String auditman) {
        this.auditman = auditman == null ? null : auditman.trim();
    }

    public Date getAudittime() {
        return audittime;
    }

    public void setAudittime(Date audittime) {
        this.audittime = audittime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Short getIsrecorded() {
        return isrecorded;
    }

    public void setIsrecorded(Short isrecorded) {
        this.isrecorded = isrecorded;
    }

    public String getBidprodtype() {
        return bidprodtype;
    }

    public void setBidprodtype(String bidprodtype) {
        this.bidprodtype = bidprodtype == null ? null : bidprodtype.trim();
    }

    public Date getRetdate() {
        return retdate;
    }

    public void setRetdate(Date retdate) {
        this.retdate = retdate;
    }

    public Date getLastretdate() {
        return lastretdate;
    }

    public void setLastretdate(Date lastretdate) {
        this.lastretdate = lastretdate;
    }

    public Short getIscompensatory() {
        return iscompensatory;
    }

    public void setIscompensatory(Short iscompensatory) {
        this.iscompensatory = iscompensatory;
    }

    public String getCompensatoryman() {
        return compensatoryman;
    }

    public void setCompensatoryman(String compensatoryman) {
        this.compensatoryman = compensatoryman == null ? null : compensatoryman.trim();
    }

    public BigDecimal getPmiscpaymanid() {
        return pmiscpaymanid;
    }

    public void setPmiscpaymanid(BigDecimal pmiscpaymanid) {
        this.pmiscpaymanid = pmiscpaymanid;
    }

    public BigDecimal getPmiscrecmanid() {
        return pmiscrecmanid;
    }

    public void setPmiscrecmanid(BigDecimal pmiscrecmanid) {
        this.pmiscrecmanid = pmiscrecmanid;
    }

    public String getValuepoint() {
        return valuepoint;
    }

    public void setValuepoint(String valuepoint) {
        this.valuepoint = valuepoint == null ? null : valuepoint.trim();
    }

    public Short getValuerule() {
        return valuerule;
    }

    public void setValuerule(Short valuerule) {
        this.valuerule = valuerule;
    }

    public BigDecimal getCompensatorymaid() {
        return compensatorymaid;
    }

    public void setCompensatorymaid(BigDecimal compensatorymaid) {
        this.compensatorymaid = compensatorymaid;
    }

    public Integer getGraceperiod() {
        return graceperiod;
    }

    public void setGraceperiod(Integer graceperiod) {
        this.graceperiod = graceperiod;
    }

    public Double getDaylatefeerate() {
        return daylatefeerate;
    }

    public void setDaylatefeerate(Double daylatefeerate) {
        this.daylatefeerate = daylatefeerate;
    }

    public String getAddman() {
        return addman;
    }

    public void setAddman(String addman) {
        this.addman = addman == null ? null : addman.trim();
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Short getIsaheadcompensatory() {
        return isaheadcompensatory;
    }

    public void setIsaheadcompensatory(Short isaheadcompensatory) {
        this.isaheadcompensatory = isaheadcompensatory;
    }

    public BigDecimal getAheadcompensatorymanid() {
        return aheadcompensatorymanid;
    }

    public void setAheadcompensatorymanid(BigDecimal aheadcompensatorymanid) {
        this.aheadcompensatorymanid = aheadcompensatorymanid;
    }

    public String getAheadcompensatoryman() {
        return aheadcompensatoryman;
    }

    public void setAheadcompensatoryman(String aheadcompensatoryman) {
        this.aheadcompensatoryman = aheadcompensatoryman == null ? null : aheadcompensatoryman.trim();
    }

    public Short getIsintcompensateon() {
        return isintcompensateon;
    }

    public void setIsintcompensateon(Short isintcompensateon) {
        this.isintcompensateon = isintcompensateon;
    }

    public Short getIsawardcompensateon() {
        return isawardcompensateon;
    }

    public void setIsawardcompensateon(Short isawardcompensateon) {
        this.isawardcompensateon = isawardcompensateon;
    }

    public Short getIsaocfee() {
        return isaocfee;
    }

    public void setIsaocfee(Short isaocfee) {
        this.isaocfee = isaocfee;
    }

    public Short getIspicompensateon() {
        return ispicompensateon;
    }

    public void setIspicompensateon(Short ispicompensateon) {
        this.ispicompensateon = ispicompensateon;
    }

    public Short getIspluscompensateon() {
        return ispluscompensateon;
    }

    public void setIspluscompensateon(Short ispluscompensateon) {
        this.ispluscompensateon = ispluscompensateon;
    }

    public Short getIsforplatformon() {
        return isforplatformon;
    }

    public void setIsforplatformon(Short isforplatformon) {
        this.isforplatformon = isforplatformon;
    }

    public Short getIsadebtattornfee() {
        return isadebtattornfee;
    }

    public void setIsadebtattornfee(Short isadebtattornfee) {
        this.isadebtattornfee = isadebtattornfee;
    }

    public BigDecimal getSnlid() {
        return snlid;
    }

    public void setSnlid(BigDecimal snlid) {
        this.snlid = snlid;
    }

    public Short getDisplaytype() {
        return displaytype;
    }

    public void setDisplaytype(Short displaytype) {
        this.displaytype = displaytype;
    }

    public Double getTscore() {
        return tscore;
    }

    public void setTscore(Double tscore) {
        this.tscore = tscore;
    }

    public String getTgrade() {
        return tgrade;
    }

    public void setTgrade(String tgrade) {
        this.tgrade = tgrade == null ? null : tgrade.trim();
    }

    public Short getAheadperiod() {
        return aheadperiod;
    }

    public void setAheadperiod(Short aheadperiod) {
        this.aheadperiod = aheadperiod;
    }

    public BigDecimal getOcmaxday() {
        return ocmaxday;
    }

    public void setOcmaxday(BigDecimal ocmaxday) {
        this.ocmaxday = ocmaxday;
    }

    public String getProgressbar() {
        return progressbar;
    }

    public void setProgressbar(String progressbar) {
        this.progressbar = progressbar;
    }

    public String getInterestrate() {
        return interestrate;
    }

    public void setInterestrate(String interestrate) {
        this.interestrate = interestrate;
    }

    public String getTamountstr() {
        return tamountstr;
    }

    public void setTamountstr(String tamountstr) {
        this.tamountstr = tamountstr;
    }

    public String getLastmoney() {
        return lastmoney;
    }

    public void setLastmoney(String lastmoney) {
        this.lastmoney = lastmoney;
    }

    public String getRepaymentprostr() {
        return repaymentprostr;
    }

    public void setRepaymentprostr(String repaymentprostr) {
        this.repaymentprostr = repaymentprostr;
    }

    public String getTendtimestr() {
        return tendtimestr;
    }

    public void setTendtimestr(String tendtimestr) {
        this.tendtimestr = tendtimestr;
    }

    public String getValuerulestr() {
        return valuerulestr;
    }

    public void setValuerulestr(String valuerulestr) {
        this.valuerulestr = valuerulestr;
    }

    public Double getTinteresttwo() {
        return tinteresttwo;
    }

    public void setTinteresttwo(Double tinteresttwo) {
        this.tinteresttwo = tinteresttwo;
    }

    public Date getPutontime() {
        return putontime;
    }

    public void setPutontime(Date putontime) {
        this.putontime = putontime;
    }

    public Date getPullofftime() {
        return pullofftime;
    }

    public void setPullofftime(Date pullofftime) {
        this.pullofftime = pullofftime;
    }
}