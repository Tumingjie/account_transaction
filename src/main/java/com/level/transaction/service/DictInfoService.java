package com.level.transaction.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.level.transaction.domain.DictInfo;
import com.level.transaction.domain.bo.DictInfoBo;

import java.util.List;

public interface DictInfoService extends IService<DictInfo> {

    List<DictInfo> getDictInfos(DictInfoBo bo);

    boolean saveDictInfo(DictInfo dictInfo);

    DictInfo getDictInfo(Integer id,String code);
}
