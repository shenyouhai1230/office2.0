package com.office.jobtask.service.impl;

import java.util.List;
import com.office.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.office.jobtask.mapper.TaskbasicMapper;
import com.office.jobtask.domain.Taskbasic;
import com.office.jobtask.service.ITaskbasicService;

/**
 * 任务总数Service业务层处理
 * 
 * @author syh
 * @date 2021-03-17
 */
@Service
public class TaskbasicServiceImpl implements ITaskbasicService 
{
    @Autowired
    private TaskbasicMapper taskbasicMapper;

    /**
     * 查询任务总数
     * 
     * @param id 任务总数ID
     * @return 任务总数
     */
    @Override
    public Taskbasic selectTaskbasicById(String id)
    {
        return taskbasicMapper.selectTaskbasicById(id);
    }

    /**
     * 查询任务总数列表
     * 
     * @param taskbasic 任务总数
     * @return 任务总数
     */
    @Override
    public List<Taskbasic> selectTaskbasicList(Taskbasic taskbasic)
    {
        return taskbasicMapper.selectTaskbasicList(taskbasic);
    }

    /**
     * 新增任务总数
     * 
     * @param taskbasic 任务总数
     * @return 结果
     */
    @Override
    public int insertTaskbasic(Taskbasic taskbasic)
    {
        return taskbasicMapper.insertTaskbasic(taskbasic);
    }

    /**
     * 修改任务总数
     * 
     * @param taskbasic 任务总数
     * @return 结果
     */
    @Override
    public int updateTaskbasic(Taskbasic taskbasic)
    {
        taskbasic.setUpdateTime(DateUtils.getNowDate());
        return taskbasicMapper.updateTaskbasic(taskbasic);
    }

    /**
     * 批量删除任务总数
     * 
     * @param ids 需要删除的任务总数ID
     * @return 结果
     */
    @Override
    public int deleteTaskbasicByIds(String[] ids)
    {
        return taskbasicMapper.deleteTaskbasicByIds(ids);
    }

    /**
     * 删除任务总数信息
     * 
     * @param id 任务总数ID
     * @return 结果
     */
    @Override
    public int deleteTaskbasicById(String id)
    {
        return taskbasicMapper.deleteTaskbasicById(id);
    }
}
