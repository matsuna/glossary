
<%@ page import="glossary.Word" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'word.label', default: 'Word')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>

                            <g:sortableColumn property="id" title="${message(code: 'word.id.label', default: 'Id')}" />

                            <th><g:message code="word.categ.label" default="Categ" /></th>

                            <g:sortableColumn property="en" title="${message(code: 'word.en.label', default: 'En')}" />

                            <g:sortableColumn property="ja" title="${message(code: 'word.ja.label', default: 'Ja')}" />

                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${wordInstanceList}" status="i" var="wordInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                            <td><g:link action="show" id="${wordInstance.id}">${fieldValue(bean: wordInstance, field: "id")}</g:link></td>

                            <td>${fieldValue(bean: wordInstance, field: "categ.name")}</td>

                            <td>${fieldValue(bean: wordInstance, field: "en")}</td>

                            <td>${fieldValue(bean: wordInstance, field: "ja")}</td>

                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${wordInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
