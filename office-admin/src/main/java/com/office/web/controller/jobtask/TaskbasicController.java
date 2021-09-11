package com.office.web.controller.jobtask;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.office.common.annotation.Log;
import com.office.common.core.controller.BaseController;
import com.office.common.core.domain.AjaxResult;
import com.office.common.enums.BusinessType;
import com.office.jobtask.domain.Taskbasic;
import com.office.jobtask.service.ITaskbasicService;
import com.office.common.utils.poi.ExcelUtil;
import com.office.common.core.page.TableDataInfo;

/**
 * 任务总数Controller
 * 
 * @author syh
 * @date 2021-03-17
 */
@RestController
@RequestMapping("/jobtask/taskbasic")
public class TaskbasicController extends BaseController
{
    @Autowired
    private ITaskbasicService taskbasicService;

    /**
     * 查询任务总数列表
     */
    @PreAuthorize("@ss.hasPermi('jobtask:taskbasic:list')")
    @GetMapping("/list")
    public TableDataInfo list(Taskbasic taskbasic)
    {
        startPage();
        List<Taskbasic> list = taskbasicService.selectTaskbasicList(taskbasic);
        return getDataTable(list);
    }

    /**
     * 导出任务总数列表
     */
    @PreAuthorize("@ss.hasPermi('jobtask:taskbasic:export')")
    @Log(title = "任务总数", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Taskbasic taskbasic)
    {
        List<Taskbasic> list = taskbasicService.selectTaskbasicList(taskbasic);
        ExcelUtil<Taskbasic> util = new ExcelUtil<Taskbasic>(Taskbasic.class);
        return util.exportExcel(list, "taskbasic");
    }

    /**
     * 获取任务总数详细信息
     */
    @PreAuthorize("@ss.hasPermi('jobtask:taskbasic:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(taskbasicService.selectTaskbasicById(id));
    }

    /**
     * 新增任务总数
     */
    @PreAuthorize("@ss.hasPermi('jobtask:taskbasic:add')")
    @Log(title = "任务总数", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Taskbasic taskbasic)
    {
        return toAjax(taskbasicService.insertTaskbasic(taskbasic));
    }

    /**
     * 修改任务总数
     */
    @PreAuthorize("@ss.hasPermi('jobtask:taskbasic:edit')")
    @Log(title = "任务总数", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Taskbasic taskbasic)
    {
        return toAjax(taskbasicService.updateTaskbasic(taskbasic));
    }

    /**
     * 删除任务总数
     */
    @PreAuthorize("@ss.hasPermi('jobtask:taskbasic:remove')")
    @Log(title = "任务总数", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(taskbasicService.deleteTaskbasicByIds(ids));
    }
}
