package org.jeecg.modules.demo.aligenie.service.impl;

import com.alibaba.da.coin.ide.spi.meta.ExecuteCode;
import com.alibaba.da.coin.ide.spi.meta.ResultType;
import com.alibaba.da.coin.ide.spi.standard.TaskQuery;
import com.alibaba.da.coin.ide.spi.standard.TaskResult;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.demo.aligenie.service.ProductService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public TaskResult execute(TaskQuery query) {
        log.info("查询产品价格:{}", JSON.toJSONString(query));
        TaskResult result = new TaskResult();
        result.setReply("4000");
        result.setResultType(ResultType.RESULT);
        result.setExecuteCode(ExecuteCode.SUCCESS);
        return result;
    }
}
