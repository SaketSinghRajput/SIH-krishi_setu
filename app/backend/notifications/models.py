from django.db import models
from users.models import Farmer

class Notification(models.Model):
    farmer = models.ForeignKey(Farmer, on_delete=models.CASCADE)
    message = models.TextField()
    created_at = models.DateTimeField(auto_now_add=True)
    read = models.BooleanField(default=False)

    def __str__(self):
        return f"Notification for {self.farmer.username}"
