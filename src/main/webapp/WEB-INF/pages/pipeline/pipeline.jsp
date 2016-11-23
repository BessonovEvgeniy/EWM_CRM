<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
        <a href="/takeLeadtpipe" class="button">Leads</a>


        <a href="/takeStudentpipe" class="button">Students</a>


        <c:if test="${pt.id > 0 || pt.id != null}">
                <form method="post" action="/addCard">
                    <input type="submit" value="Add Card" class="button" style="margin: 10px">
                    <input type="hidden" name="pt_id" value="${pt.id}">
                </form>
        </c:if>
</div>

<c:if test="${not empty cards}">
<div class="scroll">
    <div class="scroll_child">
                <c:forEach items="${cards}" var="card">
                    <div>
                        ${card.id}
                        <form method="post" action="/deleteCard">
                            <button type="submit" class="button btn">Delete</button>
                            <input type="hidden" value="${card.id}" name="card_id">
                            <input type="hidden" name="pt_id" value="${pt.id}">
                        </form>
                    </div>
                </c:forEach>
    </div>
</div>
</c:if>
<script type="text/javascript">
    $(document).ready(function(){
       //Количество элементов div находящихся внутри дочернего Div "<div class="scroll_child">"
         var quqntity=$(".scroll_cards div").size();

         //Общая длинна всех элементов
         var widthScroll=0;
         for (i=0;i<quqntity;i++){
         widthScroll+=$(".scroll_cards div:eq("+i+")").width();
         }

         //Установка ширины дочернего Div "<div class="scroll_child">"
         $(".scroll_child").width(widthScroll);
        });
</script>