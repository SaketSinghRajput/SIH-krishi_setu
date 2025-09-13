from django.urls import path
from .views import CropAdviceView, FertilizerAdviceView, PesticideAdviceView

urlpatterns = [
    path('api/ai/crop-advice/', CropAdviceView.as_view(), name='crop_advice'),
    path('api/ai/fertilizer-advice/', FertilizerAdviceView.as_view(), name='fertilizer_advice'),
    path('api/ai/pesticide-advice/', PesticideAdviceView.as_view(), name='pesticide_advice'),
]
