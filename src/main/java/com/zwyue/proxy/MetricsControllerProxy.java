package com.zwyue.proxy;

import com.zwyue.controller.MetricsController;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MetricsControllerProxy {

    private MetricsController metricsController ;

    public MetricsControllerProxy() {
        this.metricsController = new MetricsController();
    }

    public Object createProxy(Object proxiedObject) {
        Class<?>[] interfaces = proxiedObject.getClass().getInterfaces() ;
        DynamicProxyHandler dynamicProxyHandler = new DynamicProxyHandler(proxiedObject);

        return Proxy.newProxyInstance(proxiedObject.getClass().getClassLoader(),interfaces,dynamicProxyHandler);
    }

    private class DynamicProxyHandler implements InvocationHandler {

        private Object proxiedObject ;

        public DynamicProxyHandler(Object proxiedObject){
            this.proxiedObject = proxiedObject ;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            long startTimestamp = System.currentTimeMillis() ;
            Object result = method.invoke(proxiedObject,args) ;
            long endTimestamp = System.currentTimeMillis() ;

            long responseTime = endTimestamp-startTimestamp ;
            String apiName = proxiedObject.getClass().getName().concat(":").concat(method.getName()) ;
//            RequestInfo requestInfo = new RequestInfo(apiName,responseTime,startTimestamp);
//
//            metricsController

            return null;
        }


    }

}
