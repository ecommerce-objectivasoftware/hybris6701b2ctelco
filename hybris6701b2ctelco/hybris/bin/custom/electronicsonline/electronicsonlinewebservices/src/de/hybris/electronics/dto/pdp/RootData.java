package de.hybris.electronics.dto.pdp;

import java.util.List;

public class RootData {
    private List<SpecificationList> specificationList;

    private Info info;

    private List<ProductList> productList;

    private int userHasCollect;

    private boolean share;

    private Brand brand;

    private Attribute attribute;

    private List<Issue> issue;

    private Comment comment;

    private List<Groupon> groupon;

    public void setSpecificationList(List<SpecificationList> specificationList) {
        this.specificationList = specificationList;
    }

    public List<SpecificationList> getSpecificationList() {
        return this.specificationList;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public Info getInfo() {
        return this.info;
    }

    public void setProductList(List<ProductList> productList) {
        this.productList = productList;
    }

    public List<ProductList> getProductList() {
        return this.productList;
    }

    public void setUserHasCollect(int userHasCollect) {
        this.userHasCollect = userHasCollect;
    }

    public int getUserHasCollect() {
        return this.userHasCollect;
    }

    public void setShare(boolean share) {
        this.share = share;
    }

    public boolean getShare() {
        return this.share;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Brand getBrand() {
        return this.brand;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public Attribute getAttribute() {
        return this.attribute;
    }

    public void setIssue(List<Issue> issue) {
        this.issue = issue;
    }

    public List<Issue> getIssue() {
        return this.issue;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Comment getComment() {
        return this.comment;
    }

    public void setGroupon(List<Groupon> groupon) {
        this.groupon = groupon;
    }

    public List<Groupon> getGroupon() {
        return this.groupon;
    }
}
