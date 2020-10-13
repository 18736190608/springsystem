package com.gql.baseabstract;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**entity 基类
 * @author gql
 * @date 2020/7/28
 **/
public abstract class  BaseEntity implements Serializable {
    public int id;   //  主键
    public String createTime;   //
    public String createBy;  //  创建人
    public String oprationBy;  //  操作人
    public String oprationTime; //   操作时间
    public String updateTime;   // 更新时间

    public String getUpdateTime() {
        return updateTime;
    }

    public int getId() {
        return id;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getOprationTime() {
        return oprationTime;
    }

    public void setOprationTime(String oprationTime) {
        this.oprationTime = oprationTime;
    }

    public String getOprationBy() {
        return oprationBy;
    }

    public void setOprationBy(String oprationBy) {
        this.oprationBy = oprationBy;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateDateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
