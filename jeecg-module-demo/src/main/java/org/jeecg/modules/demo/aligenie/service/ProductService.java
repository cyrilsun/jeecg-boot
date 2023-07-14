package org.jeecg.modules.demo.aligenie.service;

import com.alibaba.da.coin.ide.spi.standard.TaskQuery;
import com.alibaba.da.coin.ide.spi.standard.TaskResult;

/**
 * 产品服务
 */
public interface ProductService {

    /**
     * 查询产品价格
     *
     * @param query 参数
     * @return 产品价格
     */
    TaskResult execute(TaskQuery query);
}
