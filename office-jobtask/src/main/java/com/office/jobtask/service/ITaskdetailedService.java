package com.office.jobtask.service;

import com.office.jobtask.domain.Taskdetailed;

import java.util.List;
import java.util.Map;

/**
 * 开发任务表Service接口
 * 
 * @author syh
 * @date 2021-03-16
 */
public interface ITaskdetailedService 
{
    /**
     * 查询开发任务表
     * 
     * @param xqid 开发任务表ID
     * @return 开发任务表
     */
    public Taskdetailed selectTaskdetailedById(String xqid);

    /**
     * 查询开发任务表列表
     * 
     * @param taskdetailed 开发任务表
     * @return 开发任务表集合
     */
    public List<Taskdetailed> selectTaskdetailedList(Taskdetailed taskdetailed);

    /**
     * 新增开发任务表
     * 
     * @param taskdetailed 开发任务表
     * @return 结果
     */
    public int insertTaskdetailed(Taskdetailed taskdetailed);

    /**
     * 修改开发任务表
     * 
     * @param taskdetailed 开发任务表
     * @return 结果
     */
    public int updateTaskdetailed(Taskdetailed taskdetailed);

    /**
     * 批量删除开发任务表
     * 
     * @param xqids 需要删除的开发任务表ID
     * @return 结果
     */
    public int deleteTaskdetailedByIds(String[] xqids);

    /**
     * 删除开发任务表信息
     * 
     * @param xqid 开发任务表ID
     * @return 结果
     */
    public int deleteTaskdetailedById(String xqid);

    /**
     * 查询对应开发的开发任务总数
     *
     * @param qdname 开发者名字
     * @return
     */
    public List<Map<String,Object>> selectTaskdetailedByQdname(String qdname,String state1,String state2);

    /**
     * 导入需求任务数据
     *
     * @param taskdetailedList 需求任务列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    public String importTaskdetailed(List<Taskdetailed> taskdetailedList, Boolean isUpdateSupport);
}
