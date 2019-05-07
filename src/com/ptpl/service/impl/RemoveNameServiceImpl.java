package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.RemoveNameMapper;
import com.ptpl.mapper.UserBaseAccountInfoMapper;
import com.ptpl.model.RemoveName;
import com.ptpl.model.SpecialTime;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.service.RemoveNameServiceI;
import com.ptpl.web.util.StringUtil;

public class RemoveNameServiceImpl implements RemoveNameServiceI {
	@Autowired
	private RemoveNameMapper removeNameMapper;
	
	@Override
	public int deleteByPrimaryKey(BigDecimal id)
	{
		// TODO Auto-generated method stub
		return removeNameMapper.deleteByPrimaryKey(id);
	}
	@Override
	public int insertSelective(RemoveName record)
	{
		// TODO Auto-generated method stub
		return removeNameMapper.insertSelective(record);
	}
	@Override
	public RemoveName selectByPrimaryKey(BigDecimal id)
	{
		// TODO Auto-generated method stub
		return removeNameMapper.selectByPrimaryKey(id);
	}
	@Override
	public int updateByPrimaryKeySelective(RemoveName record)
	{
		// TODO Auto-generated method stub
		return removeNameMapper.updateByPrimaryKeySelective(record);
	}
	@Override
	public List<RemoveName> selective(RemoveName record)
	{
		// TODO Auto-generated method stub
		return removeNameMapper.selective(record);
	}
	@Override
	public String selectNameNoForName(String name)
	{
		// TODO Auto-generated method stub
		return removeNameMapper.selectNameNoForName(name);
	}
	@Override
	public List<RemoveName> selectNameAndNameNo()
	{
		// TODO Auto-generated method stub
		return removeNameMapper.selectNameAndNameNo();
	}
	@Override
	public String selectNameForNameNo(String nameno)
	{
		// TODO Auto-generated method stub
		return removeNameMapper.selectNameForNameNo(nameno);
	}
	@Override
	public String selectNameNoForNT(String name, String nameType)
	{
		// TODO Auto-generated method stub
		return removeNameMapper.selectNameNoForNT(name, nameType);
	}
	@Override
	public List<RemoveName> getNameNoAndName(String nameType)
	{
		// TODO Auto-generated method stub
		return removeNameMapper.getNameNoAndName(nameType);
	}
	@Override
	public RemoveName selectNameAndNameType(String nameno)
	{
		// TODO Auto-generated method stub
		return removeNameMapper.selectNameAndNameType(nameno);
	}
	@Override
	public List<RemoveName> selectNameNamNoNameType()
	{
		// TODO Auto-generated method stub
		return removeNameMapper.selectNameNamNoNameType();
	}
	@Override
	public List<RemoveName> getGroupNameno()
	{
		// TODO Auto-generated method stub
		return removeNameMapper.getGroupNameno();
	}
	@Override
	public List<RemoveName> selectiveNameType(RemoveName removeName)
	{
		// TODO Auto-generated method stub
		List<RemoveName> list=removeNameMapper.selectiveNameType(removeName);
		for(RemoveName rName:list)
		{
			RemoveName r=new RemoveName();
			r.setNametype(rName.getNametype());
			int nameTypeCount=removeNameMapper.selectCount(r);
			r.setName(rName.getName());
			int nameCount=removeNameMapper.selectCount(r);
			rName.setNameCount(nameCount);
			rName.setNameTypeCount(nameTypeCount);
		}
		return list;
	}
	@Override
	public List<RemoveName> selectByNameTypeName(String name, String nameType)
	{
		// TODO Auto-generated method stub
		return removeNameMapper.selectByNameTypeName(name, nameType);
	}
	@Override
	public int deleteName(String nameno)
	{
		// TODO Auto-generated method stub
		return removeNameMapper.deleteName(nameno);
	}
	
