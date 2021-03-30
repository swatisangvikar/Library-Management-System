<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<nav aria-label="breadcrumb">
    <ol class="breadcrumb ">
      <li class="breadcrumb-item"><a class="black-text" href="#">Home</a></li>
      <li class="breadcrumb-item active">Issue Book</li>
    </ol>
  </nav>

<div col-md-5 img-thumbnail>
           <div id="feedback"> <div class="container">
<div class="col-md-5">
    <div class="form-area">  
       <sf:form method="post" action="${pageContext.request.contextPath}/ctl/issue"  modelAttribute="form" >
        <br style="clear:both">
                    <h3 style="margin-bottom: 15px; text-align: left: ;">Issue Book</h3>
                    <b><%@ include file="businessMessage.jsp"%></b>
                		
							<div class="form-group">
								<s:bind path="libCode">
								<label >Student Library Code:</label> 
								<sf:input path="${status.expression}" placeholder="Enter Student Library Code" class="form-control" />
									<font color="red" style="font-size: 13px"><sf:errors
											path="${status.expression}" /></font>
							</s:bind>
							</div>
							
							
							<div class="form-group">
								<s:bind path="bCode">
								<label >Book Code:</label> 
								<sf:input path="${status.expression}" placeholder="Enter Book Code" class="form-control" />
									<font color="red" style="font-size: 13px"><sf:errors
											path="${status.expression}" /></font>
							</s:bind>
							</div>
							
							
							
							
							
							
							<div class="form-group">
								<s:bind path="issueDate">
								<label >Issue Date:</label> 
								<sf:input path="${status.expression}" id="datepicker1" placeholder="Enter Issue Date(MM/dd/yyyy)" class="form-control" />
									<font color="red" style="font-size: 13px"><sf:errors
											path="${status.expression}" /></font>
							</s:bind>
							</div>
							
							<div class="form-group">
								<s:bind path="returnDate">
								<label >Return Date:</label> 
								<sf:input path="${status.expression}"  id="datepicker1" placeholder="Enter Return Date(MM/dd/yyyy)" class="form-control" />
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

	