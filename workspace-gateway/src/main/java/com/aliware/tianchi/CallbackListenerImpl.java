package com.aliware.tianchi;

import org.apache.dubbo.rpc.listener.CallbackListener;

/**
 * @author daofeng.xjf
 *
 * 客户端监听器
 * 可选接口
 * 用户可以基于获取获取服务端的推送信息，与 CallbackService 搭配使用
 *
 */
public class CallbackListenerImpl implements CallbackListener {

    @Override
    public void receiveServerMsg(String msg) {
//        String[] msgs=msg.split(" ");
//        String machine=msgs[0];
//        int maxThread = Integer.parseInt(msgs[3].split(":")[1].split(",")[0]);
//        int activeThread = Integer.parseInt(msgs[6].split(":")[1].split(",")[0]);
//        int provider=0;
//        if(machine.equals("large")){
//            provider=2;
//        }else if(machine.equals("medium")){
//            provider=1;
//        }
//        UserLoadBalance.activeThread[provider]=activeThread;
//        UserLoadBalance.maxThread[provider]=maxThread;
//        UserLoadBalance.flag=true;
//        System.out.println("收到了消息 :" + msg);
    }

}
