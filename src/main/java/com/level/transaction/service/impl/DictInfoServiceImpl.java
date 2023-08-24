package com.level.transaction.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.level.transaction.domain.DictInfo;
import com.level.transaction.domain.bo.DictInfoBo;
import com.level.transaction.mapper.DictInfoMapper;
import com.level.transaction.service.DictInfoService;
import com.level.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictInfoServiceImpl extends ServiceImpl<DictInfoMapper, DictInfo> implements DictInfoService {

    @Autowired
    DictInfoMapper dictInfoMapper;

    @Override
    public List<DictInfo> getDictInfos(DictInfoBo bo) {
        LambdaQueryWrapper<DictInfo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(null != bo.getId(), DictInfo::getId,bo.getId());
        wrapper.eq(StringUtils.isNoneBlank(bo.getCode()), DictInfo::getCode,bo.getCode());
        wrapper.like(StringUtils.isNoneBlank(bo.getPinyin()), DictInfo::getPinyin,bo.getPinyin());
        return list(wrapper);
    }

    @Override
    public boolean saveDictInfo(DictInfo dictInfo) {
        return dictInfoMapper.saveDictInfo(dictInfo) > 0 ? true : false;
    }

    @Override
    public DictInfo getDictInfo(Integer id, String code) {
        DictInfo dictInfo = null;
        if(id != null){
            dictInfo = dictInfoMapper.getDictInfo(id);
        }
        if(code != null){
            dictInfo = dictInfoMapper.getDictInfo(code);
        }
        return dictInfo;
    }

}
