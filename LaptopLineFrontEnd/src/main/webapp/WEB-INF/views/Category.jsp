<%@include file="Header.jsp"%>

<form action="addCategory" method="post">
<table align="center" class="table-bordered">
<tr>
	<td colspan="2">Category Detail</td>
</tr>

<tr>
	<td>Category Name </td>
	<td><input type="text" name="categorynames"/></td>
</tr>

<tr>
	<td>Category Desc </td>
	<td><input type="text" name="categorydescs"/></td>
</tr>

<tr>
	<td colspan="2">
		<input type="submit" value="Enter Category"/>
	</td>
</tr>

</table>
</form>


<table align="center" class="table">
<tr><td colspan="3"><center>Category Detail</center></td></tr>
<tr>
	<td> Category ID</td>
	<td> Category Name</td>
	<td> Category Desc </td>
	<td> Operation</td>
</tr>
<c:forEach items="${categoryList}" var="category">
<tr>
	
		<td>${category.categoryids}</td>
		<td>${category.categorynames}</td>
		<td>${category.categorydescs}</td>
		<td>
		<a href="<c:url value="/deleteCategory/${category.categoryids}"/>">Delete</a>
		<a href="<c:url value="/editCategory/${category.categoryids}"/>">Edit</a>
		</td>
</tr>
</c:forEach>

</table>
