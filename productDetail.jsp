<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<link rel="stylesheet" href="./css/productDetails.css">
<link rel="stylesheet" href="./css/mars.css">
<title>商品詳細</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div id="main">
<h1>商品詳細画面</h1>
<s:if test="productInfoDtoList.isEmpty()">
<div class="info">
商品の詳細情報がありません。
</div>
</s:if>
<s:else>
    <s:form action="AddCartAction">
    <div class="box">
    <div class="2column-container">
    <div class="right">
         <img src='<s:property value="%{#session.imageFilePath}"/>/<s:property value="%{#session.imageFileName}"/>' class="item-image-box-320"/><br>
    </div>
    <div class="left">
    <table class="vertical-list-table-mini">
        <tr>
          			<th scope="row"><s:label value="商品名"/></th>
          			<td><s:property value="%{#session.productName}"/></td>
        </tr>
        <tr>
        			<th scope="row"><s:label value="商品名ふりがな"/></th>
        			<td><s:property value="%{#session.productNameKana}"/></td>
        </tr>
         <tr>
          			<th scope="row"><s:label value="値段"/></th>
          			<td><s:property value="%{#session.price}"/>円</td>
        </tr>
        <tr>
          			<th scope="row"><s:label value="購入個数"/></th>
          			<td><s:select name="productCount" list="%{#session.productCountList}"/>個</td>
        </tr>
        <tr>
          			<th scope="row"><s:label value="発売会社名"/></th>
          			<td><s:property value="%{#session.releaseCompany}"/></td>
        </tr>
        <tr>
          			<th scope="row"><s:label value="発売年月日"/></th>
          			<td><s:property value="%{#session.releaseDate}"/></td>
        </tr>
        <tr>
          			<th scope="row"><s:label value="商品詳細情報"/></th>
          			<td><s:property value="%{#session.productDescription}"/></td>
        </tr>
    </table>
    </div>
    </div>
    <s:hidden name="productId" value="%{#session.productId}"/>
    <s:hidden name="productName" value="%{#session.productName}"/>
    <s:hidden name="productNameKana" value="%{#session.productNameKana}"/>
    <s:hidden name="imageFilePath" value="%{#session.imageFilePath}"/>
    <s:hidden name="imageFileName" value="%{#session.imageFileName}" />
    <s:hidden name="price" value="%{#session.price}"/>
    <s:hidden name="releaseCompany" value="%{#session.releaseCompany}"/>
    <s:hidden name="releaseDate" value="%{#session.releaseDate}"/>
    <s:hidden name="productDescription" value="%{#session.productDescription}" />
    </div>
    <div class="submit_btn_box">
    <s:submit value="カートに追加" class="submit_btn a" />
    </div>
    </s:form>
    </s:else>
    <div class="box">

<div class="product-details-recommend-box">
<s:iterator value="#session.productInfoDtoList">
    <div class="product-list">
         <div class="product-list-box">
         <div class="recommend-box">
         <a href='<s:url action="ProductDetailsAction">
		<s:param name="productId" value="%{productId}"/>
		</s:url>'><img src='<s:property value="imageFilePath"/>/<s:property value="imageFileName"/>' class="item-image-box-100"/></a>
		<p><s:property value="productName"/></p>
         </div>
         </div>
    </div>

</s:iterator>
</div>
    </div>
</div>
</body>
</html>