<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="Article List" />
<%@ include file="../common/head.jsp"%>
<section class="mt-8 text-xl">
	<div class="container mx-auto px-3">
		<div class="table-box-type-1">
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
						<tr>
							<td>${article.id}</td>
							<td>${article.regDate.substring(0, 16)}</td>
							<td><a class="hover:text-yellow-500" href="detail?id=${article.id}">${article.title}</a></td>
							<td>${article.writerName}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</section>
<%@ include file="../common/foot.jsp"%>