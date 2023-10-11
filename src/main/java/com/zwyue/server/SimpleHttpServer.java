package com.zwyue.server;

import com.zwyue.interfaces.Viewer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleHttpServer {

    private String host ;

    private int port ;

    private Map<String, List<Viewer>> viewers = new HashMap<>() ;

    public SimpleHttpServer(String host,int port) {
        this.host = host ;
        this.port = port ;
    }

    public void addViewers(String urlDirectory, Viewer viewer) {
        if(!this.viewers.containsKey(urlDirectory)) {
           this.viewers.put(urlDirectory,new ArrayList<>()) ;
        }
        this.viewers.get(urlDirectory).add(viewer);
    }

    public void run() {};
}
