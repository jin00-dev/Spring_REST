<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- jQuery 라이브러리 추가  -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		// ajax를 사용해서 RESTController 호출 => 버튼 클릭시 
		$("#btnSend").click(function(){
			alert("클릭!");
			
			// 자바스크립트 객체
			var formObj = {sno:10,name:"사용자",email:"email@email.com"};
			
			$.ajax({
				type : "post",
				url : "/sample/testAjax2",
				data : JSON.stringify(formObj), // 일반 객체를 JSON으로 바꿔주는 역할 (전달)
				contentType:"application/json",
				success: function(data){
					alert("REST컨트롤러 다녀옴");
					$('body').append("전달받은 정보 : " + data.sno+"/"+data.name);
				},
				error: function(data){
					console.log(data);
				}
			});
		});
	});
</script>



</head>
<body>
	<h1>ajax.jsp</h1>
	
	<h2>버튼 클릭시 비동기 방식의 Ajax를 사용해 정보 전달(RESTController로 보낼것)</h2>
	<input type="button" value="회원정보 전달" id="btnSend">
	
	
	
</body>
</html>