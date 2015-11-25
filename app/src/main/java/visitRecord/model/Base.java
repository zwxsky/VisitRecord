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

    private Date gmtCreated;

    private Date gmtModify;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }
}
