<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="Article Modify" />
<%@ include file="../common/head.jsp"%>

<section class="mt-8 text-xl">
	<div class="container mx-auto px-3">
		<form action="doModify">
			<input type="hidden" name="id" value="${article.id}" />
			<div class="table-box-type-1">
				<table>
					<colgroup>
						<col width="200" />
					</colgroup>

					<tbody>
						<tr>
							<th>번호</th>
							<td>${article.id}</td>
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
							<td><input class="w-full outline-none" type="text" name="title" placeholder="제목을 입력해주세요."
								value="${article.title}" /></td>
						</tr>
						<tr>
							<th>내용</th>
							<td><textarea class="w-full outline-none" name="body" placeholder="내용을 입력해주세요.">${article.body}</textarea></td>
						</tr>
						<tr>
							<td colspan="2"><button type="submit">수정</button></td>
						</tr>
					</tbody>
				</table>
			</div>
		</form>
		<div class="btns flex justify-between">
			<button class="btn-text-link" type="button" onclick="history.back();">뒤로가기</button>
			<div>
				<a class="btn-text-link" href="doDelete?id=${article.id}" onclick="if(confirm('정말 삭제하시겠습니까?') == false) return false;">삭제</a>
			</div>
		</div>
	</div>
</section>
<%@ include file="../common/foot.jsp"%>