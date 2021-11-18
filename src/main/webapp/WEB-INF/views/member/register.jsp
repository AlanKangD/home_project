<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="<%=request.getContextPath()%>/resources/js/daumpost.js"></script>
<!-- 하단의 주석으로 된것을 다른 파일로 옴긴 후 사용하는 방법입니다. -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<!-- 상단의 주소는 jquery를 사용하기 위한 소스입니다. -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
/* webapp -> resources -> js -> daumpost.js 로 이동되었습니다.
 function daumPost() {
    new daum.Postcode({
        oncomplete: function(data) {
            // R : 도로명, J : 지번
            console.log("data.userSelectedType :"+ data.userSelectedType)
            console.log("data.roadAddress : " + data.roadAddress)
            console.log("data.jibunAddress :" + data.jibunAddress)
            console.log("data.zonecode :" + data.zonecode)
        	var addr = ""
        	if(data.userSelectedType === 'R') {
        		addr = data.roadAddress //도로명 주소
        	}else {
        		addr = data.jibunAddress //지번 주소
        	}
    		document.getElementById("addr1").value = data.zonecode
    		$("#addr2").val(addr)
    		$("#addr3").focus()
        }
    }).open();
}
*/


function register() {
	addr1 = $("#addr1").val()
	addr2 = $("#addr2").val()
	addr3 = $("#addr3").val()
	addr = addr1 + "/" + addr2 + "/" + addr3
	$("#addr1").val(addr)
	fo.submit()
}
</script>
</head>
<body>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../default/header.jsp"/>
<div align="center">
   <form id="fo" action="register" method="post">
      <table border="1">
         <tr><td>
            <input type="text" name="id" placeholder="아이디"><br>
            <input type="text" name="pw" placeholder="비밀번호"><br>
            <input type="text" name="addr" id="addr1" readonly placeholder="우편번호" > <!-- readonly를 해야 사용자가 직접 작성하지 못합니다, -->
            <input type="button" onclick="daumPost()" value="우편번호 찾기"><br>
            <input type="text" id="addr2" readonly placeholder="주     소"> <!-- readonly를 해야 사용자가 직접 작성하지 못합니다, -->
            <input type="text" id="addr3" placeholder="상세주소">
            
            <br>
            <hr>
            <input type="button" onclick="register()" value="회원가입">
         </td></tr>
      </table>
   </form>
</div>
<c:import url="../default/footer.jsp"/>
</body>
</html>