package com.office.web.controller.jobtask;

import com.office.common.annotation.DataScope;
import com.office.common.annotation.Log;
import com.office.common.core.controller.BaseController;
import com.office.common.core.domain.AjaxResult;
import com.office.common.core.domain.entity.SysUser;
import com.office.common.core.page.TableDataInfo;
import com.office.common.enums.BusinessType;
import com.office.common.utils.StringUtils;
import com.office.common.utils.poi.ExcelUtil;
import com.office.jobtask.domain.Taskdetailed;
import com.office.jobtask.service.ITaskdetailedService;
import com.office.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 开发任务表Controller
 * 
 * @author syh
 * @date 2021-03-16
 */
@RestController
@RequestMapping("/jobtask/taskdetailed")
public class TaskdetailedController extends BaseController
{
    @Autowired
    private ITaskdetailedService taskdetailedService;
    @Autowired
    private ISysUserService userService;
    /**
     * 查询开发任务表列表
     */
    @PreAuthorize("@ss.hasPermi('jobtask:taskdetailed:list')")
    @GetMapping("/list")
    public TableDataInfo list(Taskdetailed taskdetailed)
    {
        startPage();
        List<Taskdetailed> list = taskdetailedService.selectTaskdetailedList(taskdetailed);
        return getDataTable(list);
    }

    /**
     * 导出开发任务表列表
     */
    @PreAuthorize("@ss.hasPermi('jobtask:taskdetailed:export')")
    @Log(title = "开发任务表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Taskdetailed taskdetailed)
    {
        List<Taskdetailed> list = taskdetailedService.selectTaskdetailedList(taskdetailed);
        ExcelUtil<Taskdetailed> util = new ExcelUtil<Taskdetailed>(Taskdetailed.class);
        return util.exportExcel(list, "taskdetailed");
    }

    /**
     * 获取开发任务表详细信息
     */
    @PreAuthorize("@ss.hasPermi('jobtask:taskdetailed:query')")
    @GetMapping(value = "/{xqid}")
    public AjaxResult getInfo(@PathVariable("xqid") String xqid)
    {
        return AjaxResult.success(taskdetailedService.selectTaskdetailedById(xqid));
    }

    /**
     * 新增开发任务表
     */
    @PreAuthorize("@ss.hasPermi('jobtask:taskdetailed:add')")
    @Log(title = "开发任务表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Taskdetailed taskdetailed)
    {
        return toAjax(taskdetailedService.insertTaskdetailed(taskdetailed));
    }

    /**
     * 修改开发任务表
     */
    @PreAuthorize("@ss.hasPermi('jobtask:taskdetailed:edit')")
    @Log(title = "开发任务表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Taskdetailed taskdetailed)
    {
        return toAjax(taskdetailedService.updateTaskdetailed(taskdetailed));
    }

    /**
     * 删除开发任务表
     */
    @PreAuthorize("@ss.hasPermi('jobtask:taskdetailed:remove')")
    @Log(title = "开发任务表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{xqids}")
    public AjaxResult remove(@PathVariable String[] xqids)
    {
        return toAjax(taskdetailedService.deleteTaskdetailedByIds(xqids));
    }
    /**
     * 开发任务数柱状图
     */
    @GetMapping("/qdnameCount")
    @DataScope(deptAlias = "d", userAlias = "u")
    public AjaxResult qdnameCount(String state1,String state2)
    {
        List<SysUser> list = userService.selectUserList(new SysUser());
        List<Map<String, Object>> reList = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = null;
        for (SysUser user : list) {
            Map<String, Object> resmap = new HashMap<String, Object>();
            resmap.put("userId", user.getUserId());
            resmap.put("qdname", user.getNickName());
            resmap.put("nums", 0);
            reList.add(resmap);
        }
        for (SysUser user : list) {
            Map<String, Object> resmap = null;
            List<Map<String, Object>> tasks = taskdetailedService.selectTaskdetailedByQdname(user.getNickName(), state1, state2);
            int taskNum = tasks.size();
            //-------------组长----------------
            if (reList.stream().anyMatch(e -> e.get("userId").equals(user.getUserId()))) {
                List<Map<String, Object>> tempList = reList.stream().filter(e -> e.get("userId").equals(user.getUserId())).collect(Collectors.toList());
                resmap = tempList.get(0);
                resmap.put("nums", taskNum);
            }
            //----------------成员---------------
            for (Map<String, Object> task : tasks) {
                Map<String, Object> rememberMap = null;
                String userIDs = task.get("jkquestion") + "";
                if (StringUtils.isNotNull(userIDs) && StringUtils.isNotEmpty(userIDs)) {
                    String[] userID = userIDs.split(",");
                    for (String id : userID) {
                        boolean a = reList.stream().anyMatch(e ->  id.equals(e.get("userId")+""));
                        if (a) {
                            List<Map<String, Object>> tempList = reList.stream().filter(e -> id.equals(e.get("userId")+"")).collect(Collectors.toList());
                            rememberMap = tempList.get(0);
                            String count = rememberMap.get("nums") + "";
                            System.out.println("qqqq>>>>>>>>>>>>>>>"+count);
                            rememberMap.put("nums", String.valueOf(Integer.valueOf(count) + 1));
                            System.out.println("hhh>>>>>>>>>>>>>>>"+rememberMap.get("nums"));
                        }
                    }
                }
            }
        }
        return AjaxResult.success(reList);
    }
    @Log(title = "需求文件上传", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('system:user:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<Taskdetailed> util = new ExcelUtil<Taskdetailed>(Taskdetailed.class);
        List<Taskdetailed> taskdetailedList = util.importExcel(file.getInputStream());
        String message = taskdetailedService.importTaskdetailed(taskdetailedList, updateSupport);
        return AjaxResult.success(message);
    }

    @GetMapping("/importTemplate")
    public AjaxResult importTemplate()
    {
        ExcelUtil<Taskdetailed> util = new ExcelUtil<Taskdetailed>(Taskdetailed.class);
        return util.importTemplateExcel("需求数据");
    }
}
