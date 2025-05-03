         <!-- partial:partials/_sidebar.html -->
        <nav class="sidebar sidebar-offcanvas" id="sidebar">
          <ul class="nav">
          
          
            <li class="nav-item">
              <a class="nav-link" href="${classpath}/admin/home">
                <i class="mdi mdi-grid-large menu-icon"></i>
                <span class="menu-title">Dashboard</span>
              </a>
            </li>
            <li class="nav-item nav-category">Management</li>
            
            
            <li class="nav-item">
              <a class="nav-link" data-bs-toggle="collapse" href="#auth" aria-expanded="false" aria-controls="auth">
              	<i class="mdi mdi-format-list-bulleted menu-icon"></i>
                <span class="menu-title">Category</span>
                <i class="menu-arrow"></i>
              </a>
              <div class="collapse" id="auth">
                <ul class="nav flex-column sub-menu">
                  <li class="nav-item"> <a class="nav-link" href="${classpath }/admin/category/list"> View</a></li>
                  <li class="nav-item"> <a class="nav-link" href="${classpath }/admin/category/add"> Add </a></li>
                </ul>
              </div>
            </li>
            
            
            <li class="nav-item">
              <a class="nav-link" data-bs-toggle="collapse" href="#icons" aria-expanded="false" aria-controls="icons">
              	<i class="mdi mdi-hanger menu-icon"></i>
                <span class="menu-title">Products</span>
                <i class="menu-arrow"></i>
              </a>
              <div class="collapse" id="icons">
                <ul class="nav flex-column sub-menu">
                  <li class="nav-item"> <a class="nav-link" href="${classpath }/admin/product/list"> View </a></li>
                  <li class="nav-item"> <a class="nav-link" href="${classpath }/admin/product/add"> Add </a></li>
                </ul>
              </div>
            </li>
            
            
            <li class="nav-item">
              <a class="nav-link" data-bs-toggle="collapse" href="#charts" aria-expanded="false" aria-controls="auth">
              	<i class="mdi mdi-account menu-icon"></i>
                <span class="menu-title">Users</span>
                <i class="menu-arrow"></i>
              </a>
              <div class="collapse" id="charts">
                <ul class="nav flex-column sub-menu">
                  <li class="nav-item"> <a class="nav-link" href="${classpath }/admin/user/list"> View </a></li>
                  <li class="nav-item"> <a class="nav-link" href="${classpath }/admin/user/add"> Add </a></li>
                </ul>
              </div>
            </li>
            
            <li class="nav-item">
              <a class="nav-link" data-bs-toggle="collapse" href="#tables" aria-expanded="false" aria-controls="auth">
              	<i class="mdi mdi-clipboard-account menu-icon"></i>
                <span class="menu-title">Role</span>
                <i class="menu-arrow"></i>
              </a>
              <div class="collapse" id="tables">
                <ul class="nav flex-column sub-menu">
                  <li class="nav-item"> <a class="nav-link" href="${classpath }/admin/role/list"> View </a></li>
                  <li class="nav-item"> <a class="nav-link" href="${classpath }/admin/role/edit"> Add </a></li>
                </ul>
              </div>
            </li>
            
            <li class="nav-item">
              <a class="nav-link" data-bs-toggle="collapse" href="#tables" aria-expanded="false" aria-controls="auth">
              	<i class="mdi mdi-note-text-outline menu-icon"></i>
                <span class="menu-title">Order Details</span>
                <i class="menu-arrow"></i>
              </a>
              <div class="collapse" id="tables">
                <ul class="nav flex-column sub-menu">
                  <li class="nav-item"> <a class="nav-link" href="${classpath }/admin/order/list"> View </a></li>
                </ul>
              </div>
            </li>
            
            
            <!-- 
            <li class="nav-item">
              <a class="nav-link" href="${classpath }/admin/order/details">
                <i class="menu-icon mdi mdi-file-document"></i>
                <span class="menu-title">Order Details</span>
              </a>
            </li>
            -->
          </ul>
        </nav>
        <!-- partial -->