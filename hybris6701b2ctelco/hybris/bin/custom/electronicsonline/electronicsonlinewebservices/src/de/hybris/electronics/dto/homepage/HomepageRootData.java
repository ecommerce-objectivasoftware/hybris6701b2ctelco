package de.hybris.electronics.dto.homepage;

import java.io.Serializable;
import java.util.List;

public class HomepageRootData implements Serializable {

    private List<Banner> banner;

    private List<Channel> channel;

    private List<CouponList> couponList;

    private List<GrouponList> grouponList;

    private List<NewGoodsList> newGoodsList;

    private Location location;

    private Member member;

    public void setBanner(List<Banner> banner){
        this.banner = banner;
    }
    public List<Banner> getBanner(){
        return this.banner;
    }
    public void setChannel(List<Channel> channel){
        this.channel = channel;
    }
    public List<Channel> getChannel(){
        return this.channel;
    }
    public void setCouponList(List<CouponList> couponList){
        this.couponList = couponList;
    }
    public List<CouponList> getCouponList(){
        return this.couponList;
    }
    public void setGrouponList(List<GrouponList> grouponList){
        this.grouponList = grouponList;
    }
    public List<GrouponList> getGrouponList(){
        return this.grouponList;
    }
    public void setNewGoodsList(List<NewGoodsList> newGoodsList){
        this.newGoodsList = newGoodsList;
    }
    public List<NewGoodsList> getNewGoodsList(){
        return this.newGoodsList;
    }
    public void setLocation(Location location){
        this.location = location;
    }
    public Location getLocation(){
        return this.location;
    }
    public void setMember(Member member){
        this.member = member;
    }
    public Member getMember(){
        return this.member;
    }

}
