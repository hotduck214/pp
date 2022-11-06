<%@ page contentType="text/html; charset=utf-8" %>
<!doctype html>
<html lang="ko">
<head>
<title><%=util.Property.title %></title>
<%@ include file="/WEB-INF/view//include/headHtml.jsp" %>
	<script>
		var oEditors; // 에디터 객체 담을 곳
		$(document).ready(function(e){
			oEditors = setEditor("<%=request.getContextPath()%>", "contents"); // 에디터 셋팅
		});
		
		function goSave() {
			if ($("#notice_title").val() == "") {
				alert('제목을 입력해 주세요.');
				$("#notice_title").focus();
				return false;
			}
			var sHTML = oEditors.getById["notice_content"].getIR();
			if (sHTML == "") {
				alert('내용을 입력해 주세요.');
				$("#notice_content").focus();
				return false;
			} else {
				oEditors.getById["notice_content"].exec("UPDATE_CONTENTS_FIELD", []);	// 에디터의 내용이 textarea에 적용됩니다.
			}
			$('#frm').submit();
		}
		
		
		function goSave() {
			frm.submit();
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
		<form name="frm" id="frm" action="insert.do" method="POST" enctype="multipart/form-data">
		<input type="hidden" name="cmd" value="write">
		<table>
			<colgroup>
				<col style="width:150px"/>
				<col style="width:*"/>
			</colgroup>
			<tbody>
				<tr>
					<th>제목</th>
					<td>
						<input type="text" id="notice_title" name="notice_title" value="" />
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
						<textarea id="notice_content" name="notice_content" rows="25"></textarea>
					</td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td>
						<input type="file" name="file">
					</td>
				</tr>
			</tbody>
		</table>
		</form>
		
		<div class="btnSet">
			<div class="right">
				<!-- <a href="javascript:;" class="btn" onclick="goSave();">저장</a> -->
				 <a class="btn" href="javascript:goSave();">저장 </a>
			</div>
		</div>
		<div style="height:300px;"></div>
	</div>
	<!--//list-->
</div>
<!--//boardWrap-->
</body>
</html>
