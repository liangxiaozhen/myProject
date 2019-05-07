package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptpl.mapper.BacthFileRecordMapper;
import com.ptpl.model.BacthFileRecord;
import com.ptpl.service.BacthFileRecordServiceI;

public class BacthFileRecordServiceImpl implements BacthFileRecordServiceI {

	@Autowired
	BacthFileRecordMapper bacthFileRecordMapper;
	
	//插入数据
	public int insert(BacthFileRecord bfr) {
		return bacthFileRecordMapper.insert(bfr);
	}

	//更新数据
	public int update(BacthFileRecord bfr) {
		return bacthFileRecordMapper.update(bfr);
	}

	//获取已成功发送，但没有处理的文件
	public List<BacthFileRecord> getBacthFileRecords(BacthFileRecord bfr) {
		return bacthFileRecordMapper.getBacthFileRecords(bfr);
	}

	//获取所有的文件记录
	@Override
	public List<BacthFileRecord> getAllBacthFileRecord(BacthFileRecord bfr) {
		return bacthFileRecordMapper.getAllBacthFileRecord(bfr);
	}

	//通过id获取记录
	@Override
	public BacthFileRecord getBacthFileRecordById(BigDecimal id) {
		return bacthFileRecordMapper.getBacthFileRecordById(id);
	}

}