	@Override
	public List<RemoveName> selectbyRewardlnno(String rewardlnno) {		
		return removeNameMapper.selectbyRewardlnno(rewardlnno);
	}
	@Override
	public RemoveName selectloginname(String loginname) {
		return removeNameMapper.selectloginname(loginname);
	}
	@Override
	public List<RemoveName> getGroupNameType()
	{
		// TODO Auto-generated method stub
		return removeNameMapper.getGroupNameType();
	}
	@Override
	public List<RemoveName> getGroupName(String nameType)
	{
		// TODO Auto-generated method stub
		return removeNameMapper.getGroupName(nameType);
	}
	@Override
	public int updateIsUse(String name, String nameType)
	{
		// TODO Auto-generated method stub
		return removeNameMapper.updateIsUse(name, nameType);
	}
	@Override
	public int updateCancelUse(String name, String nameType)
	{
		// TODO Auto-generated method stub
		return removeNameMapper.updateCancelUse(name, nameType);
	}
	@Override
	public int selectCount(RemoveName rmoveName)
	{
		// TODO Auto-generated method stub
		return removeNameMapper.selectCount(rmoveName);
	}
	@Override
	public int updateNameType(RemoveName rmoveName)
	{
		// TODO Auto-generated method stub
		return removeNameMapper.updateNameType(rmoveName);
	}
	@Override
	public List<RemoveName> getRemoveName()
	{
		// TODO Auto-generated method stub
		return removeNameMapper.getRemoveName();
	}
	@Override
	public List<RemoveName> selectListForInsert(RemoveName rName)
	{
		// TODO Auto-generated method stub
		return removeNameMapper.selectListForInsert(rName);
	}
	@Override
	public List<RemoveName> selectUserUgradeForInsert( String ugrade)
	{
		// TODO Auto-generated method stub
		return removeNameMapper.selectUserUgradeForInsert(ugrade);
	}
	@Override
	public RemoveName selectByBaseID(String baseID, String name, String nameType)
	{
		// TODO Auto-generated method stub
		return removeNameMapper.selectByBaseID(baseID, name, nameType);
	}
	@Override
	public List<RemoveName> selectUserForInsert(String userName)
	{
		// TODO Auto-generated method stub
		return removeNameMapper.selectUserForInsert(userName);
	}
	@Override
	public RemoveName selectRemoveName(String nameno)
	{
		// TODO Auto-generated method stub
		return removeNameMapper.selectRemoveName(nameno);
	}
	@Override
	public List<RemoveName> getRemove(String nameno) {
		// TODO Auto-generated method stub
		return removeNameMapper.getRemove(nameno);
	}

	@Override
	public List<RemoveName> getUser(String name) {
		// TODO Auto-generated method stub
		return removeNameMapper.getUser(name);
	}
	@Override
	public List<String> getTenderitemRemoveNameNo()
	{
		// TODO Auto-generated method stub
		return removeNameMapper.getTenderitemRemoveNameNo();
	}
	@Override
	public List<String> getRechargeRstrRemoveNameNo()
	{
		// TODO Auto-generated method stub
		return removeNameMapper.getRechargeRstrRemoveNameNo();
	}
	@Override
	public List<String> getWithdrawsCashRstrRemoveNameNo()
	{
		// TODO Auto-generated method stub
		return removeNameMapper.getWithdrawsCashRstrRemoveNameNo();
	}
	@Override
	public List<String> getDebtAttornRemoveNameNo()
	{
		// TODO Auto-generated method stub
		return removeNameMapper.getDebtAttornRemoveNameNo();
	}
	@Override
	public List<String> getManualAwardRewardLNNo()
	{
		// TODO Auto-generated method stub
		return removeNameMapper.getManualAwardRewardLNNo();
	}
	@Override
	public RemoveName selectByNameNoAndBaseId(RemoveName removeName) {
		// TODO Auto-generated method stub
		return removeNameMapper.selectByNameNoAndBaseId(removeName);
	}
	@Override
	public List<RemoveName> getUserNameMax(String rewardname_nameType1) {
		return removeNameMapper.getUserNameMax(rewardname_nameType1);
	}
	@Override
	public List<RemoveName> getUserNameMin(String rewardname_name1) {
		// TODO Auto-generated method stub
		return removeNameMapper.getUserNameMin(rewardname_name1);
	}
	@Override
	public RemoveName getBaseId(String name) {
		// TODO Auto-generated method stub
		return removeNameMapper.getBaseId(name);
	}
	
	/**
	 * @author pxl
	 * @date   2016-11-15
	 * 根据名单编号  找出RemoveName
	 */
	public List<RemoveName> getRemoveNameByNameNo(String namecontent) {
		return removeNameMapper.getRemoveNameByNameNo(namecontent);
	}

	//根据大名单获取其下的所有小名单
	public List<RemoveName> getGroupNameByNameType(String addOrDebarText) {
		return removeNameMapper.getGroupNameByNameType(addOrDebarText);
	}
}
