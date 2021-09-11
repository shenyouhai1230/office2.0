package com.office.jobtask.mapper;

import com.office.jobtask.domain.Taskdetailed;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 开发任务表Mapper接口
 * 
 * @author syh
 * @date 2021-03-16
 */
public interface TaskdetailedMapper 
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
     * 删除开发任务表
     * 
     * @param xqid 开发任务表ID
     * @return 结果
     */
    public int deleteTaskdetailedById(String xqid);

    /**
     * 批量删除开发任务表
     * 
     * @param xqids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTaskdetailedByIds(String[] xqids);

    /**
     * 查询对应开发的开发任务总数
     *
     * @param qdname 开发者名字
     * @return
     */
    public List<Map<String,Object>> selectTaskdetailedByQdname(@Param("qdname")String qdname, @Param("state1")String state1, @Param("state2")String state2);
}
