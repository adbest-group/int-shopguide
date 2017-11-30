package com.bt.shopguide.collector.mq.listener;

import com.bt.shopguide.collector.executor.DealmoonCouponJsonExecutor;
import com.bt.shopguide.collector.executor.GuangdiuJsonExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by caiting on 2017/9/12.
 */
public class GuangdiuListener implements MessageListener {
    Logger logger = LoggerFactory.getLogger(GuangdiuListener.class);

    @Autowired
    GuangdiuJsonExecutor guangdiuJsonExecutor ;

    @Override
    public void onMessage(Message message) {
        TextMessage msg = (TextMessage) message;
        String msgJson = null;
        try {
            msgJson = msg.getText();
            guangdiuJsonExecutor.execute(msgJson);
        } catch (JMSException e) {
            logger.error("get MQ message text faild!message:[{}]",new Object[]{msg});
            return;
        }
    }
}
