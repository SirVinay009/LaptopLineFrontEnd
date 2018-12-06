<%@include file="Header.jsp"%>

<form action="<c:url value="/updateCategory"/>" method="post">
<table align="center" class="table-bordered">
<tr>
	<td colspan="2">Category Detail</td>
</tr>

<tr>
	<td>Category Id</td>
	<td><input type="text" name="categoryids" value="${categoryData.categoryids}" readOnly/></td>
</tr>

<tr>
	<td>Category Name </td>
	<td><input type="text" name="categorynames" value="${categoryData.categorynames}"/></td>
</tr>

<tr>
	<td>Category Desc </td>
	<td><input type="text" name="categorydescs" value="${categoryData.categorydescs}"/></td>
</tr>

<tr>
	<td colspan="2">
		<input type="submit" value="Update Category"/>
	</td>
</tr>

</table>
</form>