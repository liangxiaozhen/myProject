package com.ptpl.service.impl;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.controller.BaseController;
import com.ptpl.mapper.AccInExRecordMapper;
import com.ptpl.mapper.ActiveObjectListMapper;
import com.ptpl.mapper.DattornRNameLinkMapper;
import com.ptpl.mapper.DebtAttornFeeMapper;
import com.ptpl.mapper.DebtAttornMapper;
import com.ptpl.mapper.RemoveNameMapper;
import com.ptpl.mapper.RepayMentMapper;
import com.ptpl.mapper.TenderItemMapper;
import com.ptpl.mapper.UserAccountMapper;
import com.ptpl.mapper.UserAccountSafeInfoMapper;
import com.ptpl.mapper.UserBaseAccountInfoMapper;
import com.ptpl.mapper.UserDebtAttornMapper;
import com.ptpl.mapper.UserTenderMapper;

public class UserDebtAttornServiceQuote extends BaseController{
	@Autowired
	 UserDebtAttornMapper userDebtAttornmapper;
	@Autowired
	 UserTenderMapper userTendermapper;
	@Autowired
	 TenderItemMapper tenderItemmapper;
	@Autowired
	 RepayMentMapper repayMentmapper;
	@Autowired
	 UserAccountSafeInfoMapper userAccountSafeInfoMapper;
	@Autowired
	 DebtAttornMapper debtAttornMapper;
	@Autowired
	 RemoveNameMapper removeNameMapper;
	@Autowired
	 DattornRNameLinkMapper dattornRNameLinkMapper;
	@Autowired
	 DebtAttornFeeMapper debtAttornFeeMapper;
	@Autowired
	 AccInExRecordMapper accInExRecordMapper;
	@Autowired
	 UserAccountMapper userAccountMapper;
	@Autowired
	 UserBaseAccountInfoMapper userBaseAccountInfoMapper;
	@Autowired
	 ActiveObjectListMapper activeObjectListMapper;
	SimpleDateFormat sf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
}
