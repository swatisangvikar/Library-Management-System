<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<nav aria-label="breadcrumb">
    <ol class="breadcrumb ">
      <li class="breadcrumb-item"><a class="black-text" href="#">Home</a></li>
      <li class="breadcrumb-item active">Book</li>
    </ol>
  </nav>

<div col-md-5 img-thumbnail>
           <div id="feedback"> <div class="container">
<div class="col-md-5">
    <div class="form-area">  
       <sf:form method="post" action="${pageContext.request.contextPath}/ctl/book"  modelAttribute="form" enctype="multipart/form-data">
        <br style="clear:both">
                    <h3 style="margin-bottom: 15px; text-align: left: ;">Book</h3>
                    <b><%@ include file="businessMessage.jsp"%></b>
                		<sf:hidden path="id"/>
							<div class="form-group">
								<s:bind path="code">
								<label >Book Code:</label> 
								<sf:input path="${status.expression}" placeholder="Enter Book Code" class="form-control" />
									<font color="red" style="font-size: 13px"><sf:errors
											path="${status.expression}" /></font>
							</s:bind>
							</div>
							
							
							<div class="form-group">
								<s:bind path="name">
								<label >Name:</label> 
								<sf:input path="${status.expression}" placeholder="Enter Name" class="form-control" />
									<font color="red" style="font-size: 13px"><sf:errors
											path="${status.expression}" /></font>
							</s:bind>
							</div>
							
							<div class="form-group">
								<s:bind path="writerName">
								<label >Writer Name:</label> 
								<sf:input path="${status.expression}" placeholder="Enter Writer Name" class="form-control" />
									<font color="red" style="font-size: 13px"><sf:errors
											path="${status.expression}" /></font>
							</s:bind>
							</div>
							
							<div class="form-group">
								<s:bind path="bookUpdate">
								<label >Status:</label> 
								<sf:input path="${status.expression}" placeholder="Enter Status" class="form-control" />
									<font color="red" style="font-size: 13px"><sf:errors
											path="${status.expression}" /></font>
							</s:bind>
							</div>
							
							<s:bind path="image">
							<div class="form-group">
							<label >Book Image:</label> 
								<sf:input type="file" path="${status.expression}" placeholder="Select Image"
									class="form-control" />
								<font color="red"><sf:errors path="${status.expression}" /></font>
							</div>
						</s:bind>
						
						<div class="form-group">
							<s:bind path="description">
								<label>Description:</label>
								<sf:textarea rows="4" cols="4" path="${status.expression}"
									placeholder="Enter Description" class="form-control" />
								<font color="red" style="font-size: 13px"><sf:errors
										path="${status.expression}" /></font>
							</s:bind>
						</div>
							
        					 <input type="submit" name="operation"
								class="btn btn-primary pull-right" value="Save">or<input type="submit" name="operation"
								class="btn btn-primary pull-right" value="Reset">
        </sf:form>
    </div>
</div>
</div> </div> <!--feedback-->
<br>

	