package com.level.transaction.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.level.transaction.domain.Transaction;

import java.util.List;

public interface TransactionService extends IService<Transaction>{

    Boolean addTransaction(Transaction transaction);

    Transaction getTransactionById(Integer id);

    List<Transaction> getMaxTransactionId();
}
