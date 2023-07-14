package org.jeecg.modules.demo.aligenie.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 认证
 */
@Slf4j
@RestController
@RequestMapping("/aligenie")
public class AliGenieAuthController {

    @GetMapping("/276eabfa4d2e3c2a6688b375b18218b2.txt")
    public String auth(){
        log.info("开始认证...");
        return "Jfc4Z4Ur15JwUBuvUQD5wg7Nu8+l+HscqYlfofbyJdYCDU7VmDoNMRTO+r81topE";
    }
}
