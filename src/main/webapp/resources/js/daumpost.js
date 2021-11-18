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