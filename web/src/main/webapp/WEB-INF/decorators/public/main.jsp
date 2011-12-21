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
    <link rel="shortcut icon" type="image/png" href="/images/logo/ud-transparentbg.png" />
</head>
<body>
<div id="wrapper">
    <header>
        <div id="logo">
            <h1><a href="<%=request.getContextPath() %>/">Annonseweb</a></h1>
            <p>
                <a href="http://www.underdusken.no">Annonser for Under Dusken</a>
            </p>
        </div>
        <div id="search">
            <form method="get">
                <fieldset>
                    <input type="text" name="s" placeholder="Søk" id="search-text" size="15" />
                    <input type="submit" id="search-submit" value="GO" />
                </fieldset>
            </form>
        </div>
        <div id="feedback">
            <p>
                <a href="https://github.com/dusken/annonseweb">Utviklet av Duskens Dataavdeling</a>
                -
                <a href="https://github.com/dusken/annonseweb/issues">Feedback?(click me)</a>
                <!-- add some javascript pop out for feedback -->
            </p>
        </div>
    </header>

    <nav id="menu">
        <page:applyDecorator name="blank" page="/annonse/menu"/>
    </nav>

    <div id="page">
        <decorator:body/>
    </div>

</div>
</body>
</html>
