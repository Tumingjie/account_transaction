package com.level.transaction.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.level.transaction.domain.AccountInfo;
import com.level.transaction.domain.bo.AccountInfoBo;

import java.util.List;

public interface AccountInfoService extends IService<AccountInfo> {

    List<AccountInfo> getAccountInfos(AccountInfoBo bo);

    Boolean insertAccountInfo(AccountInfo accountInfo);

    Boolean updateAccountInfo(AccountInfo accountInfo,int maxTryNum);
}
