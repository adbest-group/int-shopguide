package com.bt.shopguide.api.system.listener;

import com.bt.shopguide.api.system.GlobalVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by caiting on 2017/9/28.
 */
@Component("LocalApplicationListener")
public class LocalApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private GlobalVariable globalVariable;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(event.getApplicationContext().getParent()==null){
            globalVariable.init();
        }
    }


}
