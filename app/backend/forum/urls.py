from django.urls import path
from .views import ForumListCreateView, ForumDetailView, PostListCreateView, PostDetailView, CommentListCreateView, CommentDetailView

urlpatterns = [
    path('forums/', ForumListCreateView.as_view(), name='forum-list-create'),
    path('forums/<int:pk>/', ForumDetailView.as_view(), name='forum-detail'),
    path('posts/', PostListCreateView.as_view(), name='post-list-create'),
    path('posts/<int:pk>/', PostDetailView.as_view(), name='post-detail'),
    path('comments/', CommentListCreateView.as_view(), name='comment-list-create'),
    path('comments/<int:pk>/', CommentDetailView.as_view(), name='comment-detail'),
]
