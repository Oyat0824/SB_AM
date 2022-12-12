<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${pageTitle}</title>
<!-- 테일윈드 불러오기 -->
<!-- 노말라이즈, 라이브러리 -->
<script src="https://cdn.tailwindcss.com"></script>
<!-- 제이쿼리 불러오기 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js"
	integrity="sha512-aVKKRRi/Q/YV+4mjoKBsE4x3H+BkegoM/em46NNlCqNTmUYADjBbeNefNxYV7giUp0VxICtqdrbqU7iVaeZNXA=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<!-- 폰트어썸 불러오기 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css"
	integrity="sha512-MV7K8+y+gLIBoVD59lQIYicR65iaqukzvf/nwasF0nqhPay5w/9lJmVM2hMDcnK1OnMGCdVK+iQrJ7lzPJQd1w=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<!-- 커스텀 공통 CSS -->
<link rel="stylesheet" href="/resource/common.css" />
</head>
<body>
	<header>
		<div class="h-20 flex container mx-auto text-4xl">
			<a class="h-full px-3 flex items-center" href="#"><span>로고</span></a>
			<div class="flex-grow"></div><!-- 필요한지 모르겠음 -->
			<ul class="flex">
				<li class="hover:text-purple-400"><a class="h-full px-3 flex items-center" href="/"><span>HOME</span></a></li>
				<li class="hover:text-purple-400"><a class="h-full px-3 flex items-center" href="/usr/article/list"><span>LIST</span></a></li>
				<li class="hover:text-purple-400"><a class="h-full px-3 flex items-center" href="/usr/member/login"><span>LOGIN</span></a></li>
			</ul>
		</div>
	</header>
	
	<section class="my-3 text-2xl">
		<div class="container mx-auto px-3">
			<h1>${pageTitle}&nbsp;Page</h1>
		</div>
	</section>
	<main>