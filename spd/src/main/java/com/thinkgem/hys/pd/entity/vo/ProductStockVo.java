package com.thinkgem.hys.pd.entity.vo;

import com.thinkgem.hys.pd.entity.PdProduct;
import com.thinkgem.hys.pd.entity.PdProductStock;

public class ProductStockVo {

	private PdProductStock pdProductStock;
	private PdProduct pdProduct;
	public PdProductStock getPdProductStock() {
		return pdProductStock;
	}
	public void setPdProductStock(PdProductStock pdProductStock) {
		this.pdProductStock = pdProductStock;
	}
	public PdProduct getPdProduct() {
		return pdProduct;
	}
	public void setPdProduct(PdProduct pdProduct) {
		this.pdProduct = pdProduct;
	}
	public ProductStockVo(PdProductStock pdProductStock, PdProduct pdProduct) {
		super();
		this.pdProductStock = pdProductStock;
		this.pdProduct = pdProduct;
	}
	public ProductStockVo() {
		super();
	}
	
	
}
