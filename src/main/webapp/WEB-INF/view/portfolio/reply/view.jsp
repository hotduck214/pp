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
    	function del(no) {
    		if (confirm('삭제하시겠습니까?')) {
    			location.href='delete.do?no='+no;
    		}
    	}
    </script>
</head>
<body>
<div id="boardWrap" class="bbs">
	<div class="pageTitle">
		<h2><a href="/pp/portfolio/reply/index.do" class="btn">문의사항</a></h2>
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
					<td>${data.reply_title }</td>
					<th>작성자</th>
					<td>관리자</td>
				</tr>
				<tr>
					<th>작성일</th>
					<td>
						${data.reply_regdate }
					</td>
					<th>조회수</th>
					<td>
						${data.reply_viewcount }
					</td>
				</tr>
				<tr>
					<th>내용</th>
		            <td>
					 	${data.reply_content }
					  <a href="/pp/common/download.jsp?oName=${URLEncoder.encode(data.filename_org,'UTF-8')}&sName=${data.filename_real}" 
				         target="_blank"></a>
				         <!-- 이미지 출력 -->
				         
				         <img src="../../upload/${data.filename_real }">
					</td>
				       
				</tr>
				<tr>
					 <th>첨부파일 </th>
		             <td>
				         <a href="/pp/common/download.jsp?oName=${URLEncoder.encode(data.filename_org,'UTF-8')}&sName=${data.filename_real}" 
				         target="_blank">${data.filename_org }</a>
			      	 </td>
				</tr>
			  </tbody>
		</table>
		</form>
		<div class="btnSet">
			<div class="right">
				<a href="/pp/portfolio/reply/edit.do?no=${data.no }" class="btn" onclick="goEdit();">수정</a>
				<a href="/pp/portfolio/reply/delete.do?no=${data.no }" class="btn" onclick="goDelete();">삭제</a> 
				<a href="javascript:;" class="btn" onclick="location.href='index.do';">목록</a>
				<!-- 삭제하면 알아서 번호 이동 -->
                <a href="reply.do?no=${data.no}" class="btn">답변</a>
			</div>
		</div>
		<div style="height:300px;"></div>
	</div>
</div>
                
                    
        
</body>
</html>