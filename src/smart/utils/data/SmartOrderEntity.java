package smart.utils.data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

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
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "payment_id")
    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    @Basic
    @Column(name = "delivery_id")
    public Integer getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Integer deliveryId) {
        this.deliveryId = deliveryId;
    }

    @Basic
    @Column(name = "merchandise")
    public String getMerchandise() {
        return merchandise;
    }

    public void setMerchandise(String merchandise) {
        this.merchandise = merchandise;
    }

    @Basic
    @Column(name = "status")
    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @Basic
    @Column(name = "pay_status")
    public Byte getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Byte payStatus) {
        this.payStatus = payStatus;
    }

    @Basic
    @Column(name = "distribution_status")
    public Byte getDistributionStatus() {
        return distributionStatus;
    }

    public void setDistributionStatus(Byte distributionStatus) {
        this.distributionStatus = distributionStatus;
    }

    @Basic
    @Column(name = "due_amount")
    public BigDecimal getDueAmount() {
        return dueAmount;
    }

    public void setDueAmount(BigDecimal dueAmount) {
        this.dueAmount = dueAmount;
    }

    @Basic
    @Column(name = "paid_amount")
    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }

    @Basic
    @Column(name = "taxes")
    public BigDecimal getTaxes() {
        return taxes;
    }

    public void setTaxes(BigDecimal taxes) {
        this.taxes = taxes;
    }

    @Basic
    @Column(name = "payable_freight")
    public BigDecimal getPayableFreight() {
        return payableFreight;
    }

    public void setPayableFreight(BigDecimal payableFreight) {
        this.payableFreight = payableFreight;
    }

    @Basic
    @Column(name = "real_freight")
    public BigDecimal getRealFreight() {
        return realFreight;
    }

    public void setRealFreight(BigDecimal realFreight) {
        this.realFreight = realFreight;
    }

    @Basic
    @Column(name = "pay_fee")
    public BigDecimal getPayFee() {
        return payFee;
    }

    public void setPayFee(BigDecimal payFee) {
        this.payFee = payFee;
    }

    @Basic
    @Column(name = "promotions")
    public BigDecimal getPromotions() {
        return promotions;
    }

    public void setPromotions(BigDecimal promotions) {
        this.promotions = promotions;
    }

    @Basic
    @Column(name = "discount")
    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    @Basic
    @Column(name = "order_amount")
    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    @Basic
    @Column(name = "pay_time")
    public Timestamp getPayTime() {
        return payTime;
    }

    public void setPayTime(Timestamp payTime) {
        this.payTime = payTime;
    }

    @Basic
    @Column(name = "send_time")
    public Timestamp getSendTime() {
        return sendTime;
    }

    public void setSendTime(Timestamp sendTime) {
        this.sendTime = sendTime;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "completion_time")
    public Timestamp getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(Timestamp completionTime) {
        this.completionTime = completionTime;
    }

    @Basic
    @Column(name = "accept_time")
    public String getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(String acceptTime) {
        this.acceptTime = acceptTime;
    }

    @Basic
    @Column(name = "invoice")
    public byte getInvoice() {
        return invoice;
    }

    public void setInvoice(byte invoice) {
        this.invoice = invoice;
    }

    @Basic
    @Column(name = "invoice_title")
    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    @Basic
    @Column(name = "postscript")
    public String getPostscript() {
        return postscript;
    }

    public void setPostscript(String postscript) {
        this.postscript = postscript;
    }

    @Basic
    @Column(name = "note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Basic
    @Column(name = "prop")
    public String getProp() {
        return prop;
    }

    public void setProp(String prop) {
        this.prop = prop;
    }

    @Basic
    @Column(name = "exp")
    public short getExp() {
        return exp;
    }

    public void setExp(short exp) {
        this.exp = exp;
    }

    @Basic
    @Column(name = "point")
    public short getPoint() {
        return point;
    }

    public void setPoint(short point) {
        this.point = point;
    }

    @Basic
    @Column(name = "type")
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
        return id == that.id &&
                userId == that.userId &&
                paymentId == that.paymentId &&
                invoice == that.invoice &&
                exp == that.exp &&
                point == that.point &&
                type == that.type &&
                Objects.equals(deliveryId, that.deliveryId) &&
                Objects.equals(merchandise, that.merchandise) &&
                Objects.equals(status, that.status) &&
                Objects.equals(payStatus, that.payStatus) &&
                Objects.equals(distributionStatus, that.distributionStatus) &&
                Objects.equals(dueAmount, that.dueAmount) &&
                Objects.equals(paidAmount, that.paidAmount) &&
                Objects.equals(taxes, that.taxes) &&
                Objects.equals(payableFreight, that.payableFreight) &&
                Objects.equals(realFreight, that.realFreight) &&
                Objects.equals(payFee, that.payFee) &&
                Objects.equals(promotions, that.promotions) &&
                Objects.equals(discount, that.discount) &&
                Objects.equals(orderAmount, that.orderAmount) &&
                Objects.equals(payTime, that.payTime) &&
                Objects.equals(sendTime, that.sendTime) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(completionTime, that.completionTime) &&
                Objects.equals(acceptTime, that.acceptTime) &&
                Objects.equals(invoiceTitle, that.invoiceTitle) &&
                Objects.equals(postscript, that.postscript) &&
                Objects.equals(note, that.note) &&
                Objects.equals(prop, that.prop);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userId, paymentId, deliveryId, merchandise, status, payStatus, distributionStatus, dueAmount, paidAmount, taxes, payableFreight, realFreight, payFee, promotions, discount, orderAmount, payTime, sendTime, createTime, completionTime, acceptTime, invoice, invoiceTitle, postscript, note, prop, exp, point, type);
    }
}
