
from django.db import models
from users.models import Farmer

class Advice(models.Model):
	farmer = models.ForeignKey(Farmer, on_delete=models.CASCADE, related_name='advices')
	crop = models.CharField(max_length=50)
	advice_text = models.TextField()
	created_at = models.DateTimeField(auto_now_add=True)

	def __str__(self):
		return f"Advice for {self.crop} ({self.farmer.username})"
