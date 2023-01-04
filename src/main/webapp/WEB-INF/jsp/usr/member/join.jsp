<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="Member Join" />
<%@ include file="../common/head.jsp"%>

<script>
	const MemberJoin__submit = function(form) {
		let reg_num = /^[0-9]{10,11}$/;
		let reg_email =/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
		
		form.loginId.value = form.loginId.value.trim();
		if(form.loginId.value.length == 0) {
			alert("아이디를 입력해주세요.");
			form.loginId.focus();s
			
			return false;
		}
		
		form.loginPw.value = form.loginPw.value.trim();
		if(form.loginPw.value.length == 0) {
			alert("비밀번호를 입력해주세요.");
			form.loginPw.focus();
			
			return false;
		}
		
		form.loginPwChk.value = form.loginPwChk.value.trim();
		if(form.loginPwChk.value.length == 0) {
			alert("비밀번호 확인을 입력해주세요.");
			form.loginPwChk.focus();
			
			return false;
		}
	
		if (form.loginPw.value != form.loginPwChk.value) {
			alert('비밀번호가 일치하지 않습니다');
			form.loginPw.focus();
		
			return false;
		}
		
		form.name.value = form.name.value.trim();
		if(form.name.value.length == 0) {
			alert("이름을 입력해주세요.");
			form.name.focus();
			
			return false;
		}
		
		form.nickname.value = form.nickname.value.trim();
		if(form.nickname.value.length == 0) {
			alert("닉네임을 입력해주세요.");
			form.nickname.focus();
			
			return false;
		}
		
		form.cellphoneNum.value = form.cellphoneNum.value.trim();
		if(form.cellphoneNum.value.length == 0) {
			alert("전화번호를 입력해주세요.");
			form.cellphoneNum.focus();
			
			return false;
		}
		if(!reg_num.test(form.cellphoneNum.value)) {
			alert("전화번호 형식이 틀렸습니다.\n" +
				  "ex) 01012345678"
				 );
			return false;
		}
		
		form.email.value = form.email.value.trim();
		if(form.email.value.length == 0) {
			alert("이메일을 입력해주세요.");
			form.email.focus();
			
			return false;
		}
		if(!reg_email.test(form.email.value)) {
			alert("이메일 형식이 틀렸습니다.\n" +
				  "ex) abc@abc.com"
				 );
			return false;
		}
	}
</script>

<section class="mt-8 text-xl">
	<div class="container mx-auto px-3">
		<form action="doJoin" onsubmit="return MemberJoin__submit(this);">
			<div class="table-box-type-1">
				<table class="table table-zebra w-full">
					<colgroup>
						<col width="200" />
					</colgroup>

					<tbody>
						<tr>
							<th>아이디</th>
							<td><input class="input input-ghost w-full text-lg border-gray-400" type="text" name="loginId" placeholder="아이디를 입력해주세요." /></td>
						</tr>
						<tr>
							<th>비밀번호</th>
							<td><input class="input input-ghost w-full text-lg border-gray-400" type="password" name="loginPw" placeholder="비밀번호를 입력해주세요." /></td>
						</tr>
						<tr>
							<th>비밀번호 확인</th>
							<td><input class="bg-white input input-ghost w-full text-lg border-gray-400" type="password" name="loginPwChk"
								placeholder="비밀번호 확인을 위해 입력해주세요." value="" /></td>
						</tr>
						<tr>
							<th>이름</th>
							<td><input class="input input-ghost w-full text-lg border-gray-400" type="text" name="name" placeholder="이름을 입력해주세요." /></td>
						</tr>
						<tr>
							<th>닉네임</th>
							<td><input class="bg-white input input-ghost w-full text-lg border-gray-400" type="text" name="nickname"
								placeholder="닉네임을 입력해주세요." /></td>
						</tr>
						<tr>
							<th>전화번호</th>
							<td><input class="bg-white input input-ghost w-full text-lg border-gray-400" type="tel" name=cellphoneNum
								placeholder="전화번호를 입력해주세요." /></td>
						</tr>
						<tr>
							<th>이메일</th>
							<td><input class="bg-white input input-ghost w-full text-lg border-gray-400" type="email" name="email"
								placeholder="이메일을 입력해주세요." /></td>
						</tr>
						<tr>
							<td colspan="2"><button class="btn btn-outline btn-accent w-full">회원가입</button></td>
						</tr>
					</tbody>
				</table>
			</div>
		</form>
		<div class="btns mt-5">
			<button class="btn btn-primary" onclick="history.back();">뒤로가기</button>
		</div>
	</div>
</section>


<%@ include file="../common/foot.jsp"%>