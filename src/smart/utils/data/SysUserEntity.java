package smart.utils.data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "sys_user", schema = "smartmall", catalog = "")
public class SysUserEntity {
    private String userNo;
    private String userLoginName;
    private String password;
    private String userName;
    private String userNameEn;
    private String mobilePhone;
    private String pCode;
    private String deptId;
    private String deptName;
    private byte status;
    private String gender;
    private String crewType;
    private Timestamp createdOn;
    private String createdBy;
    private Timestamp modifiedOn;
    private String modifiedBy;

    @Id
    @Column(name = "user_no")
    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    @Basic
    @Column(name = "user_login_name")
    public String getUserLoginName() {
        return userLoginName;
    }

    public void setUserLoginName(String userLoginName) {
        this.userLoginName = userLoginName;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "user_name_en")
    public String getUserNameEn() {
        return userNameEn;
    }

    public void setUserNameEn(String userNameEn) {
        this.userNameEn = userNameEn;
    }

    @Basic
    @Column(name = "mobile_phone")
    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    @Basic
    @Column(name = "p_code")
    public String getpCode() {
        return pCode;
    }

    public void setpCode(String pCode) {
        this.pCode = pCode;
    }

    @Basic
    @Column(name = "dept_id")
    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    @Basic
    @Column(name = "dept_name")
    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Basic
    @Column(name = "status")
    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    @Basic
    @Column(name = "gender")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "crew_type")
    public String getCrewType() {
        return crewType;
    }

    public void setCrewType(String crewType) {
        this.crewType = crewType;
    }

    @Basic
    @Column(name = "created_on")
    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    @Basic
    @Column(name = "created_by")
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Basic
    @Column(name = "modified_on")
    public Timestamp getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Timestamp modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    @Basic
    @Column(name = "modified_by")
    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysUserEntity that = (SysUserEntity) o;
        return status == that.status &&
                Objects.equals(userNo, that.userNo) &&
                Objects.equals(userLoginName, that.userLoginName) &&
                Objects.equals(password, that.password) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(userNameEn, that.userNameEn) &&
                Objects.equals(mobilePhone, that.mobilePhone) &&
                Objects.equals(pCode, that.pCode) &&
                Objects.equals(deptId, that.deptId) &&
                Objects.equals(deptName, that.deptName) &&
                Objects.equals(gender, that.gender) &&
                Objects.equals(crewType, that.crewType) &&
                Objects.equals(createdOn, that.createdOn) &&
                Objects.equals(createdBy, that.createdBy) &&
                Objects.equals(modifiedOn, that.modifiedOn) &&
                Objects.equals(modifiedBy, that.modifiedBy);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userNo, userLoginName, password, userName, userNameEn, mobilePhone, pCode, deptId, deptName, status, gender, crewType, createdOn, createdBy, modifiedOn, modifiedBy);
    }
}
