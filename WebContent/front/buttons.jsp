<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="header.jsp" %>
    
                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <h1 class="h3 mb-4 text-gray-800">Buttons 버튼 관련</h1>

                    <div class="row">

                        <div class="col-lg-6">

                            <!-- Circle Buttons -->
                            <div class="card shadow mb-4">
                                <div class="card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary">Circle Buttons</h6>
                                </div>
                                <div class="card-body">
                                    <!-- Circle Buttons (기본값) -->
                                    
                                    <div class="mb-2">
                                        <code>.btn-circle</code> 기본 사이즈 원형 버튼
                                    </div>
                                    <a href="#" class="btn btn-primary btn-circle">
                                        <i class="fab fa-facebook-f"></i>
                                    </a>
                                    <a href="#" class="btn btn-success btn-circle">
                                        <i class="fas fa-check"></i>
                                    </a>
                                    <a href="#" class="btn btn-info btn-circle">
                                        <i class="fas fa-info-circle"></i>
                                    </a>
                                    <a href="#" class="btn btn-warning btn-circle">
                                        <i class="fas fa-exclamation-triangle"></i>
                                    </a>
                                    <a href="#" class="btn btn-danger btn-circle">
                                        <i class="fas fa-trash"></i>
                                    </a>
                                    <!-- Circle Buttons (Small) -->
                                    <div class="mt-4 mb-2">
                                        <code>.btn-circle .btn-sm</code> 작은 사이즈 원형 버튼
                                    </div>
                                    <a href="#" class="btn btn-primary btn-circle btn-sm">
                                        <i class="fab fa-facebook-f"></i>
                                    </a>
                                    <a href="#" class="btn btn-success btn-circle btn-sm">
                                        <i class="fas fa-check"></i>
                                    </a>
                                    <a href="#" class="btn btn-info btn-circle btn-sm">
                                        <i class="fas fa-info-circle"></i>
                                    </a>
                                    <a href="#" class="btn btn-warning btn-circle btn-sm">
                                        <i class="fas fa-exclamation-triangle"></i>
                                    </a>
                                    <a href="#" class="btn btn-danger btn-circle btn-sm">
                                        <i class="fas fa-trash"></i>
                                    </a>
                                    <!-- Circle Buttons (Large) -->
                                    <div class="mt-4 mb-2">
                                        <code>.btn-circle .btn-lg</code> 큰 사이즈 원형 버튼
                                    </div>
                                    <a href="#" class="btn btn-primary btn-circle btn-lg">
                                        <i class="fab fa-facebook-f"></i>
                                    </a>
                                    <a href="#" class="btn btn-success btn-circle btn-lg">
                                        <i class="fas fa-check"></i>
                                    </a>
                                    <a href="#" class="btn btn-info btn-circle btn-lg">
                                        <i class="fas fa-info-circle"></i>
                                    </a>
                                    <a href="#" class="btn btn-warning btn-circle btn-lg">
                                        <i class="fas fa-exclamation-triangle"></i>
                                    </a>
                                    <a href="#" class="btn btn-danger btn-circle btn-lg">
                                        <i class="fas fa-trash"></i>
                                    </a>
                                </div>
                            </div>

                            <!-- Brand Buttons -->
                            <div class="card shadow mb-4">
                                <div class="card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary">Brand Buttons</h6>
                                </div>
                                <div class="card-body">
                                    <p>Google 및 Facebook 버튼은 각 회사의 각 브랜드 색상을 특징으로합니다. 사용자 로그인 및 등록 페이지에서 사용됩니다.</p>
                                    <p><code>_variables.scss</code> 파일에 새 색상 변수를 추가 한 다음 부트 스트랩 버튼 변형 믹스 인을 사용하여<code>_buttons.scss</code>
                                       	 파일에 설명 된대로 새 스타일을 생성하여 더 많은 사용자 정의 버튼을 만들 수 있습니다 .</p>
                                    <a href="#" class="btn btn-google btn-block"><i class="fab fa-google fa-fw"></i>
                                        .btn-google</a>
                                    <a href="#" class="btn btn-facebook btn-block"><i
                                            class="fab fa-facebook-f fa-fw"></i> .btn-facebook</a>

                                </div>
                            </div>

                        </div>

                        <div class="col-lg-6">

                            <div class="card shadow mb-4">
                                <div class="card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary">Split Buttons with Icon</h6>
                                </div>
                                <div class="card-body">
                                    <p>색상은 btn btn-색상이름으로 작동하며 뒤에 <code>.btn-icon-split</code>이 붙으면
                                  		  네모난 직사각형 버튼이 되는 방식입니다
                                        <code>.text-white-50</code> 왼쪽 코드는 기본 text 셋팅값의 클래스명</p>
                                    <a href="#" class="btn btn-primary btn-icon-split">
                                        <span class="icon text-white-50">
                                            <i class="fas fa-flag"></i>
                                        </span>
                                        <span class="text">Split Button Primary</span>
                                    </a>
                                    <div class="my-2"></div>
                                    <a href="#" class="btn btn-success btn-icon-split">
                                        <span class="icon text-white-50">
                                            <i class="fas fa-check"></i>
                                        </span>
                                        <span class="text">Split Button Success</span>
                                    </a>
                                    <div class="my-2"></div>
                                    <a href="#" class="btn btn-info btn-icon-split">
                                        <span class="icon text-white-50">
                                            <i class="fas fa-info-circle"></i>
                                        </span>
                                        <span class="text">Split Button Info</span>
                                    </a>
                                    <div class="my-2"></div>
                                    <a href="#" class="btn btn-warning btn-icon-split">
                                        <span class="icon text-white-50">
                                            <i class="fas fa-exclamation-triangle"></i>
                                        </span>
                                        <span class="text">Split Button Warning</span>
                                    </a>
                                    <div class="my-2"></div>
                                    <a href="#" class="btn btn-danger btn-icon-split">
                                        <span class="icon text-white-50">
                                            <i class="fas fa-trash"></i>
                                        </span>
                                        <span class="text">Split Button Danger</span>
                                    </a>
                                    <div class="my-2"></div>
                                    <a href="#" class="btn btn-secondary btn-icon-split">
                                        <span class="icon text-white-50">
                                            <i class="fas fa-arrow-right"></i>
                                        </span>
                                        <span class="text">Split Button Secondary</span>
                                    </a>
                                    <div class="my-2"></div>
                                    <a href="#" class="btn btn-light btn-icon-split">
                                        <span class="icon text-gray-600">
                                            <i class="fas fa-arrow-right"></i>
                                        </span>
                                        <span class="text">Split Button Light</span>
                                    </a>
                                    <div class="mb-4"></div>
                                    <p>Also works with small and large button classes!</p>
                                    <a href="#" class="btn btn-primary btn-icon-split btn-sm">
                                        <span class="icon text-white-50">
                                            <i class="fas fa-flag"></i>
                                        </span>
                                        <span class="text">Split Button Small</span>
                                    </a>
                                    <div class="my-2"></div>
                                    <a href="#" class="btn btn-primary btn-icon-split btn-lg">
                                        <span class="icon text-white-50">
                                            <i class="fas fa-flag"></i>
                                        </span>
                                        <span class="text">Split Button Large</span>
                                    </a>
                                </div>
                            </div>

                        </div>

                    </div>

                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

<%@ include file="footer.jsp" %>

</body>

</html>