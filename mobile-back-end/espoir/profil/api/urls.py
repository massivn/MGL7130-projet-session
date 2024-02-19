from rest_framework import routers
from .views import ProfilViewSet,LoginView
from django.urls import path,include

router = routers.DefaultRouter()
router.register(r'profil',ProfilViewSet)


urlpatterns = [
    path('',include(router.urls)),
    path('login/', LoginView.as_view(), name='login')
]
