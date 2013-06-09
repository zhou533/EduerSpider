package com.gozap.spider.thread;

import com.gozap.spider.model.Saveable;
import com.gozap.spider.utils.Common;
import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: chouchris
 * Date: 13-6-7
 * Time: PM4:51
 * To change this template use File | Settings | File Templates.
 */
public class StoreThread implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(StoreThread.class);

    @Override
    public void run() {
        while (true){
            try {
                Saveable result = Common.ES_SAVE_QUEUE.poll(30, TimeUnit.MINUTES);
                if (result == null && Common.URL_PARSE_END){
                    return;
                }
                if (result == null){
                    continue;
                }
                result.save2Csv();
                LOGGER.info("save record:" + result.toString());
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
