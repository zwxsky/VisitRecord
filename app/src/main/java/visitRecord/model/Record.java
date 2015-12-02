package visitRecord.model;

import java.util.Date;

/**
 * Created by luo on 15-11-27.
 */
public class Record extends Base {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**记录名*/
    private String title;
    /**被探访者ID*/
    private Long uid;
    /**探访时间*/
    private Date gmtVisit;
    /**缘由记录*/
    private String reason;
    /**反馈日志记录*/
    private String logs;
    /**0未领取;1已领取;2已探访;3已取消*/
    private Integer status;

    public Record(){
    }

    public void setTitle(String value) {
        this.title = value;
    }

    public String getTitle() {
        return this.title;
    }
    public void setUid(Long value) {
        this.uid = value;
    }

    public Long getUid() {
        return this.uid;
    }


    public void setGmtVisit(Date value) {
        this.gmtVisit = value;
    }

    public Date getGmtVisit() {
        return this.gmtVisit;
    }

    public void setReason(String value) {
        this.reason = value;
    }

    public String getReason() {
        return this.reason;
    }

    public void setLogs(String value) {
        this.logs = value;
    }

    public String getLogs() {
        return this.logs;
    }

    public void setStatus(Integer value) {
        this.status = value;
    }

    public Integer getStatus() {
        return this.status;
    }

}
