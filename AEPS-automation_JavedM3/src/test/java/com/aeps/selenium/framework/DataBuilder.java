package com.aeps.selenium.framework;

import java.util.ArrayList;
import java.util.List;

import com.aeps.constant.GlobalConstant;
import com.aeps.datamodel.ProductData;


public class DataBuilder {

	public List<ProductData> prepareProductData() {
		List<ProductData> productsData = new ArrayList<ProductData>();
		Read_XLS read = new Read_XLS(GlobalConstant.FileNames.ProdcutFileName.toString(), GlobalConstant.FileNames.TestDataRelativePath.toString());
		Object[][] objs = new Object[read.retrieveNoOfRows("Sheet1") - 1][read.retrieveNoOfCols("Sheet1")];
		objs = read.retrieveTestData1("Sheet1");
		for (Object[] obj : objs) {
			ProductData productData = new ProductData();
			productData.setSearchName(obj[0].toString());
			productData.setProductName(obj[1].toString());
			
			productsData.add(productData);
		}
		return productsData;
	}
}
