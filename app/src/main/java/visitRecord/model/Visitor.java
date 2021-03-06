package visitRecord.model;

/**
 * Created by luo on 15-11-27.
 */
public class Visitor extends Base {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**记录ID*/
    private Long rid;
    /**探访者ID*/
    private Long uid;

    private String uname;


    public Visitor(){
    }

    public void setRid(Long value) {
        this.rid = value;
    }

    public Long getRid() {
        return this.rid;
    }
    public void setUid(Long value) {
        this.uid = value;
    }

    public Long getUid() {
        return this.uid;
    }


    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }
}

