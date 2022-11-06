<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0, user-scalable=yes">
    <meta name="format-detection" content="telephone=no, address=no, email=no">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <title>회원 로그인</title>
      <link rel="stylesheet" href="/pp/css/reset.css"/>
    <link rel="stylesheet" href="/pp/css/contents.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script>
    	function loginCheck1() {
    		if ($("#user_id").val() == '') {
    			alert('아이디를 입력해 주세요');
    			$("#user_id").focus();
    			return false;
    		}
    		if ($("#user_pwd").val() == '') {
    			alert('비밀번호를 입력해 주세요');
    			$("#user_pwd").focus();
    			return false;
    		}
    	}
    </script>
</head>
<body>
    
        <form action="login.do" method="post" id="loginFrm1" name="loginFrm1" onsubmit="return loginCheck1();">
                <div class="size">
                    <h3 class="sub_title">회원 로그인</h3>
                    
                    <div class="user">
                        <div class="box">
                            <fieldset class="login_form">
                                <ul>
                                    <li><input type="text" id="user_id" name="user_id" placeholder="아이디"></li>
                                    <li><input type="password" id="user_pwd" name="user_pwd" placeholder="비밀번호"></li>
                                    <li><label><input type="checkbox" name="reg1" id="reg1"/>아이디저장</label></li>
                                </ul>
                               <div class="login_btn"><input type="submit" value="로그인" alt="로그인" /></div>
                               <!-- type은 데이터 전송을 위해서 submit으로 해준다. 데이터를 서버로 전송 -->
                            </fieldset>
                            <div class="btnSet clear">
                                <div style="padding:10px;"> 
                                    <a href="join.do" class="btn">회원가입</a> 
                                </div>
                                <div style="padding:10px;"> 
                                    <a href="findEmail.do" class="btn">아이디 찾기</a>
                                    <a href="findPwd.do" class="btn">비밀번호 찾기</a>
                                </div>
                            </div>
                        </div>
                    </div>
        
                </div>
            </div>        
        </form>
        
</body>
</html>