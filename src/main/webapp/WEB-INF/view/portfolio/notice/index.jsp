<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.net.*" %>
<!doctype html>
<html lang="ko">
	<head>
		<title><%=util.Property.title %></title>
		<%@ include file="/WEB-INF/view//include/headHtml.jsp" %>
		<link rel="stylesheet" href="/pp/css/reset.css"/>
    	<link rel="stylesheet" href="/pp/css/contents.css"/>
      	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script>
  	
  		function goWrite(){
  			<c:if test="${empty loginUserInfo}">
				alert('관리자 로그인 후 글작성이 가능합니다!')
				location.href = '../user/login.do';
				
			</c:if>
			<c:if test="${!empty loginUserInfo}">
				location.href = 'write.do';
			</c:if>
  		}
  		
  	</script>
  	
	</head>
	
	<body>
		<div id="boardWrap" class="bbs">
			<div class="pageTitle">
				<h2>공지사항</h2>
				<div class="right">
					<a class="btn" style="text-align:right;" href="javascript:goWrite();">글작성 </a>
				</div>
			</div>
		
			<div class="list">
					<table>
						<caption> 공지사항 </caption>
						<colgroup>
							<col width="50px" />
							<col width="*" />
							<col width="10%" />
							<col width="10%" />
							<col width="10%" />
						</colgroup>
						
						<thead>
							<tr>
								<th>번호</th>
								<th>제목</th>
								<th>작성자</th>
								<th>작성일</th>
								<th>조회수</th>
							</tr>
						</thead>
						
						<tbody>
							<c:if test="${empty data.list }">
								<tr>
									<td class = "none" colspan="8">등록된 공지가 없습니다.</td>
								</tr>
							</c:if>
							<c:forEach var="vo" items = "${data.list }" varStatus="status">
	                        <tr>
								<td>${data.totalCount-status.index-(noticeVO.page-1)*noticeVO.pageRow }</td>						
	                            
	                            <td class="txt_l">
	                                 <a href="view.do?notice_no=${vo.notice_no }">${vo.notice_title }</a>
	                            </td>
	                            
	                            <td class="admin_no">
	                             	관리자                               
	                            </td>
	                             
	                            <td class="date">
	                             	<fmt:formatDate value="${vo.notice_regdate }" pattern="yyyy-MM-dd HH:mm"/>
	                            </td>
	                         	 
	                         	<td class="notice_viewcount">
	                         		${vo.notice_viewcount }
	                         	</td>
	                       	</tr>
				            </c:forEach>
						</tbody>
					</table>
			
			 <div class="pagenate clear">
                        <ul class='paging'>
                        <c:if test="${data.prev == true }">
                        	<li><a href="index.do?page=${data.startPage-1 }&stype=${param.stype}&sword=${param.sword}"><</a>
                        </c:if>
                        <c:forEach var="p" begin="${data.startPage }" end="${data.endPage }">
                            <li><a href='index.do?page=${p }&stype=${param.stype}&sword=${param.sword}' <c:if test="${askreplyVO.page == p }">class='current'</c:if>>${p }</a></li>
                        </c:forEach>
					<c:if test="${data.next == true }">
						<li><a href="index.do?page=${data.endPage+1 }&stype=${param.stype}&sword=${param.sword}">></a>
					</c:if>
				</ul>

			</div>
		    </div>
			<!--//list-->
			
		</div>
		<!--//boardWrap-->
	</body>
</html>


					 