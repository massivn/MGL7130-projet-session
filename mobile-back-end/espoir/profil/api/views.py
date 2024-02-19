from rest_framework import viewsets
from ..models import Profile
from .serializers import ProfileSerializer

class ProfilViewSet(viewsets.ModelViewSet):
    queryset = Profile.objects.all()
    serializer_class = ProfileSerializer