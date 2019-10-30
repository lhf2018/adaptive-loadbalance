package com.aliware.tianchi;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.RpcStatus;
import org.apache.dubbo.rpc.cluster.LoadBalance;

import java.util.List;

/**
 * @author daofeng.xjf
 *
 * 负载均衡扩展接口
 * 必选接口，核心接口
 * 此类可以修改实现，不可以移动类或者修改包名
 * 选手需要基于此类实现自己的负载均衡算法
 */
public class UserLoadBalance implements LoadBalance {
    private static volatile RpcStatus[] statuses=new RpcStatus[3];//记录每种的访问次数
    private static double[] weight={1,2,3};
    public static volatile int[] maxThread=new int[3];
    public static volatile int[] activeThread=new int[3];
    public static volatile boolean flag=false;


//    @Override
//    public <T> Invoker<T> select(List<Invoker<T>> invokers, URL url, Invocation invocation) throws RpcException {
//        if(statuses[0]==null){
//            statuses[0]=RpcStatus.getStatus(invokers.get(0).getUrl(),invocation.getMethodName());
//            statuses[1]=RpcStatus.getStatus(invokers.get(1).getUrl(),invocation.getMethodName());
//            statuses[2]=RpcStatus.getStatus(invokers.get(2).getUrl(),invocation.getMethodName());
//
//        }
//        int big = 0;
////        double min = (1 - activeThread[0] * 1.0 / maxThread[0]) / (weight[0]*statuses[0].getActive());//越大越好
//        double max=statuses[0].getActive()*1.0/weight[0];
//        for (int i = 1; i < invokers.size(); i++) {
//            double temp = (1 - activeThread[i] * 1.0 / maxThread[i]) / (weight[i]*statuses[i].getActive());
//            if (temp > max) {
//                big = i;
//                max = temp;
//            }
//        }
//        int index=-1;
//        max=0;
//        for(int i=0;i<invokers.size();i++){
//            if(i==big)continue;
//            double temp=(1 - activeThread[i] * 1.0 / maxThread[i]);
//            if(temp>max){
//                index=i;
//                max=temp;
//            }
//        }
//        for(int i=0;i<3;i++){
//            System.out.println("statuses["+i+"]------------------:   "+statuses[i].getActive());
//        }
//        if(flag) {
//            for (int i = 0; i < statuses.length; i++) {
//                RpcStatus.removeStatus(invokers.get(0).getUrl(),invocation.getMethodName());
//                statuses[0]=RpcStatus.getStatus(invokers.get(0).getUrl(),invocation.getMethodName());
//            }
//
//            flag = false;
//        }
//        RpcStatus.beginCount(invokers.get(index).getUrl(),invocation.getMethodName(),0);
//        return invokers.get(index);
//    }

    @Override
    public <T> Invoker<T> select(List<Invoker<T>> invokers, URL url, Invocation invocation) throws RpcException {
        if(statuses[0]==null){
            statuses[0]=RpcStatus.getStatus(invokers.get(0).getUrl(),invocation.getMethodName());
            statuses[1]=RpcStatus.getStatus(invokers.get(1).getUrl(),invocation.getMethodName());
            statuses[2]=RpcStatus.getStatus(invokers.get(2).getUrl(),invocation.getMethodName());

        }
        int index=0;
        double min=statuses[0].getActive()*1.0/(weight[0]);
        for(int i=1;i<invokers.size();i++){
            double temp=statuses[i].getActive()*1.0/(weight[i]);
            if(temp<min){
                index=i;
                min=temp;
            }
        }
//        for (int i=0;i<invokers.size();i++){
//            System.out.println("statuses["+i+"]------------------:   "+statuses[i].getActive());
//        }
        RpcStatus.beginCount(invokers.get(index).getUrl(),invocation.getMethodName(),0);
        return invokers.get(index);
    }
}
