from rest_framework import serializers
from ..models import Profile
from django.contrib.auth.models import User


class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = ['id', 'username', 'email']

class ProfileSerializer(serializers.ModelSerializer):
    user = UserSerializer()

    class Meta:
        model = Profile
        fields = ['user', 'telephone', 'adresse', 'code_postal']