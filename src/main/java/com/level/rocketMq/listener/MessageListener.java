package com.level.rocketMq.listener;

import com.level.transaction.domain.AccountInfo;
import com.level.transaction.domain.Transaction;
import com.level.transaction.domain.bo.TransactionBo;
import com.level.transaction.service.AccountInfoService;
import com.level.transaction.service.TransactionService;
import com.level.transaction.service.UserService;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;

@Component
@RocketMQTransactionListener
public class MessageListener implements RocketMQLocalTransactionListener {

    @Autowired
    UserService userService;

    @Autowired
    TransactionService transactionService;

    @Autowired
    AccountInfoService accountInfoService;

    private final String  prefix = "rocketmq_";

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        try {
            String orderKey = String.valueOf(msg.getHeaders().get(prefix + RocketMQHeaders.KEYS));
            System.out.println("--监听者--收到消息：" + new String((byte[]) msg.getPayload(), StandardCharsets.UTF_8)+"， 消息KEY为："+orderKey);
            TransactionBo bo = (TransactionBo) arg;
            //执行本地事务
            Transaction transaction = new Transaction();
            transaction.setId(Integer.valueOf(orderKey));
            transaction.setBuyerId(bo.getBuyerId());
            transaction.setSellerId(bo.getSellerId());
            transaction.setPrice(bo.getPrice());
            //增加订单记录
            Boolean orderFlag = transactionService.addTransaction(transaction);
            //买家扣钱
            AccountInfo accountInfo = new AccountInfo();
            accountInfo.setUserId(bo.getBuyerId());
            accountInfo.setPrice(0 - bo.getPrice());
            Boolean priceFlag = accountInfoService.updateAccountInfo(accountInfo,3);
            if (orderFlag && priceFlag) {
                System.out.println("--监听者--事务匹配成功");
                return RocketMQLocalTransactionState.COMMIT;
            } else {
                return RocketMQLocalTransactionState.ROLLBACK;
            }
        }catch (Exception e){
            System.out.println("！！！监听者报错咯！！！");
            e.printStackTrace();
            return RocketMQLocalTransactionState.UNKNOWN;
        }
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {   //事务回查
        String orderKey = String.valueOf(msg.getHeaders().get(prefix + RocketMQHeaders.KEYS));
        System.out.println("--事务回查--收到消息：" + new String((byte[]) msg.getPayload(), StandardCharsets.UTF_8)+"， 消息KEY为："+orderKey);
        Transaction transaction = transactionService.getTransactionById(Integer.valueOf(orderKey));
        if(null != transaction) {  //查询到了订单
            System.out.println("--事务回查--事务匹配成功！");
            //提交事务，此次发送成功
            return RocketMQLocalTransactionState.COMMIT;
        }
        //回滚事务，此次发送取消
        return RocketMQLocalTransactionState.ROLLBACK;
    }

}
