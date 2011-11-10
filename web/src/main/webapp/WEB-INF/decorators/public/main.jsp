<%--
  Copyright 2009 - 2010 Under Dusken

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
  --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>

<!DOCTYPE html>
<html lang="no">

<head>
    <title><decorator:title/>Annonseweb - Under Dusken - </title>
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/dropmenu.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/style/main.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/style/dropmenu.css">
    <link rel="shortcut icon" type="image/png" href="/images/logo/ud-transparentbg.png" />
</head>
<body>
<div id="wrapper">
    <div id="header">
        <div id="logo">
            <h1><a href="<%=request.getContextPath() %>/">Annonseweb</a></h1>
            <p><a href="https://underdusken.no/redmine/">et Under Dusken-Data produkt!</a></p>
        </div>
        <div id="search">
            <form method="get" action="">
                <fieldset>
                    <input type="text" name="s" placeholder="Søk" id="search-text" size="15" />
                    <input type="submit" id="search-submit" value="GO" />
                </fieldset>
            </form>
        </div>
    </div>

    <nav id="menu">
        <page:applyDecorator name="blank" page="/menu"/>
    </nav>

    <div id="page">
        <div id="page-bgtop">
            <div id="page-bgbtm">
                <decorator:body/>
            </div>
        </div>
    </div>

    <nav id="sidebar">
        <page:applyDecorator name="blank" page="/sidebar"/>
    </nav>

</div>
<footer id="footer">
    <p>Powered by <a href="https://underdusken.no/redmine/">Dusken - Annonseweb</a> - ($text.version)</p>
</footer>
</body>
</html>
