package de.hybris.electronics.v2.controller;

import de.hybris.electronics.dto.homepage.*;
import de.hybris.platform.webservicescommons.swagger.ApiBaseSiteIdParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@Api(tags = "Homepage")
@RequestMapping(value = "/{baseSiteId}/homepage")
public class WeChatHomePageController {

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "WeChat Homepage", notes = "WeChat Homepage")
    @ApiBaseSiteIdParam
    public WeChatHomepageResponseData getProductByCodeForWeChat() {
        
        HomepageRootData homepageRootData = new HomepageRootData();
        homepageRootData.setBanner(getBanners());
        homepageRootData.setChannel(getChannels());
        homepageRootData.setCouponList(getCouponList());
        homepageRootData.setGrouponList(getGrouponList());
        homepageRootData.setNewGoodsList(getNewGoodsList());
        homepageRootData.setLocation(getLocation());
        homepageRootData.setMember(getMember());

        WeChatHomepageResponseData weChatHomePageResponseData = new WeChatHomepageResponseData();
        weChatHomePageResponseData.setErrno(0);
        weChatHomePageResponseData.setData(homepageRootData);
        return weChatHomePageResponseData;
    }

    private List<Banner> getBanners() {

        List<Banner> bannerList = new ArrayList<>();
        Banner banner = new Banner();
        banner.setId(570);
        banner.setLink(15);
        banner.setUrl("/static/images/banner.png");
        bannerList.add(banner);

        banner = new Banner();
        banner.setId(580);
        banner.setLink(15);
        banner.setUrl("/static/images/slider.jpg");
        bannerList.add(banner);

        return bannerList;
    }

    private List<Channel> getChannels() {

        List<Channel> channelList = new ArrayList<>();
        Channel channel = new Channel();
        channel.setIconUrl("/static/images/category.png");
        channel.setId(590);
        channel.setName("CANON");
        channelList.add(channel);

        channel = new Channel();
        channel.setIconUrl("/static/images/footprint.png");
        channel.setId(61603);
        channel.setName("HUAWEI");
        channelList.add(channel);

        channel = new Channel();
        channel.setIconUrl("/static/images/pendpay.png");
        channel.setId(600);
        channel.setName("SONY");
        channelList.add(channel);

        channel = new Channel();
        channel.setIconUrl("/static/images/about.png");
        channel.setId(600);
        channel.setName("KODAK");
        channelList.add(channel);

        channel = new Channel();
        channel.setIconUrl("/static/images/about.png");
        channel.setId(602);
        channel.setName("SAMSUNG");
        channelList.add(channel);

        channel = new Channel();
        channel.setIconUrl("/static/images/customer.png");
        channel.setId(62098);
        channel.setName("FUJIFILE");
        channelList.add(channel);

        return channelList;
    }

    private List<CouponList> getCouponList() {

        CouponList couponList = new CouponList();
        couponList.setDays("5");
        couponList.setDesc("满减");
        couponList.setDiscount("45");
        couponList.setId(63000);
        couponList.setMin("10");
        couponList.setName("可兑换优惠券");
        couponList.setTag("优惠");

        return Arrays.asList(couponList);
    }

    private List<GrouponList> getGrouponList() {

        GrouponList grouponList = new GrouponList();
        grouponList.setBrief("ADIN1100");
        grouponList.setExpireTime("2021-12-31 00:00:00");
        grouponList.setGrouponMember(2);
        grouponList.setGrouponPrice(3.00);
        grouponList.setId(9001);
        grouponList.setName("ADIN1100");
        grouponList.setPicUrl("https://68.79.44.72:9002/backoffice/medias/9001.png?context=bWFzdGVyfGltYWdlc3w2MzEwNHxpbWFnZS9wbmd8aW1hZ2VzL2g2ZC9oNzEvODc5ODk3NDIxNDE3NC5wbmd8OWJmNjY0ZTQzMjNmNWMzODQxODBiN2JhOTcxODNlZDU1NDMyMTgxOWJiZWRhMzdkMDkwOTE4YjBjM2JmNDg0NA");
        grouponList.setRetailPrice(3.24);

        return Arrays.asList(grouponList);
    }

    private List<NewGoodsList> getNewGoodsList() {

        List<NewGoodsList> list = new ArrayList<>();

        NewGoodsList newGoodsList = new NewGoodsList();
        newGoodsList.setId(String.valueOf(9001));
        newGoodsList.setName("ADIN1100");
        newGoodsList.setPicUrl("https://68.79.44.72:9002/backoffice/medias/9001.png?context=bWFzdGVyfGltYWdlc3w2MzEwNHxpbWFnZS9wbmd8aW1hZ2VzL2g2ZC9oNzEvODc5ODk3NDIxNDE3NC5wbmd8OWJmNjY0ZTQzMjNmNWMzODQxODBiN2JhOTcxODNlZDU1NDMyMTgxOWJiZWRhMzdkMDkwOTE4YjBjM2JmNDg0NA");
        newGoodsList.setRetailPrice(String.valueOf(3.72));
        list.add(newGoodsList);

        newGoodsList = new NewGoodsList();
        newGoodsList.setId(String.valueOf(9002));
        newGoodsList.setName("FIDO5100 - Real-Time Ethernet Multiprotocol (REM) Switch");
        newGoodsList.setPicUrl("https://68.79.44.72:9002/backoffice/medias/9002.png?context=bWFzdGVyfGltYWdlc3w5OTkzOXxpbWFnZS9wbmd8aW1hZ2VzL2g3My9oMzQvODc5ODk3NDYwNzM5MC5wbmd8Njg4MDU3YzJmYjk0MGU2MmZmOTBkM2I4ZmU1Y2VjNDhmZTRiNzc0ZmFkMGQ4ZGY4ZWY2ZmMyNGI3ZjhlOTAxNg");
        newGoodsList.setRetailPrice(String.valueOf(7.55));
        list.add(newGoodsList);

        newGoodsList = new NewGoodsList();
        newGoodsList.setId(String.valueOf(9003));
        newGoodsList.setName("FIDO5200 - Real-Time Ethernet Multiprotocol (REM) Switch");
        newGoodsList.setPicUrl("https://68.79.44.72:9002/backoffice/medias/9003.png?context=bWFzdGVyfGltYWdlc3w5OTUzOXxpbWFnZS9wbmd8aW1hZ2VzL2gyZC9oYTQvODc5ODk3NTAwMDYwNi5wbmd8ODc5ODE2ZmNlOTdjOTAxMTg2OTdjMDUwZTk4ZWJjZDBiZDAyYzQzNzQ3MDJhZGQ5NmZhYTMzNGY0MDg0OTI5NA");
        newGoodsList.setRetailPrice(String.valueOf(652));
        list.add(newGoodsList);

        newGoodsList = new NewGoodsList();
        newGoodsList.setId(String.valueOf(9005));
        newGoodsList.setName("QuickCam for Notebooks Pro");
        newGoodsList.setPicUrl("https://68.79.44.72:9002/backoffice/medias/9005.png?context=bWFzdGVyfGltYWdlc3wxMDg3Mjl8aW1hZ2UvcG5nfGltYWdlcy9oZTcvaGM1Lzg3OTg5NzU3ODcwMzgucG5nfDFkYjEzNjRhNmQ0NjY2M2JlM2JmZGI1Yjk4ODRkODc1M2VkMWE1Yjg2ZGEwNjg1YjBhMzJjYzdkNDQzOWM1MzE");
        newGoodsList.setRetailPrice(String.valueOf(400));
        list.add(newGoodsList);

        return list;

    }

    private Location getLocation() {

        Location location = new Location();
        location.setIcon("/static/images/footprint.png");
        return location;
    }

    private Member getMember() {

        Member member = new Member();
        member.setIcon("/static/images/footprint.png");
        return member;
    }
}
