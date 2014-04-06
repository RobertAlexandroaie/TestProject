
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/auction/auctionStyle.css"/>
         
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/auction/keywords/dynamic_list_helper.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <h3>Keywords List</h3>
        <div style="border:1px solid #eaeaea;padding:20px;width:400px">
            ${message}
        </div>
        <form:form action="${cp}/auction/editcontainer" modelAttribute="keywordsListContainer" method="post" id="keywordsListForm">
            <table>
                <thead>
                    <tr>
                        <th>Keyword</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody id="keywordsListContainer">
                    <c:forEach items="${keywordsListContainer.keywordsList}" var="Keyword" varStatus="i" begin="0" >
                        <tr class="keyword">   
                             <td><form:input class="keyword-input" path="keywordsList[${i.index}].name" id="name${i.index}" /></td>
                            
                            <td><img src="${pageContext.request.contextPath}/resources/images/auction/x.png" class="removeKeyword" alt=""/></td>
                        </tr>
                    </c:forEach>
 
                    <c:if test="${keywordsListContainer.keywordsList.size() == 0}">
                        <tr class="keyword defaultKeyword">    
                            <td><input class="keyword-input" type="text" name="keywordsList[].name" value="" /></td>
                            
                            <td><img src="${pageContext.request.contextPath}/resources/images/auction/x.png" class="removeKeyword" alt=""/></td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
             <p id="add-reset">
             	<a href="#" id="addKeyword">Add More</a>
             	<a href="?f=" id="reset-keywords">Reset List</a>
             </p>
            <input type="submit" value="Save" id="submit" />&nbsp;&nbsp;

            
        </form:form>
    
        <script type="text/javascript">
            function rowAdded(rowElement) {
                //clear the imput fields for the row
                $(rowElement).find(".keyword-input").val('');
                //may want to reset <select> options etc
                
                //in fact you may want to submit the form
                saveNeeded();
            }
            function rowRemoved(rowElement) {
                saveNeeded();
            }
            
            function saveNeeded() {
                $('#submit').css('color','red');
                $('#submit').css('font-weight','bold');
                if( $('#submit').val().indexOf('!') != 0 ) {
                    $('#submit').val( '!' + $('#submit').val() );
                }
            }
            
            function beforeSubmit() {
                alert('submitting....');
                return true;
            }
            
            $(document).ready( function() {
                var config = {
                    rowClass : 'keyword',
                    addRowId : 'addKeyword',
                    removeRowClass : 'removeKeyword',
                    formId : 'keywordsListForm',
                    rowContainerId : 'keywordsListContainer',
                    indexedPropertyName : 'keywordsList',
                    indexedPropertyMemberNames : 'name',
                    rowAddedListener : rowAdded,
                    rowRemovedListener : rowRemoved,
                    beforeSubmit : beforeSubmit
                };
                new DynamicListHelper(config);
            });
        </script>
        
    </body>
</html>
