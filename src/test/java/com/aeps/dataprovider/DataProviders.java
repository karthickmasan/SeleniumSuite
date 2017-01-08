package com.aeps.dataprovider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aeps.datamodel.ProductData;
import com.aeps.selenium.framework.DataBuilder;



public class DataProviders {

	@DataProvider(name = "ProductData")
	public Iterator<Object[]> loginDataProvider() throws IOException {
		
		Collection<Object[]> customerData = new ArrayList<Object[]>();
		{
			{
				DataBuilder dataBuilder = new DataBuilder();
				List<ProductData> data = dataBuilder.prepareProductData();
				for (ProductData customerModel : data) {
					customerData.add(new Object[] { customerModel });
				}
			}
		}
		
		return customerData.iterator();
	}
	
	@Test(dataProvider = "ProductData")
	public void testData(ProductData data){
		System.out.println(data.getProductName());
		System.out.println(data.getSearchName());
	}
	
	
}
