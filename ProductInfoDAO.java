package com.internousdev.mars.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.internousdev.mars.dto.ProductInfoDTO;
import com.internousdev.mars.util.DBConnector;

public class ProductInfoDAO {

	public List<ProductInfoDTO> getProductInfoList(){
	    DBConnector dbConnector = new DBConnector();
	    Connection connection = dbConnector.getConnection();
	    List<ProductInfoDTO> productInfoDtoList = new ArrayList<ProductInfoDTO>();
	    String sql = "select * from product_info";                                //商品一覧全て表示

	    try{
	    	PreparedStatement preparedStatement = connection.prepareStatement(sql);
	    	ResultSet resultSet = preparedStatement.executeQuery();
	    	while(resultSet.next()){
	    		ProductInfoDTO productInfoDto = new ProductInfoDTO();

	    		productInfoDto.setId(resultSet.getInt("id"));
	    		productInfoDto.setProductId(resultSet.getInt("product_id"));
	    		productInfoDto.setProductName(resultSet.getString("product_name"));
	    		productInfoDto.setProductNameKana(resultSet.getString("product_name_kana"));
	    		productInfoDto.setProductDescription(resultSet.getString("product_description"));
	    		productInfoDto.setCategoryId(resultSet.getInt("category_id"));
	    		productInfoDto.setPrice(resultSet.getInt("price"));
	    		productInfoDto.setImageFilePath(resultSet.getString("image_file_path"));
	    		productInfoDto.setImageFileName(resultSet.getString("image_file_name"));
	    		productInfoDto.setReleaseDate(resultSet.getDate("release_date"));
	    		productInfoDto.setReleaseCompany(resultSet.getString("release_company"));
				productInfoDto.setStatus(resultSet.getInt("status"));
				productInfoDto.setRegistDate(resultSet.getDate("regist_date"));
				productInfoDto.setUpdateDate(resultSet.getDate("update_date"));
				productInfoDtoList.add(productInfoDto);

				}
	    }catch(SQLException e){
	    	e.printStackTrace();
	    }finally{
	    	try{
	    		connection.close();
	    	}catch(SQLException e){
	    		e.printStackTrace();
	    	}
	    }
	    return productInfoDtoList;
	}

