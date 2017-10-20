package smart.utils.data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "smart_goods", schema = "smartmall", catalog = "")
public class SmartGoodsEntity {
    @Id
    @GeneratedValue
    private int goodid;
    private String name;
    private String goodsNo;
    private int modelId;
    private BigDecimal sellPrice;
    private BigDecimal marketPrice;
    private BigDecimal costPrice;
    private Timestamp upTime;
    private Timestamp downTime;
    private Timestamp createTime;
    private int storeNums;
    private String img;
    private byte isDel;
    private String content;
    private String keywords;
    private String description;
    private String searchWords;
    private BigDecimal weight;
    private int point;
    private String unit;
    private Integer brandId;
    private int visit;
    private int favorite;
    private short sort;
    private String listImg;
    private String smallImg;
    private String specArray;
    private short exp;

    @Id
    @Column(name = "goodid", nullable = false)
    public int getGoodid() {
        return goodid;
    }

    public void setGoodid(int goodid) {
        this.goodid = goodid;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "goods_no", nullable = false, length = 20)
    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    @Basic
    @Column(name = "model_id", nullable = false)
    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    @Basic
    @Column(name = "sell_price", nullable = false, precision = 2)
    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }

    @Basic
    @Column(name = "market_price", nullable = true, precision = 2)
    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    @Basic
    @Column(name = "cost_price", nullable = true, precision = 2)
    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    @Basic
    @Column(name = "up_time", nullable = true)
    public Timestamp getUpTime() {
        return upTime;
    }

    public void setUpTime(Timestamp upTime) {
        this.upTime = upTime;
    }

    @Basic
    @Column(name = "down_time", nullable = true)
    public Timestamp getDownTime() {
        return downTime;
    }

    public void setDownTime(Timestamp downTime) {
        this.downTime = downTime;
    }

    @Basic
    @Column(name = "create_time", nullable = false)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "store_nums", nullable = false)
    public int getStoreNums() {
        return storeNums;
    }

    public void setStoreNums(int storeNums) {
        this.storeNums = storeNums;
    }

    @Basic
    @Column(name = "img", nullable = true, length = 255)
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Basic
    @Column(name = "is_del", nullable = false)
    public byte getIsDel() {
        return isDel;
    }

    public void setIsDel(byte isDel) {
        this.isDel = isDel;
    }

    @Basic
    @Column(name = "content", nullable = true, length = -1)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "keywords", nullable = true, length = 255)
    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "search_words", nullable = true, length = -1)
    public String getSearchWords() {
        return searchWords;
    }

    public void setSearchWords(String searchWords) {
        this.searchWords = searchWords;
    }

    @Basic
    @Column(name = "weight", nullable = false, precision = 2)
    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    @Basic
    @Column(name = "point", nullable = false)
    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    @Basic
    @Column(name = "unit", nullable = true, length = 10)
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Basic
    @Column(name = "brand_id", nullable = true)
    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    @Basic
    @Column(name = "visit", nullable = false)
    public int getVisit() {
        return visit;
    }

    public void setVisit(int visit) {
        this.visit = visit;
    }

    @Basic
    @Column(name = "favorite", nullable = false)
    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    @Basic
    @Column(name = "sort", nullable = false)
    public short getSort() {
        return sort;
    }

    public void setSort(short sort) {
        this.sort = sort;
    }

    @Basic
    @Column(name = "list_img", nullable = true, length = 255)
    public String getListImg() {
        return listImg;
    }

    public void setListImg(String listImg) {
        this.listImg = listImg;
    }

    @Basic
    @Column(name = "small_img", nullable = true, length = 255)
    public String getSmallImg() {
        return smallImg;
    }

    public void setSmallImg(String smallImg) {
        this.smallImg = smallImg;
    }

    @Basic
    @Column(name = "spec_array", nullable = true, length = -1)
    public String getSpecArray() {
        return specArray;
    }

    public void setSpecArray(String specArray) {
        this.specArray = specArray;
    }

    @Basic
    @Column(name = "exp", nullable = false)
    public short getExp() {
        return exp;
    }

    public void setExp(short exp) {
        this.exp = exp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SmartGoodsEntity that = (SmartGoodsEntity) o;

        if (goodid != that.goodid) return false;
        if (modelId != that.modelId) return false;
        if (storeNums != that.storeNums) return false;
        if (isDel != that.isDel) return false;
        if (point != that.point) return false;
        if (visit != that.visit) return false;
        if (favorite != that.favorite) return false;
        if (sort != that.sort) return false;
        if (exp != that.exp) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (goodsNo != null ? !goodsNo.equals(that.goodsNo) : that.goodsNo != null) return false;
        if (sellPrice != null ? !sellPrice.equals(that.sellPrice) : that.sellPrice != null) return false;
        if (marketPrice != null ? !marketPrice.equals(that.marketPrice) : that.marketPrice != null) return false;
        if (costPrice != null ? !costPrice.equals(that.costPrice) : that.costPrice != null) return false;
        if (upTime != null ? !upTime.equals(that.upTime) : that.upTime != null) return false;
        if (downTime != null ? !downTime.equals(that.downTime) : that.downTime != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (img != null ? !img.equals(that.img) : that.img != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (keywords != null ? !keywords.equals(that.keywords) : that.keywords != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (searchWords != null ? !searchWords.equals(that.searchWords) : that.searchWords != null) return false;
        if (weight != null ? !weight.equals(that.weight) : that.weight != null) return false;
        if (unit != null ? !unit.equals(that.unit) : that.unit != null) return false;
        if (brandId != null ? !brandId.equals(that.brandId) : that.brandId != null) return false;
        if (listImg != null ? !listImg.equals(that.listImg) : that.listImg != null) return false;
        if (smallImg != null ? !smallImg.equals(that.smallImg) : that.smallImg != null) return false;
        if (specArray != null ? !specArray.equals(that.specArray) : that.specArray != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = goodid;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (goodsNo != null ? goodsNo.hashCode() : 0);
        result = 31 * result + modelId;
        result = 31 * result + (sellPrice != null ? sellPrice.hashCode() : 0);
        result = 31 * result + (marketPrice != null ? marketPrice.hashCode() : 0);
        result = 31 * result + (costPrice != null ? costPrice.hashCode() : 0);
        result = 31 * result + (upTime != null ? upTime.hashCode() : 0);
        result = 31 * result + (downTime != null ? downTime.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + storeNums;
        result = 31 * result + (img != null ? img.hashCode() : 0);
        result = 31 * result + (int) isDel;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (keywords != null ? keywords.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (searchWords != null ? searchWords.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + point;
        result = 31 * result + (unit != null ? unit.hashCode() : 0);
        result = 31 * result + (brandId != null ? brandId.hashCode() : 0);
        result = 31 * result + visit;
        result = 31 * result + favorite;
        result = 31 * result + (int) sort;
        result = 31 * result + (listImg != null ? listImg.hashCode() : 0);
        result = 31 * result + (smallImg != null ? smallImg.hashCode() : 0);
        result = 31 * result + (specArray != null ? specArray.hashCode() : 0);
        result = 31 * result + (int) exp;
        return result;
    }
}
