package smart.utils.data;

import javax.persistence.*;

@Entity
@Table(name = "smart_user_addrs", schema = "smartmall", catalog = "")
public class SmartUserAddrsEntity {
    private int uid;
    private Integer addr1;
    private Integer addr2;
    private Integer addr3;
    private Integer addr4;
    private Integer addr5;
    private Integer addr6;
    private Integer addr7;
    private Integer addr8;
    private Integer addr9;
    private Integer defaultaddr;

    @Id
    @Column(name = "uid", nullable = false)
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "addr1", nullable = true)
    public Integer getAddr1() {
        return addr1;
    }

    public void setAddr1(Integer addr1) {
        this.addr1 = addr1;
    }

    @Basic
    @Column(name = "addr2", nullable = true)
    public Integer getAddr2() {
        return addr2;
    }

    public void setAddr2(Integer addr2) {
        this.addr2 = addr2;
    }

    @Basic
    @Column(name = "addr3", nullable = true)
    public Integer getAddr3() {
        return addr3;
    }

    public void setAddr3(Integer addr3) {
        this.addr3 = addr3;
    }

    @Basic
    @Column(name = "addr4", nullable = true)
    public Integer getAddr4() {
        return addr4;
    }

    public void setAddr4(Integer addr4) {
        this.addr4 = addr4;
    }

    @Basic
    @Column(name = "addr5", nullable = true)
    public Integer getAddr5() {
        return addr5;
    }

    public void setAddr5(Integer addr5) {
        this.addr5 = addr5;
    }

    @Basic
    @Column(name = "addr6", nullable = true)
    public Integer getAddr6() {
        return addr6;
    }

    public void setAddr6(Integer addr6) {
        this.addr6 = addr6;
    }

    @Basic
    @Column(name = "addr7", nullable = true)
    public Integer getAddr7() {
        return addr7;
    }

    public void setAddr7(Integer addr7) {
        this.addr7 = addr7;
    }

    @Basic
    @Column(name = "addr8", nullable = true)
    public Integer getAddr8() {
        return addr8;
    }

    public void setAddr8(Integer addr8) {
        this.addr8 = addr8;
    }

    @Basic
    @Column(name = "addr9", nullable = true)
    public Integer getAddr9() {
        return addr9;
    }

    public void setAddr9(Integer addr9) {
        this.addr9 = addr9;
    }

    @Basic
    @Column(name = "defaultaddr", nullable = true)
    public Integer getDefaultaddr() {
        return defaultaddr;
    }

    public void setDefaultaddr(Integer defaultaddr) {
        this.defaultaddr = defaultaddr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SmartUserAddrsEntity that = (SmartUserAddrsEntity) o;

        if (uid != that.uid) return false;
        if (addr1 != null ? !addr1.equals(that.addr1) : that.addr1 != null) return false;
        if (addr2 != null ? !addr2.equals(that.addr2) : that.addr2 != null) return false;
        if (addr3 != null ? !addr3.equals(that.addr3) : that.addr3 != null) return false;
        if (addr4 != null ? !addr4.equals(that.addr4) : that.addr4 != null) return false;
        if (addr5 != null ? !addr5.equals(that.addr5) : that.addr5 != null) return false;
        if (addr6 != null ? !addr6.equals(that.addr6) : that.addr6 != null) return false;
        if (addr7 != null ? !addr7.equals(that.addr7) : that.addr7 != null) return false;
        if (addr8 != null ? !addr8.equals(that.addr8) : that.addr8 != null) return false;
        if (addr9 != null ? !addr9.equals(that.addr9) : that.addr9 != null) return false;
        if (defaultaddr != null ? !defaultaddr.equals(that.defaultaddr) : that.defaultaddr != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uid;
        result = 31 * result + (addr1 != null ? addr1.hashCode() : 0);
        result = 31 * result + (addr2 != null ? addr2.hashCode() : 0);
        result = 31 * result + (addr3 != null ? addr3.hashCode() : 0);
        result = 31 * result + (addr4 != null ? addr4.hashCode() : 0);
        result = 31 * result + (addr5 != null ? addr5.hashCode() : 0);
        result = 31 * result + (addr6 != null ? addr6.hashCode() : 0);
        result = 31 * result + (addr7 != null ? addr7.hashCode() : 0);
        result = 31 * result + (addr8 != null ? addr8.hashCode() : 0);
        result = 31 * result + (addr9 != null ? addr9.hashCode() : 0);
        result = 31 * result + (defaultaddr != null ? defaultaddr.hashCode() : 0);
        return result;
    }
}
