from django.contrib.auth.models import AbstractUser
from django.db import models

class User(AbstractUser):
    ROLE_CHOICES = (
        ('govt', 'Government'),
        ('buyer', 'Buyer'),
        ('admin', 'Admin'),
    )
    role = models.CharField(max_length=10, choices=ROLE_CHOICES, default='buyer')
    # Add more fields as needed

    def __str__(self):
        return f"{self.username} ({self.role})"
