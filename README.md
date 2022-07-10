# ScheduleReminders
Schedule text messages or emails 

This project is built using Java and Microprofile. This application will run on Open Liberty

Command to RUN : mvn liberty:run

[GET] ALL Remainders : http://localhost:8585
[POST] Remainder : http://localhost:8282 @Body -> {userId:"",title:"",message:"",sendTime:"",phoneNumber:"",emailId:""}
[PUT] Remainder: same as above
[DELETE] Remainder : http://localhost:8585?id=""


Once a reminder is created, we put this entry into cache with TTL, where TTL is diff between current time and send time.
As soon as the entry is expired a callback to the reminder logic will executed.

cache used is Ehcache which is a JCache implementation. And Embedded DB (Derby) is used to store the reminder data(Although not required). Cache can be used
as source of truth.

Also Implemented TimerTask based logic to schedule reminders.

Change the logic in ReminderImpl to use cache or timerTask.

Yet to complete:

Status of reminder will not change even if the reminder is sent.

Testing:
Check which has better accuracy cache's TTL or TimerTask.
