from rest_framework import routers
from .views import ProfilViewSet
from django.urls import path,include

router = routers.DefaultRouter()
router.register(r'profil',ProfilViewSet)

urlpatterns = [
    path('',include(router.urls))
]
