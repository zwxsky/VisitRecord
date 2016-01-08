package visitRecord.model;

import java.util.Date;

/**
 * Created by luo on 15-12-8.
 */
public class RecordCond {

    private Date fromTime;
    private Date toTime;
    private Integer start;
    private Integer limit;
    /**0未领取;1已领取;2已探访;3已取消*/
    private Integer status;
    private Long uid;

    public Date getFromTime() {
        return fromTime;
    }

    public void setFromTime(Date fromTime) {
        this.fromTime = fromTime;
    }

    public Date getToTime() {
        return toTime;
    }

    public void setToTime(Date toTime) {
        this.toTime = toTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Integer getLimit() {
        return limit;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
