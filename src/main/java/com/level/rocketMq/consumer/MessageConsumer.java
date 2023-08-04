package com.level.rocketMq.consumer;

import com.level.transaction.domain.AccountInfo;
import com.level.transaction.domain.bo.AccountInfoBo;
import com.level.transaction.domain.bo.TransactionBo;
import com.level.transaction.service.AccountInfoService;
import com.level.utils.JsonUtils;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class MessageConsumer {

    @Autowired
    AccountInfoService accountInfoService;

    @Service
    @RocketMQMessageListener(consumerGroup = "consumer_group", topic = "Tran_Msg")
    class consumerTranMsg implements RocketMQListener {

        @Override
        public void onMessage(Object message) {
            //卖家收钱
            System.out.println("--消费者--收到消息：" + message);
            TransactionBo bo = JsonUtils.parseObject((String) message,TransactionBo.class);
            AccountInfo accountInfo = new AccountInfo();
            accountInfo.setUserId(bo.getSellerId());
            accountInfo.setPrice(bo.getPrice());
            Boolean priceFlag = accountInfoService.updateAccountInfo(accountInfo,3);
            if(!priceFlag){
                throw new RuntimeException("更新卖家账户信息失败！");
            }
        }

    }
}
