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

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>

<html xmlns="http://www.w3.org/1999/xhtml" lang="no" xml:lang="no">

<head>
    <title><decorator:title/> | Under Dusken</title>
</head>

<body>
<div id="header">
       <a href="<%=request.getContextPath() %>/welcome.do"><h1>Annonseweb</h1></a>
       <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/style/main.css"/>
   </div>

   <ul id="menu">
         <li><a href="<%=request.getContextPath() %>/">Hjem</a></li>
         <li><a href="<%=request.getContextPath() %>/annonseweb_logout">Logg ut</a></li>
    </ul>

    <div id="content">
        <h2><decorator:title/></h2>
        <decorator:body/>
    </div>

</body>
</html>
