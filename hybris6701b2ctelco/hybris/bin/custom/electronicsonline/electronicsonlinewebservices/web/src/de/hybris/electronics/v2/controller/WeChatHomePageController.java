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
        channel.setName("居家");
        channelList.add(channel);

        channel = new Channel();
        channel.setIconUrl("/static/images/pendpay.png");
        channel.setId(600);
        channel.setName("餐厨");
        channelList.add(channel);

        channel = new Channel();
        channel.setIconUrl("/static/images/about.png");
        channel.setId(600);
        channel.setName("饮食");
        channelList.add(channel);

        channel = new Channel();
        channel.setIconUrl("/static/images/about.png");
        channel.setId(602);
        channel.setName("配件");
        channelList.add(channel);

        channel = new Channel();
        channel.setIconUrl("/static/images/footprint.png");
        channel.setId(61603);
        channel.setName("服装");
        channelList.add(channel);

        channel = new Channel();
        channel.setIconUrl("/static/images/customer.png");
        channel.setId(62098);
        channel.setName("婴童");
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
        grouponList.setBrief("PL60 Silver");
        grouponList.setExpireTime("2021-12-31 00:00:00");
        grouponList.setGrouponMember(2);
        grouponList.setGrouponPrice(180);
        grouponList.setId(1981415);
        grouponList.setName("PL60 Silver");
        grouponList.setPicUrl("https://68.79.44.72:9002/electronicsonlinestorefront/medias/?context=bWFzdGVyfGltYWdlc3wyMjQ0OHxpbWFnZS9qcGVnfGltYWdlcy9oYTAvaDk5Lzg3OTY4MTk5NDc1NTAuanBnfGFlNTI5OWFkZWY4NmVmNzQwNjcxMjVlYmVkZDUyNWI5MjVhOGM2Mzc1MTZjMWVkODdiM2YxYzkwZmJhZGVlYjA");
        grouponList.setRetailPrice(191);

        return Arrays.asList(grouponList);
    }

    private List<NewGoodsList> getNewGoodsList() {

        List<NewGoodsList> list = new ArrayList<>();

        NewGoodsList newGoodsList = new NewGoodsList();
        newGoodsList.setId(String.valueOf(1981415));
        newGoodsList.setName("PL60 Silver");
        newGoodsList.setPicUrl("https://68.79.44.72:9002/electronicsonlinestorefront/medias/?context=bWFzdGVyfGltYWdlc3wyMjQ0OHxpbWFnZS9qcGVnfGltYWdlcy9oYTAvaDk5Lzg3OTY4MTk5NDc1NTAuanBnfGFlNTI5OWFkZWY4NmVmNzQwNjcxMjVlYmVkZDUyNWI5MjVhOGM2Mzc1MTZjMWVkODdiM2YxYzkwZmJhZGVlYjA");
        newGoodsList.setRetailPrice(String.valueOf(191));
        list.add(newGoodsList);

        newGoodsList = new NewGoodsList();
        newGoodsList.setId(String.valueOf(280916));
        newGoodsList.setName("Web Camera Black");
        newGoodsList.setPicUrl("https://68.79.44.72:9002/electronicsonlinestorefront/medias/?context=bWFzdGVyfGltYWdlc3wxMTY1N3xpbWFnZS9qcGVnfGltYWdlcy9oODQvaGJmLzg3OTY4MjQxNDE4NTQuanBnfDcxNjliN2RlM2I4OWYxNTMwYzE3NmJmYWI1Njg4NDQ4MWI2MTM1MjMzN2I0NTliMGY4NDZiNWUwY2Y2Y2QzOTY");
        newGoodsList.setRetailPrice(String.valueOf(8.2));
        list.add(newGoodsList);

        newGoodsList = new NewGoodsList();
        newGoodsList.setId(String.valueOf(289540));
        newGoodsList.setName("Light HVL-20DW2");
        newGoodsList.setPicUrl("https://68.79.44.72:9002/electronicsonlinestorefront/medias/?context=bWFzdGVyfGltYWdlc3wyMDk1MHxpbWFnZS9qcGVnfGltYWdlcy9oZjMvaDBiLzg3OTY4MjQ2MDA2MDYuanBnfGU2ZmJmY2RlMmQxOTJmZjE0MzgxOWNjN2MwNzFjNGUxY2JkNmY3MjNjOWUxNGY2MzlmNTg0MTk3YmU1MzBlNmQ");
        newGoodsList.setRetailPrice(String.valueOf(154.65));
        list.add(newGoodsList);

        newGoodsList = new NewGoodsList();
        newGoodsList.setId(String.valueOf(479742));
        newGoodsList.setName("QuickCam for Notebooks Pro");
        newGoodsList.setPicUrl("https://68.79.44.72:9002/electronicsonlinestorefront/medias/?context=bWFzdGVyfGltYWdlc3wxOTMwM3xpbWFnZS9qcGVnfGltYWdlcy9oNTMvaDcwLzg3OTY4MzE4NzUxMDIuanBnfDkxNjM5N2Q1YjIxOTQzZmQxMTQxODc0MTVhMGY0MDMwZWIxYzNiNjg3N2U1NjhmYmNjOTlkMTVlMmE4YzA3N2M");
        newGoodsList.setRetailPrice(String.valueOf(86.37));
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
