<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="${board.name } 게시판" />
<%@ include file="../common/head.jsp"%>
<section class="mt-8 text-xl">
	<div class="container mx-auto px-3">
		<div class="table-box-type-1">
			<div class="mb-3">
				게시물 총 개수 : <span class="font-bold">${articlesCount }</span>
			</div>
			<table class="table table-zebra w-full">
				<thead>
					<tr>
						<th>번호</th>
						<th>날짜</th>
						<th>제목</th>
						<th>작성자</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach var="article" items="${articles}">
						<tr class="hover">
							<td><div class="badge badge-lg">${article.id}</div></td>
							<td>${article.regDate.substring(0, 16)}</td>
							<td><a class="hover:text-yellow-500" href="detail?id=${article.id}">${article.title}</a></td>
							<td>${article.writerName}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<c:if test="${rq.getLoginedMemberId() != 0}">
			<div class="btns flex justify-end mt-5">
				<a class="btn btn-accent" href="/usr/article/write?boardId=${board.id }">작성</a>
			</div>
		</c:if>
		
		<div class="pageNav flex justify-center mt-5">
			<div class="btn-group">
				<c:if test="${param.page > 1 }">
					<a class="btn btn-sm" href="?boardId=${boardId }&page=${param.page-1}">&lt;</a>
				</c:if>
				<c:forEach begin="${from}" end="${end}" var="i">
					<a class="btn btn-sm ${param.page == i ? 'btn-active' : ''}" href="?boardId=${boardId }&page=${i}">${i}</a>
				</c:forEach>
				<c:if test="${param.page < pagesCount}">
					<a class="btn btn-sm" href="?boardId=${boardId }&page=${param.page+1}">&gt;</a>
				</c:if>
			</div>
		</div>
	</div>
</section>
<%@ include file="../common/foot.jsp"%>