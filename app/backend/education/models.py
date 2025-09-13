from django.db import models

class Course(models.Model):
    title = models.CharField(max_length=100)
    description = models.TextField(blank=True)
    instructor = models.CharField(max_length=100)
    start_date = models.DateField()
    end_date = models.DateField()

class Enrollment(models.Model):
    farmer = models.ForeignKey('users.Farmer', on_delete=models.CASCADE, related_name='enrollments')
    course = models.ForeignKey(Course, on_delete=models.CASCADE, related_name='enrollments')
    enrolled_at = models.DateTimeField(auto_now_add=True)

class Lesson(models.Model):
    course = models.ForeignKey(Course, on_delete=models.CASCADE, related_name='lessons')
    title = models.CharField(max_length=100)
    content = models.TextField()
    order = models.PositiveIntegerField()
