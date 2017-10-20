package smart.utils.data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "smart_users", schema = "smartmall", catalog = "")
public class SmartUsersEntity {
    @Id
    @GeneratedValue
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
    @Column(name = "uid", nullable = false)
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "usn", nullable = false, length = 40)
    public String getUsn() {
        return usn;
    }

    public void setUsn(String usn) {
        this.usn = usn;
    }

    @Basic
    @Column(name = "pss", nullable = false, length = -1)
    public String getPss() {
        return pss;
    }

    public void setPss(String pss) {
        this.pss = pss;
    }

    @Basic
    @Column(name = "name", nullable = true, length = -1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "title", nullable = true, length = -1)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "priv", nullable = true, length = -1)
    public String getPriv() {
        return priv;
    }

    public void setPriv(String priv) {
        this.priv = priv;
    }

    @Basic
    @Column(name = "state", nullable = false)
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Basic
    @Column(name = "sess", nullable = true, length = -1)
    public String getSess() {
        return sess;
    }

    public void setSess(String sess) {
        this.sess = sess;
    }

    @Basic
    @Column(name = "phone", nullable = true, length = -1)
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

        if (uid != that.uid) return false;
        if (state != that.state) return false;
        if (usn != null ? !usn.equals(that.usn) : that.usn != null) return false;
        if (pss != null ? !pss.equals(that.pss) : that.pss != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (priv != null ? !priv.equals(that.priv) : that.priv != null) return false;
        if (sess != null ? !sess.equals(that.sess) : that.sess != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uid;
        result = 31 * result + (usn != null ? usn.hashCode() : 0);
        result = 31 * result + (pss != null ? pss.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (priv != null ? priv.hashCode() : 0);
        result = 31 * result + state;
        result = 31 * result + (sess != null ? sess.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }
}
