<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${pageTitle}</title>
<!-- 노말라이즈, 라이브러리 -->
<!-- 제이쿼리 불러오기 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<!-- 테일윈드, 데이지 UI 불러오기 -->
<script src="https://cdn.tailwindcss.com"></script>
<link href="https://cdn.jsdelivr.net/npm/daisyui@2.45.0/dist/full.css" rel="stylesheet" type="text/css" />
<!-- 폰트어썸 불러오기 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" />
<!-- 커스텀 공통 CSS -->
<link rel="stylesheet" href="/resource/common.css" />
<!-- 공통 JS -->
<script src="/resource/common.js" defer="defer"></script>
</head>
<body>
	<header>
		<div class="h-20 flex container mx-auto text-4xl">
			<a class="h-full px-3 flex items-center" href="#"><span>로고</span></a>
			<!-- 필요한지 모르겠음 -->
			<div class="flex-grow"></div>
			<ul class="flex">
				<li class="hover:text-purple-400"><a class="h-full px-3 flex items-center" href="/"><span>HOME</span></a></li>
				<li class="hover:text-purple-400"><a class="h-full px-3 flex items-center" href="/usr/article/list?boardId=1&page=1"><span>NOTICE</span></a></li>
				<li class="hover:text-purple-400"><a class="h-full px-3 flex items-center" href="/usr/article/list?boardId=2&page=1"><span>FREE</span></a></li>
				<c:if test="${rq.getLoginedMemberId() == 0}">
					<li class="hover:text-purple-400"><a class="h-full px-3 flex items-center" href="/usr/member/login"><span>LOGIN</span></a></li>
				</c:if>
				<c:if test="${rq.getLoginedMemberId() != 0}">
					<li class="hover:text-purple-400"><a class="h-full px-3 flex items-center" href="/usr/member/myPage"><span>${rq.loginedMember.nickname}</span></a></li>
					<li class="hover:text-purple-400"><a class="h-full px-3 flex items-center" href="/usr/member/doLogout"><span>LOGOUT</span></a></li>
				</c:if>
			</ul>
		</div>
	</header>
	

	<section class="my-3 text-2xl">
		<div class="container mx-auto px-3">
			<h1>${pageTitle}&nbsp;Page</h1>
		</div>
	</section>
	<main>