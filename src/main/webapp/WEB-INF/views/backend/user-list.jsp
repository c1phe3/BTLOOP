<!DOCTYPE html>
<!-- directive của JSTL -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
   <jsp:include page ="/WEB-INF/views/backend/layout/header.jsp"></jsp:include>
 <body>
 	<jsp:include page ="/WEB-INF/views/backend/layout/header1.jsp"></jsp:include>
		<!-- partial -->
		<div class="container-fluid page-body-wrapper">
			<jsp:include page="/WEB-INF/views/backend/layout/sidebar.jsp"></jsp:include>
			<div class="main-panel">
				<div class="content-wrapper">
					<div class="row">
						<div class="col-lg-12 grid-margin stretch-card">
							<div class="card">
								<div class="card-body">
									<h4 class="card-title">Users Information</h4>
									<div class="table-responsive">
										<table id="zero_config" class="table table-striped table-bordered no-wrap">
											<thead>
												<tr>
                                            	<th >No.</th>
                                                <th >Id</th>
                                                <th >UserName</th>   
                                                <th >Password</th>    
                                                <th >Name</th>    
                                                <th >Mobile</th>    
                                                <th >Email</th>    
                                                <th >address</th>     
                                                <th >Create by</th>
                                                <th >Update by</th>
                                                <th >Create date</th>
                                                <th>Update date</th>
                                                <th>avatar</th>    
                                                <th>Status</th>  
                                                <th>Description</th>
                                                <th>Actions</th>                                  
                                            </tr>
											</thead>
											 <tbody>
                                        	<c:forEach var="user" items="${users }" varStatus="loop">
                                        		<tr>
		                                        	<td>${loop.index + 1 }</td>
		                                        	<td>${user.id }</td>
		                                        	<td>${user.username }</td>
		                                        	<td>${user.password }</td>
		                                        	<td>${user.name }</td>
		                                        	<td>${user.mobile }</td>
		                                        	<td>${user.email }</td>
		                                        	<td>${user.address }</td>
		                                        	<td>${user.userCreateUser.username }</td>
		                                        	<td>${user.userUpdateUser.username }</td>
		                                        	<td>
		                                        		<fmt:formatDate value="${user.createDate }" pattern="dd-MM-yyyy"/>
		                                        	</td>
		                                        	<td>
		                                        		<fmt:formatDate value="${user.updateDate }" pattern="dd-MM-yyyy"/>
		                                        	</td>
		                                        	<td>${user.avatar}</td>
		                                        	<td>
		                                        		<c:choose>
		                                        			<c:when test="${user.status }">
		                                        				<span>Active</span>
		                                        			</c:when>
		                                        			<c:otherwise>
		                                        				<span>Inactive</span>
		                                        			</c:otherwise>
		                                        		</c:choose>
		                                        		</td>
	                                        		<td>${user.description }</td>
	                                        		<td>
	                                        			<a href="${classpath }/admin/user/edit/${user.id }" role="button" 
	                                                							class="btn btn-primary">Edit</a>
	                                                	<a href="${classpath }/admin/user/delete/${user.id }" role="button" 
	                                                							class="btn btn-secondary">Delete</a>
	                                        		</td>
                                        		</tr>
                                        	</c:forEach>
                                        </tbody>
										</table>
									</div>
								</div>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
		<!-- content-wrapper ends -->
	<!-- main-panel ends -->

	<!-- container-scroller -->
	<jsp:include page="/WEB-INF/views/backend/layout/js.jsp"></jsp:include>
</body>
</html>