	public ProductInfoDTO getProductInfo(int productId){
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		ProductInfoDTO productInfoDto = new ProductInfoDTO();
		String sql = "select * from product_info where product_id=?";        //商品詳細
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, productId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				productInfoDto.setId(resultSet.getInt("id"));
				productInfoDto.setProductId(resultSet.getInt("product_id"));
				productInfoDto.setProductName(resultSet.getString("product_name"));
				productInfoDto.setProductNameKana(resultSet.getString("product_name_kana"));
				productInfoDto.setProductDescription(resultSet.getString("product_description"));
				productInfoDto.setCategoryId(resultSet.getInt("category_id"));
				productInfoDto.setPrice(resultSet.getInt("price"));
				productInfoDto.setImageFilePath(resultSet.getString("image_file_path"));
				productInfoDto.setImageFileName(resultSet.getString("image_file_name"));
				productInfoDto.setReleaseDate(resultSet.getDate("release_date"));
				productInfoDto.setReleaseCompany(resultSet.getString("release_company"));
				productInfoDto.setStatus(resultSet.getInt("status"));
				productInfoDto.setRegistDate(resultSet.getDate("regist_date"));
				productInfoDto.setUpdateDate(resultSet.getDate("update_date"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				connection.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return productInfoDto;
	}

	public List<ProductInfoDTO> getProductInfoListByCategoryId(int categoryId, int productId, int limitOffset,
			int limitRowCount){
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		List<ProductInfoDTO> productInfoDtoList = new ArrayList<ProductInfoDTO>();
		String sql = "select * from product_info where category_id=? and product_id not in(?) order by rand() limit ?,?";   //おすすめ商品表示
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, categoryId);
			preparedStatement.setInt(2, productId);
			preparedStatement.setInt(3, limitOffset);
			preparedStatement.setInt(4, limitRowCount);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				ProductInfoDTO productInfoDto = new ProductInfoDTO();
				productInfoDto.setId(resultSet.getInt("id"));
				productInfoDto.setProductId(resultSet.getInt("product_id"));
				productInfoDto.setProductName(resultSet.getString("product_name"));
				productInfoDto.setProductDescription(resultSet.getString("product_description"));
				productInfoDto.setCategoryId(resultSet.getInt("category_id"));
				productInfoDto.setPrice(resultSet.getInt("price"));
				productInfoDto.setImageFilePath(resultSet.getString("image_file_path"));
				productInfoDto.setImageFileName(resultSet.getString("image_file_name"));
				productInfoDto.setReleaseDate(resultSet.getDate("release_date"));
				productInfoDto.setReleaseCompany(resultSet.getString("release_company"));
				productInfoDto.setStatus(resultSet.getInt("status"));
				productInfoDto.setRegistDate(resultSet.getDate("regist_date"));
				productInfoDto.setUpdateDate(resultSet.getDate("update_date"));
				productInfoDtoList.add(productInfoDto);
				}
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				try{
					connection.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		return productInfoDtoList;
	}

	public List<ProductInfoDTO> getProductInfoListAll(String[] keywordsList){
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		List<ProductInfoDTO> productInfoDtoList = new ArrayList<ProductInfoDTO>();
		String sql = "select * from product_info where";                               //全カテゴリーでキーワード検索
		boolean initializeFlag = true;
		for(String keyword : keywordsList){
			if(initializeFlag){
				sql += " (product_name like '%" + keyword + "%' or product_name_kana like '%" + keyword + "%')";
				initializeFlag = false;
			}else{
				sql += "or (product_name like '%" + keyword + "%' or product_name_kana like '%" + keyword + "%')";
			}
		}
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				ProductInfoDTO productInfoDto = new ProductInfoDTO();
				productInfoDto.setId(resultSet.getInt("id"));
				productInfoDto.setProductId(resultSet.getInt("product_id"));
				productInfoDto.setProductName(resultSet.getString("product_name"));
				productInfoDto.setProductNameKana(resultSet.getString("product_name_kana"));
				productInfoDto.setProductDescription(resultSet.getString("product_description"));
				productInfoDto.setCategoryId(resultSet.getInt("category_id"));
				productInfoDto.setPrice(resultSet.getInt("price"));
				productInfoDto.setImageFilePath(resultSet.getString("image_file_path"));
				productInfoDto.setImageFileName(resultSet.getString("image_file_name"));
				productInfoDto.setReleaseDate(resultSet.getDate("release_date"));
				productInfoDto.setReleaseCompany(resultSet.getString("release_company"));
				productInfoDto.setStatus(resultSet.getInt("status"));
				productInfoDto.setRegistDate(resultSet.getDate("regist_date"));
				productInfoDto.setUpdateDate(resultSet.getDate("update_date"));
				productInfoDtoList.add(productInfoDto);
             }
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				connection.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return productInfoDtoList;
	}

	public List<ProductInfoDTO> getProductInfoListBykeywords(String[] keywordsList, String categoryId){
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		List<ProductInfoDTO> productInfoDtoList = new ArrayList<ProductInfoDTO>();
		String sql = "select * from product_info where";									//カテゴリーでキーワード検索
		boolean initializeFlag = true;
		for(String keyword : keywordsList){
			if(initializeFlag){
				sql += " category_id=" + categoryId + " and ((product_name like '%" + keyword + "%' or product_name_kana like '%" + keyword + "%')";
				initializeFlag = false;
			}else{
				sql += " or (product_name like '%" + keyword + "%' or product_name_kana like '%" + keyword + "%')";
			}
		}
		sql += ")";
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				ProductInfoDTO productInfoDto = new ProductInfoDTO();
				productInfoDto.setId(resultSet.getInt("id"));
				productInfoDto.setProductId(resultSet.getInt("product_id"));
				productInfoDto.setProductName(resultSet.getString("product_name"));
				productInfoDto.setProductNameKana(resultSet.getString("product_name_kana"));
				productInfoDto.setProductDescription(resultSet.getString("product_description"));
				productInfoDto.setCategoryId(resultSet.getInt("category_id"));
				productInfoDto.setPrice(resultSet.getInt("price"));
				productInfoDto.setImageFilePath(resultSet.getString("image_file_path"));
				productInfoDto.setImageFileName(resultSet.getString("image_file_name"));
				productInfoDto.setReleaseDate(resultSet.getDate("release_date"));
				productInfoDto.setReleaseCompany(resultSet.getString("release_company"));
				productInfoDto.setStatus(resultSet.getInt("status"));
				productInfoDto.setRegistDate(resultSet.getDate("regist_date"));
				productInfoDto.setUpdateDate(resultSet.getDate("update_date"));
				productInfoDtoList.add(productInfoDto);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				connection.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return productInfoDtoList;
	}
}