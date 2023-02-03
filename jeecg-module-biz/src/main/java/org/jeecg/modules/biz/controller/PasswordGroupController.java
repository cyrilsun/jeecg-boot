package org.jeecg.modules.biz.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.biz.entity.PasswordGroup;
import org.jeecg.modules.biz.service.IPasswordGroupService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;

 /**
 * @Description: 分组
 * @Author: jeecg-boot
 * @Date:   2023-02-01
 * @Version: V1.0
 */
@Api(tags="分组")
@RestController
@RequestMapping("/biz/passwordGroup")
@Slf4j
public class PasswordGroupController extends JeecgController<PasswordGroup, IPasswordGroupService> {
	@Autowired
	private IPasswordGroupService passwordGroupService;
	
	/**
	 * 分页列表查询
	 *
	 * @param passwordGroup
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "分组-分页列表查询")
	@ApiOperation(value="分组-分页列表查询", notes="分组-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<PasswordGroup>> queryPageList(PasswordGroup passwordGroup,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<PasswordGroup> queryWrapper = QueryGenerator.initQueryWrapper(passwordGroup, req.getParameterMap());
		Page<PasswordGroup> page = new Page<PasswordGroup>(pageNo, pageSize);
		IPage<PasswordGroup> pageList = passwordGroupService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param passwordGroup
	 * @return
	 */
	@AutoLog(value = "分组-添加")
	@ApiOperation(value="分组-添加", notes="分组-添加")
	//@RequiresPermissions("biz:tb_password_group:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody PasswordGroup passwordGroup) {
		passwordGroupService.save(passwordGroup);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param passwordGroup
	 * @return
	 */
	@AutoLog(value = "分组-编辑")
	@ApiOperation(value="分组-编辑", notes="分组-编辑")
	//@RequiresPermissions("biz:tb_password_group:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody PasswordGroup passwordGroup) {
		passwordGroupService.updateById(passwordGroup);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "分组-通过id删除")
	@ApiOperation(value="分组-通过id删除", notes="分组-通过id删除")
	//@RequiresPermissions("biz:tb_password_group:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		passwordGroupService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "分组-批量删除")
	@ApiOperation(value="分组-批量删除", notes="分组-批量删除")
	//@RequiresPermissions("biz:tb_password_group:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.passwordGroupService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "分组-通过id查询")
	@ApiOperation(value="分组-通过id查询", notes="分组-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<PasswordGroup> queryById(@RequestParam(name="id",required=true) String id) {
		PasswordGroup passwordGroup = passwordGroupService.getById(id);
		if(passwordGroup==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(passwordGroup);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param passwordGroup
    */
    //@RequiresPermissions("biz:tb_password_group:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, PasswordGroup passwordGroup) {
        return super.exportXls(request, passwordGroup, PasswordGroup.class, "分组");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("biz:tb_password_group:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, PasswordGroup.class);
    }

}
