<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../default/header.jsp"/>
<div align="center">
   <form action="${contextPath }/member/update" method="post">
      <table border="1">
         <tr><td>
         	<input type="hidden" name="id" value="${id }">
            <input type="text" placeholder="${id }" readonly><br>
            <input type="text" name="pw" placeholder="변경 후 비밀번호"><br>
            <input type="text" name="addr" placeholder="변경 후 주  소"><br>
            <hr>
            <input type="submit" value="수정">
         </td></tr>
      </table>
   </form>
</div>
<c:import url="../default/footer.jsp"/>
</body>
</html>