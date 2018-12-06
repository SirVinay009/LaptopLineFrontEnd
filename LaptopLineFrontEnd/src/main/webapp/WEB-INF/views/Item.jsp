<%@include file="Header.jsp"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:if test="${flag}">
	<form:form action="addItem" modelAttribute="item" method="post" enctype="multipart/form-data">
<table align="center">
	<tr>
		<td colspan="2"><center>Item Detail</center></td>
	</tr>
	<tr>
		<td>Item Name</td>
		<td><form:input path="itemnames" /></td>
	</tr>
	<tr bgcolor="pink">
		<td>Price</td>
		<td><form:input path="price" /></td>
	</tr>
	<tr>
		<td>Stock</td>
		<td><form:input path="quantity" /></td>
	</tr>
	<tr bgcolor="pink">
		<td>Category</td>
		<td><form:select path="categoryids">
				<form:option value="0" label="--Select List--" />
				<form:options items="${categoryList}" />
			</form:select></td>
	</tr>
	<tr>
		<td>Distributor</td>
		<td><form:input path="distributorsid" /></td>
	</tr>
	<tr bgcolor="pink">
		<td>Item Description</td>
		<td><form:input path="itemdescs" /></td>
	</tr>
	<tr>
		<td>Item Image</td>
		<td><form:input type="file" path="partimage"/></td>
	</tr>
	
	<tr>
		<td colspan="2"><center>
				<input type="submit" value="Insert Item" />
				<center>
		</td>
	</tr>
	
</table>
</form:form>
</c:if>

<c:if test="${!flag}">
	<form:form action="http://localhost:8097/LaptopLineFrontEnd/updateItem" modelAttribute="item" method="post">
	<table align="center">
	<tr>
		<td colspan="2"><center>Item Detail</center></td>
	</tr>
	<tr>
		<td>Item Name</td>
		<td><form:input path="itemnames" /></td>
	</tr>
	<tr bgcolor="pink">
		<td>Price</td>
		<td><form:input path="price" /></td>
	</tr>
	<tr>
		<td>Stock</td>
		<td><form:input path="quantity" /></td>
	</tr>
	<tr bgcolor="pink">
		<td>Category</td>
		<td><form:select path="categoryids">
				<form:option value="0" label="--Select List--" />
				<form:options items="${categoryList}" />
			</form:select></td>
	</tr>
	<tr>
		<td>Distributor</td>
		<td><form:input path="distributorsid" /></td>
	</tr>
	<tr bgcolor="pink">
		<td>Item Description</td>
		<td><form:input path="itemdescs" /></td>
	</tr>
	<tr>
		<td colspan="2"><center>
				<input type="submit" value="Update Item" />
				<center>
		</td>
	</tr>

	</table>
	</form:form>
</c:if>


<table align="center" class="table">
<tr><td colspan="6"><center>Item Detail</center></td></tr>
<tr>
	<td> Item ID</td>
	<td> Item Name</td>
	<td> Price </td>
	<td> Stock</td>
	<td> Category</td>
	<td> Operation</td>
</tr>
<c:forEach items="${itemList}" var="item">
<tr>
		<td>${item.itemids}</td>
		<td>${item.itemnames}</td>
		<td>${item.price}</td>
		<td>${item.quantity}</td>
		<td>${item.categoryids}</td>
		<td>
		<a href="<c:url value="/deleteItem/${item.itemids}"/>">Delete</a>
		<a href="<c:url value="/editItem/${item.itemids}"/>">Edit</a>
		</td>
</tr>
</c:forEach>

</table>
