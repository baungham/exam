package org.marker.certificate.task;
/**
 * Created by marker on 2018/2/24.
 */

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


/**
 *
 * 动态时间任务
 *
 *
 * @author marker
 * @create 2018-02-24 上午10:56
 **/
public class TimeTimerTask extends TimerTask {
    private Timer timer;
    private JLabel label;

    public TimeTimerTask(Timer timer, JLabel label) {
        this.timer = timer;
        this.label = label;
    }

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");


    @Override
    public void run() {
        label.setText(sdf.format(new Date()));
        timer.schedule(new TimeTimerTask(timer, label), 1000);// 在1秒后执行此任务
    }
}
