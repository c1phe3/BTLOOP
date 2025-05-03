<!DOCTYPE html>
<!-- directive của JSTL -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
   <jsp:include page ="/WEB-INF/views/backend/layout/header.jsp"></jsp:include>
   <!-- partial -->
    <body>
    <jsp:include page ="/WEB-INF/views/backend/layout/header1.jsp"></jsp:include>
    
    <div class="container-fluid page-body-wrapper">
    <jsp:include page="/WEB-INF/views/backend/layout/sidebar.jsp"></jsp:include>
   		<div class ="main-panel">
   			 <div class="content-wrapper">
                 <div class="col-12 grid-margin">
                <div class="card">
                <sf:form class="form" action="${classpath }/admin/category/add-save" method="post" modelAttribute="category" enctype="multipart/form-data">
                  <div class="card-body">
                    <h4 class="card-title">Category Adding</h4>
                    <form class="form-sample">
                      <div class="row">
                        <div class="col-md-6">
                          <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Category Name</label>
                            <div class="col-sm-9">
                             <sf:input path="name" type="text" class="form-control" id="name" name="name" placeholder="category name"></sf:input>
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="row">
                         <div class="col-md-6">
                          <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Create By</label>
                            <div class="col-sm-9">
                            <sf:select path="userCreateCategory.id" class="form-control" id="userCreateCategory">
			                  <sf:options items="${users }" itemValue="id" itemLabel="username"></sf:options>
			                  </sf:select> 
                            </div>
                          </div>
                        </div>
                        <div class="col-md-6">
                          <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Update By</label>
                            <div class="col-sm-9">
                            <sf:select path="userUpdateCategory.id" class="form-control" id="userUpdateCategory">
			                 <sf:options items="${users }" itemValue="id" itemLabel="username"></sf:options>
			                 </sf:select> 
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="row">
                        <div class="col-md-6">
                          <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Create Date</label>
                            <div class="col-sm-9">
                              <sf:input path="createDate" class="form-control" type="date" 
			                                        			id="createDate" name="createDate"></sf:input>
                            </div>
                          </div>
                        </div>
                        <div class="col-md-6">
                          <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Update Date</label>
                            <div class="col-sm-9">
                             <sf:input path="updateDate" class="form-control" type="date" 
			                                        			id="updateDate" name="updateDate"></sf:input>
                            </div>
                          </div>
                        </div>
                      </div>
                      <p class="card-description"> Address </p>
                      <div class="row">
                        	<div class="col-md-12">
							<div class="form-group mb-4">
			                <label for="description">Description</label>
			                 <sf:textarea path="description" id="description" name="description"
							class="form-control" rows="3" placeholder="desription..."></sf:textarea>
                      </div>
	                 </div>
                      </div>
                      <div class="row">
                      </div>
                      <div class="row">
                       <div class="col-md-12">
						<div class="form-group mb-4">
			           <a href="${classpath }/admin/category/list" class="btn btn-secondary active" role="button" aria-pressed="true">
			             Back to list
			           </a>
                       <button type="submit" class="btn btn-primary">Save category</button>
                         </div>
	                 </div>
                        
                      </div>
                    </form>
                  </div>
                  </sf:form>
                </div>
              </div>
             </div>
            </div>
            </div>
<jsp:include page="/WEB-INF/views/backend/layout/js.jsp"></jsp:include>
</body>
</html>