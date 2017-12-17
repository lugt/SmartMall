package smart.utils.data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "smart_local_delivery", schema = "smartmall", catalog = "")
public class SmartLocalDeliveryEntity {
    private int deliverid;
    private Integer orderid;
    private int uid;
    private Integer type;
    private Integer status;
    private String sender;
    private Integer address;
    private String carrier;
    private Integer starttime;
    private Integer reservetime;
    private Integer packagetime;
    private Integer accepttime;
    private Integer confirmtime;
    private String logs;

    @Id
    @Column(name = "deliverid")
    public int getDeliverid() {
        return deliverid;
    }

    public void setDeliverid(int deliverid) {
        this.deliverid = deliverid;
    }

    @Basic
    @Column(name = "orderid")
    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    @Basic
    @Column(name = "uid")
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "sender")
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    @Basic
    @Column(name = "address")
    public Integer getAddress() {
        return address;
    }

    public void setAddress(Integer address) {
        this.address = address;
    }

    @Basic
    @Column(name = "carrier")
    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    @Basic
    @Column(name = "starttime")
    public Integer getStarttime() {
        return starttime;
    }

    public void setStarttime(Integer starttime) {
        this.starttime = starttime;
    }

    @Basic
    @Column(name = "reservetime")
    public Integer getReservetime() {
        return reservetime;
    }

    public void setReservetime(Integer reservetime) {
        this.reservetime = reservetime;
    }

    @Basic
    @Column(name = "packagetime")
    public Integer getPackagetime() {
        return packagetime;
    }

    public void setPackagetime(Integer packagetime) {
        this.packagetime = packagetime;
    }

    @Basic
    @Column(name = "accepttime")
    public Integer getAccepttime() {
        return accepttime;
    }

    public void setAccepttime(Integer accepttime) {
        this.accepttime = accepttime;
    }

    @Basic
    @Column(name = "confirmtime")
    public Integer getConfirmtime() {
        return confirmtime;
    }

    public void setConfirmtime(Integer confirmtime) {
        this.confirmtime = confirmtime;
    }

    @Basic
    @Column(name = "logs")
    public String getLogs() {
        return logs;
    }

    public void setLogs(String logs) {
        this.logs = logs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SmartLocalDeliveryEntity that = (SmartLocalDeliveryEntity) o;
        return deliverid == that.deliverid &&
                uid == that.uid &&
                Objects.equals(orderid, that.orderid) &&
                Objects.equals(type, that.type) &&
                Objects.equals(status, that.status) &&
                Objects.equals(sender, that.sender) &&
                Objects.equals(address, that.address) &&
                Objects.equals(carrier, that.carrier) &&
                Objects.equals(starttime, that.starttime) &&
                Objects.equals(reservetime, that.reservetime) &&
                Objects.equals(packagetime, that.packagetime) &&
                Objects.equals(accepttime, that.accepttime) &&
                Objects.equals(confirmtime, that.confirmtime) &&
                Objects.equals(logs, that.logs);
    }

    @Override
    public int hashCode() {

        return Objects.hash(deliverid, orderid, uid, type, status, sender, address, carrier, starttime, reservetime, packagetime, accepttime, confirmtime, logs);
    }
}
