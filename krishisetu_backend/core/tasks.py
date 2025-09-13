from celery import shared_task

@shared_task
def send_notification(message):
    # Dummy notification task
    print(f"Notification sent: {message}")
    return f"Notification sent: {message}"
