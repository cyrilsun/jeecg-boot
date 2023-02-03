package org.jeecg.modules.biz.service.impl;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.modules.biz.entity.PasswordManagement;
import org.jeecg.modules.biz.mapper.PasswordManagementMapper;
import org.jeecg.modules.biz.service.IPasswordManagementService;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

/**
 * @Description: 密码管理
 * @Author: jeecg-boot
 * @Date: 2023-02-01
 * @Version: V1.0
 */
@Slf4j
@Service
public class PasswordManagementServiceImpl extends ServiceImpl<PasswordManagementMapper, PasswordManagement> implements IPasswordManagementService {

    private final PasswordManagementMapper passwordManagementMapper;

    public PasswordManagementServiceImpl(PasswordManagementMapper passwordManagementMapper) {
        this.passwordManagementMapper = passwordManagementMapper;
    }

    @Override
    public Integer addPassword(PasswordManagement passwordManagement) {
        log.debug("新增密码:{}", passwordManagement);
        PasswordManagement passwordManagementDB = this.passwordManagementMapper.selectOne(new LambdaQueryWrapper<PasswordManagement>()
                .eq(PasswordManagement::getWebsite, passwordManagement.getWebsite())
                .eq(PasswordManagement::getUsername, passwordManagement.getUsername())
                .last("limit 1"));
        if (passwordManagementDB != null) {
            throw new JeecgBootException("网站已存在");
        }
        // 设置参数
        this.setParam(passwordManagement);
        return this.passwordManagementMapper.insert(passwordManagement);
    }

    /**
     * 设置参数
     *
     * @param passwordManagement
     */
    private void setParam(PasswordManagement passwordManagement) {
        // 密钥长度
        String salt = passwordManagement.getSalt();
        int length = StrUtil.isEmpty(salt) ? 0 : salt.length();
        if (length > 0) {
            passwordManagement.setCustom(1); // 自定义秘钥
        }
        // 128 192 256位
        if (length == 16 || length == 24 || length == 32) {
            // 设置密码
            passwordManagement.setCode(Base64.encode(SecureUtil.aes(salt
                    .getBytes(StandardCharsets.UTF_8)).encrypt(passwordManagement.getCode())));
            passwordManagement.setSalt(Base64.encode(salt)); // 秘钥
        } else {
            String fill = UUID.fastUUID().toString(true).substring(length);
            String saltNew = StrUtil.isEmpty(salt) ? fill : salt + fill;
            // 设置密码
            passwordManagement.setCode(Base64.encode(SecureUtil.aes((saltNew)
                    .getBytes(StandardCharsets.UTF_8)).encrypt(passwordManagement.getCode())));
            // 设置填充
            passwordManagement.setFill(Base64.encode(fill));
            passwordManagement.setSalt(Base64.encode(saltNew)); // 秘钥
        }
        // 本地存储秘钥
        if (passwordManagement.getLocalStorage() != null && passwordManagement.getLocalStorage() == 1) {
            passwordManagement.setSalt("");
        }
    }

    @Override
    public Integer updatePassword(PasswordManagement passwordManagement) {
        log.debug("更新密码:{}", passwordManagement);

        return this.passwordManagementMapper.updateById(passwordManagement);
    }
}
