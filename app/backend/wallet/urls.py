from django.urls import path
from .views import WalletRetrieveUpdateView

urlpatterns = [
    path('<int:pk>/', WalletRetrieveUpdateView.as_view(), name='wallet-detail'),
]
