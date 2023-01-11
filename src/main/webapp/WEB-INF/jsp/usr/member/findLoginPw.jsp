<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="Member CheckPassword" />
<%@ include file="../common/head.jsp"%>

<script>
	function FindLoginPw__submit(form) {
		let reg_email = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;

		form.loginId.value = form.loginId.value.trim();
		if (form.loginId.value.length == 0) {
			alert('아이디를 입력해주세요');
			form.loginId.focus();

			return false;
		}

		form.name.value = form.name.value.trim();
		if (form.name.value.length == 0) {
			alert('이름을 입력해주세요');
			form.name.focus();

			return false;
		}

		form.email.value = form.email.value.trim();
		if (form.email.value.length == 0) {
			alert('이메일을 입력해주세요');
			form.email.focus();

			return false;
		}
		if (!reg_email.test(form.email.value)) {
			alert("이메일 형식이 틀렸습니다.\n" + "ex) abc@abc.com");

			return false;
		}
	}
</script>
<section class="mt-8 text-xl">
	<div class="container mx-auto px-3">
		<form action="doFindLoginPw" method="POST" onsubmit="return FindLoginPw__submit(this);">
			<div class="table-box-type-1">
				<table class="table table-zebra w-full">
					<colgroup>
						<col width="200" />
					</colgroup>

					<tbody>
						<tr>
							<th>아이디</th>
							<td><input class="input input-ghost w-full text-lg border-gray-400" type="text" name="loginId"
								placeholder="아이디를 입력해주세요" /></td>
						</tr>
						<tr>
							<th>이름</th>
							<td><input class="input input-ghost w-full text-lg border-gray-400" type="text" name="name"
								placeholder="이름을 입력해주세요" /></td>
						</tr>
						<tr>
							<th>이메일</th>
							<td><input class="input input-ghost w-full text-lg border-gray-400" type="email" name="email"
								placeholder="이메일을 입력해주세요" /></td>
						</tr>
						<tr>
							<td colspan="2"><button class="btn btn btn-success w-6/12">비밀번호 찾기</button></td>
						</tr>
					</tbody>
				</table>
			</div>
		</form>
		<div class="btns flex justify-between mt-5">
			<button class="btn btn-primary" onclick="history.back();">뒤로가기</button>
			<div>
				<a class="btn btn-info" href="findLoginId">아이디 찾기</a>
				<a class="btn btn-active" href="login">로그인</a>
			</div>
		</div>
	</div>
</section>
<%@ include file="../common/foot.jsp"%>