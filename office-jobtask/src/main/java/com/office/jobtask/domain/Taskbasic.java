package com.office.jobtask.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.office.common.annotation.Excel;
import com.office.common.core.domain.BaseEntity;

/**
 * 任务总数对象 taskbasic
 * 
 * @author syh
 * @date 2021-03-17
 */
public class Taskbasic extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private String id;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 数量 */
    @Excel(name = "数量")
    private Integer counts;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setCounts(Integer counts) 
    {
        this.counts = counts;
    }

    public Integer getCounts() 
    {
        return counts;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("counts", getCounts())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
