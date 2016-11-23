
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="12u">
<h3>Managers list</h3>
    <div class="table-wrapper">
        <table class="alt">
            <thead>
                <tr>
                    <th>First name</th>
                    <th>Last name</th>
                    <th>Middle name</th>
                </tr>
            </thead>
            <tbod>
                <c:forEach var="manager" items="${managers}">
                    <tr>
                        <td>${manager.person.firstName}</td>
                        <td>${manager.person.lastName}</td>
                        <td>${manager.person.middleName}</td>
                    </tr>
                </c:forEach>
            </tbod>
        </table>
    </div>
    <p><a href="/managerAdd" class="button">Add Manager</a></p><br>
    <p>Pages</p>

    <table>
        <c:forEach var="page" begin="1" end="${pages}">
            <tr>
                <a href="/managers?page=${page}" class="button atl small">${page}</a>
            </tr>
        </c:forEach>
    </table>
</div >