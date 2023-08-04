package com.level.transaction.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.level.transaction.domain.AccountInfo;
import com.level.transaction.domain.bo.AccountInfoBo;
import com.level.transaction.mapper.AccountInfoMapper;
import com.level.transaction.service.AccountInfoService;
import com.level.utils.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountInfoServiceImpl extends ServiceImpl<AccountInfoMapper, AccountInfo> implements AccountInfoService {

    @Override
    public List<AccountInfo> getAccountInfos(AccountInfoBo bo) {
        LambdaQueryWrapper<AccountInfo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(null != bo.getCommodityId(),AccountInfo::getCommodityId,bo.getCommodityId());
        wrapper.eq(null != bo.getUserId(), AccountInfo::getUserId,bo.getUserId());
        wrapper.eq(StringUtils.isNoneBlank(bo.getCommodityName()),AccountInfo::getCommodityName,bo.getCommodityName());
        wrapper.eq(StringUtils.isNoneBlank(bo.getSystemType()), AccountInfo::getSystemType,bo.getSystemType());
        wrapper.le(null != bo.getPrice(), AccountInfo::getPrice,bo.getPrice());
        wrapper.eq(null != bo.getRushSale(), AccountInfo::getRushSale,bo.getRushSale());
        wrapper.eq(null != bo.getBargain(), AccountInfo::getBargain,bo.getBargain());
        wrapper.eq(null != bo.getAccountWordCount(), AccountInfo::getAccountWordCount,bo.getAccountWordCount());
        wrapper.eq(StringUtils.isNoneBlank(bo.getAccountAttribute()), AccountInfo::getAccountAttribute,bo.getAccountAttribute());
        wrapper.eq(StringUtils.isNoneBlank(bo.getAccountGender()), AccountInfo::getAccountGender,bo.getAccountGender());
        wrapper.eq(StringUtils.isNoneBlank(bo.getAccountType()), AccountInfo::getAccountType,bo.getAccountType());
        return list(wrapper);
    }

    @Override
    public Boolean insertAccountInfo(AccountInfo accountInfo) {
        return save(accountInfo);
    }

    @Override
    public Boolean updateAccountInfo(AccountInfo accountInfo,int maxTryNum) {
        int tryNum = 0;
        boolean updateSuccess = false;
        while(tryNum < maxTryNum && !updateSuccess) {
            try {
                AccountInfo originalAccountInfo = getById(accountInfo.getUserId());
                UpdateWrapper<AccountInfo> wrapper = Wrappers.update();
                wrapper.eq("user_id", accountInfo.getUserId());
                wrapper.eq("polished_num", originalAccountInfo.getPolishedNum());
                wrapper.set("price", originalAccountInfo.getPrice() + accountInfo.getPrice());
                wrapper.set("polished_num", Integer.parseInt(originalAccountInfo.getPolishedNum()) + 1);
                updateSuccess = update(wrapper);
            }catch (Exception e){
                e.printStackTrace();
            }
            if(!updateSuccess){
                tryNum++;
            }
        }
        return updateSuccess;
    }

}
