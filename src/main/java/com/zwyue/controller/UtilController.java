package com.zwyue.controller;

import com.zwyue.service.ImporterService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("util")
public class UtilController {

    @Resource
    ImporterService importerService;

    @GetMapping("import")
    public String importData(String param) {
        importerService.setParam(param);
        importerService.importData();
        return param;
    }
}
