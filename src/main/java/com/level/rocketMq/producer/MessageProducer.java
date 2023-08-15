package com.level.rocketMq.producer;

import com.level.transaction.domain.Transaction;
import com.level.transaction.domain.bo.TransactionBo;
import com.level.transaction.service.TransactionService;
import com.level.utils.JsonUtils;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class MessageProducer {

    private static final String topic = "Tran_Msg";

    @Autowired
    TransactionService transactionService;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    final ReentrantLock reentrantLock = new ReentrantLock(true);

    public void sendMsgInTransaction(TransactionBo bo){
        try {
            int orderKey = getUniqueOrderKey();
            Message<String> sendMsg = MessageBuilder.withPayload(JsonUtils.toJsonString(bo))
                    .setHeader(RocketMQHeaders.KEYS,orderKey)
                    .build();
            rocketMQTemplate.sendMessageInTransaction(topic,sendMsg,bo);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public int getUniqueOrderKey(){
        int key = 1;
        try {
            reentrantLock.lock();
            List<Transaction> transactionList = transactionService.getMaxTransactionId();
            Optional<Transaction> max = transactionList.stream().max(Comparator.comparing(Transaction::getId));
            if(max.isPresent()){
                key = max.get().getId()+1;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            reentrantLock.unlock();
            return key;
        }
    }

}
