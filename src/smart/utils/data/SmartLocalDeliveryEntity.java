package smart.utils.data;

import javax.persistence.*;

@Entity
@Table(name = "smart_local_delivery", schema = "smartmall", catalog = "")
public class SmartLocalDeliveryEntity {
    @Id
    @GeneratedValue
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
    @Column(name = "deliverid", nullable = false)
    public int getDeliverid() {
        return deliverid;
    }

    public void setDeliverid(int deliverid) {
        this.deliverid = deliverid;
    }

    @Basic
    @Column(name = "orderid", nullable = true)
    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    @Basic
    @Column(name = "uid", nullable = false)
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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
    @Column(name = "status", nullable = true)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "sender", nullable = true, length = -1)
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    @Basic
    @Column(name = "address", nullable = true)
    public Integer getAddress() {
        return address;
    }

    public void setAddress(Integer address) {
        this.address = address;
    }

    @Basic
    @Column(name = "carrier", nullable = true, length = -1)
    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    @Basic
    @Column(name = "starttime", nullable = true)
    public Integer getStarttime() {
        return starttime;
    }

    public void setStarttime(Integer starttime) {
        this.starttime = starttime;
    }

    @Basic
    @Column(name = "reservetime", nullable = true)
    public Integer getReservetime() {
        return reservetime;
    }

    public void setReservetime(Integer reservetime) {
        this.reservetime = reservetime;
    }

    @Basic
    @Column(name = "packagetime", nullable = true)
    public Integer getPackagetime() {
        return packagetime;
    }

    public void setPackagetime(Integer packagetime) {
        this.packagetime = packagetime;
    }

    @Basic
    @Column(name = "accepttime", nullable = true)
    public Integer getAccepttime() {
        return accepttime;
    }

    public void setAccepttime(Integer accepttime) {
        this.accepttime = accepttime;
    }

    @Basic
    @Column(name = "confirmtime", nullable = true)
    public Integer getConfirmtime() {
        return confirmtime;
    }

    public void setConfirmtime(Integer confirmtime) {
        this.confirmtime = confirmtime;
    }

    @Basic
    @Column(name = "logs", nullable = true, length = -1)
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

        if (deliverid != that.deliverid) return false;
        if (uid != that.uid) return false;
        if (orderid != null ? !orderid.equals(that.orderid) : that.orderid != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (sender != null ? !sender.equals(that.sender) : that.sender != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (carrier != null ? !carrier.equals(that.carrier) : that.carrier != null) return false;
        if (starttime != null ? !starttime.equals(that.starttime) : that.starttime != null) return false;
        if (reservetime != null ? !reservetime.equals(that.reservetime) : that.reservetime != null) return false;
        if (packagetime != null ? !packagetime.equals(that.packagetime) : that.packagetime != null) return false;
        if (accepttime != null ? !accepttime.equals(that.accepttime) : that.accepttime != null) return false;
        if (confirmtime != null ? !confirmtime.equals(that.confirmtime) : that.confirmtime != null) return false;
        if (logs != null ? !logs.equals(that.logs) : that.logs != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = deliverid;
        result = 31 * result + (orderid != null ? orderid.hashCode() : 0);
        result = 31 * result + uid;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (sender != null ? sender.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (carrier != null ? carrier.hashCode() : 0);
        result = 31 * result + (starttime != null ? starttime.hashCode() : 0);
        result = 31 * result + (reservetime != null ? reservetime.hashCode() : 0);
        result = 31 * result + (packagetime != null ? packagetime.hashCode() : 0);
        result = 31 * result + (accepttime != null ? accepttime.hashCode() : 0);
        result = 31 * result + (confirmtime != null ? confirmtime.hashCode() : 0);
        result = 31 * result + (logs != null ? logs.hashCode() : 0);
        return result;
    }
}
