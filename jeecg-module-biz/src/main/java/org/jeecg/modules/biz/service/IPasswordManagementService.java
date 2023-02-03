package org.jeecg.modules.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.biz.entity.PasswordManagement;

/**
 * @Description: 密码管理
 * @Author: jeecg-boot
 * @Date: 2023-02-01
 * @Version: V1.0
 */
public interface IPasswordManagementService extends IService<PasswordManagement> {

    /**
     * 新增密码
     *
     * @param passwordManagement 参数
     * @return 结果
     */
    Integer addPassword(PasswordManagement passwordManagement);

    /**
     * 更新密码
     *
     * @param passwordManagement 参数
     * @return 结果
     */
    Integer updatePassword(PasswordManagement passwordManagement);
}
