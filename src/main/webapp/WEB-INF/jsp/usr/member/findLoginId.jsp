<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="Member CheckPassword" />
<%@ include file="../common/head.jsp"%>

<script>
	function FindLoginId__submit(form) {
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
	}
</script>

<section class="mt-8 text-xl">
	<div class="container mx-auto px-3">
		<form action="doFindLoginId" onsubmit="return FindLoginId__submit(this);">
			<div class="table-box-type-1">
				<table class="table table-zebra w-full">
					<colgroup>
						<col width="200" />
					</colgroup>

					<tbody>
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
							<td colspan="2"><button class="btn btn btn-info w-6/12">아이디 찾기</button></td>
						</tr>
					</tbody>
				</table>
			</div>
		</form>
		<div class="btns flex justify-between mt-5">
			<button class="btn btn-primary" onclick="history.back();">뒤로가기</button>
			<div>
				<a class="btn btn-success" href="findLoginPw">비밀번호 찾기</a> <a class="btn btn-active" href="login">로그인</a>
			</div>
		</div>
	</div>
</section>
<%@ include file="../common/foot.jsp"%>