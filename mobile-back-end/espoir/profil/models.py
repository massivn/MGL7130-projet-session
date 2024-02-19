from django.db import models
from django.contrib.auth.models import User
# Create your models here.
class Profile(models.Model):
    user = models.OneToOneField(User, on_delete=models.CASCADE,default=None)
    telephone = models.CharField(max_length=15)
    adresse = models.CharField(max_length=255)
    code_postal = models.CharField(max_length=10)

    def __str__(self):
        return self.user.username