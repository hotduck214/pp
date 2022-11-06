<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.net.*" %>
<!Doctype html>
<html lang="ko">
<head>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0, user-scalable=yes">
    <meta name="format-detection" content="telephone=no, address=no, email=no">
    <meta name="keywords" content="">
    <meta name="description" content="">
<title><%=util.Property.title %></title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<%@ include file="/WEB-INF/view//include/headHtml.jsp" %>

<script>

function getComment(page) {
	$.ajax({
		url : "/pp/portfolio/freecomment/list.do",
		data : {
			free_no : ${data.free_no},
			tablename : 'free',
			page : page
		},
		success : function(res) {
			$("#commentArea").html(res);
		}
	});
}
$(function(){
	getComment(1);
});
function goSave() {	

	if (confirm('댓글을 저장하시겠습니까?')) {
		$.ajax({
			url :  "/pp/portfolio/freecomment/insert.do",
			data : {
				free_no : ${data.free_no},
				tablename : 'free',
				comment_content : $("#comment_content").val()
			},
			success : function(res) {
				if (res.trim() == "1") {
					alert('정상적으로 댓글이 등록되었습니다.');
					$("#comment_content").val('');
					getComment(1);
				}
			}
		});
	}

	}
function commentDel(no){
	if(confirm("댓글을 삭제하시겠습니까?")){
		$.ajax ({
			url : '/pp/portfolio/freecomment/delete.do?no='+no,
			success : function(res){
				if (res.trim() == "1"){
				alert('댓글 삭제 완료');
				getComment(1);
				}
			}
		})
	}
}




function goEdit() {
	if (confirm("수정하시겠습니까?")) {
		location.href = "edit.do?idx=1";
	}
}

function goDelete() {
	if (confirm("삭제하시겠습니까?")) {
		location.href = "delete.do?cmd=delete&idx=1";
	}
}

</script>
</head>
<body>
<div id="boardWrap" class="bbs">
	<div class="pageTitle">
		<h2><a href="/pp/portfolio/free/index.do" class="btn">자유게시판</a></h2>
	</div>
	<!--//pageTitle-->
	<!--//search-->
	<div class="write">
		<form name="frm" id="frm" action="view.do" method="GET" enctype="multipart/form-data">
		<input type="hidden" name="cmd" value="write">
		<table>
			<colgroup>
				<col style="width:150px"/>
				<col style="width:*"/>
				<col style="width:150px"/>
				<col style="width:*"/>
			</colgroup>
			<tbody>
				<tr>
					<th>제목</th>
					<td>${data.free_title }</td>
					<th>작성자</th>
					<td>관리자</td>
				</tr>
				<tr>
					<th>작성일</th>
					<td>
						${data.free_regdate }
					</td>
					<th>조회수</th>
					<td>
						${data.free_viewcount }
					</td>
				</tr>
				<tr>
					<th>내용</th>
		            <td>
					 	${data.free_content }
					  <a href="/pp/common/download.jsp?oName=${URLEncoder.encode(data.free_filenameorg,'UTF-8')}&sName=${data.free_filenamereal}" 
				         target="_blank"></a>
				         <!-- 이미지 출력 -->
				         
				         <img src="../../upload/${data.free_filenamereal }">
					</td>
				       
				</tr>
				<tr>
					 <th>첨부파일 </th>
		             <td>
				         <a href="/pp/common/download.jsp?oName=${URLEncoder.encode(data.free_filenameorg,'UTF-8')}&sName=${data.free_filenamereal}" 
				         target="_blank">${data.free_filenameorg }</a>
			      	 </td>
				</tr>
			  </tbody>
		</table>
		</form>
		<div class="btnSet">
			<div class="right">
				<a href="/pp/portfolio/free/edit.do?free_no=${data.free_no }" class="btn" onclick="goEdit();">수정</a>
				<a href="/pp/portfolio/free/delete.do?free_no=${data.free_no }" class="btn" onclick="goDelete();">삭제</a> 
				<a href="javascript:;" class="btn" onclick="location.href='index.do';">목록</a>
				<!-- 삭제하면 알아서 번호 이동 -->
			</div>
		</div>
		<div style="height:300px;"></div>
	</div>
	 <div>
                    <form method="post" name="frm" id="frm" action="" enctype="multipart/form-data" >
                        <table class="free_write">
                            <colgroup>
                                <col width="*" />
                                <col width="100px" />
                            </colgroup>
                            <tbody>
                            <tr>
                                <td>
                                    <textarea name="comment_content" id="comment_content" style="height:50px;"><c:if test=" ${!empty loginUserInfo}">로그인이 필요합니다.</c:if></textarea>
                                </td>
                                <td>
                                    <div class="btnSet"  style="text-align:right;">
                                        <a class="btn" href="javascript:goSave();">저장 </a>
                                    </div>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                        </form>

                        <div id="commentArea"></div>
                    </div>
	<!--//list-->
</div>
<!--//boardWrap-->
</body>
</html>
