<!DOCTYPE html>
<!-- directive cua JSTL -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<html lang="en">
	<jsp:include page="/WEB-INF/views/backend/layout/header.jsp"></jsp:include>
   
	<body>
		<jsp:include page="/WEB-INF/views/backend/layout/header1.jsp"></jsp:include>
		<div class="container-fluid page-body-wrapper">
			<jsp:include page="/WEB-INF/views/backend/layout/sidebar.jsp"></jsp:include>
			<div class="main-panel">
				<div class="content-wrapper">
					<div class="table-responsive">
						<div class="row">
							<div class="col-12">
								<div class="card">
									<div class="card-body">
										<sf:form class="form" action="${classpath }/admin/role/assign" method="post" modelAttribute="role">
											<div class="form-body">
												

												
												<label for="userId">Select Users</label>
												<select name="userId" class="form-control">
													<c:forEach items="${users}" var="user">
														<option value="${user.id}">${user.username}</option>
													</c:forEach>
												</select>

												
												<label for="roleId">Select Roles</label>
												<select name="roleId" class="form-control">
													<c:forEach items="${role}" var="role">
														<option value="${role.id}">${role.name}</option>
													</c:forEach>
												</select>

												
												<button type="submit" class="btn btn-primary mt-3">Add Role</button>

												
												<a href="${classpath }/admin/role/list" class="btn btn-secondary mt-3">Back to list</a>
											</div>
										</sf:form>
									</div>
								</div>    
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/backend/layout/js.jsp"></jsp:include>
	</body>
</html>
