<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>

<html lang="en">

<head>
    <meta charset="utf-8">
    <title>E Shopper</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="Free HTML Templates" name="keywords">
    <meta content="Free HTML Templates" name="description">

    <jsp:include page="/WEB-INF/views/frontend/layout/css.jsp"></jsp:include>
</head>

<body>
    <jsp:include page="/WEB-INF/views/frontend/layout/header.jsp"></jsp:include>

    <div class="container-fluid bg-secondary mb-5">
        <div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 300px">
            <h1 class="font-weight-semi-bold text-uppercase mb-3">Shopping Cart</h1>
            <div class="d-inline-flex">
                <p class="m-0"><a href="">Home</a>
                </p>
                <p class="m-0 px-2">-</p>
                <p class="m-0">Shopping Cart</p>
            </div>
        </div>
    </div>
    <div class="container-fluid pt-5">
        <div class="row px-xl-5">
            <div class="col-lg-8 table-responsive mb-5">
                <table class="table table-bordered text-center mb-0">
                    <thead class="bg-secondary text-dark">
                        <tr>
                            <th scope="col" class="text-center">No.</th>
                            <th scope="col" class="text-center">Image</th>
                            <th scope="col" class="text-center">Product</th>
                            <th scope="col" class="text-center">Quantity</th>
                            <th scope="col" class="text-center">Price</th>
                            <th scope="col" class="text-center">Total</th>
                            <th scope="col" class="text-center">Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="cartProduct" items="${cart.cartProducts }" varStatus="loop">
                            <tr>
                                <th scope="row">${loop.index + 1 }</th>
                                <td><img width="60px" height="60px" src="${classpath}/UploadFiles/${cartProduct.avatar }" class="light-logo">
                                </td>
                                <td>${cartProduct.name }</td>
                                <td align="center">
                                    <button type="button" onclick="updateProductQuantity(${cartProduct.id }, -1)" value="-">-</button> <strong><span
																	id="productQuantity_${cartProduct.id }">${cartProduct.quantity }</span></strong>
                                    <button type="button" onclick="updateProductQuantity(${cartProduct.id }, 1)" value="+">+</button>
                                </td>
                                <td align="right">
                                    <fmt:formatNumber value="${cartProduct.price }" minFractionDigits="0" />
                                </td>
                                <td align="right"><span id="totalPrice_${cartProduct.id}"><fmt:formatNumber
																		value="${cartProduct.price * cartProduct.quantity }"
																		minFractionDigits="0" /></span>
                                </td>
                                <td align="center"><a href="${classpath }/product-detail/${cartProduct.id }" role="button" class="btn btn-secondary">Edit</a> <a href="${classpath }/product-cart-delete/${cartProduct.id }" role="button" class="btn btn-secondary">Delete</a>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="col-lg-4">
                <form action="${classpath }/cart-view" method="get">
                    <div class="row">

                        <div class="col-12">
                            <div class="card">
                                <div class="card-body">
                                    <div class="form-body">
                                        <div class="row">

                                            <div class="col-md-12">
                                                <div class="form-group mb-4">
                                                    <label for="name">Customer name (*)</label>
                                                    <input type="text" class="form-control" id="txtName" name="txtName" placeholder="your name" value="${loginedUser.name }" />
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">

                                            <div class="col-md-12">
                                                <div class="form-group mb-4">
                                                    <label for="mobile">Customer mobile (*)</label>
                                                    <input type="text" class="form-control" id="txtMobile" name="txtMobile" placeholder="your phone number" value="${loginedUser.mobile }" />
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">

                                            <div class="col-md-12">
                                                <div class="form-group mb-4">
                                                    <label for="phone">Customer email</label>
                                                    <input type="email" class="form-control" id="txtEmail" name="txtEmail" placeholder="your email" value="${loginedUser.email }" />
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">

                                            <div class="col-md-12">
                                                <div class="form-group mb-4">
                                                    <label for="phone">Customer address</label>
                                                    <input type="text" class="form-control" id="txtAddress" name="txtAddress" placeholder="your address" value="${loginedUser.address }" />
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="form-group mb-4">
                                                    <a href="${classpath }/index" class="btn btn-primary active" role="button" aria-pressed="true"> Back to shop </a>
                                                    <!--<button class="btn btn-primary" onclick="_placeOrder()">Place orders</button> -->

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </form>
                <div class="card border-secondary mb-5">
                    <div class="card-header bg-secondary border-0">
                        <h4 class="font-weight-semi-bold m-0">Cart Summary</h4>
                    </div>
                    <div class="card-body">
                        <div class="d-flex justify-content-between mb-3 pt-1">
                            <h6 class="font-weight-medium">Subtotal</h6>
                            <h6 class="font-weight-medium">
                                <fmt:formatNumber value="${totalCartPrice}" type="currency" />
                            </h6>
                        </div>
                        <div class="d-flex justify-content-between">
                            <h6 class="font-weight-medium">Shipping</h6>
                            <h6 class="font-weight-medium">$10</h6>
                        </div>
                    </div>
                    <div class="card-footer border-secondary bg-transparent">
                        <div class="d-flex justify-content-between mt-2">
                            <h5 class="font-weight-bold">Total</h5>
                            <h5 class="font-weight-bold">
                                <fmt:formatNumber value="${totalCartPrice + 10}" type="currency" />
                            </h5>
                        </div>
                        <div class="text-center mt-3">
       						 <img src="${classpath}/frontend/img/checkout.jpg" alt="Checkout Image" style="max-width: 100px;">
    					</div>
                        <button onclick="_placeOrder()" class="btn btn-block btn-primary my-3 py-3">Proceed To Checkout</button>
                    </div>
                </div>
            </div>

        </div>
    </div>
    <jsp:include page="/WEB-INF/views/frontend/layout/footer.jsp"></jsp:include>

    <a href="#" class="btn btn-primary back-to-top"><i class="fa fa-angle-double-up"></i></a>

    <jsp:include page="/WEB-INF/views/frontend/layout/js.jsp"></jsp:include>

    <script type="text/javascript">
		updateProductQuantity = function(_productId, _quantity) {
			let data = {
				id : _productId, //lay theo id
				quantity : _quantity
			};

			//$ === jQuery
			jQuery.ajax({
				url : "/update-product-quantity",
				type : "POST",
				contentType : "application/json",
				data : JSON.stringify(data),
				dataType : "json", //Kieu du lieu tra ve tu controller la json

				success : function(jsonResult) {
					let totalProducts = jsonResult.totalCartProducts; 
					//Viet lai so luong sau khi bam nut -, +
					$("#productQuantity_" + jsonResult.productId).html(jsonResult.newQuantity); 
					$("#totalCartPriceId").html(jsonResult.totalCartPrice); 
					$("#totalPrice_" + jsonResult.productId).html(jsonResult.totalPrice); 
					$("#totalCartProducts").html(jsonResult.totalCartProducts); 
					
				},

				error : function(jqXhr, textStatus, errorMessage) {
					alert("An error occur");
				}
			});
		}
	</script>

    <script type="text/javascript">
        function _placeOrder() {
            //javascript object
            let data = {
                name: jQuery("#txtName").val(),
                email: jQuery("#txtEmail").val(), //Get by Id
                mobile: jQuery("#txtMobile").val(),
                address: jQuery("#txtAddress").val(),
            };

            //$ === jQuery
            jQuery.ajax({
                url: "/place-order",
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify(data),
                dataType: "json", //Kieu du lieu tra ve tu controller la json

                success: function(jsonResult) {
                    alert(jsonResult.code + ": " + jsonResult.message);
                    //$("#placeOrderSuccess").html(jsonResult.message);
                },

                error: function(jqXhr, textStatus, errorMessage) {
                    alert("An error occur");
                }
            });
        }
    </script>

</body>

</html>