package smart.utils.data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "smart_delivery_addr", schema = "smartmall", catalog = "")
public class SmartDeliveryAddrEntity {
    private int addrid;
    private Integer uid;
    private Long mobile;
    private String recver;
    private Integer type;
    private Integer province;
    private Integer city;
    private Integer district;
    private String addr;
    private String memo;

    @Id
    @Column(name = "addrid")
    public int getAddrid() {
        return addrid;
    }

    public void setAddrid(int addrid) {
        this.addrid = addrid;
    }

    @Basic
    @Column(name = "uid")
    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "mobile")
    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    @Basic
    @Column(name = "recver")
    public String getRecver() {
        return recver;
    }

    public void setRecver(String recver) {
        this.recver = recver;
    }

    @Basic
    @Column(name = "type")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "province")
    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    @Basic
    @Column(name = "city")
    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    @Basic
    @Column(name = "district")
    public Integer getDistrict() {
        return district;
    }

    public void setDistrict(Integer district) {
        this.district = district;
    }

    @Basic
    @Column(name = "addr")
    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Basic
    @Column(name = "memo")
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SmartDeliveryAddrEntity that = (SmartDeliveryAddrEntity) o;
        return addrid == that.addrid &&
                Objects.equals(uid, that.uid) &&
                Objects.equals(mobile, that.mobile) &&
                Objects.equals(recver, that.recver) &&
                Objects.equals(type, that.type) &&
                Objects.equals(province, that.province) &&
                Objects.equals(city, that.city) &&
                Objects.equals(district, that.district) &&
                Objects.equals(addr, that.addr) &&
                Objects.equals(memo, that.memo);
    }

    @Override
    public int hashCode() {

        return Objects.hash(addrid, uid, mobile, recver, type, province, city, district, addr, memo);
    }
}
