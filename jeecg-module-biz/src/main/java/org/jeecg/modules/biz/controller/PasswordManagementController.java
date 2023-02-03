package org.jeecg.modules.biz.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.biz.entity.PasswordManagement;
import org.jeecg.modules.biz.service.IPasswordManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * @Description: 密码管理
 * @Author: jeecg-boot
 * @Date: 2023-02-01
 * @Version: V1.0
 */
@Api(tags = "密码管理")
@RestController
@RequestMapping("/biz/passwordManagement")
@Slf4j
public class PasswordManagementController extends JeecgController<PasswordManagement, IPasswordManagementService> {
    @Autowired
    private IPasswordManagementService passwordManagementService;

    /**
     * 分页列表查询
     *
     * @param passwordManagement
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    //@AutoLog(value = "密码管理-分页列表查询")
    @ApiOperation(value = "密码管理-分页列表查询", notes = "密码管理-分页列表查询")
    @GetMapping(value = "/list")
    public Result<IPage<PasswordManagement>> queryPageList(PasswordManagement passwordManagement,
                                                           @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                           @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                           HttpServletRequest req) {
        QueryWrapper<PasswordManagement> queryWrapper = QueryGenerator.initQueryWrapper(passwordManagement, req.getParameterMap());
        queryWrapper.select("id", "name", "website", "username", "code", "tip", "local_storage", "group_id", "remark");
        Page<PasswordManagement> page = new Page<PasswordManagement>(pageNo, pageSize);
        IPage<PasswordManagement> pageList = passwordManagementService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 添加
     *
     * @param passwordManagement
     * @return
     */
    @AutoLog(value = "密码管理-添加")
    @ApiOperation(value = "密码管理-添加", notes = "密码管理-添加")
    //@RequiresPermissions("biz:tb_password_management:add")
    @PostMapping(value = "/add")
    public Result<String> add(@RequestBody PasswordManagement passwordManagement) {
        passwordManagementService.addPassword(passwordManagement);
        return Result.OK("添加成功！");
    }

    /**
     * 编辑
     *
     * @param passwordManagement
     * @return
     */
    @AutoLog(value = "密码管理-编辑")
    @ApiOperation(value = "密码管理-编辑", notes = "密码管理-编辑")
    //@RequiresPermissions("biz:tb_password_management:edit")
    @RequestMapping(value = "/edit", method = {RequestMethod.PUT, RequestMethod.POST})
    public Result<String> edit(@RequestBody PasswordManagement passwordManagement) {
        passwordManagementService.updatePassword(passwordManagement);
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "密码管理-通过id删除")
    @ApiOperation(value = "密码管理-通过id删除", notes = "密码管理-通过id删除")
    //@RequiresPermissions("biz:tb_password_management:delete")
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam(name = "id", required = true) String id) {
        passwordManagementService.removeById(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "密码管理-批量删除")
    @ApiOperation(value = "密码管理-批量删除", notes = "密码管理-批量删除")
    //@RequiresPermissions("biz:tb_password_management:deleteBatch")
    @DeleteMapping(value = "/deleteBatch")
    public Result<String> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.passwordManagementService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    //@AutoLog(value = "密码管理-通过id查询")
    @ApiOperation(value = "密码管理-通过id查询", notes = "密码管理-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<PasswordManagement> queryById(@RequestParam(name = "id", required = true) String id) {
        PasswordManagement passwordManagement = passwordManagementService.getById(id);
        if (passwordManagement == null) {
            return Result.error("未找到对应数据");
        }
        return Result.OK(passwordManagement);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param passwordManagement
     */
    //@RequiresPermissions("biz:tb_password_management:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, PasswordManagement passwordManagement) {
        return super.exportXls(request, passwordManagement, PasswordManagement.class, "密码管理");
    }

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    //@RequiresPermissions("biz:tb_password_management:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, PasswordManagement.class);
    }

}
