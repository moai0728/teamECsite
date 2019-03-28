package com.internousdev.mars.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.mars.dao.ProductInfoDAO;
import com.internousdev.mars.dto.ProductInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class ProductDetailsAction extends ActionSupport implements SessionAware{
	private String productId;
	private List<ProductInfoDTO> productInfoDtoList = new ArrayList<ProductInfoDTO>();
	private Map<String, Object> session;

	public String execute(){
		//セッションタイムアウト確認
				if(session.isEmpty()){
					return "sessionError";
				}
		String result = ERROR;
		int intProductId = 0;
		 try {
		  intProductId = Integer.parseInt(productId);

		 }catch (NumberFormatException e) {
			 // 存在しない商品IDをセット
			 intProductId = -999;
		 }
		ProductInfoDAO productInfoDao = new ProductInfoDAO();
		ProductInfoDTO productInfoDto = new ProductInfoDTO();
		productInfoDto = productInfoDao.getProductInfo(intProductId);
		session.put("id", productInfoDto.getId());
		session.put("productId", productInfoDto.getProductId());
		session.put("productName", productInfoDto.getProductName());
		session.put("productNameKana", productInfoDto.getProductNameKana());
		session.put("imageFilePath", productInfoDto.getImageFilePath());
		session.put("imageFileName", productInfoDto.getImageFileName());
		session.put("price", productInfoDto.getPrice());

		session.put("releaseCompany", productInfoDto.getReleaseCompany());
		session.put("releaseDate", productInfoDto.getReleaseDate());
		session.put("productDescription", productInfoDto.getProductDescription());
		List<Integer> productCountList = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5));
		session.put("productCountList", productCountList);
		productInfoDtoList = productInfoDao.getProductInfoListByCategoryId(productInfoDto.getCategoryId(), productInfoDto.getProductId(),0,3);
		Iterator<ProductInfoDTO> iterator = productInfoDtoList.iterator();

		if(!(iterator.hasNext())){
			productCountList = null;
		}
		if(!productInfoDtoList.isEmpty() || productCountList == null){
			session.put("productInfoDtoList", productInfoDtoList);
			result = SUCCESS;
		}
        return result;
	}



	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public List<ProductInfoDTO> getProductInfoDtoList(){
		return productInfoDtoList;
	}
	public void setProductInfoDtoList(List<ProductInfoDTO> productInfoDtoList){
		this.productInfoDtoList = productInfoDtoList;
	}

	public Map<String, Object> getSession(){
		return session;
	}
	public void setSession(Map<String, Object> session){
		this.session = session;
	}
}
