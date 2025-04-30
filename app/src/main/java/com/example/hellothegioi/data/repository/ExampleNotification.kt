package com.example.hellothegioi.data.repository

import com.example.hellothegioi.data.model.Notification
import com.example.hellothegioi.data.model.NotificationTarget
import com.example.hellothegioi.data.model.NotificationTarget.ComposePage
import java.util.Arrays


class ExampleNotification {
    var lispots = ExamplePost.getAll()

    var notifications: List<Notification> = Arrays.asList(
        Notification("Bạn có một bài tập mới.", "10:00 AM", NotificationTarget.Post(lispots[1])),
        Notification("Lịch học ngày mai đã được cập nhật.", "11:00 AM", NotificationTarget.Post(lispots[2])),
        Notification(
            "Giảng viên đã chấm điểm bài kiểm tra.",
            "12:00 PM",
            NotificationTarget.Post(lispots[2])
        ),
        Notification("Giảng viên đã chấm điểm bài kiểm tra.", "12:00 PM", ComposePage("question"))
    )
}