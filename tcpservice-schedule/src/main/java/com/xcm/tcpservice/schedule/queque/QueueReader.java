package com.xcm.tcpservice.schedule.queque;

import com.xcm.tcpservice.schedule.service.KeysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

/**
 * @描述 双缓存队列读取
 * @创建人 xcm
 * @创建时间 2021/5/19
 */
@Component
public class QueueReader {

    @Autowired
    private DoubleBufferQueue queue;
    @Autowired
    private KeysService keysService;

    @PostConstruct
    public void run() {
        try {
            while (true) {
                List<String> readList = queue.getReadList();
                while (readList.isEmpty()) {
                    try {
                        if (queue.getWriteListSize() >= 1000) {
                            queue.swap();
                            readList = queue.getReadList();
                        } else {
                            Thread.sleep(1);
                        }
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                int counter = 0;
                //  執行操作
                keysService.sendKyeToJob(readList);
                System.out.println("Read: " + counter);
                readList.clear();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
