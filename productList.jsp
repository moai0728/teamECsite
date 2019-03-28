<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<link rel="stylesheet" href="./css/productList.css">
<link rel="stylesheet" href="./css/mars.css">
<title>商品一覧</title>
</head>
<body>
<jsp:include page="header.jsp" />
<div id="main">
<h1>商品一覧画面</h1>
<s:if test = "(!(keywordsErrorMessageList.isEmpty()))">
    <div class="error">
         <s:iterator value="keywordsErrorMessageList"><s:property /><br></s:iterator>
    </div>
</s:if>
<s:elseif test="productInfoDtoList==null">
<div class="info">
検索結果がありません。
</div>
</s:elseif>
<s:else>
<div id="product-list">
<s:iterator value="productInfoDtoList">
<div class="product-list-box">
<ul>
    <li>
    <a href='<s:url action="ProductDetailsAction">
    <s:param name="productId" value="%{productId}"/>
    </s:url>'><img src='<s:property value="imageFilePath"/>/<s:property value="imageFileName"/>' class="item-image-box-200"/></a><br>
    <p class="font"><s:property value="productName"/></p>
    <p class="font"><s:property value="productNameKana"/></p>
    <p><s:property value="price"/>円</p>
    </li>
</ul>
</div>
</s:iterator>
</div>

</s:else>
</div>
</body>
</html>