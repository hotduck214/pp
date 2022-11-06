<%@ page contentType="text/html; charset=utf-8" %>
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
					alert('로그인 후 글작성이 가능합니다.')
					location.href = '../user/login.do';
				</c:if>
				<c:if test="${!empty loginUserInfo}">
					location.href = 'write.do';
				</c:if>
		}
	</script>
</head>
<body>
<div id="freeWrap" class="bbs">

	<div class="pageTitle">
		<h2>자유게시판</h2>
	</div>
	<!--//pageTitle-->
	<!--//search-->
	<div class="list">
			<table>
				<caption> 자유게시판 </caption>
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
									<td>${data.totalCount-status.index-(freeVO.page-1)*freeVO.pageRow }</td>						
		                                <td class="txt_l">
		                                    <a href="view.do?free_no=${vo.free_no }">${vo.free_title } [${vo.comment_count }]</a>
		                                </td>
	                                
		                                <td class="writer">
		                                   ${vo.user_name}
		                                </td>
		                                
		                                <td class="date">
		                                	<fmt:formatDate value="${vo.free_regdate }" pattern="yyyy-MM-dd HH:mm"/>
		                                </td>
	                            	
		                            	<td class="free_viewcount">
		                            		${vo.free_viewcount }
		                            	</td>
	                            </tr>
	               </c:forEach>
				</tbody>
			</table>
		
		 <div class="btnSet"  style="text-align:right;">
                      	<p><span><strong>총${data.totalCount }개</strong>  | ${freeVO.page }/${ data.totalPage} 페이지</span></p>     
                      	<a class="btn" style="text-align:right;" href="javascript:goWrite();">글작성 </a>
                    </div>
                    <div class="pagenate clear">
                        <ul class='paging'>
                        <c:if test="${data.prev == true }">
                        	<li><a href="index.do?page=${data.startPage-1 }&stype=${param.stype}&sword=${param.sword}"><</a>
                        </c:if>
                        <c:forEach var="p" begin="${data.startPage }" end="${data.endPage }">
                            <li><a href='index.do?page=${p }&stype=${param.stype}&sword=${param.sword}' <c:if test="${freeVO.page == p }">class='current'</c:if>>${p }</a></li>
                        </c:forEach>
                        <c:if test="${data.next == true }">
                        	<li><a href="index.do?page=${data.endPage+1 }&stype=${param.stype}&sword=${param.sword}">></a>
                        </c:if>
                        </ul> 
                	</div>
                   <!-- 페이지처리 -->
                    <div class="bbsSearch">
                        <form method="get" name="searchForm" id="searchForm" action="">
                            <span class="srchSelect">
                                <select id="stype" name="stype" class="dSelect" title="검색분류 선택">
                                    <option value="all">전체</option>
                                    <option value="free_title">제목</option>
                                    <option value="free_content">내용</option>
                                </select>
                            </span>
                            <span class="searchWord">
                                <input type="text" id="sval" name="sword" value="${param.sword }"  title="검색어 입력">
                                <input type="button" id="" value="검색" title="검색">
                            </span>
                        </form>
                        
			</div>
		</div>
	</div>
	<!--//list-->
</div>
<!--//freeWrap -->
</body>
</html>

					 