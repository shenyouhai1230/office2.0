package com.office.jobtask.mapper;

import com.office.jobtask.domain.Taskbasic;

import java.util.List;

/**
 * 任务总数Mapper接口
 * 
 * @author syh
 * @date 2021-03-17
 */
public interface TaskbasicMapper 
{
    /**
     * 查询任务总数
     * 
     * @param id 任务总数ID
     * @return 任务总数
     */
    public Taskbasic selectTaskbasicById(String id);

    /**
     * 查询任务总数列表
     * 
     * @param taskbasic 任务总数
     * @return 任务总数集合
     */
    public List<Taskbasic> selectTaskbasicList(Taskbasic taskbasic);

    /**
     * 新增任务总数
     * 
     * @param taskbasic 任务总数
     * @return 结果
     */
    public int insertTaskbasic(Taskbasic taskbasic);

    /**
     * 修改任务总数
     * 
     * @param taskbasic 任务总数jobtask:taskbasic:list
     * @return 结果
     */
    public int updateTaskbasic(Taskbasic taskbasic);

    /**
     * 删除任务总数
     * 
     * @param id 任务总数ID
     * @return 结果
     */
    public int deleteTaskbasicById(String id);

    /**
     * 批量删除任务总数
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTaskbasicByIds(String[] ids);
}
