package com.internousdev.mars.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.mars.dao.ProductInfoDAO;
import com.internousdev.mars.dto.ProductInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class ProductListAction extends ActionSupport implements SessionAware{
	private String productName;
	private String productNameKana;
	private String imageFilePath;
	private String imageFileName;
	private int price;
	private String keywords;
	private List<ProductInfoDTO> productInfoDtoList = new ArrayList<ProductInfoDTO>();

	private Map<String, Object> session;
	public String execute(){
		/*セッションタイムアウト確認*/
				if(session.isEmpty()){
					return "sessionError";
				}
		String result = ERROR;

		ProductInfoDAO productInfoDao = new ProductInfoDAO();
		productInfoDtoList = productInfoDao.getProductInfoList();



		result = SUCCESS;
		return result;
	}

	public String getProductName(){
		return productName;
	}
	public void setProductName(String productName){
		this.productName=productName;
	}

	public String getProductNameKana(){
		return productNameKana;
	}
    public void setProductNameKana(String productNameKana){
		this.productNameKana=productNameKana;
	}

	public String getImageFilePath(){
		return imageFilePath;
	}
	public void setImageFilePath(String imageFilePath){
		this.imageFilePath=imageFilePath;
	}

	public String getImageFileName(){
		return imageFileName;
	}
	public void setImageFileName(String imageFileName){
		this.imageFileName=imageFileName;
	}

	public int getPrice(){
		return price;
	}
	public void setPrice(int price){
		this.price=price;
	}

	public String getKeywords(){
		return keywords;
	}
	public void setKeywords(String keywords){
		this.keywords=keywords;
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
