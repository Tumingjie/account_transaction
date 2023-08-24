package com.level.transaction.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.level.transaction.domain.DictInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DictInfoMapper extends BaseMapper<DictInfo> {

    int saveDictInfo(DictInfo dictInfo);

    DictInfo getDictInfo(@Param("id") Integer id);

    DictInfo getDictInfo(@Param("code") String code);
}
