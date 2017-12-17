package smart.utils.data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "smart_goods", schema = "smartmall", catalog = "")
public class SmartGoodsEntity {
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
    @Column(name = "goodid")
    public int getGoodid() {
        return goodid;
    }

    public void setGoodid(int goodid) {
        this.goodid = goodid;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "goods_no")
    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    @Basic
    @Column(name = "model_id")
    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    @Basic
    @Column(name = "sell_price")
    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }

    @Basic
    @Column(name = "market_price")
    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    @Basic
    @Column(name = "cost_price")
    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    @Basic
    @Column(name = "up_time")
    public Timestamp getUpTime() {
        return upTime;
    }

    public void setUpTime(Timestamp upTime) {
        this.upTime = upTime;
    }

    @Basic
    @Column(name = "down_time")
    public Timestamp getDownTime() {
        return downTime;
    }

    public void setDownTime(Timestamp downTime) {
        this.downTime = downTime;
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
    @Column(name = "store_nums")
    public int getStoreNums() {
        return storeNums;
    }

    public void setStoreNums(int storeNums) {
        this.storeNums = storeNums;
    }

    @Basic
    @Column(name = "img")
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Basic
    @Column(name = "is_del")
    public byte getIsDel() {
        return isDel;
    }

    public void setIsDel(byte isDel) {
        this.isDel = isDel;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "keywords")
    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "search_words")
    public String getSearchWords() {
        return searchWords;
    }

    public void setSearchWords(String searchWords) {
        this.searchWords = searchWords;
    }

    @Basic
    @Column(name = "weight")
    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    @Basic
    @Column(name = "point")
    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    @Basic
    @Column(name = "unit")
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Basic
    @Column(name = "brand_id")
    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    @Basic
    @Column(name = "visit")
    public int getVisit() {
        return visit;
    }

    public void setVisit(int visit) {
        this.visit = visit;
    }

    @Basic
    @Column(name = "favorite")
    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    @Basic
    @Column(name = "sort")
    public short getSort() {
        return sort;
    }

    public void setSort(short sort) {
        this.sort = sort;
    }

    @Basic
    @Column(name = "list_img")
    public String getListImg() {
        return listImg;
    }

    public void setListImg(String listImg) {
        this.listImg = listImg;
    }

    @Basic
    @Column(name = "small_img")
    public String getSmallImg() {
        return smallImg;
    }

    public void setSmallImg(String smallImg) {
        this.smallImg = smallImg;
    }

    @Basic
    @Column(name = "spec_array")
    public String getSpecArray() {
        return specArray;
    }

    public void setSpecArray(String specArray) {
        this.specArray = specArray;
    }

    @Basic
    @Column(name = "exp")
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
        return goodid == that.goodid &&
                modelId == that.modelId &&
                storeNums == that.storeNums &&
                isDel == that.isDel &&
                point == that.point &&
                visit == that.visit &&
                favorite == that.favorite &&
                sort == that.sort &&
                exp == that.exp &&
                Objects.equals(name, that.name) &&
                Objects.equals(goodsNo, that.goodsNo) &&
                Objects.equals(sellPrice, that.sellPrice) &&
                Objects.equals(marketPrice, that.marketPrice) &&
                Objects.equals(costPrice, that.costPrice) &&
                Objects.equals(upTime, that.upTime) &&
                Objects.equals(downTime, that.downTime) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(img, that.img) &&
                Objects.equals(content, that.content) &&
                Objects.equals(keywords, that.keywords) &&
                Objects.equals(description, that.description) &&
                Objects.equals(searchWords, that.searchWords) &&
                Objects.equals(weight, that.weight) &&
                Objects.equals(unit, that.unit) &&
                Objects.equals(brandId, that.brandId) &&
                Objects.equals(listImg, that.listImg) &&
                Objects.equals(smallImg, that.smallImg) &&
                Objects.equals(specArray, that.specArray);
    }

    @Override
    public int hashCode() {

        return Objects.hash(goodid, name, goodsNo, modelId, sellPrice, marketPrice, costPrice, upTime, downTime, createTime, storeNums, img, isDel, content, keywords, description, searchWords, weight, point, unit, brandId, visit, favorite, sort, listImg, smallImg, specArray, exp);
    }
}
