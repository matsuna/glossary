

<%@ page import="glossary.Word" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'word.label', default: 'Word')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${wordInstance}">
            <div class="errors">
                <g:renderErrors bean="${wordInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="categ"><g:message code="word.categ.label" default="Categ" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: wordInstance, field: 'categ', 'errors')}">
                                    <g:select name="categ.id" from="${glossary.Categ.list()}" optionValue="name" optionKey="id" value="${wordInstance?.categ?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="en"><g:message code="word.en.label" default="En" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: wordInstance, field: 'en', 'errors')}">
                                    <g:textField name="en" value="${wordInstance?.en}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="ja"><g:message code="word.ja.label" default="Ja" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: wordInstance, field: 'ja', 'errors')}">
                                    <g:textField name="ja" value="${wordInstance?.ja}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
