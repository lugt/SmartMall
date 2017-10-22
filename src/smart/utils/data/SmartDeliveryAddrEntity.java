package smart.utils.data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "smart_delivery_addr", schema = "smartmall", catalog = "")
public class SmartDeliveryAddrEntity {
    @Id
    @GeneratedValue
    private int addrid;
    private Integer uid;
    private Long mobile;
    private Serializable addr;
    private Serializable memo;
    private Serializable recver;
    private Integer type;
    private Integer province;
    private Integer district;
    private Integer city;

    @Id
    @Column(name = "addrid", nullable = false)
    public int getAddrid() {
        return addrid;
    }

    public void setAddrid(int addrid) {
        this.addrid = addrid;
    }

    @Basic
    @Column(name = "uid", nullable = true)
    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "mobile", nullable = true)
    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    @Basic
    @Column(name = "addr", nullable = false, length = -1)
    public Serializable getAddr() {
        return addr;
    }

    public void setAddr(Serializable addr) {
        this.addr = addr;
    }

    @Basic
    @Column(name = "memo", nullable = true, length = -1)
    public Serializable getMemo() {
        return memo;
    }

    public void setMemo(Serializable memo) {
        this.memo = memo;
    }

    @Basic
    @Column(name = "recver", nullable = true, length = -1)
    public Serializable getRecver() {
        return recver;
    }

    public void setRecver(Serializable recver) {
        this.recver = recver;
    }

    @Basic
    @Column(name = "type", nullable = true)
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "province", nullable = true)
    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    @Basic
    @Column(name = "district", nullable = true)
    public Integer getDistrict() {
        return district;
    }

    public void setDistrict(Integer district) {
        this.district = district;
    }

    @Basic
    @Column(name = "city", nullable = true)
    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SmartDeliveryAddrEntity that = (SmartDeliveryAddrEntity) o;

        if (addrid != that.addrid) return false;
        if (uid != null ? !uid.equals(that.uid) : that.uid != null) return false;
        if (mobile != null ? !mobile.equals(that.mobile) : that.mobile != null) return false;
        if (addr != null ? !addr.equals(that.addr) : that.addr != null) return false;
        if (memo != null ? !memo.equals(that.memo) : that.memo != null) return false;
        if (recver != null ? !recver.equals(that.recver) : that.recver != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (province != null ? !province.equals(that.province) : that.province != null) return false;
        if (district != null ? !district.equals(that.district) : that.district != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = addrid;
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        result = 31 * result + (addr != null ? addr.hashCode() : 0);
        result = 31 * result + (memo != null ? memo.hashCode() : 0);
        result = 31 * result + (recver != null ? recver.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (district != null ? district.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
    }
}
