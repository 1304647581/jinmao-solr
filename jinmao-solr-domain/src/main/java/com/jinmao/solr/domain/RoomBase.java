package com.jinmao.solr.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.solr.client.solrj.beans.Field;

public class RoomBase implements Serializable {
	@Field
	private String id;
	@Field
	private String roomNo;
	@Field
	private String shsNo;
	@Field
	private String name;
	@Field
	private String alias;
	@Field
	private String number;
	@Field
	private Double square;
	@Field
	private String face;
	@Field
	private String feature;
	@Field
	private Double rentPrice;
	@Field
	private String equipment;
	@Field
	private String description;
	@Field
	private String facilities;
	@Field
	private String image;
	@Field
	private String featureImage;
	@Field
	private String coverUrl;
	@Field
	private String people;
	@Field
	private Integer state;
	@Field
	private String traffic;
	@Field
	private String metro;
	@Field
	private Integer isShow;
	@Field
	private String ip;
	@Field
	private Date updateTime;
	@Field
	private Date createTime;
	@Field
	private String createName;
	@Field
	private String updateName;
	@Field
	private String province;
	@Field
	private String city;
	@Field
	private String county;
	@Field
	private String street;
	@Field
	private String estate;
	@Field
	private String buildingNo;
	@Field
	private String unitNo;
	@Field
	private String houseNo;
	@Field
	private String address;
	@Field
	private String houseStyle;
	@Field
	private Integer floor;
	@Field
	private Integer allFloor;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public String getShsNo() {
		return shsNo;
	}

	public void setShsNo(String shsNo) {
		this.shsNo = shsNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Double getSquare() {
		return square;
	}

	public void setSquare(Double square) {
		this.square = square;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public Double getRentPrice() {
		return rentPrice;
	}

	public void setRentPrice(Double rentPrice) {
		this.rentPrice = rentPrice;
	}

	public String getEquipment() {
		return equipment;
	}

	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFacilities() {
		return facilities;
	}

	public void setFacilities(String facilities) {
		this.facilities = facilities;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getFeatureImage() {
		return featureImage;
	}

	public void setFeatureImage(String featureImage) {
		this.featureImage = featureImage;
	}

	public String getCoverUrl() {
		return coverUrl;
	}

	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}

	public String getPeople() {
		return people;
	}

	public void setPeople(String people) {
		this.people = people;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getTraffic() {
		return traffic;
	}

	public void setTraffic(String traffic) {
		this.traffic = traffic;
	}

	public String getMetro() {
		return metro;
	}

	public void setMetro(String metro) {
		this.metro = metro;
	}

	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getUpdateName() {
		return updateName;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getEstate() {
		return estate;
	}

	public void setEstate(String estate) {
		this.estate = estate;
	}

	public String getBuildingNo() {
		return buildingNo;
	}

	public void setBuildingNo(String buildingNo) {
		this.buildingNo = buildingNo;
	}

	public String getUnitNo() {
		return unitNo;
	}

	public void setUnitNo(String unitNo) {
		this.unitNo = unitNo;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHouseStyle() {
		return houseStyle;
	}

	public void setHouseStyle(String houseStyle) {
		this.houseStyle = houseStyle;
	}

	public Integer getFloor() {
		return floor;
	}

	public void setFloor(Integer floor) {
		this.floor = floor;
	}

	public Integer getAllFloor() {
		return allFloor;
	}

	public void setAllFloor(Integer allFloor) {
		this.allFloor = allFloor;
	}

}
