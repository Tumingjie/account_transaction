package com.level.transaction.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.level.transaction.domain.Transaction;
import com.level.transaction.mapper.TransactionMapper;
import com.level.transaction.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl extends ServiceImpl<TransactionMapper, Transaction> implements TransactionService {


    @Override
    public Boolean addTransaction(Transaction transaction) {
        return save(transaction);
    }

    @Override
    public Transaction getTransactionById(Integer id) {
        return getById(id);
    }

    @Override
    public List<Transaction> getMaxTransactionId() {
        LambdaQueryWrapper wrapper = Wrappers.lambdaQuery();
        return list(wrapper);
    }


}
