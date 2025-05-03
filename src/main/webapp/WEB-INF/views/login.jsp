<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!--
<!DOCTYPE html>
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="css/login.css" rel="stylesheet" type="text/css" />
    <title>Đăng nhập hệ thống</title>
</head>
<!-- 
<body>
    <div class="login-01">
        <div class="one-login hvr-float-shadow">
            <div class="one-login-head">
                <img src="images/top-lock.png" alt="" />
                <h1>LOGIN</h1>
                <span></span>
            </div>

            <form action="LoginServlet" method="post">
                <!-- Hiển thị thông báo lỗi nếu có -->
                
               <!-- <p style="color: red">${err}</p> -->
                
                <!-- Hiển thị thông báo thành công nếu có -->
               <!--  <p style="color: green">${mess}</p> -->
			
                <!-- Form đăng nhập -->
                <!-- 
                <label for="username">Tên người dùng</label>
                <li>
                    <input type="text" class="text" name="username" required placeholder="Nhập tên người dùng">
                    <a href="#" class="icon user"></a>
                </li>

                <label for="password">Mật khẩu</label>
                <li>
                    <input type="password" name="password" required placeholder="Nhập mật khẩu">
                    <a href="#" class="icon lock"></a>
                </li>

                <div class="p-container">
                    <label class="checkbox">
                        <input type="checkbox" name="checkbox" checked>
                        <i></i> Ghi nhớ mật khẩu
                    </label>
                    <h6><a href="resetpassword.jsp">Khôi phục mật khẩu</a></h6>
                    <div class="clear"></div>
                </div>

                <div class="submit">
                    <input type="submit" value="SIGN IN">
                </div>

                <!-- Các biểu tượng mạng xã hội -->
               <!-- <div class="social-icons">
                    <p>Bạn có thể đăng nhập bằng tài khoản:</p>
                    <ul class="soc_icons2">
                        <li class="pic"><a href="#"><i class="icon_4"></i></a></li>
                        <li class="pic"><a href="#"><i class="icon_5"></i></a></li>
                        <li class="pic"><a href="#"><i class="icon_6"></i></a></li>
                        <div class="clear"></div>
                    </ul>
                </div>

                <h5>Bạn muốn đăng ký tài khoản? <a href="register.jsp">Đăng ký</a></h5>
            </form>
        </div>
    </div>
</body>
-->
<!--
</html>
-->


<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html lang="en">
  <jsp:include page ="/WEB-INF/views/backend/layout/header.jsp"></jsp:include>
  <body>
    <div class="container-scroller">
      <div class="container-fluid page-body-wrapper full-page-wrapper">
        <div class="content-wrapper d-flex align-items-center auth px-0">
          <div class="row w-100 mx-0">
            <div class="col-lg-4 mx-auto">
              <div class="auth-form-light text-left py-5 px-4 px-sm-5">
                <div class="brand-logo">
                  <img src="/backend/assets/images/logo.svg" alt="logo">
                </div>
                <h4>Hello! let's get started</h4>
                <h6 class="fw-light">Sign in to continue.</h6>
		        <form  method="POST" action="${classpath }/login_processing_url" class="pt-3">
					<c:if test="${not empty param.login_error }">
						<div class="alert alert-danger" role="alert">
						Login attempt was not successful, try again!!!</div>
					</c:if>
                  <div class="form-group">
                    <input type="text" class="form-control form-control-lg" id="username" name="username" placeholder="Username">
                  </div>
                  <div class="form-group">
                    <input type="password" class="form-control form-control-lg" id="password" name="password" placeholder="Password">
                  </div>
                  <div class="mt-3 d-grid gap-2">
                    <button type="submit" class="btn btn-block btn-primary btn-lg fw-medium auth-form-btn">SIGN IN</button>
                  </div>
                  <div class="my-2 d-flex justify-content-between align-items-center">
                    
                    <a href="#" class="auth-link text-black">Forgot password?</a>
                  </div>
                  <div class="text-center mt-4 fw-light"> Don't have an account? <a href="${classpath }/signup" class="text-primary">Create</a>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
        <!-- content-wrapper ends -->
      </div>
      <!-- page-body-wrapper ends -->
    </div>
    <!-- container-scroller -->
    <!-- plugins:js -->
    <script src="../../assets/vendors/js/vendor.bundle.base.js"></script>
    <script src="../../assets/vendors/bootstrap-datepicker/bootstrap-datepicker.min.js"></script>
    <!-- endinject -->
    <!-- Plugin js for this page -->
    <!-- End plugin js for this page -->
    <!-- inject:js -->
    <script src="../../assets/js/off-canvas.js"></script>
    <script src="../../assets/js/template.js"></script>
    <script src="../../assets/js/settings.js"></script>
    <script src="../../assets/js/hoverable-collapse.js"></script>
    <script src="../../assets/js/todolist.js"></script>
    <!-- endinject -->
  </body>
</html>