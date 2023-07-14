package org.jeecg.modules.demo.aligenie.controller;

import com.alibaba.da.coin.ide.spi.security.RSAUtil;
import com.alibaba.da.coin.ide.spi.standard.ResultModel;
import com.alibaba.da.coin.ide.spi.standard.SecurityWrapperTaskQuery;
import com.alibaba.da.coin.ide.spi.standard.TaskQuery;
import com.alibaba.da.coin.ide.spi.standard.TaskResult;
import com.alibaba.da.coin.ide.spi.trans.MetaFormat;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.demo.aligenie.service.ProductService;
import org.jeecg.modules.demo.constant.RSAConstant;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * description
 *
 * @author sunxiaogang
 */
@Slf4j
@RestController
@RequestMapping("/aligenie")
public class ProductController {

    @Resource
    private ProductService productService;

    @PostMapping("/product")
    public ResultModel<TaskResult> product(@RequestBody String securityWrapperTaskQuery,
                                           @RequestHeader HttpHeaders headers){
        log.info("请求查询产品价格,参数:{}", securityWrapperTaskQuery);
        //构建服务返回结果
        ResultModel<TaskResult> resultModel = new ResultModel<TaskResult>();
        // 需要先得到加密后传输的对象
        SecurityWrapperTaskQuery wrapperTaskQuery = MetaFormat
                .parseToWrapperQuery(securityWrapperTaskQuery);
        //然后利用RSA私钥解密语义理解对象，解密后为JSON格式的字符串
        String taskQuery = null;
        try {
            taskQuery = RSAUtil.decryptByPrivateKey(wrapperTaskQuery.getSecurityQuery(),
                    RSAConstant.PRIVATE_KEY);
            //将开发者平台识别到的语义理解的结果（json字符串格式）转换成TaskQuery
            TaskQuery query = MetaFormat.parseToQuery(taskQuery);

            //执行意图的功能
            TaskResult result = productService.execute(query);
            resultModel.setReturnCode("0");
            resultModel.setReturnValue(result);
        } catch (Exception e) {
            e.printStackTrace();
            resultModel.setReturnCode("500");
        }
        return resultModel;
    }

//    @PostMapping("/product")
//    public ResultModel<TaskResult> product(@RequestBody String taskQuery){
//        log.info("请求查询产品价格,参数:{}", taskQuery);
//        //将开发者平台识别到的语义理解的结果（json字符串格式）转换成TaskQuery
//        TaskQuery query = MetaFormat.parseToQuery(taskQuery);
//
//        //执行意图的功能
//        TaskResult result = productService.execute(query);
//        //构建服务返回结果
//        ResultModel<TaskResult> resultModel = new ResultModel<TaskResult>();
//        resultModel.setReturnCode("0");
//        resultModel.setReturnValue(result);
//        return resultModel;
//    }
}
