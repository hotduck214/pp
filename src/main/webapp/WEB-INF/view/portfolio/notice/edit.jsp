<%@ page contentType="text/html; charset=utf-8" %>
<!doctype html>
<html lang="ko">
<head>
<title><%=util.Property.title %></title>
<%@ include file="/WEB-INF//view/include/headHtml.jsp" %>
<script>
var oEditors; // 에디터 객체 담을 곳
$(document).ready(function(e){
	oEditors = setEditor("<%=request.getContextPath()%>", "notice_content"); // 에디터 셋팅
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

//function goSave() {
//	frm.submit();
//}
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
		<form name="frm" id="frm" action="update.do" method="POST" enctype="multipart/form-data">
		<input type="hidden" name="cmd" value="edit">
		<input type="hidden" name="notice_no" value="${data.notice_no }">
		<table>	
			<colgroup>
				<col style="width:150px"/>
				<col style="width:*"/>
			</colgroup>
			<tbody>
				<tr>
					<th>제목</th>
					<td>
						<input type="text" id="notice_title" name="notice_title" value="${data.notice_title }" />
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
						<textarea id="notice_content" name="notice_content" rows="25"> ${data.notice_content }</textarea>
						 <a href="/pp/common/download.jsp?oName=${URLEncoder.encode(data.filename_org,'UTF-8')}&sName=${data.filename_real}" 
				         target="_blank"></a>
				         <!-- 이미지 출력 -->
				         <img src="../../upload/${data.filename_real }">
					</td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td>
				        <a href="/pp/common/download.jsp?oName=${URLEncoder.encode(data.filename_org,'UTF-8')}&sName=${data.filename_real}" 
				         target="_blank"></a>
						<input type="file" name="file">
					</td>
				</tr>
			</tbody>
		</table>
		</form>
		<div class="btnSet">
			<div class="right">
				<a href="javascript:;" class="btn" onclick="goSave();">수정하기</a>
			</div>
		</div>
		<div style="height:300px;"></div>
	</div>
	<!--//list-->
</div>
<!--//boardWrap-->
</body>
</html>
