package com.thinkgem.hys.pd.entity.vo;

import com.thinkgem.hys.pd.entity.PdProduct;

public class PdProductPackageVo {

	private PdProduct pdProduct ; 
	private int count ;
	public PdProduct getPdProduct() {
		return pdProduct;
	}
	public void setPdProduct(PdProduct pdProduct) {
		this.pdProduct = pdProduct;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public PdProductPackageVo(PdProduct pdProduct, int count) {
		super();
		this.pdProduct = pdProduct;
		this.count = count;
	}
	public PdProductPackageVo() {
		super();
	}
	
	
}
