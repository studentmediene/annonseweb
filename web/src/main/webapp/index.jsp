<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>

<!DOCTYPE html>
<html>
<head><title>Simple jsp page</title></head>
<body>
    <nav id="sidebar">
        <page:applyDecorator name="blank" page="/annonseweb/sidebar"/>
    </nav>
    <div id="content">
        <div class="post">
            <h2 class="title"><a href="#">Velkommen til Annonseweb</a></h2>
            <div class="entry">
                <p class="meta"></p>
                <p>
                    Velkommen til Annonseweb. Annonseweb er annonsesystemet til Under Dusken, studentavisa i Trondheim.
                    Annonseweb er laget av dataavdelingen i Under Dusken.
                </p>
                <p>WORK IN PROGRESS</p>
            </div>
        </div>
    </div>
</body>
</html>