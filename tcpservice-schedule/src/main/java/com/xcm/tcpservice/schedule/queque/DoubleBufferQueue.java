package com.xcm.tcpservice.schedule.queque;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @描述 双缓存队列
 * @创建人 xcm
 * @创建时间 2021/5/19
 */
@Component
public class DoubleBufferQueue {
    private List<String> readList = new ArrayList<String>();
    private List<String> writeList = new ArrayList<String>();

    public void push(String value) {
        synchronized (writeList) {
            writeList.add(value);
        }
    }

    public int getWriteListSize() {
        synchronized (writeList) {
            return writeList.size();
        }
    }

    public List<String> getReadList() {
        return readList;
    }

    public void swap() {
        synchronized(writeList) {
            List<String> temp = readList;
            readList = writeList;
            writeList = temp;

            writeList.clear();
        }
    }
}
