<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!-- Menu -->
<security:authorize access="isAuthenticated()">
    <ul class="links">
        <li><a href="/home">Home</a></li>
        <li><a href="/mangers">Managers</a></li>
        <li><a href="/teachers">Teachers</a></li>
        <li><a href="/students">Students</a></li>
        <li><a href="/groups">Groups</a></li>
        <li><a href="/schedules">Schedules</a></li>
        <li><a href="/leads">Leads</a></li>

        <security:authorize access="hasRole('ROLE_ADMIN')">
            <li><a href="/admin">Admin</a></li>
            <li><a href="/users">Users List</a></li>
            <li><a href="/managers">Managers List</a></li>
        </security:authorize>
    </ul>
</security:authorize>
