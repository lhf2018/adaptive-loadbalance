package com.aliware.tianchi;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.cluster.LoadBalance;

import java.util.List;
import java.util.Random;

/**
 * @author daofeng.xjf
 *
 * 负载均衡扩展接口
 * 必选接口，核心接口
 * 此类可以修改实现，不可以移动类或者修改包名
 * 选手需要基于此类实现自己的负载均衡算法
 */
public class UserLoadBalance implements LoadBalance {
    private static Random random=new Random();
    private static int num=random.nextInt(3)+1;
    @Override
    public <T> Invoker<T> select(List<Invoker<T>> invokers, URL url, Invocation invocation) throws RpcException {
        int temp=random.nextInt(3);
        switch (num){
            case 0:break;
            case 1:temp=random.nextInt(2)+1;
            break;
            case 2:temp=random.nextInt(2)==0?0:2;
            break;
            case 3:temp=random.nextInt(2);
            break;
            case 4:temp=1;
            break;
            case 5:temp=0;
            break;
        }
        num+=(temp+1);
        num%=6;
        System.out.println("------第 "+temp);
        return invokers.get(temp);
    }
//    @Override
//    public <T> Invoker<T> select(List<Invoker<T>> invokers, URL url, Invocation invocation) throws RpcException {
//        return invokers.get(ThreadLocalRandom.current().nextInt(invokers.size()));
//    }
}
