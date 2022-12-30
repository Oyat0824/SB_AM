<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="pageTitle" value="${board.name } 게시판" />
<%@ include file="../common/head.jsp"%>


<section class="mt-8 text-xl">
	<div class="container mx-auto px-3">
		<div class="table-box-type-1">
			<div class="headTab flex justify-between items-center mb-3">
				<div>
					게시물 총 개수 : <span class="font-bold">${articlesCount }</span>
				</div>

				<form action="" class="form-control">
					<input type="hidden" name="page" value="1" />
					<input type="hidden" name="boardId" value="${boardId }" />
					<div class="input-group">
						<select data-value="${searchKeywordTypeCode }" name="searchKeywordTypeCode"
							class="select select-bordered focus:outline-none">
							<option value="title">제목</option>
							<option value="body">내용</option>
							<option value="title_body">제목 + 내용</option>
						</select>
						<input class="input input-bordered focus:outline-none" type="text" name="searchKeyword" placeholder="Search…" value="${searchKeyword }" />
						<button class="btn btn-square focus:outline-none">GO</button>
					</div>
				</form>
			</div>
			<table class="table table-zebra w-full">
				<thead>
					<tr>
						<th class="text-sm">번호</th>
						<th class="text-sm title">제목</th>
						<th class="text-sm">작성자</th>
						<th class="text-sm">날짜</th>
						<th class="text-sm">조회수</th>
						<th class="text-sm">추천</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach var="article" items="${articles}">
						<tr class="hover">
							<td><div class="badge badge-lg bg-purple-600 border-transparent font-bold text-white">${article.id}</div></td>
							<td><a class="hover:text-yellow-500" href="detail?id=${article.id}">${article.title}</a><span class="text-base text-gray-500 align-text-top">[${article.replyCount }]</span></td>
							<td>${article.writerName}</td>
							<td>${article.regDate.substring(2, 16)}</td>
							<td>${article.viewCnt}</td>
							<td>${article.pointUp}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<c:if test="${rq.getLoginedMemberId() != 0}">
			<div class="btns flex justify-end mt-5">
				<a class="btn" href="/usr/article/write?boardId=${board.id }">게시글 작성</a>
			</div>
		</c:if>
		<div class="pageNav flex justify-center mt-5">
			<div class="btn-group">
				<c:set var="maxPageNum" value="5" />
				<fmt:parseNumber var="pageBlock" integerOnly="true" value="${page / maxPageNum}" />
				<c:if test="${page % maxPageNum > 0}"><c:set var="pageBlock" value="${pageBlock + 1}" /></c:if>
				<c:set var="endPage" value="${pageBlock * maxPageNum}" />
				<c:set var="startPage" value="${endPage - (maxPageNum - 1)}" />
				<c:set var="endPage" value="${pagesCount < endPage ? pagesCount : endPage }" />

				<c:set var="pageBaseUri" value="&boardId=${boardId}&searchKeywordTypeCode=${searchKeywordTypeCode }&searchKeyword=${searchKeyword }" />

				<c:if test="${page == 1}">
					<a class="btn btn-sm w-12 btn-disabled">&lt;&lt;</a>
					<a class="btn btn-sm w-12 btn-disabled">&lt;</a>
				</c:if>
				<c:if test="${page > 1}">
					<a class="btn btn-sm w-12" href="?page=1${pageBaseUri}">&lt;&lt;</a>
					<a class="btn btn-sm w-12" href="?page=${page-1}${pageBaseUri}">&lt;</a>
				</c:if>
					
				<c:forEach begin="${startPage }" end="${endPage }" var="i">
					<a class="btn btn-sm w-12 ${page == i ? 'btn-active' : ''}" href="?page=${i}${pageBaseUri}">${i}</a>
				</c:forEach>

				<c:if test="${page == pagesCount}">
					<a class="btn btn-sm w-12 btn-disabled">&gt;</a>
					<a class="btn btn-sm w-12 btn-disabled">&gt;&gt;</a>
				</c:if>
				<c:if test="${page < pagesCount}">
					<a class="btn btn-sm w-12" href="?page=${page+1}${pageBaseUri}">&gt;</a>
					<a class="btn btn-sm w-12" href="?page=${pagesCount}${pageBaseUri}">&gt;&gt;</a>
				</c:if>
			</div>
		</div>
	</div>
</section>
<%@ include file="../common/foot.jsp"%>