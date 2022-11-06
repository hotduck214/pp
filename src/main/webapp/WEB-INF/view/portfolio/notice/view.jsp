<%@ page contentType="text/html; charset=utf-8" %>
<!doctype html>
<html lang="ko">
<head>
<title><%=util.Property.title %></title>
<%@ include file="/WEB-INF/view//include/headHtml.jsp" %>
	<script>
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
		<h2>공지사항</h2>
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
					<td>${data.notice_title }</td>
					<th>작성자</th>
					<td>관리자</td>
				</tr>
				<tr>
					<th>작성일</th>
					<td>
						${data.notice_regdate }
					</td>
					<th>조회수</th>
					<td>
						${data.notice_viewcount }
					</td>
				</tr>
				<tr>
					<th>내용</th>
		            <td>
					 	${data.notice_content }
				        <a href="/pp/common/download.jsp?oName=${URLEncoder.encode(data.filename_org,'UTF-8')}&sName=${data.filename_real}" 
				         target="_blank"></a>
				         <!-- 이미지 출력 -->
				         
				         <img src="../../upload/${data.filename_real }">
					     <img src="upload/${data.filename_real }"> 
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
			<div class="left">
				<a class="btn" href="/pp/portfolio/notice/index.do">목록으로</a>
			</div>
			<div class="right">
				<a href="/pp/portfolio/notice/edit.do?notice_no=${data.notice_no }" class="btn" onclick="goEdit();">수정</a>
				<a href="/pp/portfolio/notice/delete.do?notice_no=${data.notice_no }" class="btn" onclick="goDelete();">삭제</a>
			</div>
		</div>
		<div style="height:300px;"></div>
	</div>
	<!--//list-->
</div>
<!--//boardWrap-->
</body>
</html>
