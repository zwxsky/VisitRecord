package visitRecord.model;

/**
 * Created by luo on 15-11-17.
 */
import java.io.Serializable;
import java.util.Date;

public class Base implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long id;

    private Date gmtCreate;

    private Date gmtModify;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }
}
