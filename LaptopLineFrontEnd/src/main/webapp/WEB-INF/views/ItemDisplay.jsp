<%@include file="Header.jsp"%>

<div class="container">
<table align="center" class="table">
<tr>
<td colspan="3"><center>Item Detail</center></td>
</tr>
<tr>
	<td rowspan="9">
			<img src="<c:url value="/resources/images/${itemInfo.itemids}.jpg"/>" width="300" height="200"/>
	</td>
</tr>
<tr>
	<td>Item ID</td>
	<td>${itemInfo.itemids}</td>
</tr>
<tr>
	<td>Item Name</td>
	<td>${itemInfo.itemnames}</td>
</tr>
<tr>
	<td>Distributor ID</td>
	<td>${itemInfo.distributorsid}</td>
</tr>
<tr>
	<td>Category</td>
	<td>${categorynames}</td>
</tr>
<tr>
	<td>Price</td>
	<td>${itemInfo.price}</td>
</tr>
<tr>
	<td>Stock</td>
	<td>${itemInfo.quantity}</td>
</tr>
<tr>
	<td>Product Description</td>
	<td>${itemInfo.itemdescs}</td>
</tr>

<form action="<c:url value="/addToCart/${itemInfo.itemids}"/>" method="get">
<tr>
	<td>Quantity</td>
	<td><input type="text" name="qty" required/></td>
</tr>
<tr>
	<td></td>
	<td> <input type="submit" value="ADDTOCart" class="btn btn-info"/></td>
</tr>
</form>
</table>
</div>

