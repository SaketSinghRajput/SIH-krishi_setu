from django.urls import path
from .views import PredictCropView, RecommendFertilizerView, RecordCropSaleView, UserRegistrationView, EmailOTPRequestView, EmailOTPVerifyView, SMSOTPRequestView, SMSOTPVerifyView
from rest_framework_simplejwt.views import (
    TokenObtainPairView,
    TokenRefreshView,
)

urlpatterns = [
    path('predict_crop/', PredictCropView.as_view(), name='predict_crop'),
    path('recommend_fertilizer/', RecommendFertilizerView.as_view(), name='recommend_fertilizer'),
    path('record_crop_sale/', RecordCropSaleView.as_view(), name='record_crop_sale'),
    path('token/', TokenObtainPairView.as_view(), name='token_obtain_pair'),
    path('token/refresh/', TokenRefreshView.as_view(), name='token_refresh'),
    path('register/', UserRegistrationView.as_view(), name='user_registration'),
    path('email_otp/request/', EmailOTPRequestView.as_view(), name='email_otp_request'),
    path('email_otp/verify/', EmailOTPVerifyView.as_view(), name='email_otp_verify'),
    path('sms_otp/request/', SMSOTPRequestView.as_view(), name='sms_otp_request'),
    path('sms_otp/verify/', SMSOTPVerifyView.as_view(), name='sms_otp_verify'),
]
