package com.ptpl.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.BacthFileRecord;

public interface BacthFileRecordMapper {
	
	//插入数据
	int insert(BacthFileRecord bfr);
	
	//根据id更新数据
	int update(BacthFileRecord bfr);
	
	//获取已成功发送，但是没有处理的文件
	List<BacthFileRecord> getBacthFileRecords(BacthFileRecord bfr);
	
	//获取所有的记录
	List<BacthFileRecord> getAllBacthFileRecord(BacthFileRecord bfr);
	
	//通过id获取记录
	BacthFileRecord getBacthFileRecordById(BigDecimal id);
	
}
