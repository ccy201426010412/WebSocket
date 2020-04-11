<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript"  src="/WebSocket/js/jquery.js"></script>
<script type="text/javascript">
	var  ws;
	var url="ws://localhost:8080/WebSocket/chatSocket?username=${sessionScope.username}";
	
	window.onload=connect;
	function connect(){
		 if ('WebSocket' in window) {
             ws = new WebSocket(url);
         } else if ('MozWebSocket' in window) {
             ws = new MozWebSocket(url);
         } else {
             alert('WebSocket is not supported by this browser.');
             return;
         }
		 ws.onmessage=function(event){
			eval("var result="+event.data);

			
			if(result.allUser!=undefined){
				$("#userList").html("");
				$(result.allUser).each(function(){
					$("#userList").append(this+"<br/>");
				});
			}
			
			if(result.user != ''  ){
				$("#content").append(result.user+" "+result.date+
						"说：<br/>"+result.message+"<br/>");
			}else {
				$("#content").append(result.message+"<br/>");
			}
			
		 };
	}
	
	function  send(){
		var value= $("#msg").val();
		ws.send(value);
	}


</script>
</head>
<body>

	<h3>欢迎 ${sessionScope.username} 使用本系统！！</h3>

	<div  id="content"  style="
		border: 1px solid black; width: 400px; height: 300px;
		float: left;
	"  ></div>
	<div  id="userList"  style="
		border: 1px solid black; width: 100px; height: 300px;
		float:left;
	"  ></div>

	<div  style="clear: both;" >
		<input id="msg"  /><button  onclick="send();"  >send</button>
	</div>


</body>
</html>