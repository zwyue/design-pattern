package com.zwyue.calculate;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Metrics {

    private final Map<String, List<Double>> responseTimes = new HashMap<>();
    private final Map<String, List<Double>> timestamps = new HashMap<>();

    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor() ;

    public void recordResponseTimes(String apiName,double responseTime) {
        responseTimes.putIfAbsent(apiName,new ArrayList<>()) ;
        responseTimes.get(apiName).add(responseTime);
    }

    public void recordTimestamps(String apiName,double timestamp){
        timestamps.putIfAbsent(apiName,new ArrayList<>()) ;
        timestamps.get(apiName).add(timestamp);
    }

    public void startRepeatedReport(long period, TimeUnit timeUnit) {
        executor.scheduleAtFixedRate(() -> {
            Gson gson = new Gson() ;

            Map<String,Map<String,Double>> stats = new HashMap<>() ;
            responseTimes.forEach((apiName,apiTimes)->{
                stats.putIfAbsent(apiName,new HashMap<>()) ;
                stats.get(apiName).put("max",max(apiTimes));
                stats.get(apiName).put("avg",avg(apiTimes));
            });

            timestamps.forEach((apiName,apiTimeStamp)->{
                stats.putIfAbsent(apiName,new HashMap<>()) ;
                stats.get(apiName).put("count",(double)apiTimeStamp.size()) ;
            });

//            System.out.println(gson.toJson(stats));
        },0,period,timeUnit);
    }

    private double max(List<Double> dataset){
        Optional<Double> optional = dataset.stream().max(Double::compare);
        return optional.isPresent()?optional.get():0;
    }

    private double avg(List<Double> dataset){
       double sum = dataset.stream().mapToDouble(Double::doubleValue).sum();
       return sum/dataset.size() ;
    }
}
