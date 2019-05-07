package com.ptpl.mapper;

import com.ptpl.model.EmailChannel;
import java.math.BigDecimal;
import java.util.List;

public interface EmailChannelMapper {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(EmailChannel record);

    int insertSelective(EmailChannel record);

    EmailChannel selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(EmailChannel record);

    int updateByPrimaryKey(EmailChannel record);
    
    List<EmailChannel> selectAll();
    /**
     * 
    * @Title: findEmailChannelsByEmailChannel 
    * @Description: TODO(条件查询) 
    * @param @param emailChannel
    * @param @return    设定文件 
    * @return List<EmailChannel>    返回类型 
    * @author   cjm  
    * @throws
     */
    List<EmailChannel> findEmailChannelsByEmailChannel(EmailChannel emailChannel);
}