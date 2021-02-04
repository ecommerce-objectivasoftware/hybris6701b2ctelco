package de.hybris.electronics.wechat.product.populators;

import de.hybris.electronics.dto.pdp.*;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.apache.commons.collections.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WeChatProductDataPopulator implements Populator<ProductData, WeChatProductData> {

    private ConfigurationService configurationService;

    @Override
    public void populate(ProductData productData, WeChatProductData weChatProductData) throws ConversionException {

        RootData rootData = new RootData();
        rootData.setBrand(getBrand(productData));
        rootData.setInfo(getInfo(productData));
        rootData.setAttribute(getAttribute());
        rootData.setComment(getComment(productData));
        rootData.setGroupon(getGroupon(productData));
        rootData.setIssue(getIssue(productData));
        rootData.setProductList(getProductList(productData));
        rootData.setShare(false);
        rootData.setUserHasCollect(1);
        rootData.setSpecificationList(getSpecificationList(productData));
        weChatProductData.setData(rootData);
        weChatProductData.setErrno(0);
    }

    private Info getInfo(ProductData productData) {

        Info info = new Info();
        info.setId(productData.getCode());
        info.setRetailPrice(productData.getPrice().getValue().doubleValue());
        info.setCounterPrice(productData.getPrice().getValue().doubleValue() * 0.9);
        info.setName(productData.getName());
        info.setDetail(productData.getDescription());
        List<String> gallery = new ArrayList<>();
        productData.getImages().forEach(image -> gallery.add(String.format("%s%s", getSiteUrl(), image.getUrl())));

        info.setGallery(gallery);
        return info;
    }

    private Brand getBrand(ProductData productData) {

        Brand brand = new Brand();
        if (productData.getCategories().isEmpty()) {
            return brand;
        }
        brand.setName(productData.getCategories().iterator().next().getName());
        return brand;
    }

    private Attribute getAttribute() {

        Attribute attribute = new Attribute();
        attribute.setAttribute("Attribute value");
        attribute.setName("Attribute name");
        attribute.setValue("Attribute value");
        return attribute;
    }

    private Comment getComment(ProductData productData) {

        Comment comment = new Comment();

        comment.setCount(productData.getReviews().size());
        List<Data> dataList = new ArrayList<>();

        productData.getReviews().forEach(review -> {
            Data data = new Data();
            data.setAddTime(getStringDate(review.getDate()));
            data.setAdminContent(review.getHeadline());
            data.setAvatar("/static/images/avatar.png");
            data.setContent(review.getComment());
            data.setId(review.getId());
            data.setNickname(review.getAlias());

            List<String> images = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(productData.getImages())) {
                images.add(String.format("%s%s", getSiteUrl(), productData.getImages().iterator().next().getUrl()));
            }
            data.setPicList(images);
            dataList.add(data);
        });
        comment.setData(dataList);

        return comment;
    }

    private List<Groupon> getGroupon(ProductData productData) {

        List<Groupon> grouponList = new ArrayList<>();
        Groupon groupon = new Groupon();
        groupon.setChecked(true);
        groupon.setId(productData.getCode());
        grouponList.add(groupon);
        return grouponList;
    }

    private List<Issue> getIssue(ProductData productData) {

        List<Issue> issueList = new ArrayList<>();
        Issue issue = new Issue();

        issue.setAnswer("Answer for issue");
        issue.setId(1);
        issue.setQuestion("Question for issue");
        issueList.add(issue);
        return issueList;
    }

    private List<ProductList> getProductList(ProductData productData) {

        List<ProductList> productListList = new ArrayList<>();
        ProductList productList = new ProductList();
        productList.setId(productData.getCode());
        productList.setNumber(productData.getCode());
        productList.setPrice(Double.parseDouble(productData.getPrice().getValue().toString()));
        productList.setSpecifications(productData.getName());
        productListList.add(productList);
        return productListList;
    }

    private List<SpecificationList> getSpecificationList(ProductData productData) {

        List<SpecificationList> specificationListList = new ArrayList<>();

        SpecificationList specificationList = new SpecificationList();

        specificationList.setName(productData.getCategories().iterator().next().getName());

        List<ValueList> valueListList = new ArrayList<>();
        ValueList valueList = new ValueList();

        valueList.setChecked(true);
        valueList.setId(productData.getCode());
        valueList.setValue(productData.getName());

        valueListList.add(valueList);
        specificationList.setValueList(valueListList);

        specificationListList.add(specificationList);
        return specificationListList;
    }

    private String getStringDate(Date currentTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(currentTime);
    }

    private String getSiteUrl() {
        return getConfigurationService().getConfiguration().getString("demo.image.url", "https://electronics.local:9002/electronicsonlinestorefront");
    }

    public ConfigurationService getConfigurationService() {
        return configurationService;
    }

    public void setConfigurationService(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }
}
