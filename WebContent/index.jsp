<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/app.css">
</head>
<body>
<div class="grid-container">
  <div class="grid-x grid-margin-x">
    <div class="cell small-8"><textarea rows="" cols="" id="message"></textarea>
	<input type="button" value="Update" id="updateButton" class="button"/></div>
    <div class="cell small-4"><ul id="content" class="accordion" data-accordion></ul></div>
  </div>
</div>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
$(function(){
$("#updateButton").click(function(){
	var msg = $("#message").val();
	var dataString = 'message='+msg;
	$.ajax({
		type:'POST',
		url:"InsertMessage",
		data:dataString,
		cache:false,
		success:function(data){
			$("#message").val("");
			$("#content").prepend("<li>"+data+"</li>");
			$("#message").focus();
		}
	});
	return false;
});

$('body').on('click', 'a.delBtn', function() {
	var msg_id = $(this).data('id');
	var dataString = 'msg_id='+msg_id;
	$.ajax({
		type:"POST",
		url:"DeleteMessage",
		data:dataString,
		success:function(data){
			alert(data);			
		},
		error:function(e){
			console.log(e);
		}
	});
});

$.ajax({
	type:"GET",
	url:"GetMessages",
	dataType:"JSON",
	success:function(data){
		var result = data;
		if(result.Messages.length){
			$.each(result.Messages,function(i,data){
				var msg_data="<li class='accordion-item' data-accordion-item id='msg"+data.msg_id+"'>"+data.message+"<a href='#' class='delBtn' data-id='"+data.msg_id+"'>Delete</a></li>";
				$(msg_data).appendTo("#content");
			});
		}else{
			$("#content").html("No Results");
		}
	},
	error:function(e){
		console.log(e);
	}
});

});
</script>
</body>
</html>