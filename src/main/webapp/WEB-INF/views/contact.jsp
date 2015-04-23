<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title><spring:message code="label.title"/></title>
    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }

        .tg .tg-4eph {
            background-color: #f9f9f9
        }
    </style>
</head>
<body>

<a href="<c:url value="/logout" />">
    <spring:message code="label.logout"/>
</a>

<h2><spring:message code="label.title"/></h2>

<c:url var="addAction" value="/contact/add"></c:url>
<form:form action="${addAction}" commandName="contact">
    <table>
        <c:if test="${!empty contact.firstname}">
            <tr>
                <td>
                    <form:label path="id">
                        <spring:message text="ID"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="id" readonly="true" size="8" disabled="true"/>
                    <form:hidden path="id"/>
                </td>
            </tr>
        </c:if>
        <tr>
            <td><form:label path="firstname">
                <spring:message code="label.firstname"/>
            </form:label></td>
            <td><form:input path="firstname"/></td>
        </tr>
        <tr>
            <td><form:label path="lastname">
                <spring:message code="label.lastname"/>
            </form:label></td>
            <td><form:input path="lastname"/></td>
        </tr>
        <tr>
            <td><form:label path="email">
                <spring:message code="label.email"/>
            </form:label></td>
            <td><form:input path="email"/></td>
        </tr>
        <tr>
            <td><form:label path="telephone">
                <spring:message code="label.telephone"/>
            </form:label></td>
            <td><form:input path="telephone"/></td>
        </tr>
        <tr>
            <td><form:label path="age">
                <spring:message code="label.age"/>
            </form:label></td>
            <td><form:input path="age"/></td>
        <tr>
            <td colspan="2">
                <c:if test="${empty contact.firstname}">
                    <input type="submit"
                           value="<spring:message text="Добавить контакт"/>"/>
                </c:if>
                <c:if test="${!empty contact.firstname}">
                    <input type="submit"
                           value="<spring:message text="Сохранить контакт"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>

<c:if test="${!empty contactList}">
    <h3><spring:message code="label.contacts"/></h3>
    <table class="tg">
        <tr>
            <th><spring:message code="label.firstname"/></th>
            <th><spring:message code="label.lastname"/></th>
            <th><spring:message code="label.email"/></th>
            <th><spring:message code="label.telephone"/></th>
            <th><spring:message code="label.age"/></th>
            <th>&nbsp;</th>
            <th>&nbsp;</th>
        </tr>
        <c:forEach items="${contactList}" var="contact">
            <tr>
                <td>${contact.lastname}</td>
                <td>${contact.firstname}</td>
                <td>${contact.email}</td>
                <td>${contact.telephone}</td>
                <td>${contact.age}</td>
                <td><a href="<c:url value='/update/${contact.id}' />">Изменить</a></td>
                <td><a href="<c:url value='/delete/${contact.id}' />">Удалить</a></td>
            </tr>
        </c:forEach>
    </table>

    <c:if test="${currentPage != 1}">
        <td><a href="index?pageNum=${currentPage - 1}&pageSize=${currentPageSize}">Previous</a></td>
    </c:if>

    <table border="1" cellpadding="5" cellspacing="5">
        <tr>
            <c:forEach begin="1" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <td>${i}</td>
                    </c:when>
                    <c:otherwise>
                        <td><a href="index?page=${i}&pageSize=${currentPageSize}">${i}</a></td>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </tr>
    </table>
    
    <c:if test="${currentPage lt noOfPages}">
        <td><a href="index?pageNum=${currentPage + 1}&pageSize=${currentPageSize}">Next</a></td>
    </c:if>


    <%--<div class="pag">--%>
        <%--<ul>--%>
            <%--<li class="active"><a href='/pagination/${page-1}'>Previous</a></li>--%>
            <%--<li class="active"><a href='/pagination/${page+1}'>Next</a></li>--%>
        <%--</ul>--%>
    <%--</div>--%>
 </c:if>
</body>
</html>