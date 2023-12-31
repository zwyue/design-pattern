package com.zwyue.calculate;

import java.util.List;
import org.apache.coyote.RequestInfo;

public class Aggregator {

    public static RequestStat aggregate(List<RequestInfo> requestInfos,long durationMills){
        double maxRespTime = Double.MAX_VALUE ;
        double minRespTime = Double.MAX_VALUE ;
        double avgRespTime = -1 ;
        double p999ResponseTime = -1 ;
        double p99ResponseTime = -1 ;
        double sumRespTime = 0 ;
        long count = 0 ;

        for (RequestInfo requestInfo : requestInfos) {
            ++count ;
            double responseTime = requestInfo.getRequestProcessingTime() ;
        }

        return new RequestStat() ;
    }


    public static class RequestStat {
        private double maxResponseTime ;
        private double minResponseTime ;
        private double avgResponseTime ;
        private double p999ResponseTime ;
        private double p99ResponseTime ;
        private long count ;
        private long qps ;
        // ...
    }
}
