from django.db import models

class Forum(models.Model):
    title = models.CharField(max_length=100)
    description = models.TextField(blank=True)
    created_by = models.ForeignKey('users.Farmer', on_delete=models.CASCADE, related_name='forums')
    created_at = models.DateTimeField(auto_now_add=True)

class Post(models.Model):
    forum = models.ForeignKey(Forum, on_delete=models.CASCADE, related_name='posts')
    author = models.ForeignKey('users.Farmer', on_delete=models.CASCADE, related_name='posts')
    content = models.TextField()
    created_at = models.DateTimeField(auto_now_add=True)

class Comment(models.Model):
    post = models.ForeignKey(Post, on_delete=models.CASCADE, related_name='comments')
    author = models.ForeignKey('users.Farmer', on_delete=models.CASCADE, related_name='comments')
    content = models.TextField()
    created_at = models.DateTimeField(auto_now_add=True)
