<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="msg" value="${msg}" />
<c:set var="con" value="${pageContext.request.contextPath}"/>
<c:set var="post" value="${postlist}" />
<c:set var="cat" value="${catList}" />

	<c:if test="${not empty post}">
	<div class="container-fluid">
	    <h1 class="h3 mb-2">게시판</h1>
	      <div class="row ">
			<c:forEach var="p" items="${post}">
	        <div class="col-6 mt-3">
	          <div class="card card-block">
	            <div class="card-header bg-primary text-white">
	              ${p.postRegDate}
	            </div>
	            <div class="card-body">
	              <h5 class="card-title">${p.postTitle}</h5>
	              <p class="card-text">${p.postContent}</p>
	              <a href="${con}/post/view?postId=${p.postId}" class="btn btn-primary">자세히</a>
	            </div>
	          </div>
	       	</div>
			</c:forEach>
	      </div>
		</div>
        <div align="right">
            <button class="btn btn-primary mb-3 ml-2" id="serbtn" onclick="location.href='${con}/post/search'">검색</button>
            <button type="button" class="btn btn-primary mb-3 ml-2" data-toggle="modal" data-target="#myModal">카테고리</button>
            <button type="button" class="btn btn-primary mb-3 mr-4 ml-2" onclick="location.href='${con}/post/add'">글쓰기</button>
        </div>
		</c:if>
		<c:if test="${empty postlist}">
			<div class="container-fluid text-center mt-5 mb-5">
				<h1>작성된 게시글이 없습니다.</h1>
				<h2>게시글을 작성해주세요</h2>
			</div>
		        <div align="right">
		            <button type="button" class="btn btn-primary mb-3 ml-2" data-toggle="modal" data-target="#myModal">카테고리</button>
		            <button type="button" class="btn btn-primary mb-3 mr-4 ml-2" onclick="location.href='${con}/post/add'">글쓰기</button>
		        </div>
		</c:if>
      <!-- Category Modal -->
      <div class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel">
          <div class="modal-dialog" role="document">
          <div class="modal-content">
              <div class="modal-header">
                  <h4 class="modal-title" id="myModalLabel">Category</h4>
              </div>
                <form action="${con}/category/add" method="post" name="catFrm">
                  <table class="table table-bordered text-center cat-table" id="dataTable" width="100%" cellspacing="0">
                      <c:if test="${not empty cat}">
	                      <tr>
	                          <th class="text-center">Name</th>
	                      </tr>
	                      <c:forEach var="list" items="${cat}">
	                      	<tr>
	                          	<td>${list.categoryName}
								<span class="float-right">
	                        		<a href="${con}/category/delete?categoryId=${list.categoryId}" class="btn btn-primary delCat-btn" >삭제</a>
	                      		</span>
	                      		</td>
	                      	</tr>
	                      </c:forEach>
                      	</c:if>
                  </table>
                      	<c:if test="${empty cat}">
                      		<p class="text-center">생성된 카테고리가 없습니다.</p>
                      	</c:if>

                      <div class="m-3 float-right">
                          <input type="button" class="btn btn-primary btn-add" value="카테고리 생성">
                          <input type="button" class="btn btn-primary btn-close" data-dismiss="modal" value="닫기">
                      </div>
                </form>
              </div>
          </div>
      </div>

		<script>
        $(document).ready(function() {
            $('.btn-add').click(function(e) {
                if($(this).val() == '카테고리 생성'){
                    e.preventDefault();
                    $(this).attr({
                    	"type":"submit",
                    	"class":"btn btn-primary send-cat"	
                    }).val('전송');
                    $('.cat-table').append(
                    	'<input type="text" maxlength="10" class="form-control cat-input input-sm"	name="Categorytitle" placeholder="Category"/>'
                    );
                    confirmCat();
                }
            });
	        	$('[name=catFrm]').submit(confirmCat);
	        	function confirmCat(){
	        		var title = $("[name=Categorytitle]");
	        		console.log(title);
	        		if(title.val() == null && title.val() == ""){
	        			alert('빈문자열은 카테고리명으로 사용하실수 없습니다.');
	        		}
	        	}
            $('.btn-close').click(function(){
               $('.btn-add').attr('type', 'button').val('카테고리 생성');
               $('.cat-input').remove();
            });
        });	
        
	</script>

<%@ include file='/WEB-INF/views/common/footer.jsp'%>