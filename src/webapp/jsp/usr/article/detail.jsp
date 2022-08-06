<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../common/head.jspf"%>

<section>
    <div class="container px-3 mx-auto">
        <h1 class="font-bold text-lg">
            <i class="fa-solid fa-newspaper"></i> ${article.id}. ${article.title}
        </h1>

        <div class="mt-3">
            <div>
                <i class="fa-solid fa-clock"></i> 작성 : ${article.createdDate}
            </div>
            <div class = "m-3">
                <i class="fa-solid fa-pen-to-square"></i> 수정 : ${article.modifiedDate}
            </div>
            <div class="mt-2">
                    <button class="hover:bg-[blue] bg-[#F8F8F8] p-[8px_15px_5px_15px] rounded-[25px] hover:text-white"><a href="/usr/article/modify/free/\${article.id}">수정</a></button>
                    <button class="hover:bg-[red] bg-[#F8F8F8] p-[8px_15px_5px_15px] rounded-[25px] hover:text-white"><a onclick="if ( !confirm('정말로 삭제하시겠습니까?') ) return false;" href="/usr/article/delete/free/\${article.id}">삭제</a></button>
            </div>
            <div>
                <div class="toast-ui-viewer">
                    <script type="text/x-template">
                        ${article.body}
                    </script>
                </div>
            </div>
        </div>
    </div>
</section>

<%@ include file="../common/foot.jspf"%>