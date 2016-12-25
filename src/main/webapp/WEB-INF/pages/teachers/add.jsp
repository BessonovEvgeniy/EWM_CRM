<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">
    <div class="col-sm-12">
        <h2 class="page-header">Create teacher</h2>
    </div>
</div>
<div class="row">
    <div class="col-sm-5 col-sm-offset-3">
        <div class="panel panel-default">
            <div class="panel-heading">
                <strong>Add new Teacher</strong>
            </div>
            <div class="panel-body">
                <sf:form method="post" modelAttribute="teacher" action="/teacherSave">
                    <fieldset class="form-group">

                        <div class="row padding-bot">
                            <div class="col-sm-1"></div>
                            <div class="col-sm-4">
                                <sf:label path="firstName">First Name:</sf:label>
                            </div>
                            <div class="col-sm-6">
                                <sf:input path="firstName" cssClass="form-control"/>
                                <sf:errors path="firstName" cssClass="has-error"/>
                            </div>
                        </div>

                        <div class="row padding-bot">
                            <div class="col-sm-1"></div>
                            <div class="col-sm-4">
                                <sf:label path="lastName">Last Name:</sf:label>
                            </div>
                            <div class="col-sm-6">
                                <sf:input path="lastName" cssClass="form-control"/>
                                <sf:errors path="lastName" cssClass="has-error"/>
                            </div>
                        </div>
                        <div class="row padding-bot">
                            <div class="col-sm-1"></div>
                            <div class="col-sm-4">
                                <sf:label path="middleName">Middle Name:</sf:label>
                            </div>
                            <div class="col-sm-6">
                                <sf:input path="middleName" cssClass="form-control"/>
                                <sf:errors path="middleName" cssClass="has-error"/>
                            </div>
                        </div>
                        <div class="row padding-bot">
                            <div class="col-sm-1"></div>
                            <div class="col-sm-4">
                                <label path="languages">Languages:</label>
                                <div>
                                    <c:forEach var="lang" items="${languages}" >
                                        <div>${lang.language}</div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                        <div class="row padding-bot">
                            <div class="col-sm-1"></div>
                            <div class="col-sm-4">
                                <sf:label path="email">Email:</sf:label>
                            </div>
                            <div class="col-sm-6">
                                <sf:input path="email" type="email" cssClass="form-control"/>
                                <sf:errors path="email" cssClass="has-error"/>
                            </div>
                        </div>
                        <div class="row padding-bot">
                            <div class="col-sm-1"></div>
                            <div class="col-sm-4">
                                <sf:label path="login">Login:</sf:label>
                            </div>
                            <div class="col-sm-6">
                                <sf:input path="login" cssClass="form-control"/>
                                <sf:errors path="login" cssClass="has-error"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-1"></div>
                            <div class="col-sm-4">
                                <sf:label path="password">Password:</sf:label>
                            </div>
                            <div class="col-sm-6">
                                <sf:password path="password" cssClass="form-control"/>
                                <sf:errors path="password" cssClass="has-error"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-1"></div>
                            <div class="col-sm-4">
                                <sf:label path="confirmPassword">Confirm password:</sf:label>
                            </div>
                            <div class="col-sm-6">
                                <sf:password path="confirmPassword" cssClass="form-control"/>
                                <sf:errors path="confirmPassword" cssClass="has-error"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-4 col-sm-offset-4">
                                <input type="submit" value="Add teacher" class="btn btn-success"/>
                            </div>
                        </div>
                    </fieldset>
                </sf:form>
            </div>
        </div>
    </div>
</div>
