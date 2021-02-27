<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav class="nav" role="navigation">
	<div class="container nav-elements">
		<div class="branding">
            <a href="#home"><img src="images/hpluslogo.svg" alt="Logo - H Plus Sports"></a>
		</div>
		<ul class="navbar">
			<li>
			  <form action="controller" method="get">
          <input type="hidden" name="command" value="home"/>
          <input type="submit" value="home"/>
        </form>
			</li>
			<li>
			  <form action="controller" method="get">
          <input type="hidden" name="command" value="search"/>
          <input type="submit" value="catalog"/>
        </form>
			</li>
			<li>
			  <form action="controller" method="get">
          <input type="hidden" name="command" value="cart"/>
          <input type="submit" value="cart"/>
        </form>
			</li>
			<c:if test="${sessionScope.username == null}">
				<li>
				  <form action="controller" method="get">
            <input type="hidden" name="command" value="login"/>
            <input type="submit" value="login"/>
          </form>
				</li>
				<li>
				  <form action="controller" method="get">
            <input type="hidden" name="command" value="register"/>
            <input type="submit" value="new user?"/>
          </form>
				</li>
            </c:if>
            <c:if test="${sessionScope.username != null}">
				<li>
				  <form action="controller" method="get">
            <input type="hidden" name="command" value="orders"/>
            <input type="submit" value="my orders"/>
          </form>
				</li>
				<li>
				  <form action="controller" method="get">
            <input type="hidden" name="command" value="profile"/>
            <input type="submit" value="my profile"/>
          </form>
				</li>
				<li>
				  <form action="controller" method="get">
            <input type="hidden" name="command" value="logout"/>
            <input type="submit" value="logout"/>
          </form>
				</li>
            </c:if>
			<li>
        <form action="controller" method="get">
                  <input type="hidden" name="command" value="search"/>
                  <input type="submit" value="search"/>
        </form>
			</li>
			<li>
        <form action="controller" method="get">
          <input type="hidden" name="command" value="redirect"/>
          <input type="submit" value="GitHub"/>
        </form>
			</li>
		</ul>
	</div>
</nav>
