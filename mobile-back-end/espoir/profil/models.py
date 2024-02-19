from django.db import models

# Create your models here.
class Profile(models.Model):
    username = models.CharField(max_length=50,unique=True)
    email = models.EmailField(unique=True)
    password = models.CharField(max_length=50)
    telephone = models.CharField(max_length=15)
    adresse = models.CharField(max_length=255)
    code_postal = models.CharField(max_length=10)

    def __str__(self):
        return str(self.username)