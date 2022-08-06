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
                    <button class="hover:bg-[blue] bg-[#F8F8F8] p-[8px_15px_5px_15px] rounded-[25px] hover:text-white"><a href="/usr/article/modify/${article.id}">수정</a></button>
                    <button class="hover:bg-[red] bg-[#F8F8F8] p-[8px_15px_5px_15px] rounded-[25px] hover:text-white"><a onclick="if ( !confirm('정말로 삭제하시겠습니까?') ) return false;" href="/usr/article/delete/${article.id}?_method=DELETE">삭제</a></button>
            </div>
            <div>
                <div class="toast-ui-viewer">
                    <script type="text/x-template">
                        ${article.body}
                    </script>
                </div>
            </div>
            <c:if test="${prevArticle != null}">
                        <div class="mt-2">
                            <a href="/usr/article/detail/${prevArticle.id}" class="hover:text-[red]">
                                이전글 : <span class="badge badge-primary">${prevArticle.id}</span> ${prevArticle.title} - ${prevArticle.createdDate}
                            </a>
                        </div>
                        </c:if>
                        <c:if test="${nextArticle != null}">
                 <div class="mt-2">
                 <a href="/usr/article/detail/${nextArticle.id}" class="hover:text-[red]">
                       다음글 : <span class="badge badge-primary">${nextArticle.id}</span> ${nextArticle.title} - ${nextArticle.createdDate}
                     </a>
                 </div>
            </c:if>
        </div>
        <h1 class="font-bold text-lg mt-3">
                    <i class="fa-solid fa-rectangle-list"></i> 목록
                </h1>

                <ul class="mt-5">
                    <c:forEach items="${articles}" var="listItemArticle">
                    <li>
                        <a href="/usr/article/detail/${listItemArticle.id}" class="flex p-2 group">
                            <span class="badge badge-primary mr-2">${listItemArticle.id}</span>
                            <div class="group-hover:underline group-hover:text-[red]">
                                <c:if test="${listItemArticle.id == article.id}">
                                    <span class="text-[red]">현재글</span> -
                                </c:if>
                                ${listItemArticle.title} - ${listItemArticle.createdDate}
                            </div>
                        </a>
                    </li>
                    </c:forEach>
                </ul>
    </div>
</section>

<%@ include file="../common/foot.jspf"%>