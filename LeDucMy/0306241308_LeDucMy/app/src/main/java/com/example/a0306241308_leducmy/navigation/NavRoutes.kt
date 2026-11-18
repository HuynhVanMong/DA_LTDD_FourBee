package com.example.a0306241308_leducmy.navigation

sealed class AppRoutes(val route: String) {
    object Dashboard : AppRoutes("admin_dashboard")
    object UserManagement : AppRoutes("user_management")
    object TopicManagement : AppRoutes("topic_management")
    object ExamSetManagement : AppRoutes("exam_set_management")
    object CreateEditExam : AppRoutes("create_edit_exam")
    object QuestionManagement : AppRoutes("question_management")

}