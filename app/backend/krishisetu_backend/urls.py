"""
URL configuration for krishisetu_backend project.

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/5.2/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.urls import path, include
from rest_framework_simplejwt.views import (
    TokenObtainPairView,
    TokenRefreshView,
)

urlpatterns = [
    path('admin/', admin.site.urls),
    path('api/auth/', TokenObtainPairView.as_view(), name='token_obtain_pair'),
    path('api/auth/refresh/', TokenRefreshView.as_view(), name='token_refresh'),
    path('api/users/', include('users.urls')),
    path('api/dashboard/', include('dashboard.urls')),
    path('api/advisor/', include('advisor.urls')),
    path('api/shop/', include('shop.urls')),
    path('api/orders/', include('orders.urls')),
    path('api/soilalerts/', include('soilalerts.urls')),
    path('api/wallet/', include('wallet.urls')),
    path('api/notifications/', include('notifications.urls')),
    path('api/logistics/', include('logistics.urls')),
    path('api/telemedicine/', include('telemedicine.urls')),
    path('api/finance/', include('finance.urls')),
    path('api/education/', include('education.urls')),
    path('api/forum/', include('forum.urls')),
    path('api/analytics/', include('analytics.urls')),
    path('api/communication/', include('communication.urls')),
    path('api/market/', include('market.urls')),
]
