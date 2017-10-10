package smart.utils.data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "smart_delivery_addr", schema = "smartmall", catalog = "")
public class SmartDeliveryAddrEntity {
    private Serializable uid;
    private Serializable addr1;
    private Serializable addr2;
    private Serializable addr3;
    private Serializable addr4;
    private Serializable addr5;

    @Basic
    @Column(name = "uid", nullable = false)
    public Serializable getUid() {
        return uid;
    }

    public void setUid(Serializable uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "addr1", nullable = false, length = -1)
    public Serializable getAddr1() {
        return addr1;
    }

    public void setAddr1(Serializable addr1) {
        this.addr1 = addr1;
    }

    @Basic
    @Column(name = "addr2", nullable = false, length = -1)
    public Serializable getAddr2() {
        return addr2;
    }

    public void setAddr2(Serializable addr2) {
        this.addr2 = addr2;
    }

    @Basic
    @Column(name = "addr3", nullable = false, length = -1)
    public Serializable getAddr3() {
        return addr3;
    }

    public void setAddr3(Serializable addr3) {
        this.addr3 = addr3;
    }

    @Basic
    @Column(name = "addr4", nullable = false, length = -1)
    public Serializable getAddr4() {
        return addr4;
    }

    public void setAddr4(Serializable addr4) {
        this.addr4 = addr4;
    }

    @Basic
    @Column(name = "addr5", nullable = false, length = -1)
    public Serializable getAddr5() {
        return addr5;
    }

    public void setAddr5(Serializable addr5) {
        this.addr5 = addr5;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SmartDeliveryAddrEntity that = (SmartDeliveryAddrEntity) o;

        if (uid != null ? !uid.equals(that.uid) : that.uid != null) return false;
        if (addr1 != null ? !addr1.equals(that.addr1) : that.addr1 != null) return false;
        if (addr2 != null ? !addr2.equals(that.addr2) : that.addr2 != null) return false;
        if (addr3 != null ? !addr3.equals(that.addr3) : that.addr3 != null) return false;
        if (addr4 != null ? !addr4.equals(that.addr4) : that.addr4 != null) return false;
        if (addr5 != null ? !addr5.equals(that.addr5) : that.addr5 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uid != null ? uid.hashCode() : 0;
        result = 31 * result + (addr1 != null ? addr1.hashCode() : 0);
        result = 31 * result + (addr2 != null ? addr2.hashCode() : 0);
        result = 31 * result + (addr3 != null ? addr3.hashCode() : 0);
        result = 31 * result + (addr4 != null ? addr4.hashCode() : 0);
        result = 31 * result + (addr5 != null ? addr5.hashCode() : 0);
        return result;
    }
}
