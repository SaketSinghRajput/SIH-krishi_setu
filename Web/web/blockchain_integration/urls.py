from django.urls import path
from .views import LogTransactionView, VerifyTransactionView

urlpatterns = [
    path('log/', LogTransactionView.as_view(), name='log_transaction'),
    path('verify/<str:tx_id>/', VerifyTransactionView.as_view(), name='verify_transaction'),
]
