<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<script>
    window.onload = function () {
        window.history.pushState(null, '', 'depAdd');
    };
</script>
<header>
    <div align="right">
        <security:authorize access="isAuthenticated()">
            User:<security:authentication property="principal.username"/>
        </security:authorize>
        <security:authorize access="isAnonymous()">
            <a href="/login">Login</a>
            to be autorized user
        </security:authorize>
    </div>
    <table>
        <td><a href="/home">Home</a></td>
        <td><a href="/dep">Departments</a></td>
        <security:authorize access="isAnonymous()">
            <td><a href="/login">Login</a></td>
        </security:authorize>
        <security:authorize access="isAuthenticated()">
            <td><a href="/logout">Logout</a></td>
        </security:authorize>
        <security:authorize access="hasRole('ROLE_ADMIN')">
            <td><a href="/users">Users</a></td>
        </security:authorize>
    </table>
</header>
<sf:form method="post" modelAttribute="department" action="/depSave">
    <fieldset>
        <table>
            <tr>
                <th><sf:label path="name">Name:</sf:label></th>
                <td><sf:input path="name"/><br/>
                    <sf:errors path="name"/>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Add department"/>
                </td>
            </tr>
        </table>
    </fieldset>
</sf:form>
</body>
</html>
