<!DOCTYPE html>
<html lang="en">

<head>
    <title>Quantpro</title>
    <link rel="shortcut icon" href="assets/img/favicon.png" type="image/x-icon">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="quaintpro/admin/assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="quaintpro/admin/assets/css/style.css" rel="stylesheet">
</head>

<body>

    <header>
        <div class="top_header">
            <div class="container">
                <div class="top_header_box">
                    <div class="logo">
                        <a href="javascript:void(0);">
                            <img src="quaintpro/admin/assets/img/admin_logo.png">
                        </a>
                    </div>
                    <div class="menu_bar navbar-dark">
                        <button class="navbar-toggler my-2" type="button" data-bs-toggle="collapse"
                            data-bs-target="#main_nav" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                    </div>
                    <div class="profile_box">
                        <div class="setting_notification">
                            <div class="notification">
                                <a href="javascript:void(0)"><i class="fa-solid fa-bell"></i></a>
                            </div>
                            <div class="notification">
                                <a href="javascript:void(0)"><i class="fa-solid fa-gear"></i></a>
                            </div>
                            <div class="notification">
                                <div class="profile_dropdown">
                                    <a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown">
                                        <div class="media">
                                            <img src="quaintpro/admin/assets/img/profile.png" alt="profile" class="rounded">
                                            <div class="media-body">
                                                <h4>Anubhav Shah</h4>
                                                <p>Mumbai, India</p>
                                            </div>
                                        </div>
                                    </a>
                                    <div class="dropdown-menu">
                                        <a class="dropdown-item" href="login.html">Logout</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="buttom_footer">
            <div class="container">
                <nav class="navbar navbar-expand-lg navbar-dark p-0">

                    <div class="collapse navbar-collapse justify-content-center lh-lg" id="main_nav">
                        <ul class="navbar-nav p-3 p-md-0">
                            <li class="nav-item"> <a class="nav-link" href="index.html">
                                    <img src="quaintpro/admin/assets/img/dashboard_active.png" class="active">
                                    <img src="quaintpro/admin/assets/img/dashboard.png" class="disactive">
                                    Dashboard</a> </li>
                            <li class="nav-item"> <a class="nav-link " href="question-bank.html">
                                    <img src="quaintpro/admin/assets/img/question_active.png" class="active">
                                    <img src="quaintpro/admin/assets/img/question.png" class="disactive"> Question Bank</a> </li>
                            <li class="nav-item"> <a class="nav-link active" href="tests.html">
                                    <img src="quaintpro/admin/assets/img/test_active.png" class="active">
                                    <img src="quaintpro/admin/assets/img/test.png" class="disactive">Tests</a> </li>
                            <li class="nav-item dropdown ktm-mega-menu">
                                <a class="nav-link" href="#" data-bs-toggle="dropdown">
                                    <img src="quaintpro/admin/assets/img/more.png" class="disactive"> More</a>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
        </div>
    </header>




    <section class="qb_section create_question_sec result_sec">
        <div class="container">
            <div class="row">
                <div class="col-md-12 mb-4">
                    <div class="question_bank_heading">
                        <div class="result_heading">
                            <h3>Hey Sameer,</h3>
                            <p>You completed the evaluation in 1 attempt </p>
                        </div>
                        <div class="question_btn">
                            <button class="custom_btn"><img src="quaintpro/admin/assets/img/Download_Report.svg"> Download
                                Report</button>
                        </div>
                    </div>
                </div>
            </div>

            <div class="result_box_Sec">
                <div class="box_heading">
                    <h4>Configuration layout and loft-configuration layout-Conic lofting</h4>
                    <div class="result_data_Sec">
                        <div class="data">
                            <div class="data_list">
                                <div class="data_icon">
                                    <img src="quaintpro/admin/assets/img/question_list.svg">
                                </div>
                                <div class="data_text">
                                    <p>Total no. of Question</p>
                                    <h3>20 Question</h3>
                                </div>
                            </div>
                            <div class="data_list">
                                <div class="data_icon">
                                    <img src="quaintpro/admin/assets/img/time.svg">
                                </div>
                                <div class="data_text">
                                    <p>Time Spend</p>
                                    <h3>00:02:45</h3>
                                </div>
                            </div>
                            <div class="data_list">
                                <div class="data_icon">
                                    <img src="quaintpro/admin/assets/img/star.svg">
                                </div>
                                <div class="data_text">
                                    <p>Your Score</p>
                                    <h3>35%</h3>
                                </div>
                            </div>
                        </div>
                        <div class="try_btn">
                            <button class="try">Try Again</button>
                        </div>
                    </div>
                    <div class="graph_box">
                        <div id="Result"></div>
                        <div class="ques_score">
                            <h3>20 <br>Questions</h3>
                        </div>
                    </div>
                    <div class="note">
                        <p>Note: Your results have been recorded. In a short time from now, we will share your User
                            credentials, if not shared earlier, that will allow you login and see your past scores and
                            more. </p>
                    </div>
                </div>
            </div>

            <div class="answer_box_sec">
                <div class="answer_heading">
                    <h3>Answer Summary</h3>
                    <p>Section-Configuration layout and loft</p>
                    <div class="question_box mb-4">
                        <div class="question_flex">
                            <div class="question_heading">
                                <h3>Question 1.</h3>
                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eget volutpat
                                    dolor. Fusce faucibus aliquet odio pulvinar sodales.</p>
                            </div>
                            <div class="question_btn">
                                <button class="incorrect">Incorrect</button>
                            </div>
                        </div>
                        <div class="your_ans_box">
                            <div class="answer_option">
                                <p>Your Answer</p>
                                <h4>Option 1</h4>
                            </div>
                            <div class="answer_option correct_ans">
                                <p>Correct Answer</p>
                                <h4>Option 4</h4>
                            </div>
                        </div>
                        <div class="answer_justification">
                            <h3>Answer Justification</h3>
                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eget volutpat dolor. Fusce faucibus aliquet odio pulvinar sodales. Nunc placerat convallis lectus et egestas. Nulla lacus eros, tincidunt eget lacinia vel, ultricies sit amet leo.</p>
                        </div>
                    </div>
                    <div class="question_box mb-4">
                        <div class="question_flex">
                            <div class="question_heading">
                                <h3>Question 2.</h3>
                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eget volutpat
                                    dolor. Fusce faucibus aliquet odio pulvinar sodales.</p>
                            </div>
                            <div class="question_btn">
                                <button class="incorrect">Incorrect</button>
                            </div>
                        </div>
                        <div class="your_ans_box">
                            <div class="answer_option">
                                <p>Your Answer</p>
                                <h4>Option 1</h4>
                            </div>
                            <div class="answer_option correct_ans">
                                <p>Correct Answer</p>
                                <h4>Option 4</h4>
                            </div>
                        </div>
                        <div class="answer_justification">
                            <h3>Answer Justification</h3>
                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eget volutpat dolor. Fusce faucibus aliquet odio pulvinar sodales. Nunc placerat convallis lectus et egestas. Nulla lacus eros, tincidunt eget lacinia vel, ultricies sit amet leo.</p>
                        </div>
                    </div>
                    <div class="question_box mb-4">
                        <div class="question_flex">
                            <div class="question_heading">
                                <h3>Question 3.</h3>
                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eget volutpat
                                    dolor. Fusce faucibus aliquet odio pulvinar sodales.</p>
                            </div>
                            <div class="question_btn">
                                <button class="incorrect correct">Correct</button>
                            </div>
                        </div>
                        <div class="your_ans_box">
                            <div class="answer_option correct_ans">
                                <p>Your Answer</p>
                                <h4>Option 1</h4>
                            </div>
                            <div class="answer_option correct_ans">
                                <p>Correct Answer</p>
                                <h4>Option 4</h4>
                            </div>
                        </div>
                        <div class="answer_justification">
                            <h3>Answer Justification</h3>
                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eget volutpat dolor. Fusce faucibus aliquet odio pulvinar sodales. Nunc placerat convallis lectus et egestas. Nulla lacus eros, tincidunt eget lacinia vel, ultricies sit amet leo.</p>
                        </div>
                    </div>
                    <div class="question_box mb-4">
                        <div class="question_flex">
                            <div class="question_heading">
                                <h3>Question 4.</h3>
                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eget volutpat
                                    dolor. Fusce faucibus aliquet odio pulvinar sodales.</p>
                            </div>
                            <div class="question_btn">
                                <button class="incorrect">Incorrect</button>
                            </div>
                        </div>
                        <div class="your_ans_box">
                            <div class="answer_option">
                                <p>Your Answer</p>
                                <h4>Option 1</h4>
                            </div>
                            <div class="answer_option correct_ans">
                                <p>Correct Answer</p>
                                <h4>Option 4</h4>
                            </div>
                        </div>
                        <div class="answer_justification">
                            <h3>Answer Justification</h3>
                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eget volutpat dolor. Fusce faucibus aliquet odio pulvinar sodales. Nunc placerat convallis lectus et egestas. Nulla lacus eros, tincidunt eget lacinia vel, ultricies sit amet leo.</p>
                        </div>
                    </div>
                    <div class="question_box mb-4">
                        <div class="question_flex">
                            <div class="question_heading">
                                <h3>Question 5.</h3>
                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eget volutpat
                                    dolor. Fusce faucibus aliquet odio pulvinar sodales.</p>
                            </div>
                            <div class="question_btn">
                                <button class="incorrect correct">Correct</button>
                            </div>
                        </div>
                        <div class="your_ans_box">
                            <div class="answer_option correct_ans">
                                <p>Your Answer</p>
                                <h4>Option 1</h4>
                            </div>
                            <div class="answer_option correct_ans">
                                <p>Correct Answer</p>
                                <h4>Option 4</h4>
                            </div>
                        </div>
                        <div class="answer_justification">
                            <h3>Answer Justification</h3>
                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eget volutpat dolor. Fusce faucibus aliquet odio pulvinar sodales. Nunc placerat convallis lectus et egestas. Nulla lacus eros, tincidunt eget lacinia vel, ultricies sit amet leo.</p>
                        </div>
                    </div>
                    
                </div>
            </div>


        </div>
    </section>





    <script src="quaintpro/admin/assets/js/jquery.slim.min.js"></script>
    <script src="quaintpro/admin/assets/js/bootstrap.bundle.min.js"></script>
    <script src="quaintpro/admin/assets/js/jquery-slider-min.js"></script>
    <script src="quaintpro/admin/assets/js/apexcharts.js"></script>
    <script src="quaintpro/admin/assets/js/fontawesome.min.js"></script>
    <script src="quaintpro/admin/assets/js/custom.js"></script>
</body>

</html>