package visitRecord.model;

/**
 * Created by luo on 15-11-17.
 */
public class User extends Base {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String name;
    private String phone;
    private String password;
    private String pwd;
    private Integer age;
    private String address;
    private Integer type;
    private Boolean isDisable;

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public Boolean getIsDisable() {
        return isDisable;
    }
    public void setIsDisable(Boolean isDisable) {
        this.isDisable = isDisable;
    }

}