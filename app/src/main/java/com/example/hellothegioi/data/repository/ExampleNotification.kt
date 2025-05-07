package com.example.hellothegioi.data.repository

import com.example.hellothegioi.data.model.Notification
import com.example.hellothegioi.data.model.NotificationTarget
import com.example.hellothegioi.data.model.NotificationTarget.ComposePage


class ExampleNotification {
    var lispots = ExamplePost.getAll()

    var notificationsList = ArrayList<Notification>().apply {
        add(Notification("Bài viết của bạn vừa nhận được 1 bình luận.", "10:00 AM", NotificationTarget.Post(lispots[1])))
        add(Notification("Bài viết bạn theo dõi vừa nhận được một bình luận.", "11:00 AM", NotificationTarget.Post(lispots[2])))
        add(Notification("Bài viết bạn theo dõi vừa nhận được 2 bình luận.", "12:00 PM", NotificationTarget.Post(lispots[2])))
        add(Notification("Bạn có muốn thực hiện câu hỏi hằng ngày không", "12:00 PM", ComposePage("question")))
    }
}
