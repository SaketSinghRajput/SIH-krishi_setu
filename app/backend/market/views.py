from django.shortcuts import render
from rest_framework import generics, permissions
from .models import Product, Order, Transaction
from .serializers import ProductSerializer, OrderSerializer, TransactionSerializer

# Create your views here.
class ProductListCreateView(generics.ListCreateAPIView):
	queryset = Product.objects.all()
	serializer_class = ProductSerializer
	permission_classes = [permissions.IsAuthenticated]

class ProductDetailView(generics.RetrieveUpdateDestroyAPIView):
	queryset = Product.objects.all()
	serializer_class = ProductSerializer
	permission_classes = [permissions.IsAuthenticated]

class OrderListCreateView(generics.ListCreateAPIView):
	queryset = Order.objects.all()
	serializer_class = OrderSerializer
	permission_classes = [permissions.IsAuthenticated]

class OrderDetailView(generics.RetrieveUpdateDestroyAPIView):
	queryset = Order.objects.all()
	serializer_class = OrderSerializer
	permission_classes = [permissions.IsAuthenticated]

class TransactionListCreateView(generics.ListCreateAPIView):
	queryset = Transaction.objects.all()
	serializer_class = TransactionSerializer
	permission_classes = [permissions.IsAuthenticated]

class TransactionDetailView(generics.RetrieveUpdateDestroyAPIView):
	queryset = Transaction.objects.all()
	serializer_class = TransactionSerializer
	permission_classes = [permissions.IsAuthenticated]
