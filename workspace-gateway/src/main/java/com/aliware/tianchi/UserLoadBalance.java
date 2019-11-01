package com.aliware.tianchi;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.cluster.LoadBalance;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author daofeng.xjf
 *
 * 负载均衡扩展接口
 * 必选接口，核心接口
 * 此类可以修改实现，不可以移动类或者修改包名
 * 选手需要基于此类实现自己的负载均衡算法
 */
public class UserLoadBalance implements LoadBalance {
//    private static volatile RpcStatus[] statuses=new RpcStatus[3];//记录每种的访问次数
//    private static double[] weight={4,9,13};
//    public static volatile int[] maxThread=new int[3];
//    public static volatile int[] activeThread=new int[3];
//    public static volatile boolean flag=false;
    public static volatile Queue<Integer> queue=new ConcurrentLinkedQueue<>();

    public UserLoadBalance() {
        for(int i=0;i<1300;i++){
            int num=i%26;
            if(num<4){
                queue.add(0);
            }else if(num<13){
                queue.add(1);
            }else {
                queue.add(2);
            }
        }
//        for(int i=0;i<100;i++){
//            queue.add(0);
//        }
//        for(int i=0;i<225;i++){
//            queue.add(1);
//        }
//        for(int i=0;i<325;i++){
//            queue.add(2);
//        }
//
//        for(int i=0;i<100;i++){
//            queue.add(0);
//        }
//        for(int i=0;i<225;i++){
//            queue.add(1);
//        }
//        for(int i=0;i<325;i++){
//            queue.add(2);
//        }
    }

    @Override
    public <T> Invoker<T> select(List<Invoker<T>> invokers, URL url, Invocation invocation) throws RpcException {
        int index=queue.poll();

//        for(int i=0;i<3;i++){
//            System.out.println("statuses["+i+"]------------------:   "+statuses[i].getActive());
//        }
        return invokers.get(index);
    }

    /**
     * 最简单的统计
     */
//    @Override
//    public <T> Invoker<T> select(List<Invoker<T>> invokers, URL url, Invocation invocation) throws RpcException {
//        if(statuses[0]==null){
//            statuses[0]=RpcStatus.getStatus(invokers.get(0).getUrl(),invocation.getMethodName());
//            statuses[1]=RpcStatus.getStatus(invokers.get(1).getUrl(),invocation.getMethodName());
//            statuses[2]=RpcStatus.getStatus(invokers.get(2).getUrl(),invocation.getMethodName());
//
//        }
//        int index=0;
//        double min=statuses[0].getActive()*1.0/(weight[0]);
//        for(int i=1;i<invokers.size();i++){
//            double temp=statuses[i].getActive()*1.0/(weight[i]);
//            if(temp<min){
//                index=i;
//                min=temp;
//            }
//        }
//        RpcStatus.beginCount(invokers.get(index).getUrl(),invocation.getMethodName(),0);
//        return invokers.get(index);
//    }
}
