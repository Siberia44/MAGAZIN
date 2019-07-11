<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "t" %>


     <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
                <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
        <t:choose>
            <%--===========================================================================
            This way we define the ADMIN MENU.
            ===========================================================================--%>
            <t:when test="${empty sessionScope.user}">
                <!-- LOGIN -->
               <li class="nav-item">
                   <a class="nav-link" href="registration.jsp">SignUp</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" >SignIn</a>
                </li>
                </ul>
            </t:when>
            <t:otherwise>
                <!-- LOGOUT -->
            <form action="logout" method="POST"class="form-inline my-2 my-lg-0" >
                <input type="submit" class="btn btn-outline-light" value="Logout"/>
            </form>
            </t:otherwise>
        </t:choose>
    </div>