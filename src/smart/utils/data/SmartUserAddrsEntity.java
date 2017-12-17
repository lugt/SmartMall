package smart.utils.data;

import javax.persistence.*;
import java.util.Objects;

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
    @Column(name = "uid")
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "addr1")
    public Integer getAddr1() {
        return addr1;
    }

    public void setAddr1(Integer addr1) {
        this.addr1 = addr1;
    }

    @Basic
    @Column(name = "addr2")
    public Integer getAddr2() {
        return addr2;
    }

    public void setAddr2(Integer addr2) {
        this.addr2 = addr2;
    }

    @Basic
    @Column(name = "addr3")
    public Integer getAddr3() {
        return addr3;
    }

    public void setAddr3(Integer addr3) {
        this.addr3 = addr3;
    }

    @Basic
    @Column(name = "addr4")
    public Integer getAddr4() {
        return addr4;
    }

    public void setAddr4(Integer addr4) {
        this.addr4 = addr4;
    }

    @Basic
    @Column(name = "addr5")
    public Integer getAddr5() {
        return addr5;
    }

    public void setAddr5(Integer addr5) {
        this.addr5 = addr5;
    }

    @Basic
    @Column(name = "addr6")
    public Integer getAddr6() {
        return addr6;
    }

    public void setAddr6(Integer addr6) {
        this.addr6 = addr6;
    }

    @Basic
    @Column(name = "addr7")
    public Integer getAddr7() {
        return addr7;
    }

    public void setAddr7(Integer addr7) {
        this.addr7 = addr7;
    }

    @Basic
    @Column(name = "addr8")
    public Integer getAddr8() {
        return addr8;
    }

    public void setAddr8(Integer addr8) {
        this.addr8 = addr8;
    }

    @Basic
    @Column(name = "addr9")
    public Integer getAddr9() {
        return addr9;
    }

    public void setAddr9(Integer addr9) {
        this.addr9 = addr9;
    }

    @Basic
    @Column(name = "defaultaddr")
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
        return uid == that.uid &&
                Objects.equals(addr1, that.addr1) &&
                Objects.equals(addr2, that.addr2) &&
                Objects.equals(addr3, that.addr3) &&
                Objects.equals(addr4, that.addr4) &&
                Objects.equals(addr5, that.addr5) &&
                Objects.equals(addr6, that.addr6) &&
                Objects.equals(addr7, that.addr7) &&
                Objects.equals(addr8, that.addr8) &&
                Objects.equals(addr9, that.addr9) &&
                Objects.equals(defaultaddr, that.defaultaddr);
    }

    @Override
    public int hashCode() {

        return Objects.hash(uid, addr1, addr2, addr3, addr4, addr5, addr6, addr7, addr8, addr9, defaultaddr);
    }
}
