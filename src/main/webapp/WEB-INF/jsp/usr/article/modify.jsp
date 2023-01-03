<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="Article Modify" />
<%@ include file="../common/head.jsp"%>
<%@ include file="../common/toastUiEditorLib.jsp" %>

<section class="mt-8 text-xl">
	<div class="container mx-auto px-3">
		<form action="doModify" onsubmit="return submitForm(this);">
			<input type="hidden" name="id" value="${article.id}" />
			<input type="hidden" name="body" />
			<div class="table-box-type-1">
				<table class="table table-zebra w-full">
					<colgroup>
						<col width="200" />
					</colgroup>

					<tbody>
						<tr>
							<th>번호</th>
							<td><div class="badge badge-lg">${article.id}</div></td>
						</tr>
						<tr>
							<th>작성 날짜</th>
							<td>${article.regDate}</td>
						</tr>
						<tr>
							<th>수정 날짜</th>
							<td>${article.updateDate}</td>
						</tr>
						<tr>
							<th>작성자</th>
							<td>${article.writerName}</td>
						</tr>
						<tr>
							<th>제목</th>
							<td><input class="input input-ghost w-full text-lg border-gray-400" type="text" name="title" placeholder="제목을 입력해주세요."
								value="${article.title}" /></td>
						</tr>
						<tr>
							<th>내용</th>
							<td>
								<div class="toast-ui-editor text-left">
									${article.getForPrintBody()}
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2"><button class="btn btn-outline btn-accent w-full">수정</button></td>
						</tr>
					</tbody>
				</table>
			</div>
		</form>
		<div class="btns flex justify-between mt-5">
			<button class="btn btn-primary" onclick="history.back();">뒤로가기</button>
			<a class="btn btn-secondary" href="doDelete?id=${article.id}" onclick="if(confirm('정말 삭제하시겠습니까?') == false) return false;">삭제</a>
		</div>
	</div>
</section>
<%@ include file="../common/foot.jsp"%>