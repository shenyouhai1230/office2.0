package com.office.jobtask.service.impl;

import com.office.common.exception.CustomException;
import com.office.common.utils.DateUtils;
import com.office.common.utils.StringUtils;
import com.office.jobtask.domain.Taskdetailed;
import com.office.jobtask.mapper.TaskdetailedMapper;
import com.office.jobtask.service.ITaskdetailedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 开发任务表Service业务层处理
 * 
 * @author syh
 * @date 2021-03-16
 */
@Service
public class TaskdetailedServiceImpl implements ITaskdetailedService 
{
    private static final Logger log = LoggerFactory.getLogger(TaskdetailedServiceImpl.class);
    @Autowired
    private TaskdetailedMapper taskdetailedMapper;

    /**
     * 查询开发任务表
     * 
     * @param xqid 开发任务表ID
     * @return 开发任务表
     */
    @Override
    public Taskdetailed selectTaskdetailedById(String xqid)
    {
        return taskdetailedMapper.selectTaskdetailedById(xqid);
    }

    /**
     * 查询开发任务表列表
     * 
     * @param taskdetailed 开发任务表
     * @return 开发任务表
     */
    @Override
    public List<Taskdetailed> selectTaskdetailedList(Taskdetailed taskdetailed)
    {
        return taskdetailedMapper.selectTaskdetailedList(taskdetailed);
    }

    /**
     * 新增开发任务表
     * 
     * @param taskdetailed 开发任务表
     * @return 结果
     */
    @Override
    public int insertTaskdetailed(Taskdetailed taskdetailed)
    {
        return taskdetailedMapper.insertTaskdetailed(taskdetailed);
    }

    /**
     * 修改开发任务表
     * 
     * @param taskdetailed 开发任务表
     * @return 结果
     */
    @Override
    public int updateTaskdetailed(Taskdetailed taskdetailed)
    {
        taskdetailed.setUpdateTime(DateUtils.getNowDate());
        return taskdetailedMapper.updateTaskdetailed(taskdetailed);
    }

    /**
     * 批量删除开发任务表
     * 
     * @param xqids 需要删除的开发任务表ID
     * @return 结果
     */
    @Override
    public int deleteTaskdetailedByIds(String[] xqids)
    {
        return taskdetailedMapper.deleteTaskdetailedByIds(xqids);
    }

    /**
     * 删除开发任务表信息
     * 
     * @param xqid 开发任务表ID
     * @return 结果
     */
    @Override
    public int deleteTaskdetailedById(String xqid)
    {
        return taskdetailedMapper.deleteTaskdetailedById(xqid);
    }

    /**
     * 查询对应开发的开发任务总数
     *
     * @param qdname 开发者名字
     * @return
     */
    @Override
    public List<Map<String,Object>> selectTaskdetailedByQdname(String qdname,String state1,String state2){
        return taskdetailedMapper.selectTaskdetailedByQdname(qdname,state1,state2);
    }

    /**
     * 导入需求列表
     *
     * @param taskdetailedList 需求数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    @Override
    public String importTaskdetailed(List<Taskdetailed> taskdetailedList, Boolean isUpdateSupport)
    {
        if (StringUtils.isNull(taskdetailedList) || taskdetailedList.size() == 0)
        {
            throw new CustomException("导入需求数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (Taskdetailed taskdetailed : taskdetailedList)
        {
            try
            {
                // 验证是否存在这个需求
                Taskdetailed oldTaskdetailed = taskdetailedMapper.selectTaskdetailedById(taskdetailed.getXqid());
                if (StringUtils.isNull(oldTaskdetailed))
                {
                    this.insertTaskdetailed(taskdetailed);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、需求编号 " + taskdetailed.getXqid() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    this.updateTaskdetailed(taskdetailed);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、需求编号 " + taskdetailed.getXqid() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、需求编号 " + taskdetailed.getXqid() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、需求编号 " + taskdetailed.getXqid() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new CustomException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

}
