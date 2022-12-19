<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="Article Write" />
<%@ include file="../common/head.jsp"%>

<section class="mt-8 text-xl">
	<div class="container mx-auto px-3">
		<form action="doWrite">
			<div class="table-box-type-1">
				<table class="table table-zebra w-full">
					<colgroup>
						<col width="200" />
					</colgroup>

					<tbody>
						<tr>
							<th>카테고리</th>
							<td>
								<select name="boardId" class="select select-bordered w-full">
									<option>선택</option>
									<option value="1">공지사항</option>
									<option value="2">자유</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>제목</th>
							<td><input class="input input-ghost w-full text-lg border-gray-400" type="text" name="title"
								placeholder="제목을 입력해주세요." /></td>
						</tr>
						<tr>
							<th>내용</th>
							<td><textarea class="textarea textarea-ghost w-full text-base border-gray-400" name="body"
									placeholder="내용을 입력해주세요."></textarea></td>
						</tr>
						<tr>
							<td colspan="2"><button class="btn btn-outline btn-accent w-full">작성</button></td>
						</tr>
					</tbody>
				</table>
			</div>
		</form>
		<div class="btns flex justify-between mt-5">
			<button class="btn btn-primary" onclick="history.back();">뒤로가기</button>
		</div>
	</div>
</section>
<%@ include file="../common/foot.jsp"%>