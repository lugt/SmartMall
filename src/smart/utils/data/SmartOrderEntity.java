package smart.utils.data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "smart_order", schema = "smartmall", catalog = "")
public class SmartOrderEntity {
    private int id;
    private int userId;
    private int paymentId;
    private Integer deliveryId;
    private String merchandise;
    private Byte status;
    private Byte payStatus;
    private Byte distributionStatus;
    private BigDecimal dueAmount;
    private BigDecimal paidAmount;
    private BigDecimal taxes;
    private BigDecimal payableFreight;
    private BigDecimal realFreight;
    private BigDecimal payFee;
    private BigDecimal promotions;
    private BigDecimal discount;
    private BigDecimal orderAmount;
    private Timestamp payTime;
    private Timestamp sendTime;
    private Timestamp createTime;
    private Timestamp completionTime;
    private String acceptTime;
    private byte invoice;
    private String invoiceTitle;
    private String postscript;
    private String note;
    private String prop;
    private short exp;
    private short point;
    private byte type;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "payment_id", nullable = false)
    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    @Basic
    @Column(name = "delivery_id", nullable = true)
    public Integer getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Integer deliveryId) {
        this.deliveryId = deliveryId;
    }

    @Basic
    @Column(name = "merchandise", nullable = true, length = 300)
    public String getMerchandise() {
        return merchandise;
    }

    public void setMerchandise(String merchandise) {
        this.merchandise = merchandise;
    }

    @Basic
    @Column(name = "status", nullable = true)
    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @Basic
    @Column(name = "pay_status", nullable = true)
    public Byte getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Byte payStatus) {
        this.payStatus = payStatus;
    }

    @Basic
    @Column(name = "distribution_status", nullable = true)
    public Byte getDistributionStatus() {
        return distributionStatus;
    }

    public void setDistributionStatus(Byte distributionStatus) {
        this.distributionStatus = distributionStatus;
    }

    @Basic
    @Column(name = "due_amount", nullable = true, precision = 2)
    public BigDecimal getDueAmount() {
        return dueAmount;
    }

    public void setDueAmount(BigDecimal dueAmount) {
        this.dueAmount = dueAmount;
    }

    @Basic
    @Column(name = "paid_amount", nullable = false, precision = 2)
    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }

    @Basic
    @Column(name = "taxes", nullable = false, precision = 2)
    public BigDecimal getTaxes() {
        return taxes;
    }

    public void setTaxes(BigDecimal taxes) {
        this.taxes = taxes;
    }

    @Basic
    @Column(name = "payable_freight", nullable = false, precision = 2)
    public BigDecimal getPayableFreight() {
        return payableFreight;
    }

    public void setPayableFreight(BigDecimal payableFreight) {
        this.payableFreight = payableFreight;
    }

    @Basic
    @Column(name = "real_freight", nullable = false, precision = 2)
    public BigDecimal getRealFreight() {
        return realFreight;
    }

    public void setRealFreight(BigDecimal realFreight) {
        this.realFreight = realFreight;
    }

    @Basic
    @Column(name = "pay_fee", nullable = false, precision = 2)
    public BigDecimal getPayFee() {
        return payFee;
    }

    public void setPayFee(BigDecimal payFee) {
        this.payFee = payFee;
    }

    @Basic
    @Column(name = "promotions", nullable = false, precision = 2)
    public BigDecimal getPromotions() {
        return promotions;
    }

    public void setPromotions(BigDecimal promotions) {
        this.promotions = promotions;
    }

    @Basic
    @Column(name = "discount", nullable = false, precision = 2)
    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    @Basic
    @Column(name = "order_amount", nullable = false, precision = 2)
    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    @Basic
    @Column(name = "pay_time", nullable = true)
    public Timestamp getPayTime() {
        return payTime;
    }

    public void setPayTime(Timestamp payTime) {
        this.payTime = payTime;
    }

    @Basic
    @Column(name = "send_time", nullable = true)
    public Timestamp getSendTime() {
        return sendTime;
    }

    public void setSendTime(Timestamp sendTime) {
        this.sendTime = sendTime;
    }

    @Basic
    @Column(name = "create_time", nullable = true)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "completion_time", nullable = true)
    public Timestamp getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(Timestamp completionTime) {
        this.completionTime = completionTime;
    }

    @Basic
    @Column(name = "accept_time", nullable = true, length = 80)
    public String getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(String acceptTime) {
        this.acceptTime = acceptTime;
    }

    @Basic
    @Column(name = "invoice", nullable = false)
    public byte getInvoice() {
        return invoice;
    }

    public void setInvoice(byte invoice) {
        this.invoice = invoice;
    }

    @Basic
    @Column(name = "invoice_title", nullable = true, length = 100)
    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    @Basic
    @Column(name = "postscript", nullable = true, length = 255)
    public String getPostscript() {
        return postscript;
    }

    public void setPostscript(String postscript) {
        this.postscript = postscript;
    }

    @Basic
    @Column(name = "note", nullable = true, length = -1)
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Basic
    @Column(name = "prop", nullable = true, length = 255)
    public String getProp() {
        return prop;
    }

    public void setProp(String prop) {
        this.prop = prop;
    }

    @Basic
    @Column(name = "exp", nullable = false)
    public short getExp() {
        return exp;
    }

    public void setExp(short exp) {
        this.exp = exp;
    }

    @Basic
    @Column(name = "point", nullable = false)
    public short getPoint() {
        return point;
    }

    public void setPoint(short point) {
        this.point = point;
    }

    @Basic
    @Column(name = "type", nullable = false)
    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SmartOrderEntity that = (SmartOrderEntity) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (paymentId != that.paymentId) return false;
        if (invoice != that.invoice) return false;
        if (exp != that.exp) return false;
        if (point != that.point) return false;
        if (type != that.type) return false;
        if (deliveryId != null ? !deliveryId.equals(that.deliveryId) : that.deliveryId != null) return false;
        if (merchandise != null ? !merchandise.equals(that.merchandise) : that.merchandise != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (payStatus != null ? !payStatus.equals(that.payStatus) : that.payStatus != null) return false;
        if (distributionStatus != null ? !distributionStatus.equals(that.distributionStatus) : that.distributionStatus != null)
            return false;
        if (dueAmount != null ? !dueAmount.equals(that.dueAmount) : that.dueAmount != null) return false;
        if (paidAmount != null ? !paidAmount.equals(that.paidAmount) : that.paidAmount != null) return false;
        if (taxes != null ? !taxes.equals(that.taxes) : that.taxes != null) return false;
        if (payableFreight != null ? !payableFreight.equals(that.payableFreight) : that.payableFreight != null)
            return false;
        if (realFreight != null ? !realFreight.equals(that.realFreight) : that.realFreight != null) return false;
        if (payFee != null ? !payFee.equals(that.payFee) : that.payFee != null) return false;
        if (promotions != null ? !promotions.equals(that.promotions) : that.promotions != null) return false;
        if (discount != null ? !discount.equals(that.discount) : that.discount != null) return false;
        if (orderAmount != null ? !orderAmount.equals(that.orderAmount) : that.orderAmount != null) return false;
        if (payTime != null ? !payTime.equals(that.payTime) : that.payTime != null) return false;
        if (sendTime != null ? !sendTime.equals(that.sendTime) : that.sendTime != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (completionTime != null ? !completionTime.equals(that.completionTime) : that.completionTime != null)
            return false;
        if (acceptTime != null ? !acceptTime.equals(that.acceptTime) : that.acceptTime != null) return false;
        if (invoiceTitle != null ? !invoiceTitle.equals(that.invoiceTitle) : that.invoiceTitle != null) return false;
        if (postscript != null ? !postscript.equals(that.postscript) : that.postscript != null) return false;
        if (note != null ? !note.equals(that.note) : that.note != null) return false;
        if (prop != null ? !prop.equals(that.prop) : that.prop != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + paymentId;
        result = 31 * result + (deliveryId != null ? deliveryId.hashCode() : 0);
        result = 31 * result + (merchandise != null ? merchandise.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (payStatus != null ? payStatus.hashCode() : 0);
        result = 31 * result + (distributionStatus != null ? distributionStatus.hashCode() : 0);
        result = 31 * result + (dueAmount != null ? dueAmount.hashCode() : 0);
        result = 31 * result + (paidAmount != null ? paidAmount.hashCode() : 0);
        result = 31 * result + (taxes != null ? taxes.hashCode() : 0);
        result = 31 * result + (payableFreight != null ? payableFreight.hashCode() : 0);
        result = 31 * result + (realFreight != null ? realFreight.hashCode() : 0);
        result = 31 * result + (payFee != null ? payFee.hashCode() : 0);
        result = 31 * result + (promotions != null ? promotions.hashCode() : 0);
        result = 31 * result + (discount != null ? discount.hashCode() : 0);
        result = 31 * result + (orderAmount != null ? orderAmount.hashCode() : 0);
        result = 31 * result + (payTime != null ? payTime.hashCode() : 0);
        result = 31 * result + (sendTime != null ? sendTime.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (completionTime != null ? completionTime.hashCode() : 0);
        result = 31 * result + (acceptTime != null ? acceptTime.hashCode() : 0);
        result = 31 * result + (int) invoice;
        result = 31 * result + (invoiceTitle != null ? invoiceTitle.hashCode() : 0);
        result = 31 * result + (postscript != null ? postscript.hashCode() : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        result = 31 * result + (prop != null ? prop.hashCode() : 0);
        result = 31 * result + (int) exp;
        result = 31 * result + (int) point;
        result = 31 * result + (int) type;
        return result;
    }
}
