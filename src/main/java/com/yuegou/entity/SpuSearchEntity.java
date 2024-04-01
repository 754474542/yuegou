package com.yuegou.entity;

public class SpuSearchEntity {
    private Integer page;
    private Integer size;
    private Integer offset;
    private String typeSales;
    private String typePrice;
    private String typeDiscounts;
    private String typeSpuName;

    @Override
    public String toString() {
        return "SpuSearchEntity{" +
                "page=" + page +
                ", size=" + size +
                ", offset=" + offset +
                ", typeSales='" + typeSales + '\'' +
                ", typePrice='" + typePrice + '\'' +
                ", typeDiscounts='" + typeDiscounts + '\'' +
                ", typeSpuName='" + typeSpuName + '\'' +
                '}';
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getTypeSales() {
        return typeSales;
    }

    public void setTypeSales(String typeSales) {
        this.typeSales = typeSales;
    }

    public String getTypePrice() {
        return typePrice;
    }

    public void setTypePrice(String typePrice) {
        this.typePrice = typePrice;
    }

    public String getTypeDiscounts() {
        return typeDiscounts;
    }

    public void setTypeDiscounts(String typeDiscounts) {
        this.typeDiscounts = typeDiscounts;
    }

    public String getTypeSpuName() {
        return typeSpuName;
    }

    public void setTypeSpuName(String typeSpuName) {
        this.typeSpuName = typeSpuName;
    }
}
