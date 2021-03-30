<%@page import="com.librarymgm.dto.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page isELIgnored="false"%>

<c:url var="addUrl" value="/ctl/book" />

<c:url var="addSearch" value="/ctl/book/search" />

<c:url var="editUrl" value="/ctl/book?id=" />

<c:url var="issueUrl" value="/ctl/issue?bCode=" />

<br>
<nav aria-label="breadcrumb">
	<ol class="breadcrumb ">
		<li class="breadcrumb-item"><a class="black-text" href="#">Home</a></li>
		<li class="breadcrumb-item active">Book List</li>
	</ol>
</nav>

<div class="col-md-5">
	<div class="form-area">
		<h4>Book List</h4>
	</div>
</div>
<b><%@ include file="businessMessage.jsp"%></b>
<hr>
<sf:form action="${addSearch}" method="post" modelAttribute="form">

	<div class="container">
		<div class="row row-cols-4">
			<div class="col">
				<s:bind path="name">
					<div class="input-group">
						<sf:input path="name" type="text" placeholder="Name"
							class="form-control" />
					</div>
				</s:bind>
			</div>
			<div class="col">
				<s:bind path="code">
					<div class="input-group">
						<sf:input path="code" type="text" placeholder="Book Code"
							class="form-control" />
					</div>
				</s:bind>
			</div>
			<%-- <div class="col">
				<s:bind path="city">
					<div class="input-group">
						<sf:input path="City" type="text" placeholder="city"
							class="form-control" />
					</div>
				</s:bind>
			</div> --%>
			<div class="col">
				<div class="input-group">

					<input type="submit" name="operation" class="btn btn-md btn-info"
						value="Search">&nbsp;&nbsp; <input type="submit"
						name="operation" class="btn btn-md btn-info" value="Reset">
				</div>
			</div>
		</div>
	</div>

	<br>

	<sf:input type="hidden" path="pageNo" />
	<sf:input type="hidden" path="pageSize" />

	<sf:input type="hidden" path="listsize" />
	<sf:input type="hidden" path="total" />
	<sf:input type="hidden" path="pagenosize" />

	<table class="table table-striped table-bordered table-sm" width="99%">
		<thead>
			<tr>
			<%
			UserDTO userBean = (UserDTO) session.getAttribute("user");
			if(userBean.getRoleId()==1){%>
				<th class="th-sm"><input type="checkbox" id="selectall">Select
					All</th>
					<%} %>
					<th class="th-sm">Image</th>
				<th class="th-sm">Book Code</th>
				<th class="th-sm">Book Name</th>
				<th class="th-sm">Writer Name</th>
				<th class="th-sm">Book Update</th>
				<th class="th-sm">Description</th>
				<%if(userBean.getRoleId()==1){ %>
				<th class="th-sm">Action</th>
				<th class="th-sm">Issue</th>
				<%} %>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="us" varStatus="rc">
				<tr>
				<%if(userBean.getRoleId()==1){ %>
					<td><input type="checkbox" class="case" name="ids"
						value="${us.id}"></td>
						<%} %>
						<td><img width="100px" height="100px" src="${pageContext.request.contextPath}/ctl/book/getImage/<c:out value="${us.id}"/>"></td>
					<td><c:out value="${us.bookCode}" /></td>
					<td><c:out value="${us.name}" /></td>
					<td><c:out value="${us.writerName}" /></td>
					<td><c:out value="${us.bookUpdate}" /></td>
					<td><c:out value="${us.description}" /></td>
					<%if(userBean.getRoleId()==1){ %>
					<td><a class="btn btn-primary" href="${editUrl}${us.id}">Edit</a></td>
					<td><a class="btn btn-primary" href="${issueUrl}${us.bookCode}">Issue</a></td>
					<%} %>
				</tr>
			</c:forEach>

		</tbody>
	</table>
	<div style="margin: 10px" class="row row-cols-4">
		<div class="col">
			<div class="input-group">
				<input type="submit" name="operation"
					<c:if test="${form.pageNo == 1}">disabled="disabled"</c:if>
					class="btn btn-primary" value="Previous">
			</div>
		</div>
		<%if(userBean.getRoleId()==1){ %>
		<div class="col">
			<div class="input-group">
				<input type="submit" name="operation"
					<c:if test="${listsize== 0}">disabled="disabled"</c:if>
					class="btn btn-primary" value="Delete">
			</div>
		</div>
		<div class="col">
			<div class="input-group">
				<input type="submit" name="operation" class="btn btn-primary"
					value="New">
			</div>
		</div>
		<%} %>
		<div class="col">
			<div class="input-group">

				<input type="submit" name="operation"
					<c:if test="${total == pagenosize  || listsize < pageSize   }">disabled="disabled"</c:if>
					class="btn btn-primary" value="Next">
			</div>
		</div>
	</div>
</sf:form>