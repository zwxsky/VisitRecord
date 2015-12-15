package visitRecord.model;

import java.util.List;

/**
 * Created by luo on 15-11-27.
 */
public class RecordModel extends Record{
    //被探访者名字
    private String name;
    //被探访者地址
    private String addr;
    //探访时间
    private String date;

    private Long uidEdit;

    private List<VisitorModel> visitors;

    public Long getUidEdit() {
        return uidEdit;
    }

    public void setUidEdit(Long uidEdit) {
        this.uidEdit = uidEdit;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public List<VisitorModel> getVisitors() {
        return visitors;
    }

    public void setVisitors(List<VisitorModel> visitors) {
        this.visitors = visitors;
    }
}
