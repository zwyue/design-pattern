package com.zwyue.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
public class ImporterService {

    @Getter
    @Setter
    private String param ;

    public void importData() {
        System.out.println(param);
    }
}
