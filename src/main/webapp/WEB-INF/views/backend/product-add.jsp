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
   		<div class="main-panel">
          <div class="content-wrapper">
           
<div class="table-responsive">
                                	
                                  <div class="row">
                	<div class="col-12">
	                    <div class="card">
	                        <div class="card-body">
	                        	<sf:form class="form" action="${classpath }/admin/product/add-save" method="post" modelAttribute="product" enctype="multipart/form-data">
	                        		 
	                        		 <div class="form-body">
	                        			<div class="row">
	                        		 		<div class="col-md-6">
												<div class="form-group mb-4">
			                                        <label for="category">Select category</label>
			                                        <sf:select path="category.id" class="form-control" id="category">
			                                            <sf:options items="${categories }" itemValue="id" itemLabel="name"></sf:options>
			                                        </sf:select>
                                        		</div>
	                                    	</div>
										
	                        		 		<div class="col-md-6">
												<div class="form-group mb-4">
			                                        <label for="name">Product name</label>
			                                        <sf:input path="name" type="text" class="form-control" id="name" name="name" placeholder="product name"></sf:input>
                                        		</div>
	                                    	</div>
										</div>
										
										<div class="row">
	                        		 		<div class="col-md-6">
												<div class="form-group mb-4">
			                                        <label for="price">Price</label>
			                                        <sf:input path="price" type="number" autocomplete="off" id="price" name="price" class="form-control" placeholder="price"></sf:input>
                                        		</div>
	                                    	</div>
										
	                        		 		<div class="col-md-6">
												<div class="form-group mb-4">
			                                        <label for="salePrice">Sale price</label>
			                                        <sf:input path="salePrice" type="number" autocomplete="off" id="salePrice" name="salePrice" class="form-control" placeholder="Sale price"></sf:input>
                                        		</div>
	                                    	</div>
										</div>
										
										<div class="row">
	                        		 		<div class="col-md-6">
												<div class="form-group mb-4">
			                                        <label for="create">Create by</label>
			                                        <sf:select path="userCreateProduct.id" class="form-control" id="createBy">
			                                            <sf:options items="${users }" itemValue="id" itemLabel="username"></sf:options>
			                                        </sf:select>
                                        		</div>
	                                    	</div>
									
											<div class="col-md-6">
												<div class="form-group mb-4">
			                                        <label for="update">Update by</label>
			                                        <sf:select path="userUpdateProduct.id" class="form-control" id="updateBy">
			                                            <sf:options items="${users }" itemValue="id" itemLabel="username"></sf:options>
			                                        </sf:select>
                                        		</div>
	                                    	</div>
										</div>
										
										<div class="row">
	                        		 		<div class="col-md-6">
												<div class="form-group mb-4">
			                                        <label for="createdate">Create date</label>
			                                        
			                                        <sf:input path="createDate" class="form-control" type="date" 
			                                        			id="createDate" name="createDate"></sf:input>
                                        		</div>
	                                    	</div>
									
											<div class="col-md-6">
												<div class="form-group mb-4">
			                                        <label for="updatedate">Update date</label>
			                                       
			                                        <sf:input path="updateDate" class="form-control" type="date" 
			                                        			id="updateDate" name="updateDate"></sf:input>
                                        		</div>
	                                    	</div>
										</div>
										
										<div class="row">
	                        		 		<div class="col-md-12">
												<div class="form-group mb-4">
			                                        <label for="description">Description</label>
			                                        <sf:textarea path="shortDescription" id="shortDescription" name="shortDescription"
																class="form-control" rows="3" placeholder="Short desription..."></sf:textarea>
                                        		</div>
	                                    	</div>
										</div>
										
										<div class="row">
	                        		 		<div class="col-md-12">
												<div class="form-group mb-4">
			                                        <label for="detailDescription">Detail description</label>
			                                        <sf:textarea path="detailDescription" id="detailDescription" name="detailDescription"
																class="form-control" rows="3" placeholder="detail desription..."></sf:textarea>
                                        		</div>
	                                    	</div>
										</div>
										
										<div class="row">
	                        		 		<div class="col-md-2">
												<div class="form-group mb-4">
													<label for="isHot">&nbsp;&nbsp;&nbsp;&nbsp;</label>
													<sf:checkbox path="isHot" class="form-check-input" id="isHot" name="isHot"></sf:checkbox>
			                                        <label for="isHot">Is a hot product?</label>
			                                       
                                        		</div>
	                                    	</div>
	                                    	
	                                    	<div class="col-md-10">
												<div class="form-group mb-4">
													<label for="status">&nbsp;&nbsp;&nbsp;&nbsp;</label>
													<sf:checkbox path="status" class="form-check-input" id="status" name="status"></sf:checkbox>
			                                        <label for="status">Active</label>
			                                       
                                        		</div>
	                                    	</div>
	                                    	
										</div>
										
										<div class="row">
	                        		 		<div class="col-md-12">
												<div class="form-group mb-4">
			                                        <label for="avatarFile">Choose product Avatar</label>
                                    				
                                        		</div>
                                        		<input id="avatarFile" name="avatarFile" type="file" class="form-control-file" multiple="multiple" >
	                                    	</div>
										</div>
										
										<!-- 
										<div class="row">
	                        		 		<div class="col-md-12">
												<div class="form-group mb-4">
			                                        <label for="image">Choose product Image</label>
                               
                                        		</div>
                                        		<input id="imageFiles" name="imageFiles" type="file" class="form-control-file" multiple="multiple" >
	                                    	</div>
										</div>
									
										<div class="row">
	                        		 		<div class="col-md-12">
												<div class="form-group mb-4">
			                                        <label for="image">Choose product Image</label>
                                    				<input id="imageFiles" name="imageFiles" type="file" class="form-control-file" multiple="multiple" >
                                        		</div>
	                                    	</div>
										</div>
										
										<div class="row">
	                        		 		<div class="col-md-12">
												<div class="form-group mb-4">
			                                        <label for="image">Choose product Image</label>
                                    				<input id="imageFiles" name="imageFiles" type="file" class="form-control-file" multiple="multiple" >
                                        		</div>
	                                    	</div>
										</div>
										
										<div class="row">
	                        		 		<div class="col-md-12">
												<div class="form-group mb-4">
			                                        <label for="image">Choose product Image</label>
                                    				<input id="imageFiles" name="imageFiles" type="file" class="form-control-file" multiple="multiple" >
                                        		</div>
	                                    	</div>
										</div>
										-->
										<div class="row">
	                        		 		<div class="col-md-12">
												<div class="form-group mb-4">
													<button type="submit" class="btn btn-primary">Save add product</button>
			                                        <a href="${classpath }/admin/product/list" class="btn btn-secondary active" role="button" aria-pressed="true">
			                                        	Back to list
			                                        </a>
                                        		</div>
	                                    	</div>
										</div>
										
	                        		</div>
	                        	</sf:form>
	                        </div>
	                    </div>    
                  </div> 
				
				</div>
	                            </div>
        </div>
        <!-- main-panel ends -->
      </div>
            </div>
<jsp:include page="/WEB-INF/views/backend/layout/js.jsp"></jsp:include>
<script type="text/javascript">
		$( document ).ready(function() {
			//Dat gia tri cua status ung voi dieu kien search truoc do
			$("#status").val(${productSearch.status});
			//Dat gia tri cua category ung voi dieu kien search truoc do
			$("#categoryId").val(${productSearch.categoryId});
			//Dat gia tri cua keyword ung voi dieu kien search truoc do
			$("#keyword").val(${productSearch.keyword});
			
			
			$("#paging").pagination({
				currentPage: ${productSearch.currentPage}, //Trang hien tai
				items: ${productSearch.totalItems}, //Tong so san pham (total products)
				itemsOnPage: ${productSearch.sizeOfPage},
				cssStyle: 'light-theme',
				onPageClick: function(pageNumber, event) {
					$('#currentPage').val(pageNumber);
					$('#btnSearch').trigger('click');
				},
			});
		});
	</script>
</body>
</html>