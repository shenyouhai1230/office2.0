package com.office.jobtask.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.office.common.annotation.Excel;
import com.office.common.core.domain.BaseEntity;

/**
 * 开发任务表对象 taskdetailed
 * 
 * @author syh
 * @date 2021-03-16
 */
public class Taskdetailed extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @Excel(name = "需求编号")
    private String xqid;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String bgid;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String taskname;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String begintime;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String endtime;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String ywname;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String hdname;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String qdname;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String state;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String xqquestion;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String jkquestion;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String csquestion;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String isdelay;

    /** 规模估算状态 */
    @Excel(name = "规模估算状态")
    private String kfdays;

    /** 性能测试状态 */
    @Excel(name = "性能测试状态")
    private String usedmanhours;

    /** 质验文档状态 */
    @Excel(name = "质验文档状态")
    private String totalmanhours;
    public void setKfdays(String kfdays)
    {
        this.kfdays = kfdays;
    }

    public String getKfdays()
    {
        return kfdays;
    }
    public void setUsedmanhours(String usedmanhours)
    {
        this.usedmanhours = usedmanhours;
    }

    public String getUsedmanhours()
    {
        return usedmanhours;
    }
    public void setTotalmanhours(String totalmanhours)
    {
        this.totalmanhours = totalmanhours;
    }

    public String getTotalmanhours()
    {
        return totalmanhours;
    }


    public void setXqid(String xqid) 
    {
        this.xqid = xqid;
    }

    public String getXqid() 
    {
        return xqid;
    }
    public void setBgid(String bgid) 
    {
        this.bgid = bgid;
    }

    public String getBgid() 
    {
        return bgid;
    }
    public void setTaskname(String taskname) 
    {
        this.taskname = taskname;
    }

    public String getTaskname() 
    {
        return taskname;
    }
    public void setBegintime(String begintime) 
    {
        this.begintime = begintime;
    }

    public String getBegintime() 
    {
        return begintime;
    }
    public void setEndtime(String endtime) 
    {
        this.endtime = endtime;
    }

    public String getEndtime() 
    {
        return endtime;
    }
    public void setYwname(String ywname) 
    {
        this.ywname = ywname;
    }

    public String getYwname() 
    {
        return ywname;
    }
    public void setHdname(String hdname) 
    {
        this.hdname = hdname;
    }

    public String getHdname() 
    {
        return hdname;
    }
    public void setQdname(String qdname) 
    {
        this.qdname = qdname;
    }

    public String getQdname() 
    {
        return qdname;
    }
    public void setState(String state) 
    {
        this.state = state;
    }

    public String getState() 
    {
        return state;
    }
    public void setXqquestion(String xqquestion) 
    {
        this.xqquestion = xqquestion;
    }

    public String getXqquestion() 
    {
        return xqquestion;
    }
    public void setJkquestion(String jkquestion) 
    {
        this.jkquestion = jkquestion;
    }

    public String getJkquestion() 
    {
        return jkquestion;
    }
    public void setCsquestion(String csquestion) 
    {
        this.csquestion = csquestion;
    }

    public String getCsquestion() 
    {
        return csquestion;
    }
    public void setIsdelay(String isdelay) 
    {
        this.isdelay = isdelay;
    }

    public String getIsdelay() 
    {
        return isdelay;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("xqid", getXqid())
            .append("bgid", getBgid())
            .append("taskname", getTaskname())
            .append("begintime", getBegintime())
            .append("endtime", getEndtime())
            .append("ywname", getYwname())
            .append("hdname", getHdname())
            .append("qdname", getQdname())
            .append("state", getState())
            .append("xqquestion", getXqquestion())
            .append("jkquestion", getJkquestion())
            .append("csquestion", getCsquestion())
            .append("updateTime", getUpdateTime())
            .append("isdelay", getIsdelay())
            .toString();
    }
}
