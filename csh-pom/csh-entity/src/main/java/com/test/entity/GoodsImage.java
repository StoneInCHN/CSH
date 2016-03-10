package com.test.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cshm_goods_image database table.
 * 
 */
@Entity
@Table(name="cshm_goods_image")
@NamedQuery(name="GoodsImage.findAll", query="SELECT g FROM GoodsImage g")
public class GoodsImage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CSHM_GOODS_IMAGE_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CSHM_GOODS_IMAGE_ID_GENERATOR")
	private String id;

	@Column(name="file_id")
	private int fileId;

	@Column(name="goods_id")
	private int goodsId;

	@Column(name="image_url")
	private String imageUrl;

	@Column(name="sort_order")
	private byte sortOrder;

	private String thumbnail;

	public GoodsImage() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getFileId() {
		return this.fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	public int getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public byte getSortOrder() {
		return this.sortOrder;
	}

	public void setSortOrder(byte sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getThumbnail() {
		return this.thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

}