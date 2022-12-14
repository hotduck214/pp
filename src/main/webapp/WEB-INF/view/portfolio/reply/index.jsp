<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.net.*" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0, user-scalable=yes">
    <meta name="format-detection" content="telephone=no, address=no, email=no">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <title>문의사항</title>
    <%@ include file="/WEB-INF/view//include/headHtml.jsp" %>
    <link rel="stylesheet" href="/pp/css/reset.css"/>
    <link rel="stylesheet" href="/pp/css/contents.css"/>
   <script>
  	
  		function goWrite(){
  			<c:if test="${empty loginUserInfo}">
				alert('로그인 후 글작성이 가능합니다!!!.')
				location.href = '/portfolio/user/login.do';
				
			</c:if>
			<c:if test="${empty loginUserInfo}">
				location.href = 'write.do';
			</c:if>
  		}
  		
  	</script>
</head> 
<body>
    
     <div class="sub">
            <div class="size">
                  <a href="index.do" ><h3 class="sub_title">문의사항</h3></a>
    
                <div class="bbs">
                    <table class="list">
                           <h3 class="logconfirm" style="text-align:right;"></h3>
                     
                        <caption>문의사항 목록</caption>
                        <colgroup>
                            <col width="80px" />
                            <col width="*" />
                            <col width="100px" />
                            <col width="100px" />
                            <col width="200px" />
                        </colgroup>
                        <thead>
                            <tr>
                                <th>번호</th>
                                <th>제목</th>
                                <th>조회수</th>
                                <th>작성자</th>
                                <th>작성일</th>
                            </tr>
                        </thead>
                        <tbody>
						<c:if test="${empty data.list }">
                            <tr>
                                <td class="first" colspan="5">등록된 글이 없습니다.</td>
                            </tr>
                        </c:if>
                        <c:forEach var="vo" items="${data.list }" varStatus="status">
                            <tr>
                                <td>${data.totalCount-status.index-(replyVO.page-1)*replyVO.pageRow }<!-- 총개수 - 인덱스-(현재페이지번호-1)*페이지당개수 --></td>
                                <td class="txt_l" style="text-align: left;">
                                	<c:forEach begin="1" end="${vo.nested }">&nbsp;&nbsp;&nbsp;</c:forEach>
                                    <a href="view.do?no=${vo.no }">${vo.reply_title} </a>
                                	<c:if test="${vo.nested > 0}"><img src="/pp/img/answer_icon.gif"></c:if>
                                	<c:if test="${vo.nested > 0}"><a href="view.do?no=${vo.no }">답변완료 </a></c:if>
                                	  
                                </td>
                                <td>
                                	${vo.reply_viewcount }
                                </td>
                                <td class="writer">
	                                    ${vo.user_name } 
                                </td>
                                <td class="date"><fmt:formatDate value="${vo.reply_regdate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                      <div class="btnSet"  style="text-align:right;">
                      	<p><span><strong>총${data.totalCount }개</strong>  | ${ReplyVO.page }/${ data.totalPage} 페이지</span></p>     
                      	<a class="btn" style="text-align:right;" href="javascript:goWrite();">글작성 </a>
                    </div>
           		
           		  <div class="pagenate clear">
                        <ul class='paging'>
                        <c:if test="${data.prev == true }">
                        	<li><a href="index.do?page=${data.startPage-1 }&stype=${param.stype}&sword=${param.sword}"><</a>
                        </c:if>
                        <c:forEach var="p" begin="${data.startPage }" end="${data.endPage }">
                            <li><a href='index.do?page=${p }&stype=${param.stype}&sword=${param.sword}' <c:if test="${ReplyVO.page == p }">class='current'</c:if>>${p }</a></li>
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
                                    <option value="title">제목</option>
                                    <option value="content">내용</option>
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
        </div>
        
</body>
</html>