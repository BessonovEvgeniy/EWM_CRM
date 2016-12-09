<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<div class="row row-horizon">
    <c:if test="${not empty cards}">
        <c:forEach items="${cards}" var="card">
            <div class="pipe_wrapper col-lg-2 col-md-3 col-sm-4 col-xs-12">

                <div class="pipe ">

                    <div class="editToolbar">
                        <form method="post" action="/editCardName" id="cardNameForm">
                            <input type="text" value="${card.cardName}" name="cardName" id="cardName">
                            <input type="hidden" value="${card.id}" name="cardId">
                            <input type="hidden" value="${pipeType.id}" name="pipeTypeId">
                            <button type="submit" class="btn btn-default btn-xs" id="submitCardName"><span
                                    class="glyphicon glyphicon-pencil"></span></button>
                        </form>

                        <form method="post" action="/deleteCard" id="deleteCardForm">
                            <button type="submit" class="btn btn-default btn-xs"><span
                                    class="glyphicon glyphicon-remove"></span></button>
                            <input type="hidden" value="${card.id}" name="cardId">
                            <input type="hidden" name="pipeTypeId" value="${pipeType.id}">
                        </form>
                    </div>

                    <c:forEach items="${card.persons}" var="person">

                        <div class="person row">

                            <input type="hidden" id="from" name="from" value="${card.id}">

                            <div class="avatar col-lg-2 col-md-2 col-sm-2 col-xs-3">
                                <c:if test="${person.avatarURL==null}">
                                    <span class="glyphicon glyphicon-picture"/>
                                </c:if>
                                <c:if test="${person.avatarURL!=null}">
                                    <img src="${person.avatarURL}" class="img-responsive"/>
                                </c:if>
                            </div>
                            <div class="personData col-lg-8 col-md-8 col-sm-8 col-xs-6">
                                <input type="hidden" id="personId" name="personId" value="${person.id}">
                                <p>
                                        ${person.lastName}
                                        ${fn:substring(person.firstName,0,1)}.${fn:substring(person.middleName,0,1)}.
                                </p>
                            </div>


                            <form method="post" action="/leadAdd" class="editPersonFrom col-lg-1 col-md-1 col-sm-1 col-xs-1">
                                <input type="hidden" value="${card.id}" name="cardId">
                                <input type="hidden" value="${pipeType.id}" name="pipeTypeId">
                                <button type="submit" class="btn btn-default btn-xs">
                                    <span class="glyphicon glyphicon-pencil"></span>
                                </button>
                            </form>


                            <form method="post" action="/deleteLeadFromPipe" class="deletePersonForm col-lg-1 col-md-1 col-sm-1 col-xs-1">
                                <button type="submit" class="btn btn-default btn-xs">
                                    <span class="glyphicon glyphicon-remove"></span>
                                </button>
                                <input type="hidden" name="cardId" value="${card.id}">
                                <input type="hidden" name="pipeTypeId" value="${pipeType.id}">
                            </form>

                        </div> <%-- end person --%>

                    </c:forEach>

                    <input type="hidden" id="destination" name="destination" value="${card.id}">
                </div>

                <form method="post" action="/leadAdd">
                    <input type="hidden" name="cardId" value="${card.id}">
                    <input type="hidden" name="pipeTypeId" value="${pipeType.id}">
                    <button type="submit" class="add">
                        <span>Add Lead</span>
                    </button>
                </form>

            </div>
        </c:forEach>
    </c:if>

    <c:if test="${pipeType.id > 0 || pipeType.id != null}">
        <div class="pipe_wrapper col-lg-2 col-md-3 col-sm-4 col-xs-12">
            <form class="pipe" method="post" action="/addCard">
                <input type="submit" value="Add Card" class="add_pipe">
                <input type="hidden" name="pipeTypeId" value="${pipeType.id}">
            </form>
        </div>
    </c:if>
</div>