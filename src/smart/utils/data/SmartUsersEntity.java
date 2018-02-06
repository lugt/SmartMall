package smart.utils.data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "smart_users", schema = "smartmall", catalog = "")
public class SmartUsersEntity {
    private int uid;
    private String usn;
    private String pss;
    private String name;
    private String title;
    private String priv;
    private int state;
    private String sess;
    private Long phone;

    @Id
    @Column(name = "uid")
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "usn")
    public String getUsn() {
        return usn;
    }

    public void setUsn(String usn) {
        this.usn = usn;
    }

    @Basic
    @Column(name = "pss")
    public String getPss() {
        return pss;
    }

    public void setPss(String pss) {
        this.pss = pss;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "priv")
    public String getPriv() {
        return priv;
    }

    public void setPriv(String priv) {
        this.priv = priv;
    }

    @Basic
    @Column(name = "state")
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Basic
    @Column(name = "sess")
    public String getSess() {
        return sess;
    }

    public void setSess(String sess) {
        this.sess = sess;
    }

    @Basic
    @Column(name = "phone")
    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SmartUsersEntity that = (SmartUsersEntity) o;
        return uid == that.uid &&
                state == that.state &&
                Objects.equals(usn, that.usn) &&
                Objects.equals(pss, that.pss) &&
                Objects.equals(name, that.name) &&
                Objects.equals(title, that.title) &&
                Objects.equals(priv, that.priv) &&
                Objects.equals(sess, that.sess) &&
                Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {

        return Objects.hash(uid, usn, pss, name, title, priv, state, sess, phone);
    }
}
