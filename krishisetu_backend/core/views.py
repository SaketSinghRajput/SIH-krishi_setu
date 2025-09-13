from .sms_otp import send_sms_otp, verify_sms_otp
class SMSOTPRequestView(APIView):
	permission_classes = []
	def post(self, request):
		phone = request.data.get('phone')
		otp = send_sms_otp(phone)
		return Response({'message': 'OTP sent to phone.'}, status=status.HTTP_200_OK)

class SMSOTPVerifyView(APIView):
	permission_classes = []
	def post(self, request):
		phone = request.data.get('phone')
		otp = request.data.get('otp')
		if verify_sms_otp(phone, otp):
			return Response({'message': 'OTP verified.'}, status=status.HTTP_200_OK)
		return Response({'error': 'Invalid OTP.'}, status=status.HTTP_400_BAD_REQUEST)
from django_otp.plugins.otp_email.models import EmailDevice
class EmailOTPRequestView(APIView):
	permission_classes = []
	def post(self, request):
		email = request.data.get('email')
		user = User.objects.filter(email=email).first()
		if not user:
			return Response({'error': 'User not found.'}, status=status.HTTP_404_NOT_FOUND)
		device, created = EmailDevice.objects.get_or_create(user=user, name='default')
		device.generate_challenge()
		return Response({'message': 'OTP sent to email.'}, status=status.HTTP_200_OK)

class EmailOTPVerifyView(APIView):
	permission_classes = []
	def post(self, request):
		email = request.data.get('email')
		otp = request.data.get('otp')
		user = User.objects.filter(email=email).first()
		if not user:
			return Response({'error': 'User not found.'}, status=status.HTTP_404_NOT_FOUND)
		device = EmailDevice.objects.filter(user=user, name='default').first()
		if device and device.verify_token(otp):
			return Response({'message': 'OTP verified.'}, status=status.HTTP_200_OK)
		return Response({'error': 'Invalid OTP.'}, status=status.HTTP_400_BAD_REQUEST)
from django.contrib.auth.models import User
from rest_framework import serializers
class UserRegistrationSerializer(serializers.ModelSerializer):
	password = serializers.CharField(write_only=True)

	class Meta:
		model = User
		fields = ('username', 'password', 'email')

	def create(self, validated_data):
		user = User.objects.create_user(
			username=validated_data['username'],
			password=validated_data['password'],
			email=validated_data.get('email', '')
		)
		return user

class UserRegistrationView(APIView):
	permission_classes = []
	def post(self, request):
		serializer = UserRegistrationSerializer(data=request.data)
		if serializer.is_valid():
			serializer.save()
			return Response({'message': 'User registered successfully.'}, status=status.HTTP_201_CREATED)
		return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
from .blockchain import record_crop_sale
class RecordCropSaleView(APIView):
	def post(self, request):
		seller_wallet = request.data.get('seller_wallet')
		buyer_wallet = request.data.get('buyer_wallet')
		crop_details = request.data.get('crop_details')
		tx_hash = record_crop_sale(seller_wallet, buyer_wallet, crop_details)
		return Response({'tx_hash': tx_hash}, status=status.HTTP_200_OK)
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import status
import requests

FASTAPI_URL = "http://127.0.0.1:8000"

class PredictCropView(APIView):
	def post(self, request):
		soil = request.data.get('soil')
		climate = request.data.get('climate')
		payload = {"soil": soil, "climate": climate}
		try:
			response = requests.post(f"{FASTAPI_URL}/predict_crop", json=payload)
			return Response(response.json(), status=response.status_code)
		except Exception as e:
			return Response({"error": str(e)}, status=status.HTTP_500_INTERNAL_SERVER_ERROR)

class RecommendFertilizerView(APIView):
	def post(self, request):
		soil = request.data.get('soil')
		climate = request.data.get('climate')
		crop = request.data.get('crop')
		payload = {"soil": soil, "climate": climate, "crop": crop}
		try:
			response = requests.post(f"{FASTAPI_URL}/recommend_fertilizer", json=payload)
			return Response(response.json(), status=response.status_code)
		except Exception as e:
			return Response({"error": str(e)}, status=status.HTTP_500_INTERNAL_SERVER_ERROR)
