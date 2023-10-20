<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>회원가입 페이지</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/resources/css/member/signup.css">
</head>
<body>
    <div class="container vh-100 d-flex justify-content-center align-items-center">
        <div class="p-5 d-flex flex-column">
            <div class="text-center w-50 mx-auto">
                <img src="/resources/img/logo.png" class="img-fluid"></img>
                <p class="title fs-4">회원가입 페이지</p>
            </div>
            <form action="/signup" method="post">
                <div class = "w-50 mx-auto">
                    <div class="mb-3">
                        <label for="inputId" class="form-label">아이디</label>
                        <input type="text" class="form-control" id="inputId" name="loginId" required>
                    </div>
					<%-- 실패 메시지가 있는 경우에만 출력. 파라미터 error가 비어 있지 않은 경우에 참으로 평가 --%>
					<c:if test="${not empty error}">
					    <p style="color: red;">${error}</p>
					</c:if>
                    <div class="mb-3">
                        <label for="inputPassword" class="form-label">비밀번호</label>
                        <input type="password" class="form-control" id="inputPassword" onchange="check_pw()" name="password" required>
                        <label for="inputPassword" class="form-label">비밀번호 확인</label>
                        <input type="password" class="form-control" id="inputPassword2" onchange="check_pw()" required>&nbsp;<span id="check"></span>
                    </div>
                </div> 
                <div class="d-flex text-center w-50 mx-auto">
                    <input type="submit" class="signup-btn rounded" value="가입하기">
                </div>
                <div class="d-flex text-center w-50 mx-auto">
                    <button type="button" class="btn login-btn rounded" onclick="window.location.href='/'">로그인</button>
                </div>
            </form>
        </div>
    </div>
    <script type="text/javascript">
    function check_pw(){
    	 
        let pw = document.getElementById('inputPassword').value;
        let passwordPattern = /^(?=.*[a-z])(?=.*\d)(?=.*[@#$%^!&*])[A-Za-z\d@#$%^!&*]{6,16}$/;

        if (!passwordPattern.test(pw)) {
            document.getElementById('check').innerHTML = '소문자, 숫자, 특수 문자 중 하나 이상을 포함하여 작성해주세요.';
            document.getElementById('check').style.color = 'red';
        } else {
            document.getElementById('check').innerHTML = '비밀번호가 유효합니다.';
            document.getElementById('check').style.color = 'blue';
        }

        if(document.getElementById('inputPassword').value !='' && document.getElementById('inputPassword2').value!=''){
            if(document.getElementById('inputPassword').value==document.getElementById('inputPassword2').value){
                document.getElementById('check').innerHTML='비밀번호가 일치합니다.'
                document.getElementById('check').style.color='blue';
            }
            else{
                document.getElementById('check').innerHTML='비밀번호가 일치하지 않습니다.';
                document.getElementById('check').style.color='red';
            }
        }
    }
	</script>
</body>
</html>
